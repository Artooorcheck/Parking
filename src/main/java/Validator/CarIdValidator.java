package Validator;

import jakarta.xml.bind.ValidationException;

import static Validator.StringValidator.SymbolType.isChar;
import static Validator.StringValidator.SymbolType.isNumber;

public class CarIdValidator extends StringValidator {

    private final SymbolType[] pattern = {isChar, isNumber, isNumber, isNumber, isChar, isChar, isNumber, isNumber, isNumber};

    public CarIdValidator(String value) {
        super(value);
    }

    public CarIdValidator isCarNumber() throws ValidationException {
        var valueCopy = value.toUpperCase();
        lengthMore(6);
        lengthLess(9);
        String availableChars = "АВЕКМНОРСТУХ";

        for (int i = 0; i < valueCopy.length(); i++) {
            char ch = value.charAt(i);
            if ((pattern[i] == isNumber && (ch > '9' || ch < '0')) ||
                    (pattern[i] == isChar && !availableChars.contains(ch+""))) {
                result = false;
                validate(value + " is not car number");
            }
        }
        return this;
    }
}
