package Bees;

import java.awt.*;

public interface IBehaviour {
    int getX();
    int getY();
    void setX(int x);
    void setY(int y);

    void move(int x, int y);    //Do I need it?
}
