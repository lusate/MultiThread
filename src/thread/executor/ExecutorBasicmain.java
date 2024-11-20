package thread.executor;

import java.util.concurrent.*;

import static thread.executor.ExecutorUtils.printState;
import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

/**
 * LinkedBlockingQueue 는 스레드가 작업에 대해 대기하기 위한 큐
 * 무한으로 넣는 것이 가능. 데이터 꺼낼 때는 없으면 기다림. 사이즈가 없기 때문에 무제한 기다림
 */
public class ExecutorBasicmain {
    public static void main(String[] args) {
        ExecutorService es = new ThreadPoolExecutor(2, 2, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        log("-- 초기 상태 --");
        // 작업이 하나라도 와야 스레드를 생성함
        printState(es);

        es.execute(new RunnableTask("taskA"));
        es.execute(new RunnableTask("taskB"));
        es.execute(new RunnableTask("taskC"));
        es.execute(new RunnableTask("taskD"));

        log("-- 작업 수행 중 --");
        printState(es);

        sleep(3000);
        log("-- 작업 수행 완료 --");
        printState(es);

        es.close();
        log("-- shutdonw 완료 --");
        printState(es);
    }
}
