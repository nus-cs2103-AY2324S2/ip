package lex.storage;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.*;

public class Deserializer<T> implements JsonDeserializer<T> {
    private final String identifier;
    private final Gson gson;
    private final Map<String, Class<? extends T>> registry;

    public Deserializer(String identifier) {
        this.identifier = identifier;
        this.gson = new Gson();
        this.registry = new HashMap<>();
    }

    public void bind(String type, Class<? extends T> classT) {
        registry.put(type, classT);
    }

    @Override
    public T deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();
        JsonElement element = object.get(identifier);

        Class<? extends T> classT = registry.get(element.getAsString());
        return gson.fromJson(object, classT);
    }
}
