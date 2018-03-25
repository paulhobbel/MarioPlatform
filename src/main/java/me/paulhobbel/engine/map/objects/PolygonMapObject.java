package me.paulhobbel.engine.map.objects;

import me.paulhobbel.engine.map.MapObject;

import java.awt.Polygon;
import java.awt.Shape;

public class PolygonMapObject extends MapObject {
    private Polygon polygon;

    public PolygonMapObject(Polygon polygon) {
        this.polygon = polygon;
    }

    public Polygon getPolygon() {
        return polygon;
    }

    @Override
    public Shape getShape() {
        return polygon;
    }
}
