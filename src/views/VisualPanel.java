package views;

import Bees.Bee;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VisualPanel extends JPanel {
    ArrayList<Bee> bees = new ArrayList<>();
    public VisualPanel(){
        super();
    }

    public void paintBee(ArrayList<Bee> bees){
        this.bees = bees;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i < bees.size(); i++){
            Bee bee = bees.get(i);
            g.drawImage(bee.getImage(), bee.getX(), bee.getY(), null);
        }
    }
}