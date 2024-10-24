package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV3 {
    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        thread.start();

        sleep(100); // 0.1초 뒤 바로 작업 중단 지시하면 인터럽트 걸림
        log("작업 중단 지시 thread.interrupt");
        thread.interrupt();
        log("work 스레드 인터럽트 상태1 = " + thread.isInterrupted()); // true
    }

    static class MyTask implements Runnable {

        /**
         * 예외가 터져서 작동하게 됐을 때 왜 인터럽트 상태를 자바가 내부적으로 true인 것을 다시 false로 돌리는 이유
         * -> true 상태로 있다가 인터럽트 예외가 걸리는 메서드를 만나는 순간 인터럽트 터져서 catch 코드로 갑니다.
         * 기대하는 건 자원 정리 중에는 인터럽트가 발생하지 않기를 기대했습니다.
         */
        @Override
        public void run() {
            log("처음 상태 = " + Thread.currentThread().isInterrupted()); // false
            while(!Thread.currentThread().isInterrupted()) { // 인터럽트 상태만 확인할 뿐 변경을 하지는 않음
                log("MyTask 작업 중");
            }
            // 예외가 터지지 않으므로 인터럽트 상태가 true
            log("work 스레드 인터럽트 상태2 = " + Thread.currentThread().isInterrupted()); // true

            try{
                log("자원 정리");
                Thread.sleep(1000); // 인터럽트 예외 발생
                log("자원 종료");
            } catch (InterruptedException e) {
                log("자원 정리 실패 - 자원 정리 중 인터럽트 발생");
                log("work 스레드 인터럽트 상태 = " + Thread.currentThread().isInterrupted()); // false
            }

            log("작업 종료");
        }
    }
}
