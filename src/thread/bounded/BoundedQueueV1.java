package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;

import static util.MyLogger.log;

/**
 * 밑에 구현체들을 여러 스레드에서 호출할 수 있기 때문에 synchronized를 걸었음
 * 한 번에 한 쓰레드만 호출할 수 있기 때문에 동기화에 대해서는 문제X
 * 누군가 put을 호출하고 있다면 take 호출 시 put 호출이 끝날 때까지 기다려야  함 (같은 인스턴스 lock을 기다려서)
 */
public class BoundedQueueV1 implements BoundedQueue{
    private final Queue<String> q = new ArrayDeque<>();
    private final int max;

    public BoundedQueueV1(int max) {
        this.max = max;
    }

    @Override
    public synchronized void put(String data) {
        if (q.size() == max) {
            log("[put] 큐가 가득 차서 버림 : " + data);
            return;
        }
        q.offer(data);
    }

    @Override
    public synchronized String take() {
        if (q.isEmpty()) {
            return null;
        }
        return q.poll();
    }

    // q에 들어있는 데이터를 보여주기 위함
    @Override
    public String toString() {
        return q.toString();
    }
}
