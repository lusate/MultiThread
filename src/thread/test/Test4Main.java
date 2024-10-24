package thread.test;

import static util.MyLogger.log;

public class Test4Main {
    public static void main(String[] args) {
        PrintWorker a = new PrintWorker("A", 1000);
        PrintWorker b = new PrintWorker("B", 2000);

        Thread threadA = new Thread(a, "Thread-A");
        Thread threadB = new Thread(b, "Thread-B");

        threadA.start();
        threadB.start();
    }

    /**
     * 하나의 Runnable에서 2개의 스레드가 다르게 수행 가능
     * 객체를 생성할 때 private 으로 어떤 내용을 출력하고 얼마나 쉴 것인지 처럼 넣어줘서 하나의 클래스로 해결 가능하다
     */
    static class PrintWorker implements Runnable {
        private String content;
        private int sleepMs;

        public PrintWorker(String content, int sleepMs) {
            this.content = content;
            this.sleepMs = sleepMs;
        }

        @Override
        public void run() {
            while (true) {
                log(content);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
