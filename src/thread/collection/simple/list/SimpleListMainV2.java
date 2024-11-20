package thread.collection.simple.list;

import thread.collection.simple.synclist.SyncList;
import thread.collection.simple.synclist.SyncProxyList;

import static util.MyLogger.log;

public class SimpleListMainV2 {
    public static void main(String[] args) throws InterruptedException {
//        test(new BasicList());
//        test(new SyncList());

        // SyncProxyList에 BasicList를 넣었으므로 동기화가 걸려있는 메서드를 호출하고 target으로는 BasicList가 있습니다.

        test(new SyncProxyList(new BasicList()));
    }

    private static void test(SimpleList list) throws InterruptedException {
        log("list = " + list.getClass().getSimpleName());

        // A를 리스트에 저장하는 코드
        Runnable addA = new Runnable() {

            @Override
            public void run() {
                list.add("A");
                log("Thread-1: list.add(A)");
            }
        };

        Runnable addB = new Runnable() {

            @Override
            public void run() {
                list.add("B");
                log("Thread-2: list.add(B)");
            }
        };

        Thread t1 = new Thread(addA, "Thread-1");
        Thread t2 = new Thread(addB, "Thread-2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        log("list = " + list);
    }
}
