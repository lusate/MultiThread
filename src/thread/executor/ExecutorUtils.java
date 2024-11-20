package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

import static util.MyLogger.log;

/**
 * 직접 생성해 두지 말라고 추상으로 함
 * 스레드 풀에 스레드가 몇 개 있고, 스레드의 상태는 어떻고 등을 확인
 */
public abstract class ExecutorUtils {
    public static void printState(ExecutorService executorService) {

        // ThreadPoolExecutor 로 캐스팅한 이유는 ExecutorService에는 getPoolSize() 같은 메서드가 없기 때문에
        if (executorService instanceof ThreadPoolExecutor poolExecutor) {
            int poolSize = poolExecutor.getPoolSize(); // 스레드 개수
            int activeCount = poolExecutor.getActiveCount();// 실제 작업을 하고 있는 스레드가 몇개인지

            // pool에 작업을 던지면 스레드가 바로 실행하는 게 아니라 큐에 담김
            int queueSize = poolExecutor.getQueue().size();
            long completedTaskCount = poolExecutor.getCompletedTaskCount();// 완료된 작업 카운트

            log("[pool = " + poolSize + ", active = " + activeCount +
                    ", queueTasks = " + queueSize + ", completedTaskCount = " + completedTaskCount + "]");

        }
        else{ // ExecutorService 가 아닌 다른 구현체가 들어온다면
            log(executorService);
        }
    }
}
