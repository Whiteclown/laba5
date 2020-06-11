package Habitat;

abstract public class BaseAI extends Thread{

    public BaseAI(String myThread){
        super(myThread);
    }

    @Override
    public void run() {}
}
