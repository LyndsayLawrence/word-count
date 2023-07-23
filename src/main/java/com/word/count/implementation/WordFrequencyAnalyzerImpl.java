package com.word.count.implementation;

import com.word.count.interfaces.WordFrequency;
import com.word.count.interfaces.WordFrequencyAnalyzer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WordFrequencyAnalyzerImpl implements WordFrequencyAnalyzer {

    public static final Logger LOGGER = LoggerFactory.getLogger(WordFrequencyAnalyzerImpl.class);

    @Override
    public int calculateHighestFrequency(String text) {
        LOGGER.debug("Calculating highest frequency word in text [{}]", text);
        List<WordFrequency> words = getSortedWordFrequencies(text);
        return words.stream().iterator().next().getFrequency();
    }

    @Override
    public int calculateFrequencyForWord(String text, String word) {
        LOGGER.debug("Calculating frequency for word [{}] in text [{}]", word, text);
        List<WordFrequency> words = getSortedWordFrequencies(text);
        int index = getIndexOfWordInList(words, word);
        return index > -1 ? words.get(index).getFrequency() : 0;
    }

    @Override
    public List<WordFrequency> calculateMostFrequentNWords(String text, int n) {
        LOGGER.debug("Calculating [{}] most frequent words in text [{}]", n, text);
        List<WordFrequency> words = getSortedWordFrequencies(text);
        List<WordFrequency> topWords = new ArrayList<>();
        Iterator<WordFrequency> iterator = words.iterator();
        for (int i = 0; i < n; i++) {
            if (iterator.hasNext()) {
                topWords.add(iterator.next());
            }
        }
        return topWords;
    }

    private List<WordFrequency> getSortedWordFrequencies(String text) {
        text = simplifySeparators(text);
        List<WordFrequency> words = new ArrayList<>();
        Arrays.stream(text.toLowerCase().split(" ")).forEach(word -> {
            WordFrequency wordFrequency = new WordFrequencyImpl(word);
            boolean contains = words.stream().anyMatch(wf -> wf.getWord().equalsIgnoreCase(word));
            if (contains) {
                words.get(getIndexOfWordInList(words, word)).add();
            } else {
                words.add(wordFrequency);
            }
        });

        return words.stream().sorted().collect(Collectors.toList());
    }

    public String simplifySeparators(String text) {
        text = text.replaceAll("[^a-zA-Z ]", " ");
        text = text.replaceAll("( )+", " ");
        return text;
    }

    private int getIndexOfWordInList(List<WordFrequency> words, String word) {
        return IntStream.range(0, words.size())
                .filter(i -> words.get(i).getWord().equalsIgnoreCase(word))
                .findFirst()
                .orElse(-1);
    }

}
