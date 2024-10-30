package thread.sync;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

/**
 * 동시성 문제 발생
 */
public class BankAccountV1 implements BankAccount{
    private int balance;

    public BankAccountV1(int balance) {
        this.balance = balance;
    }

    @Override
    public boolean withdraw(int amount) {
        log("거래 시작: " + getClass().getSimpleName());
        // 잔고가 출금액보다 적으면, 진행하면 안됨

        log("[검증 시작] 출금액: " + amount + ", 잔액: " + balance);
        if (balance < amount) {
            log("[검증 실패] 출금액: " + amount + ", 잔액: " + balance);
            return false;
        }


        // 출금 가능하므로 진행
        log("[검증 완료] 출금액: " + amount + ", 잔액: " + balance);
        sleep(1000); // 출금에 걸리는 시간 -> 이것은 t1이 너무 빨리 계산해서 확인하기 위한 것으로 동시성 문제 해결은 안됨.

        // 나는 else 로 넣어서 사용하는 것이 더 가독성 있어 보여서 좋을 거라 생각했지만
        // else 없어도 정상적인 로직 흐름인데 깊이가 한번 더 들어가서 좋지 방법은 아님.
        // 검증 로직으로 if가 더 들어간다면 좀 더 성공하는 로직을 분리해서 명확하게 볼 수 있습니다.
        //
        balance -= amount; // 잔고 - 출금 금액
        log("[출력 완료] 출금액: " + amount + ", 잔액: " + balance);


        log("거래 종료");
        return true;
    }

    @Override
    public int getBalance() {
        return balance;
    }
}
