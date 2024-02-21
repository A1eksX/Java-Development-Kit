import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Philosopher extends Thread {
    // Пять безмолвных философов сидят вокруг круглого стола, перед каждым философом стоит тарелка спагетти.
    // Вилки лежат на столе между каждой парой ближайших философов.
    // Каждый философ может либо есть, либо размышлять.
    // Философ может есть только тогда, когда держит две вилки — взятую справа и слева.
    // Философ не может есть два раза подряд, не прервавшись на размышления (можно не учитывать).
    // Можно брать только две вилки одновременно.

    // Описать в виде кода такую ситуацию. Каждый философ должен поесть три раза
    
    private int MAX_COUNT_EAT = 3;
    private String name;
    private Fork leftFork;
    private Fork rigthFork;
    private Random rnd = new Random();
    private int countEat = 0;
    private boolean hungry = true;
    private CountDownLatch countDownLatch;

    public Philosopher(String name, Fork leftFork, Fork rigthFork, CountDownLatch countDownLatch) {
        this.name = name;
        this.leftFork = leftFork;
        this.rigthFork = rigthFork;
        this.countDownLatch = countDownLatch;
        rigthFork.setUsed(false);
        leftFork.setUsed(false);
    }

    @Override
    public void run(){
        while (hungry) {
            try {
                eating();
                think();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void think(){
        while (leftFork.isUsed() && rigthFork.isUsed()){        // пока вилки true
//            System.out.println(this + " размышляет");       // Thread.currentThread().getName()
            toString();
        }
    }

    public synchronized void eating() throws InterruptedException {
        if (countEat >= MAX_COUNT_EAT){
                hungry = false;
                countDownLatch.countDown();
            } else {
            stateFork();
            System.out.println(name + " - ест");
            Thread.sleep(rnd.nextInt(1000, 2000));
            leftFork.setUsed(false);    // true - вилки заняты, false - вилки свободны
            rigthFork.setUsed(false);
            System.out.println(name + " - закончил есть и положил вилки на стол");
            countEat++;
        }

    }

    public void stateFork() throws InterruptedException {
        while (leftFork.isUsed()){
            Thread.sleep(100);
        }
        leftFork.setUsed(true);
        while (rigthFork.isUsed()){
            Thread.sleep(100);
        }
        rigthFork.setUsed(true);
    }

    @Override
    public String toString() {
        return String.format("%s - размышляет", name);
    }
}
