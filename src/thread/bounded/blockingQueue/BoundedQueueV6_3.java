package thread.bounded.blockingQueue;

import thread.bounded.BoundedQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import static util.MyLogger.log;

/**
 * BoundedQueueV6 4가지 중 "시간 대기" 를 선택한 예제
 * 성공하면 offer가 가득 차서 스레드가 대기해야 한다면 지정한 시간까지 대기하다가 지나면 false 반환
 * 버퍼에 데이터가 없어서 스레드가 대기해야 하는 상황이라면 지정한 시간까지 대기, 대기하다 시간 지나면 null
 * 그래서 버퍼가 가득 차면 1mm 만큼만 기다리다가 false를 반환하고 버퍼가 빈 경우 2초만큼 대기한 다음에 null을 반환
 */
public class BoundedQueueV6_3 implements BoundedQueue {
    private final BlockingQueue<String> q;

    public BoundedQueueV6_3(int max) {
        q = new ArrayBlockingQueue<>(max);
    }

    @Override
    public void put(String data) {
        // 시간 대기
        try{
            boolean result = q.offer(data, 1, TimeUnit.NANOSECONDS);
            log("저장 시도 결과 = " + result);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public String take() {
        try {
            return q.poll(2, TimeUnit.SECONDS);
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
