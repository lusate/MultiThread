package util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 추상 클래스로 하여 직접 생섣할 수 없도록.
 *
 */
public abstract class MyLogger {
    // 시간이기 때문에 formatting 필요
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    /**
     * Object로 한 이유는 String으로 한다면 숫자를 받지 못하기 때문
     * printf로 할 때 Object로 받으면 더 유연하게 출력기 가능
     * @param obj
     */
    public static void log(Object obj) {
        String time = LocalTime.now().format(formatter);
        // 항상 아홉 칸을 차지하도록 문자를 출력. 9칸을 차지 하지 않는다면 왼쪽에 공백이 생김.
        // 출력 이쁘게 할려고 하는 것.
        System.out.printf("%s [%9s] %s\n", time, Thread.currentThread().getName(), obj);
    }
}
