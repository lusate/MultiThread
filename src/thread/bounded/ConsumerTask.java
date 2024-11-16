package thread.bounded;

import static util.MyLogger.log;

/**
 * 소비자 역할
 */
public class ConsumerTask implements Runnable{
    private BoundedQueue q;

    public ConsumerTask(BoundedQueue q) {
        this.q = q;
    }

    @Override
    public void run() {
        // q에서 꺼내야함
        log("[소비 시도]     ? <- " + q);
        String data = q.take();
        log("[소비 완료] " + data + " <- " + q);
    }
}
