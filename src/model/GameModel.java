package model;

import backgroundActions.quests.Quest;
import backgroundActions.quests.QuestPool;
import model.enemy.Boss;
import model.mapGenerators.BaseMapGenerator;
import model.mapGenerators.MapGenerator;
import model.notifiers.Notifier;
import model.playerClasses.Player;
import model.playerClasses.PlayerClass;
import view.GameView;

import java.util.InputMismatchException;

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

     */

    private GameMap map;
    private transient Notifier notifier;
    private transient BaseMapGenerator mapGenerator;

    public void init(GameView view){
        notifier = new Notifier();
        notifier.addObserver(view);
        map.setEventNotifier(notifier);
    }

    public GameModel(GameView view, String mapFileName, PlayerClass playerClass){
        mapGenerator = new MapGenerator();
        try {
            map = mapGenerator.generateMapFromFile(mapFileName, playerClass);
            this.init(view);
            map.addEnemyAt(new Boss(), 11, 11);
        }
        catch(InputMismatchException inputMismatchException){
            System.exit(-1);
        }
    }
    public void movePlayer(int rowAddition, int columnAddition){
            map.movePlayer(rowAddition, columnAddition);
    }

    public Player getPlayer(){
        return map.getPlayer();
    }

    public void attackAt(int rowAddition, int colAddition){
            map.attackAt(rowAddition, colAddition);
    }

    public void useItemAt(int index){
        map.useItemAt(index);
    }

    public void startQuest(String questName){
        Quest q = QuestPool.getQuestByName(questName);
        if(q == null)return;
        map.startQuest(q);
    }

    public String getCompletedQuestsInfo(){
        return map.getCompletedQuestsInfo();
    }

    public GameMap map(){
        synchronized (map) {
            return this.map;
        }
    }

}
