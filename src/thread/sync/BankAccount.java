package thread.sync;

/**
 * 계좌의 잔액이 출금할 금액보다 많다면 출금 성공
 * 아니면 실패
 */
public interface BankAccount {
    boolean withdraw(int amount); // 출금
    int getBalance(); // 잔고 확인
}