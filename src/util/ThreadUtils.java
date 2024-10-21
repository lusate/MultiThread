package util;

import static util.MyLogger.log;

public abstract class ThreadUtils {

    /**
     * sleep 한번 호출할 때마다 try-catch를 계속 해야 하기 때문에 편하게 하기 위함
     */
    public static void sleep(long millis) {
        try{
            Thread.sleep(millis);
        } catch(InterruptedException e){
            log("인터럽트 발생, " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
