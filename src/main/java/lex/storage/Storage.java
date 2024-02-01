package lex.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import lex.Constant;
import lex.tasks.Deadline;
import lex.tasks.Event;
import lex.tasks.Task;
import lex.tasks.Todo;

public class Storage {
    private final String filePath;
    private final Gson gson;

    public Storage() {
        this(new GsonBuilder(), "./data.json");
    }

    public Storage(GsonBuilder builder, String filePath) {
        Deserializer<Task> taskDeserializer = new Deserializer<>("type");
        taskDeserializer.bind("Todo", Todo.class);
        taskDeserializer.bind("Deadline", Deadline.class);
        taskDeserializer.bind("Event", Event.class);

        builder.registerTypeAdapter(Task.class, taskDeserializer);

        this.gson = builder.create();
        this.filePath = filePath;
    }

    public List<Task> load() {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            return new ArrayList<>();
        }

        try {
            String content = new String(Files.readAllBytes(path));
            return gson.fromJson(content, new TypeToken<>() {});
        } catch (Exception e) {
            System.out.println("Error parsing file.");
            System.out.println(Constant.SEPERATOR);
            return new ArrayList<>();
        }
    }

    public void save(List<Task> tasks) {
        Path path = Paths.get(filePath);

        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
            }

            String content = gson.toJson(tasks);
            Files.write(path, content.getBytes());
        } catch (Exception e) {
            System.out.println("Error saving file.");
            System.out.println(Constant.SEPERATOR);
        }
    }
}
