package me.travis.steering.engine;

import me.travis.steering.Steering;

import java.util.Timer;
import java.util.TimerTask;

/**
 * engine that runs the game
 */
public class Engine extends TimerTask {

    private final Timer timer;

    private boolean running;

    private int ticks;
    private int tps;

    public Engine(int tps) {
        this.tps = tps;
        this.ticks = 0;
        this.running = false;
        this.timer = new Timer();
    }

    public void toggle() {
        this.running = !this.running;
    }

    public void start() {
        this.running = true;
    }

    public boolean isRunning() {
        return this.running;
    }

    public double getTps() {
        return this.tps;
    }

    public void setTps(int tps) {
        this.tps = tps;
    }

    public Timer getTimer() {
        return this.timer;
    }

    public int getTicks() {
        return this.ticks;
    }

    public void resetTicks() {
        this.ticks = 0;
    }

    /**
     * each tick this is ran to update the board
     */
    @Override
    public void run() {
        if (this.isRunning()) {
            Steering.INSTANCE.roadway.tickVehicles();
            Steering.INSTANCE.repaint();
        }
    }
}
