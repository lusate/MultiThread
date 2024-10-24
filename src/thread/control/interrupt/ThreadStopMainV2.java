package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV2 {
    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        thread.start();

        sleep(4000);
        log("작업 중단 지시 thread.interrupt");
        thread.interrupt();
        log("work 스레드 인터럽트 상태1 = " + thread.isInterrupted());
    }

    static class MyTask implements Runnable {

        /**
         * 스레드에 인터럽트가 걸리면 예외가 터집니다
         * 대기하다가 갑자기 깨어나서 exception으로 넘어갑니다
         * 인터럽트 주는 방법은 thread.interrupt()
         */
        @Override
        public void run() {
            try{
                while (true) {
                    log("작업 중");
                    Thread.sleep(3000);
                }
            }catch (InterruptedException e){
                // 인터럽트가 걸렸는지 확인
                log("work 스레드 인터럽트 상태2 = " + Thread.currentThread().isInterrupted());
                log("interrupt message = " + e.getMessage());
                log("state = " + Thread.currentThread().getState());
            }
            log("자원 정리");
            log("자원 종료");
        }
    }
}
