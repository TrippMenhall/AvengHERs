package Maps;

//import java.util.ArrayList;

import Engine.GraphicsHandler;
// import Engine.ImageLoader;
// import EnhancedMapTiles.EndLevelBox;
// import EnhancedMapTiles.HorizontalMovingPlatform;
// import GameObject.Rectangle;
// import GameObject.Sprite;
// import Level.EnhancedMapTile;
import Level.Map;
//import Level.TileType;
import Tilesets.CommonTileset;
// import Utils.Colors;
// import Utils.Point;

// Represents the map that is used as a background for the main menu and credits menu screen
public class BlankMap extends Map {

    // private Sprite cat, cat2;

    public BlankMap() {
        super("title_screen_map.txt", new CommonTileset());

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
    }
}
