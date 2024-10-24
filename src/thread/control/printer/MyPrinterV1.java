package thread.control.printer;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class MyPrinterV1 {
    public static void main(String[] args) {
        Printer printer = new Printer();
        Thread thread = new Thread(printer, "printer");
        thread.start();

        // 입력
        Scanner userInput = new Scanner(System.in);

        /**
         * 메인 스레드가 작업을 q에 넣어줌.
         * 사용자가 입력하면 addJob으로 해서 들어가서 q에 들어가고 프린터 스레드는 q에서 값을 꺼내서 프린트
         */
        while (true) {
            log("프린터할 문서를 입력하세요. 종료(q): ");
            String s = userInput.nextLine();
            if(s.equals("q")){
                printer.flag = false;
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
            while (flag) {
                if(q.isEmpty()) continue;

                String job = q.poll();
                log("출력 시작 : " + job + ", 대기 문 : " + q);
                sleep(3000);
                log("출력 완료");
            }

            log("프린터 종료");
        }

        public void addJob(String input) {
            q.offer(input);
        }
    }
}
