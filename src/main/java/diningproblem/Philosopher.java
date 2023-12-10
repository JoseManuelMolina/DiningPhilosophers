package diningproblem;

public class Philosopher implements Runnable{

//    The forks on either side of this Philosopher
    private Object leftFork;
    private Object rightFork;

    public Philosopher(Object leftFork, Object rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    /*
    * Method that instructs a Philosophe to perform an action - eat, think, or acquire forks in preparation for eating
    */
    private void doAction(String action) throws InterruptedException{
        System.out.println(
                Thread.currentThread().getName() + " " + action);
        Thread.sleep((int) (Math.random() * 100));
    }

    @Override
    public void run() {
        try{
            while(true){

                // Thinking
                doAction(System.nanoTime() + ": Thinking");
                synchronized (leftFork){
                    doAction(
                            System.nanoTime()
                                + ": Picked left fork");
                    synchronized (rightFork){
                        doAction(
                                System.nanoTime()
                                    + ": Picked right fork - eating");

                        doAction(
                                System.nanoTime()
                                    + ": Put down right fork");
                    }

                    doAction(
                            System.nanoTime()
                                + ": Put down left fork. Back to thinking");
                }
            }
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

}
