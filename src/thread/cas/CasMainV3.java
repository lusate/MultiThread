package thread.cas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static util.MyLogger.log;

public class CasMainV3 {
    private static final int THREAD_COUNT = 2;

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println("start value = " + atomicInteger.get());

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                incrementAndGet(atomicInteger);
            }
        };

        // 스레드 여러 개 만들고 기다리게 하기
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(runnable);
            threads.add(thread);

            thread.start();
        }

        for(Thread thread : threads){
            thread.join();
        }

        int result = atomicInteger.get();
        System.out.println("결과 : " + atomicInteger.getClass().getSimpleName() + " resultValue : " + result);

    }

    private static int incrementAndGet(AtomicInteger atomicInteger) {
        int getValue;
        boolean result;

        do {
            getValue = atomicInteger.get(); // 현재 atomicInteger 값을 읽기
            log("getValue : " + getValue);
            // 읽은 값과 업데이트하는 시점의 값과 같으면 하나 증가시키기 -> CAS 연산
            result = atomicInteger.compareAndSet(getValue, getValue + 1);
            log("result : " + result);
        } while (!result);

        return getValue + 1;
    }
}
