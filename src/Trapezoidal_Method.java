import static java.lang.Math.cos;
import static java.lang.Math.pow;

class Trapezoidal_Method {
    private int func_num;
    private double low;
    private double high;
    private double user_accuracy;

    private double I2n;
    private int piece;
    private double accuracy;
    private boolean interrupt = false;


    Trapezoidal_Method(int func_num, double low, double high, double accuracy) {
        this.func_num = func_num;
        if (high > low) {
            this.high = high;
            this.low = low;
        } else {
            this.high = low;
            this.low = high;
        }
        this.user_accuracy = accuracy;
    }

    boolean CalcTheIntegral() {
        if(high != low) {
            int n = 10000;
            double In;
            do {
                In = I2n;
                I2n = 0;
                double h = (high - low) / n;
                for(int i = 1; i < n; i++) {
                    I2n += Function(low + (i * h));
                }
                I2n += ((Function(low) + Function(high)) / 2.0);
                I2n *= h;
                piece = n;
                n *= 2;
                if(n > 100000) {
                    interrupt = true;
                    break;
                }
            } while (!RungAssessment(I2n, In));
             return true;
        } else {
            return false;
        }
    }

    private double Function(double x) {
        if(func_num == 1) {
            //return (sqrt(x) - 3);
           // return pow(x, 2);
            return (4 * pow(x,2)) - (5 * x) + 1;
        } else if(func_num == 2) {
            if(x == 2) {
                return cos(x + 0.001) / (x + 0.001 - 2);
            } else {
                return cos(x) / (x - 2);
            }
        } else if(func_num == 3) {
            return 1/Math.log(x);
        } else {
            return (pow(x, 4) / 12) + (x / 3) - (0.125);
        }
    }

    private boolean RungAssessment(double I1, double I0) {
         accuracy = (Math.abs(I1 - I0)/ 3.0);
         return accuracy < user_accuracy;
    }

    void toString(boolean flag) {

        if(flag) {
            System.out.println("Значение интеграла: " + String.format("%.7f",I2n));
            System.out.println("Количество разбиений, на которое пришлось разбить: " + piece);
            if(interrupt){
                System.out.println("Не было достигнута точность");
            } else {
                System.out.println("Полученная погрешность: " + String.format("%.9f", accuracy));
            }
        } else {
            System.out.println("Верхний предел равен нижнему, определенный интеграл равен нулю");
        }
    }
}