package me.paulhobbel.engine.physics.box2d;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;

public class Geometry {

    public static CircleShape createCirle(float radius) {
        CircleShape circle = new CircleShape();
        circle.setRadius(radius);
        System.out.println(circle);
        return circle;
    }

    public static PolygonShape createPolygon(Vec2 ...vertices) {
        PolygonShape polygon = new PolygonShape();
        polygon.set(vertices, vertices.length);
        return polygon;
    }

    public static PolygonShape createSquare(float size) {
        return createRectangle(size, size);
    }

    public static PolygonShape createRectangle(float width, float height) {
        PolygonShape rectangle = new PolygonShape();
        rectangle.setAsBox(width, height);
        return rectangle;
    }

    public static PolygonShape createEllipse(float width, float height) {
        // TODO: Write own ellipse shape somehow
        throw new RuntimeException("createEllipse: Method not done yet!");
    }
}
