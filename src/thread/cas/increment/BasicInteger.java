package thread.cas.increment;

public class BasicInteger implements IncrementInteger {
    // value는 인스턴스의 필드이기 때문에 여러 스레드가 공유할 수 있음.
    private int value;

    @Override
    public void increment() {
        value++;
    }

    @Override
    public int get() {
        return value;
    }
}

