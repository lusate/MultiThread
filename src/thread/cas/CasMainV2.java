package thread.cas;

import java.util.concurrent.atomic.AtomicInteger;

import static util.MyLogger.log;

public class CasMainV2 {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println("start value = " + atomicInteger.get());

        // incrementAndGet 구현
        int resultValue1 = incrementAndGet(atomicInteger);

        int result = atomicInteger.incrementAndGet();
        System.out.println("result = " + result);

        int result2 = atomicInteger.incrementAndGet();
        System.out.println("result = " + result2);
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
