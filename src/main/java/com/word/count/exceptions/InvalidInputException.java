package com.word.count.exceptions;

import jakarta.xml.bind.ValidationException;

public class InvalidInputException extends ValidationException {
    public InvalidInputException(String reason) {
        super(reason);
    }
}
