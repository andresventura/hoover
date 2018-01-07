package com.yoti;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HooverAppIntegrationTest {
    private static HooverApp hooverApp;

    @BeforeClass
    public static void setUp() {
        hooverApp = new HooverApp(9897);
        hooverApp.start();
    }

    @AfterClass
    public static void tearDown() {
        hooverApp.stop();
    }

    @Test
    public void canSuccessfullyCallTheService() throws UnirestException {
        HttpResponse<JsonNode> jsonNodeHttpResponse = Unirest.post("http://localhost:9897/hoover")
                .body("{\n" +
                        "  \"roomSize\" : [5, 5],\n" +
                        "  \"coords\" : [1, 2],\n" +
                        "  \"patches\" : [\n" +
                        "    [1, 0],\n" +
                        "    [2, 2],\n" +
                        "    [2, 3]\n" +
                        "  ],\n" +
                        "  \"instructions\" : \"NNESEESWNWW\"\n" +
                        "}")
                .asJson();

        JSONArray actualCoords = jsonNodeHttpResponse.getBody().getObject().getJSONArray("coords");
        assertEquals(1, actualCoords.get(0));
        assertEquals(3, actualCoords.get(1));
        assertEquals(1, jsonNodeHttpResponse.getBody().getObject().getInt("patches"));

        assertEquals(jsonNodeHttpResponse.getStatus(), 200);
        assertEquals("application/json", jsonNodeHttpResponse.getHeaders().get("Content-Type").get(0));
    }

    @Test
    public void handlesBusinessErrorsCorrectly() throws UnirestException {
        HttpResponse<JsonNode> jsonNodeHttpResponse = Unirest.post("http://localhost:9897/hoover")
                .body("{\n" +
                        "  \"roomSize\" : [5, 5],\n" +
                        "  \"coords\" : [1, 2],\n" +
                        "  \"patches\" : [\n" +
                        "    [1, 0],\n" +
                        "    [2, 2],\n" +
                        "    [2, 3]\n" +
                        "  ],\n" +
                        "  \"instructions\" : \"XXXX\"\n" +
                        "}")
                .asJson();

        String errorMessage = jsonNodeHttpResponse.getBody().getObject().getString("error");
        assertEquals("Unrecognized instruction X", errorMessage);

        assertEquals(jsonNodeHttpResponse.getStatus(), 400);
        assertEquals("application/json", jsonNodeHttpResponse.getHeaders().get("Content-Type").get(0));
    }

    @Test
    public void handlesInfrastructureErrorsCorrectly() throws UnirestException {
        HttpResponse<JsonNode> jsonNodeHttpResponse = Unirest.post("http://localhost:9897/hoover")
                .body("ABC123")
                .asJson();

        String errorMessage = jsonNodeHttpResponse.getBody().getObject().getString("error");
        assertEquals("Invalid JSON", errorMessage);

        assertEquals(jsonNodeHttpResponse.getStatus(), 400);
        assertEquals("application/json", jsonNodeHttpResponse.getHeaders().get("Content-Type").get(0));
    }
}
