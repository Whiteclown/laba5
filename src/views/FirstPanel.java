package views;

import Bees.Bee;
import Bees.BeeBig;
import Bees.BeeWork;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FirstPanel extends JPanel {
    ArrayList<Bee> bees = new ArrayList<>();
    public FirstPanel(){
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
            if (bee instanceof BeeWork){
                g.drawImage(BeeWork.image, bee.getX(), bee.getY(), null);
            }
            else {
                g.drawImage(BeeBig.image, bee.getX(), bee.getY(), null);
            }
        }
    }
}
