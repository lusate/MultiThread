package thread.sync.lock;

import java.util.concurrent.locks.LockSupport;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class LockSupportMainV1 {
    public static void main(String[] args) {
        Thread t1 = new Thread(new ParkTest(), "Thread-1");
        t1.start();

        // 잠시 대기하여 Thread-1이 park 상태에 빠질 시간을 주기
        sleep(100);

        // park로 빠질 때 상태 확인하기
        log("Thread-1 state: " + t1.getState());

//        log("main -> unpark(Thread-1)");
//        LockSupport.unpark(t1); // t1에 unpark 사용

//        t1.interrupt(); // t1 interrupt 사용. 인터럽트 상태는 true가 되고 WAITING -> RUNNABLE이 됨

    }

    static class ParkTest implements Runnable {

        @Override
        public void run() {
            log("Park 시작");
//            LockSupport.park();

            LockSupport.parkNanos(2_000_000_000); // 2초 뒤에 깨어남
            log("park 종료, state : " + Thread.currentThread().getState());
            log("인터럽트 상태: " + Thread.currentThread().isInterrupted());
        }
    }
}
