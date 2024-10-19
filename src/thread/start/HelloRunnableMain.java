package thread.start;

public class HelloRunnableMain {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ": main() start");

        HelloRunnable runnable = new HelloRunnable();

        // Thread는 Runnable을 받습니다.
        Thread thread = new Thread(runnable); // 작업을 넣어주기 가능
        thread.start();

        System.out.println(Thread.currentThread().getName() + ": main() end");

    }
}
