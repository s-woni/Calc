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
            
            // 첫 번째 숫자를 입력 받고 유효한 숫자일 경우 firstNum에 저장
            int firstNum = calculator.getValidNumber(1);

            // 첫 번째 숫자를 입력 받고 유효한 숫자일 경우 secondNum에 저장
            int secondNum = calculator.getValidNumber(2);
            
            // 연산자를 입력 받고 연산자가 유효하면 값을 반환하여 받아옴
            char operator = calculator.getValidOperator(secondNum);

            calculator.printPerformCalculation(firstNum, secondNum, operator);

        }
    }
}