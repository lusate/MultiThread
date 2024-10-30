package thread.sync;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

/**
 * 동시성 문제 해결
 */
public class BankAccountV3 implements BankAccount{
    private int balance;

    public BankAccountV3(int balance) {
        this.balance = balance;
    }

    @Override
    public boolean withdraw(int amount) {
        log("거래 시작: " + getClass().getSimpleName());
        // 잔고가 출금액보다 적으면, 진행하면 안됨

        // 현재 나의 인스턴스에서 락을 흭득. this는 현재 나의 인스턴스를 가리킴
        synchronized (this) {
            // ==임계 영역 시작==
            log("[검증 시작] 출금액: " + amount + ", 잔액: " + balance);
            if (balance < amount) {
                log("[검증 실패] 출금액: " + amount + ", 잔액: " + balance);
                return false;
            }

            // 출금 가능하므로 진행
            log("[검증 완료] 출금액: " + amount + ", 잔액: " + balance);
            sleep(1000); // 출금에 걸리는 시간 -> 이것은 t1이 너무 빨리 계산해서 확인하기 위한 것으로 동시성 문제 해결은 안됨.

            balance -= amount; // 잔고 - 출금 금액
            log("[출력 완료] 출금액: " + amount + ", 잔액: " + balance);
            // ==임계 영역 종료==
        }


        log("거래 종료");
        return true;
    }

    @Override
    public synchronized int getBalance() {
        return balance;
    }
}
