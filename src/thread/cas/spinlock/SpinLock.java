package thread.cas.spinlock;

import java.util.concurrent.atomic.AtomicBoolean;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

/**
 * synchronized, lock 없이 CAS로 락을 구현
 * 스레드가 락을 흭득하면 lock의 값이 true, 반납하면 false
 * 락을 흭득하면 while문을 탈출
 * 흭득하지 못하면 락을 흭득할 때까지 while문을 반복
 */
public class SpinLock {
    // lock을 가져가면 true, 반납하면 false
    private final AtomicBoolean lock = new AtomicBoolean(false);

    public void lock() {
        log("락 흭득 시도");

        // 락이 false로 사용하고 있지 않다면 true로 바꿀 거임
        while (!lock.compareAndSet(false, true)) {
            log("락 흭득 실패 - 스핀 대기");
        }
        log("락 흭득 완료");
    }

    public void unlock() {
        lock.set(false);
        log("락 반납 완료");
    }
}
