package thread.control.test;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class JoinTest2Main {
    /**
     * 전체 실행 시간을 3초 앞당기기
     */
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new JoinTest1Main.MyTask(), "t1");
        Thread t2 = new Thread(new JoinTest1Main.MyTask(), "t2");
        Thread t3 = new Thread(new JoinTest1Main.MyTask(), "t3");

        // 답 맞음 -> 각각 3초 걸리고 join으로 대기를 하기 때문에 총 9초가 걸림
        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("모든 스레드 실행 완료");
    }

    static class MyTask implements Runnable {

        @Override
        public void run() {
            for (int i = 1; i <= 3; i++) {
                log(i);
                sleep(1000);
            }
        }
    }
}