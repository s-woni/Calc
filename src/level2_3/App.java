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

            String expression = calculator.getValidExpression();
            int result = calculator.evaluateExpression(expression);
            calculator.setResults(result);
            calculator.printResults(result, expression);
        }
    }
}