package thread.bounded;

import static util.MyLogger.log;

/**
 * 생산자 역할
 */
public class ProducerTask implements Runnable {
    private BoundedQueue q;
    private final String request; // 누가 요청했는지

    public ProducerTask(BoundedQueue q, String request) {
        this.q = q;
        this.request = request;
    }

    @Override
    public void run() {
        log("[생산 시도] " + request + " -> " + q);
        q.put(request); // q에 삽입
        log("[생산 완료] " + request + " -> " + q);
    }
}
