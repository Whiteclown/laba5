package Bees;

public interface IBehaviour {
    int getX();
    int getY();
    void setX(int x);
    void setY(int y);
    int getX0();
    int getY0();
    void setX0(int x);
    void setY0(int y);
    int getTimeFromStart();
    void setTimeFromStart(int timeFromStart);
    int getY1();

    void setY1(int y1);

    int getX1();

    void setX1(int x1);

    void move(int x, int y);    //Do I need it?
}
