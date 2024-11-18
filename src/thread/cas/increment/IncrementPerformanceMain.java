package thread.cas.increment;

public class IncrementPerformanceMain {
    public static final long COUNT = 100_000_000;
    public static void main(String[] args) {
        test(new BasicInteger());
        test(new VolatileInteger());
        test(new SyncInteger());
        test(new MyAtomicInteger());
    }

    // 성능 테스트
    private static void test(IncrementInteger incrementInteger) {
        long startMs = System.currentTimeMillis();

        // 시간이 얼마나 걸리는지 보기 위함
        for (long i = 0; i < COUNT; i++) {
            incrementInteger.increment();
        }
        long endMs = System.currentTimeMillis();

        System.out.println(incrementInteger.getClass().getSimpleName() + ": ms=" + (endMs - startMs));

    }
}
