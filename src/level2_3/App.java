package level2_3;

import java.util.List;
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

            List<Integer> numbers = calculator.getValidNumber();
            char operator = calculator.getValidOperator();

            calculator.printPerformCalculation(numbers, operator);

        }
    }
}