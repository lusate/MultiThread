package thread.collection.simple.synclist;

import thread.collection.simple.list.SimpleList;

import java.util.Arrays;

import static util.ThreadUtils.sleep;

public class SyncList implements SimpleList {
    private static final int DEFAULT_CAPACITY = 5; // 5개만 데이터 넣음

    private Object[] elements; // 데이터 넣기
    private int size;

    public SyncList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public synchronized int size() {
        return size;
    }

    @Override
    public synchronized void add(Object o) {
        // 원자적 연산이 아님
        elements[size] = o;
        sleep(100);
        size++;
    }

    @Override
    public synchronized Object get(int index) {
        return elements[index];
    }

    @Override
    public synchronized String toString() {
        return Arrays.toString(Arrays.copyOf(elements, size)) +
                " size = " + size + ", capacity = " + elements.length;
    }
}
