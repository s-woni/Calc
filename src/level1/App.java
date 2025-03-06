package level1;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println();
        System.out.println("계산기를 시작합니다");

        while (true) {
            System.out.println();
            System.out.println("-------------(exit 입력 시 종료)---------------");

            int firstNum = 0;
            int secondNum = 0;
            char operator;

            while (true) {
                System.out.print("첫 번째 숫자를 입력해주세요 : ");

                if (scanner.hasNext("exit")) { // exit 입력시 return을 통해 계산기 종료
                    System.out.println();
                    System.out.println("계산기를 종료합니다.");
                    scanner.close();
                    return;
                }
                if (scanner.hasNextInt()) {
                    firstNum = scanner.nextInt();
                    if (firstNum < 0) { // 첫 번째 숫자가 음수 입력시 continue를 통하여 반복
                        System.out.println();
                        System.out.println("음수를 입력하셨습니다.");
                        System.out.println("양수를 입력해주세요.");
                        System.out.println();
                        continue;
                    }
                    scanner.nextLine();
                    break;
                } else { // 숫자가 아닐 시에도 반복문을 탈출하지 못하여 반복
                    System.out.println();
                    System.out.println("숫자를 입력해주세요.");
                    System.out.println();
                    scanner.nextLine();
                }
            }

            while (true) {
                System.out.print("두 번째 숫자를 입력해주세요 : ");

                if (scanner.hasNext("exit")) {
                    System.out.println();
                    System.out.println("계산기를 종료합니다.");
                    scanner.close();
                    return;
                }
                if (scanner.hasNextInt()) {
                    secondNum = scanner.nextInt();
                    if (secondNum < 0) {
                        System.out.println();
                        System.out.println("음수를 입력하셨습니다.");
                        System.out.println("양수를 입력해주세요.");
                        System.out.println();
                        continue;
                    }
                    scanner.nextLine();
                    break;
                } else {
                    System.out.println();
                    System.out.println("숫자를 입력해주세요.");
                    System.out.println();
                    scanner.nextLine();
                }
            }

            while (true) {
                System.out.print("사칙 연산 기호를 입력하세요 (+, -, *, /) : ");

                if (scanner.hasNext("exit")) {
                    System.out.println();
                    System.out.println("계산기를 종료합니다.");
                    scanner.close();
                    return;
                }

                operator = scanner.next().charAt(0);

                if ("+-*/".indexOf(operator) != -1) { // 연산자의 값에 + - * / 가 입력되어 있는지 확인 후 아니면 다시 반복
                    if (secondNum == 0 && operator == '/') { // 0으로 나누는걸 방지하기 위해 두번째 숫자가 0이고 연산기호가 / 이면 continue를 통하여 반복
                        System.out.println();
                        System.out.println("나눗셈 연산에서 두번째 정수에 0이 입력될 수 없습니다.");
                        System.out.println();
                        continue;
                    }
                    break;
                } else {
                    System.out.println();
                    System.out.println("잘못된 입력입니다.");
                    System.out.println("+, -, *, / 중 하나를 입력해주세요.");
                    System.out.println();
                }
            }

            int result = 0;

            switch (operator) {
                case '+':
                    result = firstNum + secondNum;
                    break;
                case '-':
                    result = firstNum - secondNum;
                    break;
                case '*':
                    result = firstNum * secondNum;
                    break;
                case '/':
                    result = firstNum / secondNum;
                    break;
            }
            System.out.println("-----------------------------------------");

            System.out.println("결과 : " + firstNum + " " + operator + " " + secondNum + " = " + result);
        }
    }
}