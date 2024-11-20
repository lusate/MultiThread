package thread.collection.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SynchronizedListMain {
    public static void main(String[] args) {
        // 동기화가 된 synchronized가 적용된 컬렉션
        List<String> list = Collections.synchronizedList(new ArrayList<>());

        list.add("data1");
        list.add("data2");
        list.add("data3");
        System.out.println(list.getClass());
        System.out.println("list = " + list);
    }
}
