package com.word.count.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.word.count.interfaces.WordFrequency;

import java.util.Comparator;
import java.util.Objects;

public class WordFrequencyImpl implements WordFrequency, Comparable<WordFrequencyImpl> {
    private final String word;
    private int frequency;

    public WordFrequencyImpl(String word) {
        this.word = word;
        this.frequency = 1;
    }

    public WordFrequencyImpl(String word, int frequency) {
        this.word = word;
        this.frequency = frequency;
    }

    public void add() {
        this.frequency += 1;
    }

    @Override
    public String getWord() {
        return word;
    }

    @Override
    public int getFrequency() {
        return frequency;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        WordFrequencyImpl that = (WordFrequencyImpl) other;
        return Objects.equals(word, that.word) && Objects.equals(frequency, that.frequency);
    }

    @Override
    public int compareTo(WordFrequencyImpl other) {
        int res = Integer.compare(other.frequency, this.frequency);
        if (res == 0) {
            res = this.getWord().compareTo(other.getWord());
        }
        return res;
    }

    @Override
    public String toString() {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            return ow.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
