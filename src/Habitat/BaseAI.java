package Habitat;

abstract public class BaseAI extends Thread{
    public static boolean movement = false;

    public BaseAI(String myThread){
        super(myThread);
    }

    synchronized public void continueThread(){
        notify();
    }

    @Override
    public void run() {}
}
