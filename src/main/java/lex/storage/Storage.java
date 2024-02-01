package lex.storage;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lex.Constant;
import lex.tasks.Task;

public class Storage {
    private final String filePath;
    private final ObjectMapper mapper;

    public Storage() {
        this(new ObjectMapper(), "./data.json");
    }

    public Storage(ObjectMapper mapper, String filePath) {
        mapper.registerModule(new JavaTimeModule());

        this.mapper = mapper;
        this.filePath = filePath;
    }

    public List<Task> load() {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            return new ArrayList<>();
        }

        try {
            return mapper.readValue(Files.readAllBytes(path), new TypeReference<>(){});
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

            byte[] content = mapper.writeValueAsBytes(tasks);
            Files.write(path, content);
        } catch (Exception e) {
            System.out.println("Error saving file.");
            System.out.println(Constant.SEPERATOR);
        }
    }
}
