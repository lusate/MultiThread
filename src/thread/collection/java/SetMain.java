package thread.collection.java;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class SetMain {
    public static void main(String[] args) {
        // 입력 순서 보장 X
        Set<Integer> list = new CopyOnWriteArraySet<>();

        list.add(3);
        list.add(2);
        list.add(1);
        System.out.println(list.getClass());
        System.out.println("list = " + list);

        // 항상 정렬돼서 넣어줌
        Set<Integer> skipSet = new ConcurrentSkipListSet<>();
        skipSet.add(3);
        skipSet.add(2);
        skipSet.add(1);
        System.out.println("skipSet = " + skipSet);
    }
}
