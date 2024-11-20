package thread.cas.spinlock;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

/**
 * synchronized, lock 없이 CAS로 락을 구현
 * 스레드가 락을 흭득하면 lock의 값이 true, 반납하면 false
 * 락을 흭득하면 while문을 탈출
 * 흭득하지 못하면 락을 흭득할 때까지 while문을 반복
 */
public class SpinLockBad {
    // lock을 가져가면 true, 반납하면 false
    private volatile boolean lock = false;
    public void lock() {
        log("락 흭득 시도");

        // 한 스레드만 락을 흭득하고 나머지는 락 흭득을 대기
        while (true) {
            if (!lock) { // 1. 락 사용 여부 확인
                sleep(100);
                lock = true; // 락의 값 변경
                break;
            }
            else{
                // 락을 흭득할 때까지 스핀 대기(바쁜 대기) 한다
                // 스레드를 RUNNABLE 상태로 계속
                log("락 흭득 실패 - 스핀 대기");
            }
        }
        log("락 흭득 완료");
    }

    public void unlock() {
        lock = false; // 이거는 원자적 연산이기 때문에 냅둬도 됨.
        log("락 반납 완료");
    }
}
