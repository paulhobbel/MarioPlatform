package me.paulhobbel.engine.component;

import me.paulhobbel.engine.GameObject;
import me.paulhobbel.engine.graphics.renderer.DebugRenderer;

import java.awt.Color;
import java.awt.Shape;

public class DebugComponent extends Component {

    private Shape shape;
    private Color color;

    public DebugComponent(Shape shape, Color color, GameObject parent) {
        super(parent);

        this.shape = shape;
        this.color = color;
    }

    public Shape getShape() {
        return shape;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public void resume() {
        DebugRenderer.getInstance().addComponent(this);
    }

    @Override
    public void pause() {
        DebugRenderer.getInstance().removeComponent(this);
    }
}
