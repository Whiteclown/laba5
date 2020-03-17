package views;

import Habitat.Habitat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FirstFrame extends JFrame implements KeyListener {
    private final String beginButtonText = "Begin";
    FirstPanel firstPanel;
    Habitat habitat;
    JLabel timeLabel;
    int time;
    public FirstFrame(){
        habitat = new Habitat(1,2, 100, 50, this);
        firstPanel = new FirstPanel();

        setTitle("Bees");
        setPreferredSize(new Dimension(habitat.getWIDTH(), habitat.getHEIGHT()));
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        firstPanel.setPreferredSize(new Dimension(habitat.getWIDTH(), habitat.getHEIGHT()));
        add(firstPanel);
        addKeyListener(this);

        timeLabel = new JLabel(" ", SwingConstants.CENTER);
        add(timeLabel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(true);
    }

    public void updateTime(int time){
        this.time = time;
        timeLabel.setText(time / 60 + ":" + time % 60);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
