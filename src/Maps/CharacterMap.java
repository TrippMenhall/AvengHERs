package Maps;

import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Sprite;
import Level.Map;
import Tilesets.CommonTileset;
import Utils.Colors;
import Utils.Point;

// Represents the map that is used as a background for the main menu and credits menu screen
public class CharacterMap extends Map {

    // private Sprite cat, cat2;
    private Sprite background;

    public CharacterMap() {
        super("title_screen_map.txt", new CommonTileset());

        // background = new Sprite(ImageLoader.load("StartingScreenBackground.jpg"));
        // background.setWidth(90);
        // background.setHeight(65);
        // background.setScale(9);

        // Point catLocation = getMapTile(8, 9).getLocation().subtractX(53).subtractY(-10);
        // cat = new Sprite(ImageLoader.loadSubImage("Cat.png", Colors.MAGENTA, 0, 0, 24, 24));
        // cat.setScale(3);
        // cat.setLocation(catLocation.x, catLocation.y);
        // cat2 = new Sprite(ImageLoader.loadSubImage("CatFlipped.png", Colors.MAGENTA, 0, 0, 24, 24));
        // cat2.setScale(3);
        // cat2.setLocation(catLocation.x + 80, catLocation.y);
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        // cat.draw(graphicsHandler);
        // cat2.draw(graphicsHandler);
        
        // background.draw(graphicsHandler);
    }

}
