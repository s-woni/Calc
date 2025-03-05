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

    public void setResults(int result) {
        results.offer(result);
    }

    public void removeResult() {
        if (!results.isEmpty()) {
            results.poll();
        } else {
            System.out.println("저장된 데이터가 없습니다.");
        }
    }

    public Queue<Integer> getResults() {
        return results;
    }

    public int getValidNumber(int tempNumber) {
        while (true) {
            String tempString;

            if (tempNumber == 1) {
                tempString = "첫";
            } else {
                tempString = "두";
            }

            System.out.print(tempString + " 번째 숫자를 입력해주세요 : ");

            try {
                if (scanner.hasNext("exit")) {
                    System.out.println();
                    System.out.println("계산기를 종료합니다.");
                    scanner.close();
                    System.exit(0);
                }

                int number = scanner.nextInt();

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

                char operators = scanner.next().charAt(0);

                if ("+-*/".indexOf(operators) == -1) {
                    throw new IllegalArgumentException("\n잘못된 입력입니다.\n+, -, *, / 중 하나를 입력해주세요.\n");
                }
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

        setResults(result);
        System.out.print("저장된 계산 결과 : ");
        for (int i : getResults()) {
            System.out.print(i + " ");
        }

        System.out.println();
        System.out.println("결과 : " + firstNum + " " + operator + " " + secondNum + " = " + result);
        System.out.println();

        System.out.println("------------(del 입력 시 삭제)------------");
        System.out.print("저장된 결과를 삭제하시겠습니까? : ");
        if (scanner.hasNext("del")) {
            System.out.println();

            removeResult();

            System.out.print("저장된 계산 결과 : ");
            for (int i : getResults()) {
                System.out.print(i + " ");
            }
        }
        scanner.nextLine();
    }
}