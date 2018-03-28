package me.paulhobbel.engine.graphics.renderer;

import me.paulhobbel.engine.Engine;
import me.paulhobbel.engine.graphics.Renderer;
import me.paulhobbel.engine.physics.box2d.Body;
import me.paulhobbel.engine.physics.box2d.Fixture;
import me.paulhobbel.engine.physics.box2d.World;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.ShapeType;
import org.jbox2d.common.Vec2;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.util.Arrays;

public class DebugDraw implements Renderer {
    private static DebugDraw instance;

    public static DebugDraw getInstance() {
        if(instance == null) instance = new DebugDraw();
        return instance;
    }

    private DebugDraw() {
        scale = Engine.PPM;
    }

    private World world;
    private double scale = 1;

    public void setWorld(World world) {
        this.world = world;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    @Override
    public void render(Graphics2D g2d) {
        if(world != null) {
            for (Body b : world.getBodyList()) {
                AffineTransform originalTransform = g2d.getTransform();

                AffineTransform bodyTransform = new AffineTransform();
                bodyTransform.translate(b.getTransform().getTranslationX() * scale, b.getTransform().getTranslationY() * scale);
                bodyTransform.rotate(b.getTransform().getRotation());
                g2d.transform(bodyTransform);

                Color oldColor = g2d.getColor();

                for (Fixture fixture : b.getFixtureList()) {
                    g2d.setColor(Color.GREEN);
                    g2d.draw(AffineTransform.getScaleInstance(scale, scale).createTransformedShape(getShape(fixture)));

                    AffineTransform tx = AffineTransform.getScaleInstance(scale, scale);
                    tx.rotate(b.getTransform().getOrientation().x, b.getTransform().getOrientation().y);

                    g2d.setColor(Color.RED);
                    g2d.draw(tx.createTransformedShape(new Line2D.Double(0, 0, 2, 0)));

                }

                g2d.setColor(oldColor);
                g2d.setTransform(originalTransform);
            }
        }
    }

    private Shape getShape(Fixture fixture) {
        if(fixture.getType() == ShapeType.POLYGON)
            return getShape((PolygonShape) fixture.getShape());
        if(fixture.getType() == ShapeType.CIRCLE)
            return getShape((CircleShape) fixture.getShape());
        else
            System.out.println("Unsupported shape: " + fixture);
        return null;
    }


    private Shape getShape(PolygonShape shape) {
        GeneralPath path = new GeneralPath();

        Vec2 vertex = shape.getVertex(0);
        path.moveTo(vertex.x, vertex.y);
        for(int i = 1; i < shape.getVertexCount(); i++) {
            vertex = shape.getVertex(i);
            path.lineTo(vertex.x, vertex.y);
        }
        path.closePath();
        return path;
    }

    private Shape getShape(CircleShape shape) {
        return new Ellipse2D.Double(0 - shape.getRadius(),
                0 - shape.getRadius(),
                shape.getRadius()*2,
                shape.getRadius()*2);
    }
}