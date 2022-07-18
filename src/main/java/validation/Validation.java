package validation;

import enums.RomanNumEnum;
import exception.ApplicationException;

public class Validation {

    public static boolean validInputLength(String[] array) throws ApplicationException {
        if (array.length < 3) {
            throw new ApplicationException("Строка не является математической операцией");
        } else if (array.length > 3) {
            throw new ApplicationException("Формат математической операции не удовлетворяет заданию");
        }
        return true;
    }

    public static boolean validSolutionForRoman(int solution) throws ApplicationException {
        if (solution <= 0) {
            throw new ApplicationException("Результат меньше либо равен нулю");
        }

        if (solution > 100) {
            throw new ApplicationException("Результат вычисления (" + solution + ") выходит за границы 100. " +
                    "В условии сказано, что операции могут проводиться " +
                    "только над числами от 1 до 10, поэтому 100 - максимальное значение");
        }
        return true;
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
}
