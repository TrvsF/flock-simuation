package me.travis.steering.gui;

import com.sun.javafx.geom.Vec2d;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * holds the information used to manipulate the vehicles
 */
public class Roadway {

    private final Dimension drawingPanelDimension;

    private final List<Vehicle> vehicles;

    private final Goal goal;

    private final Random random;

    public Roadway(Dimension drawingPanelDimension) {
        this.drawingPanelDimension = drawingPanelDimension;
        this.vehicles = new ArrayList<>();
        this.random = new Random();
        this.goal = new Goal(random.nextInt(drawingPanelDimension.width - 20) + 20, random.nextInt(drawingPanelDimension.height - 20) + 20);
        this.populateVehicles(40);
        // this.lookAtGoal();
    }

    /**
     * creates n random vehicles within the constraints of the window
     */
    private void populateVehicles(int n) {
        int width = drawingPanelDimension.width;
        int height = drawingPanelDimension.height;

        for (int i = 0; i < n; i++) {
            int x = random.nextInt(width - 20) + 20;
            int y = random.nextInt(height - 20) + 20;
            Vehicle v = new Vehicle(x, y);
            vehicles.add(v);
        }
    }

    /**
     * sets the angle of each vehicle to look at the goal object
     */
    public void lookAtGoal() {
        for (Vehicle vehicle : this.vehicles) {
            Vec2d directionVec = new Vec2d(vehicle.getX() - (goal.getX() + (float) goal.getWidth() / 2),
                    vehicle.getY() - (goal.getY() + (float) goal.getHeight() / 2)); // mafs
            float angle = (float) Math.atan2(directionVec.y, directionVec.x);
            vehicle.setRotation((float) (angle + Math.PI));
        }
    }

    public void lookAtCoord(int x, int y) {
        for (Vehicle vehicle : this.vehicles) {
            Vec2d directionVec = new Vec2d(x - vehicle.getX(), y - vehicle.getY());
            float angle = (float) Math.atan2(directionVec.y, directionVec.x);
            vehicle.setRotation(angle);
        }
    }

    public float getRotateTarget(Vehicle vehicle) {
        Vec2d directionVec = new Vec2d(vehicle.getX() - (goal.getX() + (float) goal.getWidth() / 2),
                vehicle.getY() - (goal.getY() + (float) goal.getHeight() / 2));
        return (float) (Math.atan2(directionVec.y, directionVec.x) + Math.PI);
    }

    public float getRotateMidpoint(Vehicle vehicle) {
        Vec2d directionVec = new Vec2d(this.getMidpoint()[0] - vehicle.getX(), this.getMidpoint()[1] - vehicle.getY());
        return (float) Math.atan2(directionVec.y, directionVec.x);
    }

    public void tickVehicles() {
        for (Vehicle vehicle : this.vehicles) {
            float rotation = getRotateTarget(vehicle) + getRotateMidpoint(vehicle);
            vehicle.setTargetRotation(rotation);
            vehicle.wander();
        }
    }

    public int[] getMidpoint() {
        int[] midpoint = {0, 0};
        for (Vehicle vehicle : this.vehicles) {
            midpoint[0] += vehicle.getX();
            midpoint[1] += vehicle.getY();
        }
        midpoint[0] = midpoint[0] / this.vehicles.size();
        midpoint[1] = midpoint[1] / this.vehicles.size();
        return midpoint;
    }

    public float getAverageRotation() {
        float totalRotation = 0;
        for (Vehicle vehicle : this.vehicles) {
            totalRotation += vehicle.getRotation();
        }
        return totalRotation / this.vehicles.size();
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public Goal getGoal() {
        return this.goal;
    }

}
