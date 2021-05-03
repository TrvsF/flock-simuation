package me.travis.steering.gui;

import javax.swing.*;
import java.awt.*;

public class Goal extends JPanel {

    private int x;
    private int y;

    private final Color color;

    public Goal(int x, int y) {
        this.x = x;
        this.y = y;
        this.color = Color.BLACK;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Color getColor() {
        return this.color;
    }

    public void setCoords(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
