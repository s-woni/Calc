package level2_3;

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

    public String getValidExpression() {
        while (true) {

            System.out.print("계산식을 입력하세요 (예: 5-2*3) : ");
            String expression = scanner.nextLine().replace("\s", "");

            if (scanner.hasNext("exit")) {
                System.out.println();
                System.out.println("계산기를 종료합니다.");
                scanner.close();
                System.exit(0);
            }

            if (expression.matches("[0-9+\\-*/]+")) {
                return expression;
            } else {
                System.out.println();
                System.out.println("잘못된 입력입니다. 숫자와 사칙연산 기호(+,-,*,/)만 입력해주세요.");
            }
        }
    }

    public int evaluateExpression(String expression) {
        List<String> postfix = convertToPostfix(expression);
        return calculatePostfix(postfix);
    }

    private List<String> convertToPostfix(String expression) {
        List<String> output = new ArrayList<>();
        Stack<Character> operators = new Stack<>();
        Map<Character, Integer> precedence = Map.of('+', 1, '-', 1, '*', 2, '/', 2);

        StringBuilder numberBuffer = new StringBuilder();
        for (char ch : expression.toCharArray()) {
            if (Character.isDigit(ch)) {
                numberBuffer.append(ch);
            } else {
                if (numberBuffer.length() > 0) {
                    output.add(numberBuffer.toString());
                    numberBuffer.setLength(0);
                }
                while (!operators.isEmpty() && precedence.get(operators.peek()) >= precedence.get(ch)) {
                    output.add(String.valueOf(operators.pop()));
                }
                operators.push(ch);
            }
        }
        if (numberBuffer.length() > 0) {
            output.add(numberBuffer.toString());
        }
        while (!operators.isEmpty()) {
            output.add(String.valueOf(operators.pop()));
        }
        return output;
    }

    private int calculatePostfix(List<String> postfix) {
        Stack<Integer> stack = new Stack<>();
        for (String token : postfix) {
            if (token.matches("\\d+")) {
                stack.push(Integer.parseInt(token));
            } else {
                int b = stack.pop();
                int a = stack.pop();
                switch (token.charAt(0)) {
                    case '+' -> stack.push(a + b);
                    case '-' -> stack.push(a - b);
                    case '*' -> stack.push(a * b);
                    case '/' -> {
                        if (b == 0) {
                            throw new ArithmeticException("0으로 나눌 수 없습니다.");
                        }
                        stack.push(a / b);
                    }
                }
            }
        }
        return stack.pop();
    }

    public void printResults(int result, String expression) {
        System.out.println();
        System.out.println("----------------------------------------");
        System.out.print("저장된 계산 결과 : ");
        for (int i : getResults()) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("결과 : " + expression + " = " + result);
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