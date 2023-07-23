package com.word.count.validation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidatorTest {
    Validator validator = new Validator();
    private static Stream<Arguments> sentences() {
        return Stream.of(
                Arguments.of("The fox jumped over the moon", true),
                Arguments.of("Hello world, you are awesome", true),
                Arguments.of("Peter piper picked a peck of pickled peppers, a peck of picked peppers peter piper picked", true),
                Arguments.of("Foo bar FOO foo FoO bar", true),
                Arguments.of("Foo,,, Foo bar      ", true),
                Arguments.of("Ant      ", true),
                Arguments.of("       ", false),
                Arguments.of("", false),
                Arguments.of(null, false)
        );
    }

    @ParameterizedTest
    @MethodSource("sentences")
    public void testValidateSentence(String sentence, boolean expected) {
        assertEquals(expected, validator.validateSentence(sentence));
    }

    private static Stream<Arguments> words() {
        return Stream.of(
                Arguments.of("The", true),
                Arguments.of("asdf", true),
                Arguments.of("Ant,", false),
                Arguments.of("1234", false),
                Arguments.of("", false),
                Arguments.of(null, false)
        );
    }

    @ParameterizedTest
    @MethodSource("words")
    public void testValidateWord(String word, boolean expected) {
        assertEquals(expected, validator.validateWord(word));
    }
}
