package thread.start;

public class DaemonThreadMain {
    /**
     * 보조적인 일을 하는 스레드가 필요하다고 할 때 만들면 됨.
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ": main() start");

        // 데몬 생성
        DaemonThread daemonThread = new DaemonThread();
        daemonThread.setDaemon(true); // 데몬 스레드 여부. 기본적으론 false인 상태
        daemonThread.start();

        System.out.println(Thread.currentThread().getName() + ": main() end");
    }

    /**
     * 자바가 바로 끝나버리는 이유는 main에서 daemon 실행한 후에 10초 기다렸다가 run() end를 실행해야 하는데
     * main 스레드가 끝나버렸기 때문입니다
     * 자바 입장에서는 사용자 스레드 다 끝나버린 것으로 인식하여 남은 건 데몬스레드 밖에 없으므로 자바가 종료됩니다.
     * 위에서 setDaemon을 false라고 하면 데콘 스레드가 아닌 것이 되어 버리고 진짜 사용자 스레드가 되어 버려
     * 10초 후에 run() end가 출력됩니다.
     */
    static class DaemonThread extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ": run()");

            try {
                Thread.sleep(1000); // 10초간 실행
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println(Thread.currentThread().getName() + ": run() end");
        }
    }
}
