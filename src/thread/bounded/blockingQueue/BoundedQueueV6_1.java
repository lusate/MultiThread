package thread.bounded.blockingQueue;

import thread.bounded.BoundedQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * BoundedQueueV6 4가지 중 "대기" 를 선택한 예제
 */
public class BoundedQueueV6_1 implements BoundedQueue {
    private final BlockingQueue<String> q;

    public BoundedQueueV6_1(int max) {
        q = new ArrayBlockingQueue<>(max);
    }

    @Override
    public void put(String data) {
        try{
            q.put(data); // 인터럽트 예외를 터뜨림 -> 인터럽트하면 중간에 대기하다 빠져나옴

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String take() {
        try{
            return q.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // q에 들어있는 데이터를 보여주기 위함
    @Override
    public String toString() {
        return q.toString();
    }
}

// 모든 객체는 모든 클래스의 자식