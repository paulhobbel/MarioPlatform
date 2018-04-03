package me.paulhobbel.engine.graphics.renderer;

import me.paulhobbel.engine.core.Engine;
import me.paulhobbel.engine.graphics.Renderer;
import me.paulhobbel.engine.physics.box2d.Body;
import me.paulhobbel.engine.physics.box2d.Fixture;
import me.paulhobbel.engine.physics.box2d.World;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.EdgeShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.ShapeType;
import org.jbox2d.common.Vec2;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;

public class DebugRenderer implements Renderer {
    private static DebugRenderer instance;

    public static DebugRenderer getInstance() {
        if(instance == null) instance = new DebugRenderer();
        return instance;
    }

    private DebugRenderer() {
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

                for (Fixture fixture : b.getFixtureList()) {
                    g2d.setColor(Color.BLACK);
                    g2d.draw(AffineTransform.getScaleInstance(scale, scale).createTransformedShape(getShape(fixture)));

                    AffineTransform tx = AffineTransform.getScaleInstance(scale, scale);
                    tx.rotate(b.getTransform().getOrientation().x, b.getTransform().getOrientation().y);

                    g2d.setColor(Color.RED);
                    g2d.draw(tx.createTransformedShape(new Line2D.Double(0, 0, 3/scale, 0)));

                }

                g2d.setTransform(originalTransform);
            }
        }
    }

    private Shape getShape(Fixture fixture) {
        if(fixture.getType() == ShapeType.POLYGON)
            return getShape((PolygonShape) fixture.getShape());
        if(fixture.getType() == ShapeType.CIRCLE)
            return getShape((CircleShape) fixture.getShape());
        if(fixture.getType() == ShapeType.EDGE)
            return getShape((EdgeShape) fixture.getShape());
        else
            System.out.println("Unsupported shape: " + fixture.getType());
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

    private Shape getShape(EdgeShape shape) {
        GeneralPath path = new GeneralPath();

        path.moveTo(shape.m_vertex1.x, shape.m_vertex1.y);
        path.lineTo(shape.m_vertex2.x, shape.m_vertex2.y);
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