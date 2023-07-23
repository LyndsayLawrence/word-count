package com.word.count.resources.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;

public class WordFrequencyResponse {

    @JsonProperty("word")
    @Nullable
    private String word;
    @JsonProperty("frequency")
    private Integer frequency;

    public WordFrequencyResponse(@Nullable String word, Integer frequency) {
        this.word = word;
        this.frequency = frequency;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }
}
