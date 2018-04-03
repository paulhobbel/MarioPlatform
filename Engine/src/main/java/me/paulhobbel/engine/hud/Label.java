package me.paulhobbel.engine.hud;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class Label extends HudElement {

    private String text;
    private LabelStyle style;

    public Label(int x, int y, String text, LabelStyle style) {
        this(new Point2D.Double(x, y), text, style);
    }

    public Label(Point2D position, String label, LabelStyle style) {
        super(position);
        this.text = label;
        this.style = style;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void draw(Graphics2D g2d) {
        Color oldColor = g2d.getColor();

        Shape shape = style.getFont().createGlyphVector(g2d.getFontRenderContext(), text).getOutline();


        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // TODO: Add option to disable this part
        g2d.setColor(Color.DARK_GRAY);
        g2d.fill(AffineTransform.getTranslateInstance(position.getX() + 1, position.getY() + shape.getBounds().height + 2).createTransformedShape(shape));

        g2d.setColor(style.getColor());
        g2d.fill(AffineTransform.getTranslateInstance(position.getX(), position.getY() + shape.getBounds().height).createTransformedShape(shape));

        g2d.setColor(oldColor);
    }

    public static class LabelStyle {
        private Font font;
        private Color color;

        public LabelStyle(Font font, Color color) {
            this.font = font;
            this.color = color;
        }

        public Font getFont() {
            return font;
        }

        public Color getColor() {
            return color;
        }
    }
}
