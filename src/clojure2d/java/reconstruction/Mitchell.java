package clojure2d.java.reconstruction;

import net.jafama.FastMath;

public class Mitchell extends AFilter {
    double B, C;

    public Mitchell(double radius, double B, double C) {
        super(radius);
        this.B = B;
        this.C = C;

        init();
    }

    private double Mitchell1d(double x) {
        x = FastMath.abs(x + x);
        if (x > 1)
            return ((-B - 6 * C) * x * x * x + (6 * B + 30 * C) * x * x +
                    (-12 * B - 48 * C) * x + (8 * B + 24 * C)) / 6.0;
        else
            return ((12 - 9 * B - 6 * C) * x * x * x +
                    (-18 + 12 * B + 6 * C) * x * x + (6 - 2 * B)) / 6.0;
    }

    public double evaluate(double x, double y) {
        return Mitchell1d(x*iradius) * Mitchell1d(y*iradius);
    }
}
