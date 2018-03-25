package me.paulhobbel.engine.map.tiled;

import javafx.scene.paint.Color;
import me.paulhobbel.engine.map.MapLayer;
import me.paulhobbel.engine.map.MapProperties;
import me.paulhobbel.engine.map.tiled.TiledMapTileLayer.Cell;
import me.paulhobbel.engine.map.tiled.strategies.OrthogonalTiledMapRenderStrategy;

import javax.imageio.ImageIO;
import javax.json.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map.Entry;

public class TiledMapLoader {

    public TiledMap load(String fileName) {
        JsonReader reader = Json.createReader(getClass().getResourceAsStream(fileName));

        TiledMap map = loadTileMap(reader.readObject());

        return map;
    }

    private TiledMap loadTileMap(JsonObject object) {
        TiledMap map = new TiledMap();

        String orientation = object.getString("orientation", null);
        int mapWidth = object.getInt("width", 0);
        int mapHeight = object.getInt("height", 0);
        int tileWidth = object.getInt("tilewidth", 0);
        int tileHeight = object.getInt("tileheight", 0);

        if(orientation != null) {
            if(orientation.equals("orthogonal")) {
                map.setStrategy(new OrthogonalTiledMapRenderStrategy());
            }
        }

        MapProperties mapProperties = map.getProperties();
        if(orientation != null) {
            mapProperties.put("orientation", orientation);
        }
        mapProperties.put("width", mapWidth);
        mapProperties.put("height", mapHeight);
        mapProperties.put("tilewidth", tileWidth);
        mapProperties.put("tileheight", tileHeight);

        if(object.containsKey("properties")) {
            loadProperties(mapProperties,
                    object.getJsonObject("properties"), object.getJsonObject("propertytypes"));
        }

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
        int firstGid = object.getInt("firstgid", 1);
        int tileWidth = object.getInt("tilewidth", 0);
        int tileHeight = object.getInt("tileheight", 0);
        int spacing = object.getInt("spacing", 0);
        int margin = object.getInt("margin", 0);

        // TODO: Add support for non image tileSets

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
        tileset.getProperties().put("firstgid", firstGid);

        if(image != null) {
            MapProperties properties = tileset.getProperties();
            properties.put("imagesource", imageSource);
            properties.put("imagewidth", imageWidth);
            properties.put("imageheight", imageHeight);
            properties.put("tilewidth", tileWidth);
            properties.put("tileheight", tileHeight);
            properties.put("margin", margin);
            properties.put("spacing", spacing);

            int stopWidth = image.getWidth() - tileWidth;
            int stopHeight = image.getHeight() - tileHeight;
            int id = firstGid;

            for(int y = margin; y <= stopHeight; y += tileHeight + spacing) {
                for(int x = margin; x <= stopWidth; x += tileWidth + spacing) {
                    TiledMapTile tile = new TiledMapTile();
                    tile.setId(id);
                    tile.setImage(image.getSubimage(x, y, tileWidth, tileHeight));
                    tileset.putTile(id++, tile);
                }
            }
        }

        // TODO: Read tileset and tile properties

        map.getTileSets().addTileSet(tileset);

    }

    private void loadLayer(TiledMap map, JsonObject object) {
        switch (object.getString("type")) {
            case "tilelayer":
                loadTileLayer(map, object);
                break;
            case "objectgroup":
                loadObjectGroup(map, object);
                break;
            default:
                System.out.println(object);
                throw new RuntimeException("Unsupported layer found!");
        }
    }

    private void loadTileLayer(TiledMap map, JsonObject object) {
        if(object.getString("type").equals("tilelayer")) {
            int width = object.getInt("width", 0);
            int height = object.getInt("height", 0);

            int tileWidth = map.getProperties().get("tilewidth", Integer.class);
            int tileHeight = map.getProperties().get("tileheight", Integer.class);

            TiledMapTileLayer layer = new TiledMapTileLayer(width, height, tileWidth, tileHeight);

            loadLayerInfo(layer, object);

            if(object.containsKey("properties")) {
                loadProperties(layer.getProperties(),
                        object.getJsonObject("properties"), object.getJsonObject("propertytypes"));
            }

            TiledMapTileSets tileSets = map.getTileSets();
            JsonArray tiles = object.getJsonArray("data");
            for(int y = 0; y < height; y++) {
                for(int x = 0; x < width; x++) {
                    int id = tiles.getInt(y * width + x);
                    TiledMapTile tile = tileSets.getTile(id);
                    if(tile != null) {
                        Cell cell = new Cell();
                        cell.setTile(tile);
                        layer.setCell(x, y, cell);
                    }
                }
            }

            map.getLayers().add(layer);
        }
    }

    private void loadObjectGroup(TiledMap map, JsonObject object) {
        MapLayer layer = new MapLayer();

        loadLayerInfo(layer, object);

        if(object.containsKey("properties")) {
            loadProperties(layer.getProperties(),
                    object.getJsonObject("properties"), object.getJsonObject("propertytypes"));
        }

        System.err.println("[TiledMapLoader] ObjectGroups are not fully supported yet!");

        map.getLayers().add(layer);
    }

    /**
     * Load basic layer information
     * @param layer The layer to set info to
     * @param object THe object to read from
     */
    private void loadLayerInfo(MapLayer layer, JsonObject object) {
        int opacity = object.getInt("opacity", 0);
        boolean visible = object.getBoolean("visible", true);
        String name = object.getString("name", null);

        layer.setName(name);
        layer.setOpacity(opacity);
        layer.setVisible(visible);
    }

    private void loadProperties(MapProperties properties, JsonObject values, JsonObject types) {
        for(String key : types.keySet()) {
            String type = types.getString(key);

            if(type.equals("int")) {
                properties.put(key, values.getInt(key));
            } else if(type.equals("float")) {
                properties.put(key, Float.parseFloat(values.get(key).toString()));
            } else if(type.equals("bool")) {
                properties.put(key, values.getBoolean(key));
            } else if(type.equals("color")) {
                String value = values.getString(key);
                String opaqueColor = value.substring(3);
                String alpha = value.substring(1, 3);
                properties.put(key, Color.valueOf(opaqueColor + alpha));
            } else {
                throw new RuntimeException("Wrong type given for property " + key + ", given : " + type
                        + ", supported : string, bool, int, float, color");
            }
        }
    }

    private void loadTileSetProperties() {

    }
}
