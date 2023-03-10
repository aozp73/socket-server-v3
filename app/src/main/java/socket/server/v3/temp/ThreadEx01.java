package socket.server.v3.temp;

// main 메서드 stack이랑 하나 또 만들어지는게 있음

// Queue가 만들어진다. (코드가 어떤 순서로 실행될지를 저장한 영역)
// 메서드의 종료는 Queue가 flush 되는 순간 
// FIFO : 들어온대로 나가기 때문에 first in first out

class NewThread implements Runnable {

    // 메서드 생성되면 스택,큐가 생성되는데
    // 큐를 서브 쓰레드로 활용
    // 새로운 실 run()
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println("뉴쓰레드 : " + i);
                Thread.sleep(1000);
            } catch (Exception e) {

            }
        }

    }
}

// 소켓도 os에 소켓하나 만들어줘 요청 하는 것
// os에 쓰레드 하나 만들어줘 요청 하는 것
public class ThreadEx01 {

    public static void main(String[] args) {

        // 만약 1급객체가 있는 언어라면 클래스 없이 메서드를 사용 할 수 있으므로
        // Thread 생성자에 함수를 넣었을 거임. 큐가 필요한거
        Thread t1 = new Thread(new NewThread());

        // 타겟이 없어서 이렇게만 하면 실행이 안됨 (타켓:run())
        // run을 실행하는 메서드
        t1.start();

        for (int i = 0; i < 10; i++) {
            try {
                System.out.println("메인쓰레드 : " + i);
                Thread.sleep(1000);
            } catch (Exception e) {

            }
        }

    }
}

// go1 stack(b a)과 go2 stack(d c)가 따로 만들어지고
// go2 스택이 먼저 없어짐
// 이 과정을 보면 메서드는 스택으로 만든 이유 확인 가능
// public void go1(){
// int a = 1;
// int b = 2;
// go2();
// }
// public void go2(){
// int c = 1;
// int d = 2;
// }