package thread.volatile1;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class VolatileFlagMain {
    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        Thread thread = new Thread(myTask);
        log("runFlag = " + myTask.runFlag);
        thread.start();

        sleep(1000); // 메인 스레드 대기
        log("runFlag를 false로 변경 시도");
        myTask.runFlag = false;

        log("runFlag = " + myTask.runFlag);
        log("main 종료");
    }

    static class MyTask implements Runnable {
        boolean runFlag = true;
//        volatile boolean runFlag = true;

        /**
         * volatile 사용하면 메인 메모리에 직접 접근, 사용 안 하면 캐시 메모리에 접근
         * 각 스레드 안에 CPU 코어와 캐시 메모리가 있고 메인 메모리는 따로 존재. 캐시 메모리는 메인 메모리와 가장 가까이 존재
         */
        @Override
        public void run() {
            log("task 시작");
            while (runFlag) {
                /**
                 * runFlag가 false면 탈출
                 * volatile 하지 않으면 이 while을 빠져나오지 못함
                 */
            }

            // volatile 하지 않은 경우 false인데도 이 task 종료가 출력되지 않음.
            // 애플리케이션도 종료되지 않음.
            log("task 종료");
        }
    }
}
