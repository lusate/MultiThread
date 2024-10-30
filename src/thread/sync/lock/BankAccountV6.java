package thread.sync.lock;

import thread.sync.BankAccount;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

/**
 * 동시성 문제 해결
 */
public class BankAccountV6 implements BankAccount {
    private int balance;

    private final Lock lock = new ReentrantLock();

    public BankAccountV6(int balance) {
        this.balance = balance;
    }

    @Override
    public boolean withdraw(int amount) {
        log("거래 시작: " + getClass().getSimpleName());
        // 잔고가 출금액보다 적으면, 진행하면 안됨

        // 0.5초간 기다리고 만약 기다려도 결과가 없으면 진입 실패. - false 반환
        // 그 사이에 interrupt가 들어오면 catch로 빠져나감
        try{
            if (!lock.tryLock(500, TimeUnit.MILLISECONDS)) {
                log("[진입 실패] 이미 처리 중인 작업이 있습니다");
                return false;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try{
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
        } finally {
            lock.unlock();
        }

        log("거래 종료");
        return true;
    }

    @Override
    public int getBalance() {
        lock.lock();
        try{
            return balance;
        } finally {
            lock.unlock();
        }
    }
}
