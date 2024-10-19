package thread.start;

public class HelloThreadMain {
    public static void main(String[] args) {
        // 현재 실행하는 Thread 객체 반환
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
        helloThread.start(); // start()는 쓰레드를 실행하는 메서드

        System.out.println(Thread.currentThread().getName() + ": start() 호출 후");

        System.out.println(thread.getName() + ": main Thread end");
    }
}
/**
 * 수행할 때마다 Thread-0: run()의 위치가 달라질 수 있다.
 * CPU가 여러 스레드를 스케줄링 하는데 메인 스레드를 잠깐 실행했다가 Thread-0을 실행했다가 하므로
 * 이 타이밍이 언제가 될지 모릅니다. 그래서 스레드 실행 결과는 다를 수 있습니다.
 * OS가 CPU에 뭘 언제 수행하라는 게 그때그때마다 조금씩 달라질 수 있습니다.
 * HelloThread 객체를 생성한 다음에 start 메서드를 호출하면 자바는 스레드를 위한 별도의 스택 공간을 할당합니다
 * start()를 호출해야 스택 공간을 할당받고 스레드가 작동하여 Thread-0이 run 메서드를 호출해서 실행
 *
 */