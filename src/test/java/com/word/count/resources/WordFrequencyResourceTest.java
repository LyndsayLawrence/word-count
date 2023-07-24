package com.word.count.resources;

import com.word.count.resources.model.request.WordFrequencyRequest;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class WordFrequencyResourceTest {

    @Test
    public void testHighestFrequency() {
        given()
                .body(new WordFrequencyRequest("The sun shines over the lake", null, null))
                .contentType(MediaType.APPLICATION_JSON)
                .when().post("/wordFrequency/highest")
                .then()
                .statusCode(200)
                .body(is("{\"frequency\":2}"));
    }

    @Test
    public void testHighestFrequencyValidationFailure() {
        given()
                .body(new WordFrequencyRequest(null, null, null))
                .contentType(MediaType.APPLICATION_JSON)
                .when().post("/wordFrequency/highest")
                .then()
                .statusCode(400)
                .body(is("{\"errorMessage\":\"Provided sentence cannot be empty or null.\"}"));
    }

    @Test
    public void testFrequencyForWord() {
        given()
                .body(new WordFrequencyRequest("The sun shines over the lake", "Shines", null))
                .contentType(MediaType.APPLICATION_JSON)
                .when().post("/wordFrequency/frequency")
                .then()
                .statusCode(200)
                .body(is("{\"frequency\":1}"));
    }

    @Test
    public void testFrequencyForWordValidationFailureSentence() {
        given()
                .body(new WordFrequencyRequest("", "Shines", null))
                .contentType(MediaType.APPLICATION_JSON)
                .when().post("/wordFrequency/frequency")
                .then()
                .statusCode(400)
                .body(is("{\"errorMessage\":\"Provided sentence cannot be empty or null.\"}"));
    }

    @Test
    public void testFrequencyForWordValidationFailureWord() {
        given()
                .body(new WordFrequencyRequest("The sun shines over the lake", "", null))
                .contentType(MediaType.APPLICATION_JSON)
                .when().post("/wordFrequency/frequency")
                .then()
                .statusCode(400)
                .body(is("{\"errorMessage\":\"Provided word cannot be empty or null and must contain only alpha characters.\"}"));
    }

    @Test
    public void testFrequencyForMissingWord() {
        given()
                .body(new WordFrequencyRequest("The sun shines over the lake", "nothing", null))
                .contentType(MediaType.APPLICATION_JSON)
                .when().post("/wordFrequency/frequency")
                .then()
                .statusCode(200)
                .body(is("{\"frequency\":0}"));
    }

    @Test
    public void testMostFrequentNWords() {
        given()
                .body(new WordFrequencyRequest("The sun shines over the lake", null, 2))
                .contentType(MediaType.APPLICATION_JSON)
                .when().post("/wordFrequency/mostFrequent")
                .then()
                .statusCode(200)
                .body(is("[{\"frequency\":2,\"word\":\"the\"},{\"frequency\":1,\"word\":\"lake\"}]"));
    }

    @Test
    public void testMostFrequentNWordsValidationFailure() {
        given()
                .body(new WordFrequencyRequest("    ", null, 2))
                .contentType(MediaType.APPLICATION_JSON)
                .when().post("/wordFrequency/mostFrequent")
                .then()
                .statusCode(400)
                .body(is("{\"errorMessage\":\"Provided sentence cannot be empty or null.\"}"));
    }

}