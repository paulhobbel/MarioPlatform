package me.paulhobbel.engine.physics.box2d;

import me.paulhobbel.engine.graphics.Renderer;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.ShapeType;
import org.jbox2d.common.Vec2;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class Box2DDebugRenderer implements Renderer {
    private static Box2DDebugRenderer instance;

    public static Box2DDebugRenderer getInstance() {
        if(instance == null) instance = new Box2DDebugRenderer();
        return instance;
    }

    public void setActiveWorld(World world) {
        this.world = world;
    }

    private World world;

    private final Color SHAPE_NOT_ACTIVE = new Color(0.5f, 0.5f, 0.3f, 1);
    private final Color SHAPE_STATIC = new Color(0.5f, 0.9f, 0.5f, 1);
    private final Color SHAPE_KINEMATIC = new Color(0.5f, 0.5f, 0.9f, 1);
    private final Color SHAPE_NOT_AWAKE = new Color(0.6f, 0.6f, 0.6f, 1);
    private final Color SHAPE_AWAKE = new Color(0.9f, 0.7f, 0.7f, 1);
    private final Color JOINT_COLOR = new Color(0.5f, 0.8f, 0.8f, 1);
    private final Color AABB_COLOR = new Color(1.0f, 0, 1.0f, 1f);
    private final Color VELOCITY_COLOR = new Color(1.0f, 0, 0f, 1f);

    private boolean drawBodies;
    private boolean drawJoints;
    private boolean drawAABBs;
    private boolean drawInactiveBodies;
    private boolean drawVelocities;
    private boolean drawContacts;

    public Box2DDebugRenderer () {
        this(true, true, false, true, false, true);
    }

    public Box2DDebugRenderer (boolean drawBodies, boolean drawJoints, boolean drawAABBs, boolean drawInactiveBodies,
                               boolean drawVelocities, boolean drawContacts) {
        this.drawBodies = drawBodies;
        this.drawJoints = drawJoints;
        this.drawAABBs = drawAABBs;
        this.drawInactiveBodies = drawInactiveBodies;
        this.drawVelocities = drawVelocities;
        this.drawContacts = drawContacts;
    }

    private void renderBodies(Graphics2D g2d) {
        for(Body body : world.getBodyList()) {
            renderBody(g2d, body);
        }
    }

    private void renderBody(Graphics2D g2d, Body body) {
        Transform transform = body.getTransform();
        ArrayList<Fixture> fixtures = body.getFixtureList();

        for(Fixture fixture : fixtures) {
//            if(!body.isActive() && drawInactiveBodies) {
//
//            }
            //System.out.println(fixture);
            drawShape(g2d, fixture, transform, SHAPE_STATIC);
        }
    }

    private void drawShape(Graphics2D g2d, Fixture fixture, Transform transform, Color color) {
        if(fixture.getType() == ShapeType.CIRCLE) {
            CircleShape circleShape = (CircleShape) fixture.getShape();
            //transform.mul(circleShape.m_p);
            //System.out.println(circleShape.m_p);
            //System.out.println(transform.getPosition());
            drawSolidCircle(g2d, transform, circleShape.getRadius(), transform.getOrientation(), color);

        } else if(fixture.getType() == ShapeType.POLYGON) {
            PolygonShape chain = (PolygonShape) fixture.getShape();
            Vec2[] vertices = new Vec2[chain.getVertexCount()];
            for(int i = 0; i < vertices.length; i++) {
                vertices[i] = chain.getVertex(i);
                //System.out.println(vertices[i]);
                //transform.mul(vertices[i]);
                //System.out.println(vertices[i]);
            }
            drawSolidPolygon(g2d, transform, vertices, vertices.length, color);
        }
    }

    private void drawSolidCircle(Graphics2D g2d, Transform transform, float radius, Vec2 axis, Color color) {
        AffineTransform tx = getTransform(transform);
        g2d.draw(tx.createTransformedShape(new Ellipse2D.Double(0-radius, 0-radius, radius*2, radius*2)));
    }

    private void drawSolidPolygon(Graphics2D g2d, Transform transform, Vec2[] vertices, int vertexCount, Color color) {
        Polygon polygon = new Polygon();
        for(Vec2 point : vertices) {
            polygon.addPoint((int) point.x, (int) point.y);
        }

        AffineTransform tx = getTransform(transform);

        g2d.draw(tx.createTransformedShape(polygon));
    }

    private AffineTransform getTransform(Transform transform) {
        Vec2 position = transform.getPosition();
        AffineTransform tx = AffineTransform.getTranslateInstance(position.x, position.y);
        tx.rotate(transform.getRotation());


        return tx;
    }

    @Override
    public void render(Graphics2D g2d) {
        if(world != null) {
            renderBodies(g2d);
        }
    }
}
