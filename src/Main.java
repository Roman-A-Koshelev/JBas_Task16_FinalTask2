import java.util.Scanner;

public class Main {
    static int calc(final char OP, int val1, int val2) {
        final char OP_PLUS  = '+';
        final char OP_MINUS = '-';
        int res = 0;
        switch(OP) {
            case OP_PLUS:
                res = val1 + val2;
                break;
            case OP_MINUS:
                res = val1 - val2;
                break;
        }

        return res;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        final char OP_PLUS  = '+';
        final char OP_MINUS = '-';
        final char OP_EQUAL = '=';
        final char SYMB_UNK = 'x';
        final int POS_L_OP = 0; // Позиция левого операнда выражения
        final int POS_OP   = 1; // Позиция операции
        final int POS_R_OP = 2; // Позиция правого операнда выражения
        final int POS_EQ   = 3; // Позиция знака равенства
        final int POS_RES  = 4; // Позиция результата выражения
        System.out.println("Программа решает простое уравнение относительно x. ");
        System.out.println("Введите строку длиной 5 символов. Второй символ строки является знаком ‘+’ или\n" +
            "‘-’, четвертый символ строки ‘=’. Первым, третьим и пятым символом являются две\n" +
            "цифры (от 0 до 9) и буква ‘x’ (обозначает неизвестное) в любом порядке:");
        String str = in.nextLine();

        if (str.length() < 5) {
            System.out.println("Вы ввели некорректный данные. Строка должна быть длиной 5 символов.");
            return;
        }

        char op =  str.charAt(POS_OP);
        char eq =  str.charAt(POS_EQ);
        if (OP_PLUS != op && OP_MINUS != op || OP_EQUAL != eq) {
            System.out.println("Вы ввели уравнение в неверном формате.");
            return;
        }
        String sVal1 = str.substring(0,POS_OP);
        String sVal2 = str.substring(POS_OP+1,POS_EQ);
        String sRes  = str.substring(POS_EQ+1,POS_EQ+2);
        int val1 = 0;
        int val2 = 0;
        int valRes = 0;
        int valX = 0;
        int posX = -1; // Позиция неизвестного в уравнении
        int cntX = 0;  // Количество неизвестных в уравнении

        if (SYMB_UNK != sVal1.charAt(0)) { // Неизвестное НЕ в первом значении уравнения
            if (!Character.isDigit(sVal1.charAt(0))) {
                System.out.println("Вы ввели уравнение в неверном формате. Первое значение в уравнении должно быть цифрой или латинской буквой 'x'.");
                return;
            }
            val1 = Integer.parseInt(sVal1);
        }
        else {
            posX = POS_L_OP;
            ++cntX;
        }

        if (SYMB_UNK != sVal2.charAt(0)) { // Неизвестное НЕ во втором значении уравнения
            if (!Character.isDigit(sVal2.charAt(0))) {
                System.out.println("Вы ввели уравнение в неверном формате. Второе значение в уравнении должно быть цифрой или латинской буквой 'x'.");
                return;
            }
            val2 = Integer.parseInt(sVal2);
        }
        else {
            posX = POS_R_OP;
            ++cntX;
        }

        if (SYMB_UNK != sRes.charAt(0)) { // Неизвестное НЕ в результате уравнения
            if (!Character.isDigit(sRes.charAt(0))) {
                System.out.println("Вы ввели уравнение в неверном формате. Результирующее значение уравнения должно быть цифрой или латинской буквой 'x'.");
                return;
            }
            valRes = Integer.parseInt(sRes);
        }
        else {
            posX = POS_RES;
            ++cntX;
        }

        // Проверка, что в уравнении задано только одно неизвестное
        if (0 == cntX) {
            System.out.println("Вы не задали неизвестное.");
            return;
        }
        if (1 < cntX) {
            System.out.println("Вы ввели слишком много неизвестных.");
            return;
        }

        if (OP_MINUS == op && POS_R_OP != posX) {
            val2 *= -1;
        }

        switch(posX) {
            case POS_L_OP:
                valX = calc(OP_MINUS, valRes, val2);
                break;
            case POS_R_OP:
                valX = calc(OP_MINUS, valRes, val1);
                if (OP_MINUS == op) {
                    valX *= -1;
                }
                break;
            case POS_RES:
                valX = calc(OP_PLUS, val1, val2);
                break;
        }

        System.out.println("x = " + valX);
    }
}
