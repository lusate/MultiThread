package thread.control.yield;

import static util.ThreadUtils.sleep;

public class YieldMain {
    static final int THREAD_COUNT = 1000;

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(new MyRunnable());
            thread.start();

        }
    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " - " + i);

                // sleep(1) 을 통해서 특정 스레드르 잠시 쉬게 함
//                sleep(1);

                // 다른 스레드에 실행을 양보
                // yield는 스레드의 상태가 바뀌지 않고 RUNNABLE로 유지하는데 CPU에서 실행 하는 거를 빠져서 스케줄링 큐에 다시 들어갑니다
                // yield라고 하면 내가 CPU에서 지금 실행해야 하는데 그냥 다른 스레드를 실행하고 스케줄링 대기 큐로 들어갑니다.
                // 그래서 스레드의 상태는 바뀌지 않고 RUNNABLE 상태로 있습니다.
                Thread.yield();
            }
        }
    }
}
