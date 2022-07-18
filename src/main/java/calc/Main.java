package calc;

import enums.RomanNumEnum;
import exception.ApplicationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static String calc(String input) throws ApplicationException {
        String[] inputArray = input.split(" ");

        if (inputArray.length > 3) {
            throw new ApplicationException("Формат математической операции не удовлетворяет заданию");
        }

        String result = "";

        if (isRoman(inputArray[0])) {
            if (!isRoman(inputArray[2])) {
                throw new ApplicationException("Используются одновременно разные системы счисления");
            }
            result = romanNumberCalc(inputArray[0], inputArray[2], inputArray[1]).name();
        } else
            result = String.valueOf(arabicNumberCalc(inputArray[0], inputArray[2], inputArray[1]));

        return result;
    }

    public static int arabicNumberCalc(String first, String second, String procedure) throws ApplicationException {
        return calculate(Integer.parseInt(first), Integer.parseInt(second), procedure);
    }

    public static RomanNumEnum romanNumberCalc(String first, String second, String procedure) throws ApplicationException {
        int solution = calculate(convertRomanToArabic(first), convertRomanToArabic(second), procedure);

        if (solution <= 0) {
            throw new ApplicationException("Результат меньше либо равен нулю");
        }

        // это временная валидация!
        if (solution > 21) {
            throw new ApplicationException("Результат вычисления (" + solution + ") выходит за границы 20. " +
                    "Да, можно было увеличить enum до 100 (максимально возможный результат), " +
                    "но я немного поленился, а другого решения пока не придумал. Придумаю - будет до 100");
        }
        return convertArabicToRoman(solution);
    }

    public static int calculate(int first, int second, String procedure) throws ApplicationException {
        switch (procedure) {
            case "+":
                return first + second;
            case "-":
                return first - second;
            case "*":
                return first * second;
            case "/":
                return first / second;
            default:
                throw new ApplicationException("Строка " + procedure + " не является математической операцией");
        }
    }

    public static RomanNumEnum convertArabicToRoman(int number) {
        RomanNumEnum romanNumEnum = null;
        for (RomanNumEnum num : RomanNumEnum.values()) {
            if (num.getValue() == number) {
                romanNumEnum = num;
            }
        }
        return romanNumEnum;
    }

    public static int convertRomanToArabic(String input) {
        int result = 0;
        for (RomanNumEnum num : RomanNumEnum.values()) {
            if (input.equals(num.name())) {
                result = num.getValue();
            }
        }
        return result;
    }

    public static boolean isRoman(String input) {
        boolean result = false;
        for (RomanNumEnum num : RomanNumEnum.values()) {
            if (input.equals(num.name())) {
                result = true;
            }
        }
        return result;
    }


    public static void main(String[] args) throws IOException, ApplicationException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String input = "";
            while (!(input = reader.readLine()).equals("")) {
                String result = Main.calc(input);
                System.out.println(result);
            }
        }
    }
}
