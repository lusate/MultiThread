package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV1 {
    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        thread.start();

        sleep(4000);
        log("작업 중단 지시 flag = false");
        task.flag = false;
    }

    /**
     * while 문으로 계쏙 "작업 중" 인데 이것을 종료시키는 방법
     * 특정 스레드의 작업을 중단하느 가장 쉬운 방법은 변수를 사용하는 것
     */
    static class MyTask implements Runnable {
        volatile boolean flag = true;
        @Override
        public void run() {
            while (flag) {
                log("작업 중");
                sleep(3000);
            }
            log("자원 정리");
            log("자원 종료");
        }
    }
}
