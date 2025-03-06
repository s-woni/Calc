package level2_2;

import java.util.*;

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
    public List<Integer> getValidNumber() {
        List<Integer> numbers = new ArrayList<>();
        while (true) {
            // String tempString;

            System.out.print("숫자를 입력하세요 (완료 시 'done' 입력) : ");

            try {
                if (scanner.hasNext("exit")) {
                    System.out.println();
                    System.out.println("계산기를 종료합니다.");
                    scanner.close();
                    System.exit(0);
                }
                // done 입력 전까지 숫자를 입력 받아 numbers에 저장
                if (scanner.hasNext("done")) {
                    scanner.next();
                    // 두개 이상의 숫자가 입력되지 않으면 다시 반복
                    if (numbers.size() < 2) {
                        System.out.println("두개 이상의 숫자를 입력해주세요.");
                        continue;
                    }
                    break;
                }

                int number = scanner.nextInt();

                // 음수이면 예외처리
                if (number < 0) {
                    throw new IllegalArgumentException("\n음수를 입력하셨습니다.\n양수를 입력해주세요.\n");
                }
                numbers.add(number);
                scanner.nextLine();
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

    // 유효한 연산자를 입력 받아 반환
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

                // charAt(0)을 통해 맨 앞 문자만 가져옴
                char operators = scanner.next().charAt(0);

                // + - * / 중 하나가 아닐시 예외 처리
                if ("+-*/".indexOf(operators) == -1) {
                    throw new IllegalArgumentException("\n잘못된 입력입니다.\n+, -, *, / 중 하나를 입력해주세요.\n");
                }
                scanner.nextLine();
                return operators;
            } catch (IllegalArgumentException | ArithmeticException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // 숫자 리스트와 연산자로 계산 수행 및 결과 출력
    public void printPerformCalculation(List<Integer> numbers, char operator) {

        int result = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            switch (operator) {
                case '+' -> result += numbers.get(i);
                case '-' -> result -= numbers.get(i);
                case '*' -> result *= numbers.get(i);
                case '/' -> {
                    // 0으로 나누려고 하면 예외처리
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

        // 결과 저장
        setResults(result);
        // 저장된 결과 출력
        System.out.print("저장된 계산 결과 : ");
        for (int i : getResults()) {
            System.out.print(i + " ");
        }

        // 결과 출력
        System.out.println();
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