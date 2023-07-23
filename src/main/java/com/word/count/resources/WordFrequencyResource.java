package com.word.count.resources;

import com.word.count.exceptions.InvalidInputException;
import com.word.count.implementation.WordFrequencyAnalyzerImpl;
import com.word.count.interfaces.WordFrequency;
import com.word.count.interfaces.WordFrequencyAnalyzer;
import com.word.count.resources.model.request.WordFrequencyRequest;
import com.word.count.resources.model.response.WordFrequencyResponse;
import com.word.count.validation.Validator;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Path("/wordFrequency")
public class WordFrequencyResource {

    public static final Logger LOGGER = LoggerFactory.getLogger(WordFrequencyAnalyzerImpl.class);
    WordFrequencyAnalyzer wordFrequencyAnalyzer = new WordFrequencyAnalyzerImpl();

    private Validator validator = new Validator();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/highest")
    public String calculateHighestFrequency(WordFrequencyRequest request) throws InvalidInputException {
        if (!validator.validateSentence(request.sentence())) {
            LOGGER.error("Invalid input. Provided sentence cannot be empty or null.");
            throw new InvalidInputException("Provided sentence cannot be empty or null.");
        }
        Integer frequency = wordFrequencyAnalyzer.calculateHighestFrequency(request.sentence());
        LOGGER.info("Highest frequency word calculation in [{}] completed successfully with result of [{}]", request.sentence(), frequency);
        return JsonbBuilder.create().toJson(new WordFrequencyResponse(null, frequency));
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/frequency")
    public String calculateFrequencyForWord(WordFrequencyRequest request) throws InvalidInputException {
        if (!validator.validateSentence(request.sentence())) {
            LOGGER.error("Invalid input. Provided sentence cannot be empty or null.");
            throw new InvalidInputException("Provided sentence cannot be empty or null.");
        }
        if (!validator.validateWord(request.word())) {
            LOGGER.error("Invalid input. Provided word cannot be empty or null and must contain only alpha characters.");
            throw new InvalidInputException("Provided word cannot be empty or null and must contain only alpha characters.");
        }
        Integer frequency = wordFrequencyAnalyzer.calculateFrequencyForWord(request.sentence(), request.word());
        LOGGER.info("Frequency of word [{}] calculation in [{}] completed successfully with result of [{}]", request.word(), request.sentence(), frequency);
        return JsonbBuilder.create().toJson(new WordFrequencyResponse(null, frequency));
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/mostFrequent")
    public String calculateMostFrequentNWords(WordFrequencyRequest request) throws InvalidInputException {
        if (!validator.validateSentence(request.sentence())) {
            LOGGER.error("Invalid input. Provided sentence cannot be empty or null.");
            throw new InvalidInputException("Provided sentence cannot be empty or null.");
        }
        List<WordFrequency> mostFrequentNWords = wordFrequencyAnalyzer.calculateMostFrequentNWords(request.sentence(), request.n());
        LOGGER.info("Most frequent [{}] words calculation in [{}] completed successfully with result of [{}]", request.n(), request.sentence(), mostFrequentNWords);
        return JsonbBuilder.create().toJson(mostFrequentNWords);
    }
}
