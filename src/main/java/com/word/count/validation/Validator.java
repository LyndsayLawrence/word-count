package com.word.count.validation;

public class Validator {
    public boolean validateSentence(String sentence) {
        return sentence != null && !sentence.isBlank();
    }

    public boolean validateWord(String word) {
        return word!= null && !word.isBlank() && word.chars().allMatch(Character::isLetter);
    }
}
