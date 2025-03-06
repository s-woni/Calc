package level2_3;

import java.util.*;

public class Calculator {
    private final Scanner scanner;
    private final Queue<Integer>  results;

    public Calculator() {
        this.scanner = new Scanner(System.in);
        this.results = new LinkedList<>();
    }

    // 계산 결과 저장
    public void setResults(int result) {
        results.offer(result);
    }

    // 계산 결과 제거
    public void removeResult() {
        if (!results.isEmpty()) {
            results.poll();
        } else {
            System.out.println("저장된 데이터가 없습니다.");
        }
    }

    // 계산 결과 반환
    public Queue<Integer> getResults() {
        return results;
    }

    // 유효한 수식을 입력받음
    public String getValidExpression() {
        while (true) {

            System.out.print("계산식을 입력하세요 (예: 5-2*3) : ");
            // 공백 제거
            String expression = scanner.nextLine().replace("\s", "");

            // exit 입력시 계산기 종료
            if (scanner.hasNext("exit")) {
                System.out.println();
                System.out.println("계산기를 종료합니다.");
                scanner.close();
                System.exit(0);
            }

            // 입력된 수식이 숫자와 사칙연산만 포함하는지 확인
            if (expression.matches("[0-9+\\-*/]+")) {
                return expression;
            } else {
                System.out.println();
                System.out.println("잘못된 입력입니다. 숫자와 사칙연산 기호(+,-,*,/)만 입력해주세요.");
            }
        }
    }

    // 입력받은 수식을 계산
    public int evaluateExpression(String expression) {
        List<String> postfix = convertToPostfix(expression); // 중위 표기를 후위 표기법으로 변환
        return calculatePostfix(postfix); // 후위 표기법을 계산
    }

    // 중위 표기법을 후위 표기법으로 변환
    private List<String> convertToPostfix(String expression) {
        List<String> output = new ArrayList<>();
        Stack<Character> operators = new Stack<>();
        // 연산자 우선순위를 맵에 저장
        Map<Character, Integer> precedence = Map.of('+', 1, '-', 1, '*', 2, '/', 2);

        StringBuilder numberBuffer = new StringBuilder();
        for (char ch : expression.toCharArray()) {
            if (Character.isDigit(ch)) {
                // 숫자를 버퍼에 추가
                numberBuffer.append(ch);
            } else {
                if (numberBuffer.length() > 0) {
                    // 버퍼에 있는 숫자를 출력 리스트에 추가
                    output.add(numberBuffer.toString());
                    // 버퍼 초기화
                    numberBuffer.setLength(0);
                }
                // 연산자와 우선순위가 더 낮은 연산자들을 출력 리스트에 추가
                while (!operators.isEmpty() && precedence.get(operators.peek()) >= precedence.get(ch)) {
                    output.add(String.valueOf(operators.pop()));
                }
                // 연산자를 스택에 추가
                operators.push(ch);
            }
        }
        // 남아 있는 숫자 버퍼를 출력 리스트에 추가
        if (numberBuffer.length() > 0) {
            output.add(numberBuffer.toString());
        }
        // 남아 이쓴 연산자를 출려 ㄱ리스트에 추가
        while (!operators.isEmpty()) {
            output.add(String.valueOf(operators.pop()));
        }
        return output;
    }

    // 후위 표기법을 계산
    private int calculatePostfix(List<String> postfix) {
        Stack<Integer> stack = new Stack<>();
        for (String token : postfix) {
            // 숫자면 스택에 넣어줌
            if (token.matches("\\d+")) {
                stack.push(Integer.parseInt(token));
            } else { // 연산자가 나오면 스택에서 두개의 숫자를 넣어 연산 수행(stack은 LIFO 선입 후출)
                int b = stack.pop();
                int a = stack.pop();
                switch (token.charAt(0)) {
                    case '+' -> stack.push(a + b);
                    case '-' -> stack.push(a - b);
                    case '*' -> stack.push(a * b);
                    case '/' -> {
                        if (b == 0) { // 0으로 나눌 때 예외처리
                            throw new ArithmeticException("0으로 나눌 수 없습니다.");
                        }
                        stack.push(a / b);
                    }
                }
            }
        }
        // 최종 결과를 반환
        return stack.pop();
    }

    // 계산 결과 출력
    public void printResults(int result, String expression) {
        System.out.println();
        System.out.println("----------------------------------------");
        // 저장된 계산 결과 출력
        System.out.print("저장된 계산 결과 : ");
        for (int i : getResults()) {
            System.out.print(i + " ");
        }
        // 계산된 수식과 결과 출력
        System.out.println();
        System.out.println("결과 : " + expression + " = " + result);
        System.out.println();

        System.out.println("------------(del 입력 시 삭제)------------");
        System.out.print("저장된 결과를 삭제하시겠습니까? : ");
        // del 입력시 결과 삭제
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