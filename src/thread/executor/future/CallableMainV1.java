package thread.executor.future;

import java.util.Random;
import java.util.concurrent.*;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

/**
 * Runnable 때문에 join으로 기다리고 result라는 멤버 변수를 통해 결과를 받아야 함.
 * main 스레드가 별도의 스레드에서 만든 무작위 값을 받아오려면 Thread-1 스레드가 종료될 때까지 기다려야 함.
 * 그래서 main스레드는 join()을 호츨해서 대기
 */
public class CallableMainV1 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // 최대 1개의 스레드만 사용할 수 있는 스레드 풀을 생성.
        ExecutorService es = Executors.newFixedThreadPool(1);
        Future<Integer> future = es.submit(new MyCallable());// Callable을 넘기려면 submit() 사용

        Integer result = future.get();
        log("result value = " + result);
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
