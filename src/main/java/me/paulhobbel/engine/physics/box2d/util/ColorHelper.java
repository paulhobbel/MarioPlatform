package me.paulhobbel.engine.physics.box2d.util;

import java.util.HashMap;

public abstract class ColorHelper<T> {
    private HashMap<ColorKey, T> colorMap = new HashMap<>();

    public T getColor(float red, float green, float blue) {
        return getColor(red, green, blue, 1);
    }

    public T getColor(float red, float green, float blue, float alpha) {
        ColorKey colorKey = new ColorKey();
        colorKey.set(red, green, blue, alpha);

        if(colorMap.containsKey(colorKey)) {
            return colorMap.get(colorKey);
        }

        T color = newColor(red, green, blue, alpha);
        return colorMap.put(colorKey, color);
    }

    protected abstract T newColor(float red, float green, float blue, float alpha);

    private class ColorKey {
        float r, g, b, a;

        public void set(float red, float green, float blue) {
            set(red, green, blue, 1);
        }

        public void set(float red, float green, float blue, float alpha) {
            r = red;
            g = green;
            b = blue;
            a = alpha;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;

            result = prime * result + Float.floatToIntBits(a);
            result = prime * result + Float.floatToIntBits(b);
            result = prime * result + Float.floatToIntBits(g);
            result = prime * result + Float.floatToIntBits(r);

            return  result;
        }

        @Override
        public boolean equals(Object obj) {
            if(this == obj)
                return  true;
            if(obj == null)
                return false;
            if(getClass() != obj.getClass())
                return false;

            ColorKey other = (ColorKey) obj;
            if (Float.floatToIntBits(a) != Float.floatToIntBits(other.a))
                return false;
            if (Float.floatToIntBits(b) != Float.floatToIntBits(other.b))
                return false;
            if (Float.floatToIntBits(g) != Float.floatToIntBits(other.g))
                return false;
            if (Float.floatToIntBits(r) != Float.floatToIntBits(other.r))
                return false;

            return true;

        }
    }
}
