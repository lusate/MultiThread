package thread.collection.simple.list;

import java.util.Arrays;

import static util.ThreadUtils.sleep;

/**
 * 멀티 스레드 문제를 확인하는 코드 add()
 * 원자적 연산이 아님
 */
public class BasicList implements SimpleList {
    private static final int DEFAULT_CAPACITY = 5; // 5개만 데이터 넣음

    private Object[] elements; // 데이터 넣기
    private int size;

    public BasicList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(Object o) {
        // 원자적 연산이 아님
        elements[size] = o;
        sleep(100);
        size++;
    }

    @Override
    public Object get(int index) {
        return elements[index];
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(elements, size)) +
                " size = " + size + ", capacity = " + elements.length;
    }
}
