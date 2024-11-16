package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

/**
 * 밑에 구현체들을 여러 스레드에서 호출할 수 있기 때문에 synchronized를 걸었음
 * 한 번에 한 쓰레드만 호출할 수 있기 때문에 동기화에 대해서는 문제X
 * 누군가 put을 호출하고 있다면 take 호출 시 put 호출이 끝날 때까지 기다려야  함 (같은 인스턴스 lock을 기다려서)
 */
public class BoundedQueueV2 implements BoundedQueue{
    private final Queue<String> q = new ArrayDeque<>();
    private final int max;

    public BoundedQueueV2(int max) {
        this.max = max;
    }

    @Override
    public synchronized void put(String data) {
        while (q.size() == max) {
            log("[put] 큐가 가득 차서 대기 : " + data);
            sleep(1000); // 로그 너무 자주 출력될 수 있어서 좀 길게 함
        }
        q.offer(data);
    }

    @Override
    public synchronized String take() {
        while (q.isEmpty()) {
            // 그냥 대기하고 1초 기다리는 상태
            log("[take] 큐에 데이터가 없음, 소비자 대기");
            sleep(1000);
        }
        return q.poll();
    }

    // q에 들어있는 데이터를 보여주기 위함
    @Override
    public String toString() {
        return q.toString();
    }
}
