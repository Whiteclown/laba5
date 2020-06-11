package views;

import Bees.Bee;
import Habitat.SingletonObjects;

import javax.swing.*;
import java.awt.*;

public class VisualPanel extends JPanel {
    public VisualPanel(){
        super();
    }

    public synchronized void paintBee(){
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i < SingletonObjects.beesList.size(); i++){
            Bee bee = SingletonObjects.beesList.get(i);
            g.drawImage(bee.getImage(), bee.getX(), bee.getY(), null);
        }
    }
}
