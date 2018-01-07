package com.yoti.route;

import com.google.gson.*;
import com.yoti.domain.Coordinates;

import java.lang.reflect.Type;

public class GsonJSONSerializer implements JSONSerializer {

    private Gson gson = createGson();

    @Override
    public String toJson(Object src) {
        return gson.toJson(src);
    }

    @Override
    public <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    private class CoordinatesSerializer implements JsonSerializer<Coordinates> {
        public JsonElement serialize(Coordinates src, Type typeOfSrc, JsonSerializationContext context) {
            JsonArray array = new JsonArray();
            array.add(src.getX());
            array.add(src.getY());
            return array;
        }
    }
    private class CoordinatesDeserializer implements JsonDeserializer<Coordinates> {
        public Coordinates deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            JsonArray asJsonArray = json.getAsJsonArray();
            return Coordinates.fromXY(asJsonArray.get(0).getAsInt(), asJsonArray.get(1).getAsInt());
        }
    }

    private Gson createGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Coordinates.class, new CoordinatesSerializer());
        gsonBuilder.registerTypeAdapter(Coordinates.class, new CoordinatesDeserializer());
        return gsonBuilder.create();
    }
}
