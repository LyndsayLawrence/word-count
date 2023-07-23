package com.word.count.implementation;

import com.word.count.interfaces.WordFrequency;
import com.word.count.interfaces.WordFrequencyAnalyzer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordFrequencyAnalyzerImplTest {
    WordFrequencyAnalyzer wordFrequencyAnalyzer = new WordFrequencyAnalyzerImpl();

    private static Stream<Arguments> frequencyValues() {
        return Stream.of(
                Arguments.of("The fox jumped over the moon", 2),
                Arguments.of("Hello world, you are awesome", 1),
                Arguments.of("Peter piper picked a peck of pickled peppers, a peck of picked peppers peter piper picked", 3),
                Arguments.of("Foo bar FOO foo FoO bar", 4),
                Arguments.of("Foo,,, Foo bar      ", 2),
                Arguments.of("Ant      ", 1)
        );
    }

    @ParameterizedTest
    @MethodSource("frequencyValues")
    public void testCalculateHighestFrequency(String text, Integer expected) {
        int frequency = wordFrequencyAnalyzer.calculateHighestFrequency(text);
        assertEquals(expected, frequency);
    }

    private static Stream<Arguments> frequencyForWordsValues() {
        return Stream.of(
                Arguments.of("The fox jumped over the moon", "fox", 1),
                Arguments.of("Hello world, you are awesome", "sun", 0),
                Arguments.of("Peter piper picked a peck of pickled peppers, a peck of picked peppers peter piper picked", "peter", 2),
                Arguments.of("Foo bar FOO foo FoO bar", "Foo", 4),
                Arguments.of("Foo,,, Foo bar      ", "BAR", 1),
                Arguments.of("Ant      ", "ant", 1)
        );
    }

    @ParameterizedTest
    @MethodSource("frequencyForWordsValues")
    public void testCalculateFrequencyForWord(String text, String word, Integer expected) {
        int frequency = wordFrequencyAnalyzer.calculateFrequencyForWord(text, word);
        assertEquals(expected, frequency);
    }

    private static Stream<Arguments> mostFrequentNWords() {
        return Stream.of(
                Arguments.of("The fox jumped over the awesome moon", 2, List.of(new WordFrequencyImpl("the", 2), new WordFrequencyImpl("awesome"))),
                Arguments.of("The sun shines over the lake", 3, List.of(new WordFrequencyImpl("the", 2), new WordFrequencyImpl("lake"), new WordFrequencyImpl("over"))),
                Arguments.of("Hello world, you are awesome", 3, List.of(new WordFrequencyImpl("are"), new WordFrequencyImpl("awesome"), new WordFrequencyImpl("hello"))),
                Arguments.of("Peter piper picked a peck of pickled peppers, a peck of picked peppers peter piper picked", 2, List.of(new WordFrequencyImpl("picked", 3), new WordFrequencyImpl("a", 2))),
                Arguments.of("Foo bar FOO foo FoO bar", 2, List.of(new WordFrequencyImpl("foo", 4), new WordFrequencyImpl("bar", 2))),
                Arguments.of("Foo,,, Foo bar      ", 1, List.of(new WordFrequencyImpl("foo", 2))),
                Arguments.of("A:stranGe!Separators.SentAnce,with\"ODD\"...Separators,and a single{double-barrel}as/two[words", 5, List.of(new WordFrequencyImpl("a", 2), new WordFrequencyImpl("separators", 2), new WordFrequencyImpl("and"), new WordFrequencyImpl("as"), new WordFrequencyImpl("barrel"))),
                Arguments.of("Ant      ", 2, List.of(new WordFrequencyImpl("ant")))
        );
    }

    @ParameterizedTest
    @MethodSource("mostFrequentNWords")
    public void testCalculateMostFrequentNWords(String text, Integer n, List<WordFrequency> expected) {
        List<WordFrequency> mostFrequent = wordFrequencyAnalyzer.calculateMostFrequentNWords(text, n);
        assertEquals(expected, mostFrequent);
    }
}
