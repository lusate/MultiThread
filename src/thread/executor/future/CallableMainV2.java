package thread.executor.future;

import java.util.Random;
import java.util.concurrent.*;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

/**
 * `submit()`은 작업의 결과를 반환하는 대신에 결과를 나중에 받을 수 있는 `Future`라는 객체를 대신 제공합니다.
 * `Future` 객체는 전달한 작업의 미래 결과를 담고 있다고 생각하면 됩니다.
 * Future 객체가 어떤 건지 알기 위해 로그만 추가
 */
public class CallableMainV2 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // 최대 1개의 스레드만 사용할 수 있는 스레드 풀을 생성.
        ExecutorService es = Executors.newFixedThreadPool(1);
        log("submit() 호출");
        Future<Integer> future = es.submit(new MyCallable());// Callable을 넘기려면 submit() 사용
        log("future 즉시 반환, future = " + future);

        log("future.get() [블로킹] 메서드 호출 시작 -> main 스레드 WAITING");
        Integer result = future.get(); // 작업이 끝나지 않으면 결과가 없음. -> 결과가 나올때까지 기다림.
        log("future.get() [블로킹] 메서드 호출 시작 -> main 스레드 WAITING");
        log("result value = " + result);
        log("future 완료, future = " + future);
        es.close();
    }

    static class MyCallable implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            log("Callable 시작");
            sleep(2000);
            int value = new Random().nextInt(10);

            log("result value = " + value);
            log("Callable 완료");
            return value;
        }
    }
}
