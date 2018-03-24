package me.paulhobbel.engine.map.tiled;

import me.paulhobbel.engine.map.MapProperties;

import javax.imageio.ImageIO;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TiledMapLoader {

    public TiledMap load(String fileName) {
        JsonReader reader = Json.createReader(getClass().getResourceAsStream(fileName));

        TiledMap map = loadTileMap(reader.readObject());

        return map;
    }

    public TiledMap loadTileMap(JsonObject object) {
        TiledMap map = new TiledMap();

        String orientation = object.getString("orientation");
        int mapWidth = object.getInt("width", 0);
        int mapHeight = object.getInt("height", 0);
        int tileWidth = object.getInt("tilewidth", 0);
        int tileHeight = object.getInt("tileheight", 0);

        MapProperties mapProperties = map.getProperties();
        if(orientation != null) {
            mapProperties.put("orientation", orientation);
        }
        mapProperties.put("width", mapWidth);
        mapProperties.put("height", mapHeight);
        mapProperties.put("tilewidth", tileWidth);
        mapProperties.put("tileheight", tileHeight);

        // TODO: Load map properties object too

        JsonArray tilesets = object.getJsonArray("tilesets");
        for(int t = 0; t < tilesets.size(); t++) {
            loadTileSet(map, tilesets.getJsonObject(t));

        }

        JsonArray layers = object.getJsonArray("layers");
        for(int l = 0; l < layers.size(); l++) {
            loadLayer(map, layers.getJsonObject(l));
        }

        return map;
    }

    private void loadTileSet(TiledMap map, JsonObject object) {
        String name = object.getString("name");
        int firstgid = object.getInt("firstgid", 1);
        int tilewidth = object.getInt("tilewidth", 0);
        int tileheight = object.getInt("tileheight", 0);
        int spacing = object.getInt("spacing", 0);
        int margin = object.getInt("margin", 0);

        BufferedImage image = null;
        String imageSource = null;
        int imageWidth = 0;
        int imageHeight = 0;

        try {
            imageSource = object.getString("image").replaceAll("\\.\\./", "/");
            imageWidth = object.getInt("width", 0);
            imageHeight = object.getInt("height", 0);
            image = ImageIO.read(getClass().getResourceAsStream(imageSource));
        } catch(IOException e) {
            e.printStackTrace();
        }

        TiledMapTileSet tileset = new TiledMapTileSet();
        tileset.setName(name);
        tileset.getProperties().put("firstgid", firstgid);

        if(image != null) {
            MapProperties properties = tileset.getProperties();
            properties.put("imagesource", imageSource);
            properties.put("imagewidth", imageWidth);
            properties.put("imageheight", imageHeight);
            properties.put("tilewidth", tilewidth);
            properties.put("tileheight", tileheight);
            properties.put("margin", margin);
            properties.put("spacing", spacing);

            int stopWidth = image.getWidth() - tilewidth;
            int stopHeight = image.getHeight() - tileheight;
            int id = firstgid;

            for(int y = margin; y <= stopHeight; y += tileheight + spacing) {
                for(int x = margin; x <= stopWidth; x += tilewidth + spacing) {
                    TiledMapTile tile = new TiledMapTile();
                    tile.setId(id);
                    tile.setImage(image.getSubimage(x, y, tilewidth, tileheight));
                    tileset.putTile(id++, tile);
                }
            }
        }

        // TODO: Read tileset and tile properties

        map.getTileSets().addTileSet(tileset);

    }

    private void loadLayer(TiledMap map, JsonObject object) {
        if(object.getString("type").equals("tilelayer")) {
            int width = object.getInt("width");
            int height = object.getInt("height");
            int opacity = object.getInt("opacity");
            boolean visible = object.getBoolean("visible");

            TiledMapTileLayer layer = new TiledMapTileLayer(width, height, map.getProperties().get("tilewidth", Integer.class), map.getProperties().get("tileheight", Integer.class));
            map.getLayers().add(layer);

            TiledMapTileSets tileSets = map.getTileSets();
            JsonArray tiles = object.getJsonArray("data");
            for(int y = 0; y < height; y++) {
                for(int x = 0; x < width; x++) {
                    int id = tiles.getInt(y * width + x);
                    TiledMapTile tile = tileSets.getTile(id);
                    if(tile != null) {
                        TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
                        cell.setTile(tile);
                        layer.setCell(x, y, cell);
                    }
                }
            }
        } else {
            System.out.println(object);
        }
    }
}
