package thread.bounded.blockingQueue;

import thread.bounded.BoundedQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import static util.MyLogger.log;

/**
 * BoundedQueueV6 4가지 중 "예외" 를 선택한 예제
 */
public class BoundedQueueV6_4 implements BoundedQueue {
    private final BlockingQueue<String> q;

    public BoundedQueueV6_4(int max) {
        q = new ArrayBlockingQueue<>(max);
    }

    @Override
    public void put(String data) {
        q.add(data); // 버퍼 가득 차면 IllegalStateException 예외 터뜨림
    }

    @Override
    public String take() {
        return q.remove(); // 버퍼에 데이터가 없으면 NoSuchElementException 예외
    }

    // q에 들어있는 데이터를 보여주기 위함
    @Override
    public String toString() {
        return q.toString();
    }
}
