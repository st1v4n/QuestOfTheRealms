package model;

import backgroundActions.Day;
import backgroundActions.DayUpdater;
import backgroundActions.spawners.EnemySpawner;
import backgroundActions.spawners.ItemSpawner;
import backgroundActions.quests.QuestPool;
import backgroundActions.statsIncreasers.HealthStatIncreaser;
import backgroundActions.statsIncreasers.ManaStatIncreaser;
import model.actionResults.ActionResult;
import model.actionResults.Status;
import model.gameObjects.GameObject;
import model.items.Item;
import model.enemy.Enemy;
import model.mapGenerators.BaseMapGenerator;
import model.mapGenerators.MapGenerator;
import model.notifiers.Notifier;
import model.playerClasses.Player;
import view.GameView;

import java.util.InputMismatchException;
import java.util.List;

public class GameModel {

    /*
    GameModel е клас, който отговаря за управлението на state-a на играта и осъществяване на основната игрова логика
    в него се намира GameMap, който всъщност е самия state на играта - местоположенията на чудовища, предмети, играчи и т.н
    Причината, поради която GameMap е вътре в GameModel, а не GameModel да е самия GameMap, е че всъщност според мен е работа
    на модела да изпълнява функционалности като spawner-и на чудовища/предмети, да нотифицира view-то за постигнати резултати
    да управлява времето и механиките в играта, докато GameMap е просто състоянието на играта, което се променя от всички неща,
    които са в модела. Ако премахнем GameModel и оставим само GameMap и набутаме всички тези QuestPool, Notifier, ItemSpawner...
    неща в него, излиза че картата на играта си се управлява сама и сама си изпълнява функционалности върху себе си (което според
    мен не е правилно).
    Това е причината GameMap да изглежда като фасада.
    Всъщност идеята на GameModel е да съдържа map, както и обекти, които да упражняват действията си върху този map.
    Map-a само ,,страда'' от действията на model-a, като в него са дефинирани ефектите от действията на model-a (които са кръстени
    по същия начин като в model-a, за да може да се увеличи readability-то).
    Реално това, което става, е че контролера изпълнява комнади. Ефектът от тези команди отива към model-a (дали е атака, преместване и т.н)
    Model-a реагира и казва на картата ,,заповядаха ми да направя с теб <това>''.
    Картата си поема действието и реагира и съответно връща резултата от изпълнението на това действие.
    Този резултат се връща на модела, който от своя страна го връща на контролера и резултата се представя пред потребителя от view-то (което е идеята на MVC)

    Въпроси и отговори:

    Въпрос: Можеше ли да го няма този GameModel и направо да има GameMap?
    Отговор: Да, но тогава всички background събития и известявания отиват в map-a (state-а на играта) => той сам си прави всичко върху себе си, т.е
    няма модул от по-високо ниво, който да го управлява

    Въпрос: Защо има функция String getAvailableQuestInfo()?
    Отговор: Функцията е предназначена да даде на view-то информация за съществуващите quest-и в играта и то да ги представи пред потребителя
    Тъй като във всяка игра подобни функционалности представят нещата като текст, затова избрах String като тип на връщане (независимо дали е конзолно,
    с графичен интерфейс или ако искате холограмно изображение, то все пак ще може да представи пред потребителя текст)

    Въпрос: Добре, тогава защо има и функция getCompletedQuests()? Тя не прави ли същото?
    Отговор: Не, тя връща резултат от опита за достъпване на завършените quest-ове на потребителя. За разлика от горната функция, която винаги успява,
    тази ако бъде разширена играта (т.е ако реша да го направя ако Player-a няма завършени quest-ове да връща Status.Error) може и да не успее, затова резултата
    от нея е wrap-нат в обект. И разбира се поведението на двете функции е различно.

    Въпрос: Защо има (имаше, защото я махнах, понеже не я използвах така или иначе) функция useItemAt(), която вика map.useItemAt(), която вика още неща надолу?
    Отговор: Model-a приема команда от контролера. Неговата версия на функцията всъщност е длъжна да изпълна заповедта на контролера
    Версията на функция в map-a е длъжна да каже как ще риагира нашето състояние при опит за достъпване на предмет на картата.
    Отделно, player-a е длъжен да каже какво ще направи, като вземе предмета.
    Така 3 функции, с еднакво име, правят 3 различни неща. Затова ги има и трите.

     */

    private GameMap map;
    private transient BaseMapGenerator mapGenerator;
    private transient QuestPool questPool;
    private transient Notifier notifier;
    private transient Day daytimer;
    private transient ItemSpawner itemSpawner;
    private transient EnemySpawner enemySpawner;
    private transient HealthStatIncreaser healthStatIncreaser;
    private transient ManaStatIncreaser manaStatIncreaser;

    public void init(GameView view){
        notifier = new Notifier();
        notifier.addObservable(view);
        questPool = new QuestPool(getPlayer(), notifier);
        daytimer = new Day();
        DayUpdater dayUpdater = new DayUpdater(daytimer, notifier, map.getPlayer());
        dayUpdater.start();
        itemSpawner = new ItemSpawner(map, notifier);
        itemSpawner.start();
        enemySpawner = new EnemySpawner(map, notifier);
        enemySpawner.start();
        healthStatIncreaser = new HealthStatIncreaser(map.getPlayer(), notifier);
        healthStatIncreaser.start();
        manaStatIncreaser = new ManaStatIncreaser(map.getPlayer(), notifier);
        manaStatIncreaser.start();
    }

    public GameModel(GameView view, String mapFileName){
        mapGenerator = new MapGenerator();
        try {
            map = mapGenerator.generateMapFromFile(mapFileName);
        }
        catch(InputMismatchException inputMismatchException){
            notifier.notify(new ActionResult(Status.ERROR, inputMismatchException.getMessage()));
            System.exit(-1);
        }
        this.init(view);
    }

    public List<List<GameObject>> getMap(){
        return map.getMap();
    }

    public int getMapColumns(){
        return map.getXBorder();
    }

    public int getMapRows(){
        return map.getYBorder();
    }

    public ActionResult movePlayer(int rowAddition, int columnAddition){
        return map.movePlayer(rowAddition, columnAddition);
    }

    public int getPlayerHealth(){
        return map.getPlayerHealth();
    }

    public int getPlayerMana(){
        return map.getPlayerMana();
    }

    public int getPlayerAttack(){
        return map.getPlayerAttack();
    }

    public int getPlayerDefense(){
        return map.getPlayerDefense();
    }

    public String getPlayerInventoryContent(){
        return map.getPlayerInventoryContent();
    }

    public Player getPlayer(){
        return map.getPlayer();
    }

    public ActionResult attackAt(int rowAddition, int colAddition){
        return map.attackAt(rowAddition, colAddition);
    }

    public ActionResult useItemAt(int index){
        return map.useItemAt(index);
    }

    public ActionResult startQuest(int index){
        if(map.getPlayer().hasCompletedQuest(questPool.getQuestAtIndex(index))){
            return new ActionResult(Status.ERROR, "Quest already completed!");
        }
        return questPool.startQuest(index);
    }

    public String getAvailableQuestsInfo(){
        return questPool.getAvailableQuestsInfo();
    }

    public ActionResult getCompletedQuests(){
        return map.getCompletedQuests();
    }

}
