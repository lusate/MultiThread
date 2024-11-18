package thread.cas.increment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class IncrementThreadMain {
    public static final int THREAD_COUNT = 1000;

    public static void main(String[] args) throws InterruptedException {
        test(new BasicInteger());
        test(new VolatileInteger());
        test(new SyncInteger());
        test(new MyAtomicInteger());
    }

    private static void test(IncrementInteger incrementInteger) throws InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // 너무 빨리 실행하므로 다른 스레드와 동시 실행을 위해 잠깐 쉬기
                // 간단한 로직이라면 여러 스레드가 동시에 실행되는 상황을 확인하기 어렵기 때문에 sleep을 설정
                sleep(10);
                incrementInteger.increment();
            }
        };

        // 쓰레드 상태 출력을 위함
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(runnable);
            threads.add(thread);
            thread.start();
        }

        // 끝날 때까지 기다림
        for(Thread thread : threads) {
            thread.join();
        }

        int result = incrementInteger.get();
        log("result = " + incrementInteger.getClass().getSimpleName() + ": " + result);
    }
}
