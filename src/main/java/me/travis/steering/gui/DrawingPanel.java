package me.travis.steering.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * draws the vehicles
 */
public class DrawingPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private final Roadway roadway;

    public DrawingPanel(Roadway roadway, Dimension drawingPanelDimension) {
        this.roadway = roadway;
        this.setBackground(Color.WHITE);
        this.setPreferredSize(drawingPanelDimension);
    }

    /**
     * draws each object in the roadway
     * @param g Graphics
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for (Vehicle vehicle : roadway.getVehicles()) {
            // set rotation of rectangle
            AffineTransform transform = new AffineTransform();
            transform.rotate(vehicle.getRotation(), vehicle.getX() + (double) vehicle.getWidth() / 2,
                    vehicle.getY() + (double) vehicle.getHeight() / 2);
            AffineTransform old = g2d.getTransform(); // save old transform to reset for later
            g2d.transform(transform);
            // set color and draw rectangle
            g2d.setColor(vehicle.getColor());
            g2d.fillRect(vehicle.getX(), vehicle.getY(), vehicle.getWidth(), vehicle.getHeight());
            g2d.setTransform(old); // reset transform
        }

        // draw goal
        Goal goal = roadway.getGoal();
        g2d.setColor(goal.getColor());
        g2d.fillOval(goal.getX(), goal.getY(), 40, 40);

    }

}
