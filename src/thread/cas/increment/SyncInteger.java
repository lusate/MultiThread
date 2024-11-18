package thread.cas.increment;

/**
 * synchronized를 사용하면 메모리 가시성 문제가 해결됩니다.
 * lock, Reentrant lock도 마찬가지.
 * 한 번에 하나의 스레드만 이 연산을 수행. 다른 스레드는 lock 기다리면서 대기
 */
public class SyncInteger implements IncrementInteger {
    private int value;

    @Override
    public synchronized void increment() {
        value++;
    }

    @Override
    public synchronized int get() {
        return value;
    }
}
