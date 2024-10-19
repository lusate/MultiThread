package thread.start;

import static util.MyLogger.log;

public class InnerRunnableMainV1 {
    public static void main(String[] args) {
        log("main() start");

        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.start();

        log("main() end");
    }

    // 중첩 클래스 - 정적 중첩 클래스
    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            log(Thread.currentThread().getName() + ": run()");
            log("run()");

        }
    }
}
