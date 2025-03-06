package level2_2;

import java.util.*;

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

    public List<Integer> getValidNumber() {
        List<Integer> numbers = new ArrayList<>();
        while (true) {
            // String tempString;

            System.out.print("숫자를 입력하세요 (완료 시 'done' 입력) : ");

            // if (tempNumber == 1) {
            //     tempString = "첫";
            // } else {
            //     tempString = "두";
            // }

            // System.out.print(tempString + " 번째 숫자를 입력해주세요 : ");

            try {
                if (scanner.hasNext("exit")) {
                    System.out.println();
                    System.out.println("계산기를 종료합니다.");
                    scanner.close();
                    System.exit(0);
                }
                if (scanner.hasNext("done")) {
                    scanner.next();
                    if (numbers.size() < 2) {
                        System.out.println("두개 이상의 숫자를 입력해주세요.");
                        continue;
                    }
                    break;
                }

                int number = scanner.nextInt();

                if (number < 0) {
                    throw new IllegalArgumentException("\n음수를 입력하셨습니다.\n양수를 입력해주세요.\n");
                }
                numbers.add(number);
                scanner.nextLine();
                // return number;
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
        return numbers;
    }

    public char getValidOperator() {
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
                scanner.nextLine();
                return operators;
                // if (secondNum == 0 && operators == '/') {
                //     throw new ArithmeticException("\n나눗셈 연산에서 두번째 정수에 0이 입력될 수 없습니다.\n다시 입력해주세요.\n");
                // }
                // scanner.nextLine();
                // return operators;
            } catch (IllegalArgumentException | ArithmeticException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void printPerformCalculation(List<Integer> numbers, char operator) {
        // int result = switch (operator) {
        //     case '+' -> firstNum + secondNum;
        //     case '-' -> firstNum - secondNum;
        //     case '*' -> firstNum * secondNum;
        //     case '/' -> firstNum / secondNum;
        //     default -> 0;
        // };

        int result = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            switch (operator) {
                case '+' -> result += numbers.get(i);
                case '-' -> result -= numbers.get(i);
                case '*' -> result *= numbers.get(i);
                case '/' -> {
                    if (numbers.get(i) == 0) {
                        System.out.println("\n나눗셈 연산에서 0으로 나눌 수 없습니다. 다시 시도하세요.\n");
                        return;
                    }
                    result /= numbers.get(i);
                }
            }
        }

        System.out.println();
        System.out.println("----------------------------------------");

        setResults(result);
        System.out.print("저장된 계산 결과 : ");
        for (int i : getResults()) {
            System.out.print(i + " ");
        }

        System.out.println();
        // System.out.println("결과 : " + firstNum + " " + operator + " " + secondNum + " = " + result);
        System.out.print("결과 : ");
        for (int i = 0; i < numbers.size(); i++) {
            System.out.print(numbers.get(i));
            if (i < numbers.size() - 1) {
                System.out.print(" " + operator + " ");
            }
        }
        System.out.println(" = " + result);
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