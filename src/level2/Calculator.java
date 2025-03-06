package level2;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Calculator {
    private final Scanner scanner;
    private final Queue<Integer>  results;

    public Calculator() {
        this.scanner = new Scanner(System.in);
        this.results = new LinkedList<>();
    }

    // 계산 결과를 results에 저장
    public void setResults(int result) {
        results.offer(result);
    }

    // 계산 결과를 하나 삭제
    public void removeResult() {
        if (!results.isEmpty()) {
            results.poll();
        } else {
            System.out.println("저장된 데이터가 없습니다.");
        }
    }

    // 저장된 계산 결과를 반환
    public Queue<Integer> getResults() {
        return results;
    }

    // 유요한 숫자를 입력받아 반환
    public int getValidNumber(int tempNumber) {
        while (true) {
            String tempString;

            // 첫 번째 숫자와 두 번째 숫자를 구분하기 위한 문자열
            if (tempNumber == 1) {
                tempString = "첫";
            } else {
                tempString = "두";
            }

            System.out.print(tempString + " 번째 숫자를 입력해주세요 : ");

            try {
                // exit 입력시 계산기 종료
                if (scanner.hasNext("exit")) {
                    System.out.println();
                    System.out.println("계산기를 종료합니다.");
                    scanner.close();
                    System.exit(0);
                }

                int number = scanner.nextInt();

                // 음수인 경우 예외 처리
                if (number < 0) {
                    throw new IllegalArgumentException("\n음수를 입력하셨습니다.\n양수를 입력해주세요.\n");
                }
                scanner.nextLine();
                return number;
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("잘못된 입력입니다.");
                System.out.println("숫자를 입력해주세요.");
                System.out.println();
                scanner.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // 유효한 연산자를 입력 받아 반환
    public char getValidOperator(int secondNum) {
        while(true) {
            System.out.print("사칙 연산 기호를 입력하세요 (+, -, *, /) : ");

            try {
                if (scanner.hasNext("exit")) {
                    System.out.println();
                    System.out.println("계산기를 종료합니다.");
                    scanner.close();
                    System.exit(0);
                }

                // charAt(0)을 통해 맨 앞 문자만 가져옴
                char operators = scanner.next().charAt(0);

                // + - * / 중 하나가 아닐시 예외 처리
                if ("+-*/".indexOf(operators) == -1) {
                    throw new IllegalArgumentException("\n잘못된 입력입니다.\n+, -, *, / 중 하나를 입력해주세요.\n");
                }
                // 0으로 나누려고 하면 예외처리
                if (secondNum == 0 && operators == '/') {
                    throw new ArithmeticException("\n나눗셈 연산에서 두번째 정수에 0이 입력될 수 없습니다.\n다시 입력해주세요.\n");
                }
                scanner.nextLine();
                return operators;
            } catch (IllegalArgumentException | ArithmeticException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // 계산을 수행 및 결과 출력
    public void printPerformCalculation(int firstNum, int secondNum, char operator) {
        int result = switch (operator) {
            case '+' -> firstNum + secondNum;
            case '-' -> firstNum - secondNum;
            case '*' -> firstNum * secondNum;
            case '/' -> firstNum / secondNum;
            default -> 0;
        };

        System.out.println();
        System.out.println("----------------------------------------");

        // 결과 저장
        setResults(result);
        // 저장된 결과 출력
        System.out.print("저장된 계산 결과 : ");
        for (int i : getResults()) {
            System.out.print(i + " ");
        }

        // 결과 출력
        System.out.println();
        System.out.println("결과 : " + firstNum + " " + operator + " " + secondNum + " = " + result);
        System.out.println();

        System.out.println("------------(del 입력 시 삭제)------------");
        System.out.print("저장된 결과를 삭제하시겠습니까? : ");
        // del 입력시 결과 하나 삭제
        if (scanner.hasNext("del")) {
            System.out.println();

            // 결과 삭제
            removeResult();

            System.out.print("저장된 계산 결과 : ");
            for (int i : getResults()) {
                System.out.print(i + " ");
            }
        }
        scanner.nextLine();
    }
}