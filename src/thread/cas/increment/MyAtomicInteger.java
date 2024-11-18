package thread.cas.increment;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicInteger 클래스 안에 동시성을 위한, 멀티스레드를 위한 코드들이 안에 구현되어 있습니다
 */
public class MyAtomicInteger implements IncrementInteger{
    AtomicInteger atomicInteger = new AtomicInteger(0); // 초기값은 0

    @Override
    public void increment() {
        atomicInteger.incrementAndGet(); // 값을 증가하고 반환하는 메서드
    }

    @Override
    public int get() {
        return atomicInteger.get(); // 조회하는 메서드
    }
}
