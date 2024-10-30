package thread.sync.test;

/**
 * 동시성 문제 때문에 t1 스레드와 t2 스레드가 같이 카운트를 진행
 * 현재 카운트가 공유 자원인 상태고 각 스레드 당 increment()를 만번 호출하는데
 * t1이 카운트해서 1 -> 2 가 될 때 t2가 2를 읽지 않고 1을 읽은 상태로 + 1 해서 2가 되는 경우가 다수 발생한 것입니다.
 *
 */
public class SyncTest1BadMain {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Runnable task = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    counter.increment();
                }
            }
        };

        Thread t1 = new Thread(task); // 10000
        Thread t2 = new Thread(task); // 20000

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("counter.getCount() = " + counter.getCount());

    }

    static class Counter {
        volatile private int count = 0;

        public void increment() {
            count += 1;
        }

        public int getCount() {
            return count;
        }
    }
}
