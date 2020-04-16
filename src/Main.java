import java.util.Scanner;

@SuppressWarnings("Duplicates")
public class Main {
    private static int func_num;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        double accuracy;
        double low;
        double high;

        func_num = FunctionNumber();
        low = Limit("нижний");
        high = Limit("верхний");
        accuracy = Accuracy();
        Trapezoidal_Method trapezoidal_method = new Trapezoidal_Method(func_num, low, high, accuracy);
        boolean flag = trapezoidal_method.CalcTheIntegral();
        trapezoidal_method.toString(flag);
    }

    private static double Accuracy() {
        double accuracy;
        boolean mistake;
        do {
            System.out.println("Введите точность:");
            mistake = false;
            accuracy = scanner.nextDouble();
            if (accuracy < 0.000000001) {
                mistake = true;
                System.out.println("Точность должна быть не больше 7 знаков после запетой");
            }
        } while (mistake);

        return accuracy;
    }

    private static double Limit(String text) {
        double limit;
        boolean mistake;
        do {
            System.out.println("Введите " + text + " предел: ");
            limit = scanner.nextDouble();
            mistake = false;
            if (limit < -1000 || limit > 1000) {
                System.out.println("Введите число в диапазоне от -1000 до 1000 включительно");
                mistake = true;
            }
            if (func_num == 3) {
                if (limit < 0) {
                    if (!mistake) {
                        mistake = true;
                        System.out.println("Предел не существует");
                    }
                }
            }
            if (func_num == 2) {
                if (limit == 2) {
                    if (!mistake) {
                        mistake = true;
                        System.out.println("Предел не существует");
                    }
                }
            }
        } while (mistake);
        return limit;
    }

    private static int FunctionNumber() {
        int number;
        boolean mistake;
        do {
            System.out.println("Выбирите функцию, интеграл которой вы бы хотели вычеслить:\n" +
                    "1. 4*x^2 - 5*x + 1\n" +
                    "2. (cos (x))/(x - 2)\n" +
                    "3. 1/ln(x)\n" +
                    "4. x^4/12 + x/3 - 1/8"
            );
            mistake = false;
            number = scanner.nextInt();
            if (number < 1 || number > 4) {
                System.out.println("Введите число в диапазоне от 1 до 4 включительно.");
                mistake = true;
            }
        } while (mistake);

        return number;
    }

}
