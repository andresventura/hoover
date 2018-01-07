package com.yoti;

import com.yoti.domain.instruction.DrivingInstructionFactory;
import com.yoti.route.GsonJSONSerializer;
import com.yoti.route.HooverRoute;
import com.yoti.route.JSONSerializer;
import com.yoti.service.HooverService;


public class HooverApp {
    private final HooverRoute hooverRoute;

    public HooverApp(int port) {
        // Could have used a DI framework but keeping it simple
        HooverService service = new HooverService(new DrivingInstructionFactory());
        JSONSerializer jsonSerializer = new GsonJSONSerializer();
        this.hooverRoute = new HooverRoute(service, jsonSerializer,port);
    }

    void start() {
        this.hooverRoute.startRoutes();
    }

    void stop() {
        this.hooverRoute.stopRoutes();
    }

    public static void main(String[] args) {
        int port = 8080; // Should come form external config but keeping it simple
        new HooverApp(port).start();
    }
}
