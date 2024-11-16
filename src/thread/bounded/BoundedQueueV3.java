package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

/**
 * 밑에 구현체들을 여러 스레드에서 호출할 수 있기 때문에 synchronized를 걸었음
 * WAITING되면 락을 반납하고 생산자 깨어남
 */
public class BoundedQueueV3 implements BoundedQueue{
    private final Queue<String> q = new ArrayDeque<>();
    private final int max;

    public BoundedQueueV3(int max) {
        this.max = max;
    }

    @Override
    public synchronized void put(String data) {
        while (q.size() == max) {
            log("[put] 큐가 가득 차서 대기 : " + data);
            try {
                wait(); // RUNNABLE -> WAITING 되면서 락을 반납!!
                log("[put] 생산자 깨어남");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        // 생산자가 데이터를 생산했고 대기하는 소비자가 있다면 소비자를 깨워서 데이터가 있다고 알려야 합니다.
        // 즉 생산자가 데이터를 큐에 넣었으니 쉬고 있는 소비자가 있다면 가져가라고 알려주는 것.
        q.offer(data);
        log("[put] 생산자 데이터 저장, notify() 호출");
        notify(); // 대기 스레드, WAIT -> BLOCKED / 저렇게 쉬고 있는 소비자를 깨워주는 것.
    }

    @Override
    public synchronized String take() {
        while (q.isEmpty()) {
            // 그냥 대기하고 1초 기다리는 상태
            log("[take] 큐에 데이터가 없음, 소비자 대기");
            try {
                wait();
                log("[take] 소비자 깨어남");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        String data = q.poll();
        log("[take] 소비자 데이터 흭득, notify() 호출");

        // 큐가 가득 차서 더 넣기를 기다리는 스레드가 있다면 알려줘야 함
        notify(); // 대기 스레드, WAIT -> BLOCKED
        return data;
    }

    // q에 들어있는 데이터를 보여주기 위함
    @Override
    public String toString() {
        return q.toString();
    }
}

// 모든 객체는 모든 클래스의 자식