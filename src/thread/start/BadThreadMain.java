package thread.start;

public class BadThreadMain extends Thread {
    public static void main(String[] args) {
        Thread thread = Thread.currentThread();

        // 자바를 처음 시작할 때 메인이라는 스레드가 떠서 이 메인 메서드를 실행
        // 자바가 만들어주는 기본 스레드
        System.out.println(thread.getName() + ": main Thread start");

        HelloThread helloThread = new HelloThread();
        System.out.println(Thread.currentThread().getName() + ": start() 호출 전");
        /**
         * 절대로 run을 호출하면 안됩니다.
         * 결과가 마지막에 Thread-0: run() 이 출력됩니다.
         */
        helloThread.run(); // start()는 쓰레드를 실행하는 메서드

        System.out.println(Thread.currentThread().getName() + ": start() 호출 후");

        System.out.println(thread.getName() + ": main Thread end");
    }
}
