package thread.control;

import thread.start.HelloRunnable;

import static util.MyLogger.log;

public class ThreadInfoMain {
    public static void main(String[] args) {
        // main 스레드 가져오기
        Thread mainThread = Thread.currentThread(); // 메인 스레드가 실행

        log("mainThread = " + mainThread);
        log("threadId = " + mainThread.threadId());
        log("getName = " + mainThread.getName());
        // 우선순위가 높을수록 조금 더 많이 실행됩니다. 항상 그런 것은 아니고 OS마다 다를 수 있음
        // 우선순위는 기본이 5.
        log("getPriority = " + mainThread.getPriority());
        log("getThreadGroup = " + mainThread.getThreadGroup());
        // 실행했으므로 RUNNABLE 상태
        log("getState = " + mainThread.getState());


        // new Thread는 메인 스레드에 의해 생성됐으므로 부모는 메인 스레드다
        Thread myThread = new Thread(new HelloRunnable(), "myThread");

        log("mainThread = " + myThread);
        log("threadId = " + myThread.threadId());
        log("getName = " + myThread.getName());
        // 우선순위가 높을수록 조금 더 많이 실행됩니다. 항상 그런 것은 아니고 OS마다 다를 수 있음
        // 우선순위는 기본이 5.
        log("getPriority = " + myThread.getPriority());
        log("getThreadGroup = " + myThread.getThreadGroup());
        // 스레드 생성하고 실행을 아직 하지 않았다면 NEW인 상태
        log("getState = " + myThread.getState());
    }
}