import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Пять безмолвных философов сидят вокруг круглого стола, перед каждым философом стоит тарелка спагетти.
        // Вилки лежат на столе между каждой парой ближайших философов.
        // Каждый философ может либо есть, либо размышлять.
        // Философ может есть только тогда, когда держит две вилки — взятую справа и слева.
        // Философ не может есть два раза подряд, не прервавшись на размышления (можно не учитывать).
        // Можно брать только две вилки одновременно.

        // Описать в виде кода такую ситуацию. Каждый философ должен поесть три раза

        Fork fork1 = new Fork();
        Fork fork2 = new Fork();
        Fork fork3 = new Fork();
        Fork fork4 = new Fork();
        Fork fork5 = new Fork();

        CountDownLatch countDownLatch = new CountDownLatch(5);

        List<Philosopher> table = new ArrayList<>();

        table.add(new Philosopher("Максим Поддубный", fork1, fork2, countDownLatch));
        table.add(new Philosopher("Илья Муромец", fork2, fork3, countDownLatch));
        table.add(new Philosopher("Алёша Попович", fork3, fork4, countDownLatch));
        table.add(new Philosopher("Никита Сергеевич", fork4, fork5, countDownLatch));
        table.add(new Philosopher("Вася Пупкин", fork5, fork1, countDownLatch));

        System.out.println("Начинаем трапезу");

        for (Philosopher philosopher : table) {
            philosopher.start();
        }

        while (countDownLatch.getCount() != 0) {
            Thread.sleep(1000);
        }

        System.out.println("После вкусного обеда по закону Архимеда, полагается поспать");
    }
}
