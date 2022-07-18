import enums.RomanNumEnum;
import exception.ApplicationException;
import validation.Validation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static String calc(String input) throws ApplicationException {
        String[] inputArray = input.split(" ");
        String result = "";

        if (Validation.validInputLength(inputArray)) {
            if (Validation.isRoman(inputArray[0])) {
                if (!Validation.isRoman(inputArray[2])) {
                    throw new ApplicationException("Используются одновременно разные системы счисления");
                }
                result = romanNumberCalc(inputArray[0], inputArray[2], inputArray[1]).name();
            } else {
                if (!Validation.isRoman(inputArray[2])) {
                    result = String.valueOf(arabicNumberCalc(inputArray[0], inputArray[2], inputArray[1]));
                } else
                    throw new ApplicationException("Используются одновременно разные системы счисления");
            }
        }
        return result;
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

    public static int arabicNumberCalc(String first, String second, String procedure) throws ApplicationException {
        return calculate(Integer.parseInt(first), Integer.parseInt(second), procedure);
    }

    public static RomanNumEnum romanNumberCalc(String first, String second, String procedure) throws ApplicationException {
        int solution = calculate(convertRomanToArabic(first), convertRomanToArabic(second), procedure);
        Validation.validSolutionForRoman(solution);
        return convertArabicToRoman(solution);
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
