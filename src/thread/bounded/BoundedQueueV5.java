package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static util.MyLogger.log;

/**
 * 밑에 구현체들을 여러 스레드에서 호출할 수 있기 때문에 synchronized를 걸었음
 * WAITING되면 락을 반납하고 생산자 깨어남
 */
public class BoundedQueueV5 implements BoundedQueue{
    private final Lock lock = new ReentrantLock();

    // wait, notify할 때 대기 집합에 들어가는 곳 (Rock Interface가 제공)
    private final Condition producerCond = lock.newCondition(); // 생산자 대기 집합
    private final Condition consumerCond = lock.newCondition(); // 소비자 대기 집합

    private final Queue<String> q = new ArrayDeque<>();
    private final int max;

    public BoundedQueueV5(int max) {
        this.max = max;
    }

    @Override
    public void put(String data) {
        lock.lock();
        try {
            while (q.size() == max) {
                log("[put] 큐가 가득 차서 대기 : " + data);
                try {
                    producerCond.await();
                    log("[put] 생산자 깨어남");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            q.offer(data);
            log("[put] 생산자 데이터 저장, consumerCond.signal() 호출");
            consumerCond.signal();

        } finally{
            lock.unlock();
        }

    }

    @Override
    public String take() {
        lock.lock();
        try{
            while (q.isEmpty()) {
                // 그냥 대기하고 1초 기다리는 상태
                log("[take] 큐에 데이터가 없음, 소비자 대기");
                try {
                    consumerCond.await();
                    log("[take] 소비자 깨어남");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            String data = q.poll();
            log("[take] 소비자 데이터 흭득, producerCond.signal() 호출");
            producerCond.signal();
            return data;

        } finally {
            lock.unlock();
        }
    }

    // q에 들어있는 데이터를 보여주기 위함
    @Override
    public String toString() {
        return q.toString();
    }
}

// 모든 객체는 모든 클래스의 자식