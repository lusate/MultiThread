package thread.bounded.blockingQueue;

import thread.bounded.BoundedQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static util.MyLogger.log;

/**
 * BoundedQueueV6 4가지 중 "대기하지 않고 즉시 반환" 를 선택한 예제
 */
public class BoundedQueueV6_2 implements BoundedQueue {
    private final BlockingQueue<String> q;

    public BoundedQueueV6_2(int max) {
        q = new ArrayBlockingQueue<>(max);
    }

    @Override
    public void put(String data) {
        // 바로 결과를 반환
        boolean result = q.offer(data); // 버퍼가 가득 차서 실패하면 false를 반환
        log("저장 시도 결과 = " + result);
    }

    @Override
    public String take() {
        return q.poll(); // 버퍼에 데이터가 없으면 즉시 null을 반환
    }

    // q에 들어있는 데이터를 보여주기 위함
    @Override
    public String toString() {
        return q.toString();
    }
}
