package thread.start;

import static util.MyLogger.log;

/**
 * 중첩 클래스 사용
 * 익명 클래스
 */
public class InnerRunnableMainV2 {
    public static void main(String[] args) {
        log("main() start");

        // Runnable 인터페이스를 바로 구현해버리기

        // 더 간단하게 하고 싶다면 안에 넣어버리기
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                log("run() start");
            }
        });
        thread.start();

        log("main() end");
    }
}
