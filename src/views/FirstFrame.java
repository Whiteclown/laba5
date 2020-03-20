package views;

import Bees.Bee;
import Bees.BeeBig;
import Bees.BeeWork;
import Habitat.Habitat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class FirstFrame extends JFrame implements KeyListener {
    private final String beginButtonText = "Begin";
    FirstPanel firstPanel;
    Habitat habitat;
    JLabel timeLabel;
    boolean timeVisible = true;
    int time;
    public FirstFrame(){
        habitat = new Habitat(5,3, 0.8, 0.5, this);
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

    public void beesDraw(ArrayList<Bee> bees){
        firstPanel.paintBee(bees);
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
        switch (e.getKeyCode()){
            case KeyEvent.VK_B:
                habitat.startBorn();
                break;
            case KeyEvent.VK_E:
                habitat.stopBorn();
                createDialogWindow();
                BeeWork.countBeeWork = 0;
                BeeBig.countBeeBig = 0;
                Bee.countBees = 0;
                break;
            case KeyEvent.VK_T:
                if (timeVisible == true)
                    timeVisible = false;
                else
                    timeVisible = true;
                timeLabel.setVisible(timeVisible);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void createDialogWindow(){
        JDialog jDialog = new JDialog(this, "Result", true);
        JPanel jPanelDialog = new JPanel();
        jPanelDialog.setLayout(new BoxLayout(jPanelDialog, BoxLayout.Y_AXIS));
        jDialog.setContentPane(jPanelDialog);

        Font font = new Font("Georgia", Font.BOLD, 15);
        Font font1 = new Font("Georgia", Font.ITALIC, 15);
        JLabel jLabelBees = new JLabel("Bees: " + Bee.countBees, SwingConstants.CENTER);
        jLabelBees.setFont(font);
        jPanelDialog.add(jLabelBees);
        JLabel jLabelBeeWork = new JLabel("Work bees: " + BeeWork.countBeeWork, SwingConstants.CENTER);
        jLabelBeeWork.setFont(font1);
        JLabel jLabelBeeBig = new JLabel("Big bees: " + BeeBig.countBeeBig, SwingConstants.CENTER);
        jLabelBeeBig.setForeground(Color.ORANGE);
        jPanelDialog.add(jLabelBeeWork);
        jPanelDialog.add(jLabelBeeBig);
        jPanelDialog.add(new JLabel("Time: " + time / 60 + ":" + time % 60));

        jDialog.setDefaultCloseOperation(HIDE_ON_CLOSE);
        jDialog.setPreferredSize(new Dimension(150, 150));
        jDialog.setResizable(false);
        jDialog.pack();
        jDialog.setLocationRelativeTo(this);
        jDialog.setVisible(true);
    }
}
