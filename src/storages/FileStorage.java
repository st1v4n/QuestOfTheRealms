package storages;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import externalLibraries.RuntimeTypeAdapterFactory;
import model.GameModel;
import model.actionResults.ActionResult;
import model.actionResults.Status;
import model.enemy.Bandit;
import model.enemy.Boss;
import model.enemy.Enemy;
import model.enemy.Monster;
import model.gameObjects.GameObject;
import model.items.Armor;
import model.items.Item;
import model.items.Potion;
import model.items.Weapon;
import model.terrain.Blank;
import model.terrain.Wall;
import view.GameView;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//Внимание!
//Не съм работил с Gson досега, възможно е да има бъгове
//Класът FileStorage не е писан изцяло със мой знания, но пък поне разбирам какво прави
//Всичко останало във проекта е 100% писано от мен ;)
public class FileStorage{

    private static final Gson gson;

    static{
        RuntimeTypeAdapterFactory<GameObject> gameObjectAdapter =
                RuntimeTypeAdapterFactory.of(GameObject.class, "type")
                        .registerSubtype(Enemy.class, "enemy")
                        .registerSubtype(Bandit.class, "bandit")
                        .registerSubtype(Boss.class, "boss")
                        .registerSubtype(Monster.class, "monster")
                        .registerSubtype(Item.class, "item")
                        .registerSubtype(Armor.class, "armor")
                        .registerSubtype(Potion.class, "potion")
                        .registerSubtype(Weapon.class, "weapon")
                        .registerSubtype(Blank.class, "blank")
                        .registerSubtype(Wall.class, "wall");

        gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapterFactory(gameObjectAdapter)
                .create();
    }

    public static ActionResult save(GameModel game, String filename) {
        synchronized (game.map()) {
            try (FileWriter writer = new FileWriter(filename)) {
                gson.toJson(game, writer);
                System.out.println("Game saved to " + filename);
            } catch (IOException e) {
                return new ActionResult(Status.ERROR, "Could not save to file!");
            }
            return new ActionResult(Status.SUCCESS, "Saved successfully!");
        }
    }

    public static GameModel load(GameView view, String filename) throws FileNotFoundException{
        try (FileReader reader = new FileReader(filename)) {
            GameModel model = gson.fromJson(reader, GameModel.class);
            if(model == null)throw new FileNotFoundException("Game was not properly saved!");
            model.init(view);
            return model;
        } catch (IOException e) {
            throw new FileNotFoundException("No saves exist!");
        }
    }
}
