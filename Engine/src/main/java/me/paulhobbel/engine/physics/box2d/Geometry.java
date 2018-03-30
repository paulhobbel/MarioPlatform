package me.paulhobbel.engine.physics.box2d;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.EdgeShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;

public class Geometry {

    public static CircleShape createCircle(float radius) {
        CircleShape circle = new CircleShape();
        circle.setRadius(radius);
        System.out.println(circle);
        return circle;
    }

    public static PolygonShape createPolygon(Vec2 ...vertices) {
        System.err.println("Polygons are not fully supported, please do not use them!");
        PolygonShape polygon = new PolygonShape();
        polygon.set(vertices, vertices.length);
        return polygon;
    }

    public static PolygonShape createSquare(float size) {
        return createRectangle(size, size);
    }

    public static PolygonShape createRectangle(float width, float height) {
        PolygonShape rectangle = new PolygonShape();
        rectangle.setAsBox(width / 2, height / 2);
        return rectangle;
    }

    public static EdgeShape createEdge(float x1, float y1, float x2, float y2) {
        return createEdge(new Vec2(x1, y1), new Vec2(x2, y2));
    }

    public static EdgeShape createEdge(Vec2 v1, Vec2 v2) {
        EdgeShape edge = new EdgeShape();
        edge.set(v1, v2);
        return edge;
    }

    public static PolygonShape createEllipse(float width, float height) {
        // TODO: Write own ellipse shape somehow
        throw new RuntimeException("createEllipse: Method not done yet!");
    }
}
