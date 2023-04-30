package Validator;

import jakarta.xml.bind.ValidationException;

import static Validator.StringValidator.SymbolType.isChar;
import static Validator.StringValidator.SymbolType.isNumber;

public class StringValidator {

    public enum SymbolType {
        isNumber,
        isChar
    }

    protected String value;

    protected boolean result = true;

    public StringValidator(String value) {
        this.value = value;
    }

    public StringValidator lengthEqual(int length) throws ValidationException {
        result &= value.length() == length;
        validate(value + " length not equal " + length);
        return this;
    }

    public StringValidator onlyDigit() throws ValidationException {
        for (int i = 0; i < value.length(); i++) {
            char ch = value.charAt(i);
            if (ch > '9' || ch < '0') {
                result = false;
                validate(value + " contains more than just numbers");
            }
        }
        return this;
    }

    public StringValidator lengthMore(int length) throws ValidationException {
        result &= value.length() > length;
        validate(value + " must be more than " + length);
        return this;
    }

    public StringValidator lengthLess(int length) throws ValidationException {
        result &= value.length() < length;
        validate(value + " must be less than " + length);
        return this;
    }

    public StringValidator checkPattern(SymbolType[] pattern) throws ValidationException {
        var valueCopy = value.toUpperCase();
        for (int i = 0; i < value.length(); i++) {
            char ch = value.charAt(i);
            if ((pattern[i] == isNumber && (ch > '9' || ch < '0')) ||
                    (pattern[i] == isChar && (ch >= 'А' || ch <= 'Z'))) {
                result = false;
                validate(value + " doesn't match pattern");
            }
        }
        return this;
    }

    protected void validate(String message) throws ValidationException {
        if (!result) {
            throw new ValidationException(message);
        }
    }
}
