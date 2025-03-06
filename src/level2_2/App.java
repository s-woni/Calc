package level2_2;

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
            
            // 여러 숫자를 입력받아 처리하는 기능
            List<Integer> numbers = calculator.getValidNumber();
            // 연산자를 입력 받음
            char operator = calculator.getValidOperator();

            // 연산 수행 및 결과 출력
            calculator.printPerformCalculation(numbers, operator);

        }
    }
}