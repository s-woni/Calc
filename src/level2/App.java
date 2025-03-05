package level2;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Calculator calculator = new Calculator();

        System.out.println();
        System.out.println("계산기를 시작합니다");

        while(true) {
            System.out.println();
            System.out.println("-------------(exit 입력 시 종료)---------------");

            int firstNum = calculator.getValidNumber(1);
            int secondNum = calculator.getValidNumber(2);
            char operator = calculator.getValidOperator(secondNum);

            calculator.printPerformCalculation(firstNum, secondNum, operator);

        }
    }
}