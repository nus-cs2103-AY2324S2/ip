package lex.storage;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lex.TaskList;

public class Storage {
    private final String filePath;
    private final ObjectMapper mapper;

    public Storage(String filePath) {
        this(new ObjectMapper(), filePath);
    }

    public Storage(ObjectMapper mapper, String filePath) {
        mapper.registerModule(new JavaTimeModule());

        this.mapper = mapper;
        this.filePath = filePath;
    }

    public TaskList load() throws Exception {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            return new TaskList();
        }

        try {
            return mapper.readValue(Files.readAllBytes(path), new TypeReference<>(){});
        } catch (Exception e) {
            throw new Exception("Error parsing file.");
        }
    }

    public void save(TaskList tasks) throws Exception {
        Path path = Paths.get(filePath);

        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
            }

            byte[] content = mapper.writeValueAsBytes(tasks);
            Files.write(path, content);
        } catch (Exception e) {
            throw new Exception("Error saving file.");
        }
    }
}
