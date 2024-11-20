package thread.collection.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListMain {
    public static void main(String[] args) {
        // 동기화가 된 synchronized가 적용된 컬렉션
        List<Integer> list = new CopyOnWriteArrayList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list.getClass());
        System.out.println("list = " + list);
    }
}
