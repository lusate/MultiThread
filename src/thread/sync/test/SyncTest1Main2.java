package thread.sync.test;

import static util.MyLogger.log;

/**
 * 동시성 문제 때문에 t1 스레드와 t2 스레드가 같이 카운트를 진행
 * 현재 카운트가 공유 자원인 상태고 각 스레드 당 increment()를 만번 호출하는데
 * t1이 카운트해서 1 -> 2 가 될 때 t2가 2를 읽지 않고 1을 읽은 상태로 + 1 해서 2가 되는 경우가 다수 발생한 것입니다.
 *
 */
public class SyncTest1Main2 {
    public static void main(String[] args) throws InterruptedException {
        MyCounter myCounter = new MyCounter();
        Runnable task = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    myCounter.count();
                }
            }
        };

        Thread t1 = new Thread(task, "Thread-1"); // 10000
        Thread t2 = new Thread(task, "Thread-2"); // 20000

        t1.start();
        t2.start();
        t1.join();
        t2.join();

    }

    /**
     * 지역 변수이고 두 스레드 다 count()를 호출하는데 스택 영역은 각각의 스레드가 가지는 완전 별도의 메모리 공간입니다.
     * 다른 스레드와 절대 공유하지 않기 때문에 지역 변수는 스레드의 개별 저장 공간인 스택 영역에 생성되는 것입니다.
     * 그래서 localValue로 같은 이름의 지역 변수이지만 서로 완전 다른 변수입니다.
     * 꼭 기억!!!! -> 지역 변수는 스레드의 개별 저장 공간인 스택 영역에 생성됩니다.
     */
    static class MyCounter {
        public void count() {
            int localValue = 0; // 자기 고유의 스택 영역을 생성
            for(int i=0 ;i<1000; i++){
                localValue++;
            }

            log("결과 : " + localValue);
        }
    }
}
