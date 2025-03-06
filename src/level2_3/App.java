package level2_3;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();

        System.out.println();
        System.out.println("계산기를 시작합니다");

        while (true) {
            System.out.println();
            System.out.println("-------------(exit 입력 시 종료)---------------");

            // 유효한 수식 입력 받기
            String expression = calculator.getValidExpression();
            // 입력받은 수식을 평가하여 결과 계산
            int result = calculator.evaluateExpression(expression);
            // 계산된 결과를 저장
            calculator.setResults(result);
            // 계산된 결과와 입력된 수식 출력
            calculator.printResults(result, expression);
        }
    }
}