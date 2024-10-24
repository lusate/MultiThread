package thread.control.yield;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import static util.MyLogger.log;

public class MyPrinterYieldV4 {
    public static void main(String[] args) {
        Printer printer = new Printer();
        Thread thread = new Thread(printer, "printer");
        thread.start();
        log("스레드명1 = " + Thread.currentThread().getName()); // main 스레드

        // 입력
        Scanner userInput = new Scanner(System.in);

        while (true) {
            log("프린터할 문서를 입력하세요. 종료(q): ");
            String s = userInput.nextLine();

            // 인터럽트 걸기
            if(s.equals("q")){
                thread.interrupt();
                break;
            }
            printer.addJob(s);
        }
    }

    static class Printer implements Runnable {
        volatile boolean flag = true;
        // 여러 군데서 프린터에 작업을 넣는다고 가정
        // ConcurrentLinkedQueue : 여러 스레드가 동시에 접근할 수 있도록 설계된 큐
        Queue<String> q = new ConcurrentLinkedQueue<>();

        @Override
        public void run() {
            /** 이 while 문은 CPU가 계속 반복 돌리면서 계산합니다.
             * 인터럽트가 발생하기 전까지 계속 인터럽트의 상태를 체크하고 큐를 확인합니다.
             * 문제점 : 쉴틈없이 CPU에서 이 로직을 계속 반복합니다. 1초에 반복이 수억 번 발생할 수도 있습니다.
             * CPU 자원 매우 많이 사용
             * 안터럽트도 걸리지 않고 큐도 비어있는데 체크 로직에 CPU 자원을 많이 사용하게 되면 정작 필요한 스레들의 효율이 떨어집니다.
             * 차라리 그 시간에 다른 스레드들을 더 많이 실행해서 큐에 필요한 작업을 빠르게 만들어 넣어주는 것이 효율적
             * 큐에 작업이 비어있으면 yield()를 호출해서 다른 스레드에 작업을 양보하는 것이 효율적.
             */
            while (!Thread.interrupted()) {
//                if(q.isEmpty()) continue; // 의미 없게 CPU를 계속 태움
                if (q.isEmpty()) {
                    Thread.yield();
                    continue;
                }

                try {
                    String job = q.poll();
                    log("출력 시작 : " + job + ", 대기 문 : " + q);
                    log("스레드명2 = " + Thread.currentThread().getName()); // printer 스레드
                    Thread.sleep(3000); // 여기서 인터럽트 터지면 잡음
                    log("출력 완료");
                } catch (InterruptedException e) {
                    log("인터럽트 잡기");
                    break; // while 빠져나오기
                }
            }

            log("프린터 종료");
        }

        public void addJob(String input) {
            q.offer(input);
        }
    }
}
