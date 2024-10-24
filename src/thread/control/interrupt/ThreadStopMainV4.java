package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV4 {
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

        @Override
        public void run() {
            log("처음 상태 = " + Thread.currentThread().isInterrupted()); // false
            while(!Thread.interrupted()) { // 인터럽트 상태만 확인할 뿐 변경 O
                log("MyTask 작업 중");
            }
            // 예외가 터지지 않으므로 인터럽트 상태가 true
            log("work 스레드 인터럽트 상태2 = " + Thread.currentThread().isInterrupted()); // false

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
