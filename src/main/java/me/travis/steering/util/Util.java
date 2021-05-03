package me.travis.steering.util;

import java.util.Random;

public class Util {

    private static final Random random = new Random();

    /**
     * generates a random angle in radians
     * @return
     */
    public static float randomAngle() {
        return (float) (random.nextFloat() * 2 * Math.PI);
    }

    /**
     * converts tps to ms
     * @param tps Tps
     * @return ms
     */
    public static double tpsToMs(double tps) {
        return (1/tps) * 1000;
    }

    public static float randomFloat(float min, float max) {
        return min + random.nextFloat() * (max - min);
    }

}
