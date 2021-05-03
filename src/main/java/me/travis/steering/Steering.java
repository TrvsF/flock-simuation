package me.travis.steering;

import me.travis.steering.engine.Engine;
import me.travis.steering.gui.DrawingPanel;
import me.travis.steering.gui.Roadway;
import me.travis.steering.util.Util;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Steering implements Runnable {

    public static Steering INSTANCE;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Steering());
    }

    private final Dimension drawingPanelDimension;

    public final Roadway roadway;

    private final Engine engine;

    private DrawingPanel drawingPanel;

    public Steering() {
        INSTANCE = this;
        this.drawingPanelDimension =  new Dimension(1400, 920);
        this.roadway = new Roadway(this.drawingPanelDimension);
        this.engine = new Engine(10);
    }

    @Override
    public void run() {
        JFrame frame = new JFrame("Steering");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.drawingPanel = new DrawingPanel(this.roadway, this.drawingPanelDimension);
        frame.add(this.drawingPanel, BorderLayout.CENTER);

        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);

        this.setEngineTps();
        this.engine.start();
    }

    public void repaint() {
        this.drawingPanel.repaint();
    }

    public void setEngineTps() {
        double ms = Util.tpsToMs(engine.getTps()) > 0.0 ?  Util.tpsToMs(engine.getTps()) : 1;
        engine.getTimer().purge();
        engine.getTimer().schedule(engine, 0, (int) ms);
    }

}