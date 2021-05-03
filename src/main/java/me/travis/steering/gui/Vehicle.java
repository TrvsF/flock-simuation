package me.travis.steering.gui;

import me.travis.steering.util.Util;

import javax.swing.*;
import java.awt.*;

public class Vehicle extends JPanel {

    private int x;
    private int y;

    private final int speed;

    private float rotation;
    private float targetRotation;

    private final int width;
    private final int height;

    private final Color color;

    public Vehicle(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 25;
        this.height = 10;
        this.speed = 2;
        this.rotation = Util.randomAngle();

        int red = (int) (Math.random() * 255);
        int green = (int) (Math.random() * 255);
        int blue = (int) (Math.random() * 255);
        this.color = new Color(red, green, blue);
    }

    public void rotateVehicle(float t) {
        this.rotation += t * this.speed;
    }

    public void moveVehicle(double x_, double y_) {
        this.x += x_ * this.speed;
        this.y += y_ * this.speed;
    }

    public void wander() {
        this.rotation += this.randomRotationOffset() + ((this.targetRotation - this.rotation) / 10f);
        this.moveVehicle(Math.cos(this.rotation), Math.sin(this.rotation));
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getSpeed() {
        return this.speed;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public Color getColor() {
        return this.color;
    }

    public float getRotation() {
        return this.rotation;
    }

    public void setRotation(float r) {
        this.rotation = r;
    }

    public void setTargetRotation(float r) {
        this.targetRotation = r;
    }

    private float randomRotationOffset() {
        return Util.randomFloat(-0.2f, 0.2f);
    }

}
