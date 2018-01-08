package com.yoti.route;

import com.google.gson.JsonParseException;
import com.yoti.domain.InvalidRoomCoordinates;
import com.yoti.domain.instruction.UnrecognizedInstructionException;
import com.yoti.service.HooverRequest;
import com.yoti.service.HooverService;
import spark.Response;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static spark.Spark.*;

public class HooverRoute {
    private final HooverService hooverService;
    private final JSONSerializer jsonSerializer;
    private int port;

    public HooverRoute(HooverService hooverService, JSONSerializer jsonSerializer, int port) {
        this.hooverService = hooverService;
        this.jsonSerializer = jsonSerializer;
        this.port = port;
    }

    private void configure() {
        port(port);

        exception(UnrecognizedInstructionException.class, (exception, request, response) -> {
            setBadRequest(response, exception.getMessage());
        });

        exception(InvalidRoomCoordinates.class, (exception, request, response) -> {
            setBadRequest(response, exception.getMessage());
        });

        exception(JsonParseException.class, (exception, request, response) -> {
            setBadRequest(response,"Invalid JSON");
        });

        after((request, response) -> {
            response.type("application/json");
        });
    }

    private void setBadRequest(Response response, String message) {
        response.type("application/json");
        response.status(SC_BAD_REQUEST);
        response.body(formatError(message));
    }

    private String formatError(String errorMessage) {
        return String.format("{ \"error\": \"%s\" }", errorMessage);
    }

    public void startRoutes() {
        configure();
        post("/hoover", (request, response) -> hooverService.hoover(
                jsonSerializer.fromJson(request.body(), HooverRequest.class)), jsonSerializer::toJson);
    }

    public void stopRoutes() {
        stop();
    }
}
