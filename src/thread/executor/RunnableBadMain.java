package thread.executor;

import java.util.Random;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

/**
 * Runnable 때문에 join으로 기다리고 result라는 멤버 변수를 통해 결과를 받아야 함.
 * main 스레드가 별도의 스레드에서 만든 무작위 값을 받아오려면 Thread-1 스레드가 종료될 때까지 기다려야 함.
 * 그래서 main스레드는 join()을 호츨해서 대기
 */
public class RunnableBadMain {
    public static void main(String[] args) throws InterruptedException {
        MyRunnable task = new MyRunnable();
        Thread thread = new Thread(task, "Thread-1");
        thread.start();
        thread.join();

        int result = task.value;
        log("result value = " + result);
    }

    static class MyRunnable implements Runnable {
        int value;

        @Override
        public void run() {
            log("Runnable 시작");
            sleep(2000);
            value = new Random().nextInt(10); // 0~9까지 랜덤
            log("create value = " + value);

            log("Runnable 완료");
        }
    }
}
