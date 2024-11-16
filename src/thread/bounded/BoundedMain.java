package thread.bounded;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

/**
 * 생산자 3개 소비자 3개 실행하는 코드
 * 상산자가 먼저 실행되는 경우와 소비자가 먼저 실행되는 예제를 만듦
 */
public class BoundedMain {
    public static void main(String[] args) {
        // 1. BoundedQueue 선택
        BoundedQueue queue = new BoundedQueueV5(2);// 버퍼에 2개까지만 담을 수 있게 함

        // 2. 생산자, 소비자 실행 순서 선택, 반드시 하나만 선택!!
        producerFirst(queue); // 생산자 먼저 실행

//        consumerFirst(queue); // 소비자 먼저 실행
    }
    private static void producerFirst(BoundedQueue queue){
        log("== [생산자 먼저 실행 시작],  " + queue.getClass().getSimpleName() + " ==");
        List<Thread> threads = new ArrayList<>(); // 스레드 3개 보관

        startProducer(queue, threads);
        printAllState(queue, threads);
        startConsumer(queue, threads);

        // 총 6개의 스레드가 담긴 상태
        printAllState(queue, threads);
        log("== [생산자 먼저 실행 종료],  " + queue.getClass().getSimpleName() + " ==");
    }

    private static void consumerFirst(BoundedQueue queue){
        log("== [소비자 먼저 실행 시작],  " + queue.getClass().getSimpleName() + " ==");

        List<Thread> threads = new ArrayList<>(); // 스레드 3개 보관

        startConsumer(queue, threads);
        printAllState(queue, threads);
        startProducer(queue, threads);

        // 총 6개의 스레드가 담긴 상태
        printAllState(queue, threads);

        log("== [소비자 먼저 실행 종료],  " + queue.getClass().getSimpleName() + " ==");
    }

    private static void startProducer(BoundedQueue queue, List<Thread> threads){
        System.out.println();
        log("생산자 시작");

        // 생산자 3개 만들기
        for (int i = 1; i <= 3; i++) {
            Thread producer = new Thread(new ProducerTask(queue, "data" + i), "producer" + i);
            threads.add(producer); // 상태 확인을 위함

            producer.start();
            sleep(100); // 동시에 실행이 되버리면 순서가 뒤죽박죽 되기 때문에 순서대로 하기 위해 sleep을 함 (하지 않아도 됨)
        }
    }

    // List에 담긴 스레드들을 출력하기 위
    private static void printAllState(BoundedQueue queue, List<Thread> threads){
        System.out.println();
        log("현재 상태 출력, 큐 데이터 : " + queue);

        for(Thread thread : threads){
            log("스레드 상태 : " + thread.getName() + " : " + thread.getState());
        }
    }

    private static void startConsumer(BoundedQueue queue, List<Thread> threads){
        System.out.println();
        log("소비자 시작");

        for (int i = 1; i <= 3; i++) {
            Thread consumer = new Thread(new ConsumerTask(queue), "consumer" + i);
            threads.add(consumer);
            consumer.start();
            sleep(100);
        }
    }
}
