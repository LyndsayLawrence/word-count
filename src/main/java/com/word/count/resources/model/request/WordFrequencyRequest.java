package com.word.count.resources.model.request;

import jakarta.annotation.Nullable;
import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;

import java.io.Serializable;

public record WordFrequencyRequest(String sentence, String word, Integer n) implements Serializable {

    @JsonbCreator
    public WordFrequencyRequest(@JsonbProperty("sentence") String sentence, @JsonbProperty("word") @Nullable String word, @JsonbProperty("n") @Nullable Integer n) {
        this.sentence = sentence;
        this.word = word;
        this.n = n;
    }
}
