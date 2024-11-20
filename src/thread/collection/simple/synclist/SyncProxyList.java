package thread.collection.simple.synclist;

import thread.collection.simple.list.SimpleList;

/**
 * 클라이언트 -> 프록시 -> 서버
 * 타겟은 프록시가 대상으로 알아야 하는 것 -> 즉 서버
 * 누군가 target을 호출하면 synchronized를 걸고 메서드를 호출한
 * 하나의 메서드가 끝나고 나와서 반환할 때까지는 전부 동기화가 걸린 상태
 */
public class SyncProxyList implements SimpleList {
    private SimpleList target;

    public SyncProxyList(SimpleList target) {
        this.target = target;
    }

    @Override
    public synchronized int size() {
        return target.size();
    }

    @Override
    public synchronized void add(Object o) {
        target.add(o);
    }

    @Override
    public synchronized Object get(int index) {
        return target.get(index);
    }

    @Override
    public String toString() {
        return target.toString() + " + by " + this.getClass().getSimpleName();
    }
}
