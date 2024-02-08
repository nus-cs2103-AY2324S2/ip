package nihao.handler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import nihao.action.task.Task;
import nihao.exception.IndexOutOfBoundsException;
import nihao.util.TaskTypeAdapter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DataHandler {
    private static final String fileNamePath = "data/PersistentData.json";
    static ArrayList<Task> tasks = new ArrayList<>();
    private DataHandler() {};

    private static void readFromJson() throws IOException {
        FileReader fileReader = new FileReader(fileNamePath);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeHierarchyAdapter(Task.class, new TaskTypeAdapter());
        Gson gson = gsonBuilder.create();
        Type objectType = TypeToken.getParameterized(ArrayList.class, Task.class).getType(); // Type objectType = new TypeToken<ArrayList<Task>>() {}.getType();
        tasks = gson.fromJson(fileReader, objectType);
        fileReader.close();
    }

    private static void saveToJson() throws IOException {
        FileWriter fileWriter = new FileWriter(fileNamePath);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeHierarchyAdapter(Task.class, new TaskTypeAdapter());
        Gson gson = gsonBuilder.create();
        gson.toJson(tasks, fileWriter);
        fileWriter.flush();
        fileWriter.close();
    }

    public static void read() {
        try {
            readFromJson();
        } catch (IOException e) {
            Path newFilePath = Paths.get(fileNamePath);
            try {
                Files.createFile(newFilePath);
            } catch (IOException ex) {
                PrintHandler.printException(ex);
            }
        }
    }

    public static void save() {
        try {
            saveToJson();
        } catch (IOException e) {
            PrintHandler.printException(e);
        }
    }

    public static void handleData(Task task) {
        tasks.add(task);
        // Todo: Exception handling
    }

    public static void markTask(int index) throws IndexOutOfBoundsException {
        if (index > size() || index <= 0) {
            throw new IndexOutOfBoundsException(index, size());
        }
        tasks.get(index - 1).mark();
    }

    public static void unmarkTask(int index) throws IndexOutOfBoundsException {
        if (index > size() || index <= 0) {
            throw new IndexOutOfBoundsException(index, size());
        }
        tasks.get(index - 1).unmark();
    }

    public static void deleteTask(int index) throws IndexOutOfBoundsException {
        if (index > size() || index <= 0) {
            throw new IndexOutOfBoundsException(index, size());
        }
        tasks.remove(index - 1);
    }

    public static void deleteAll() {
        tasks = new ArrayList<>();
    }

    public static int size() {
        return tasks.size();
    }

    public static ArrayList<Task> getData() {
        return tasks;
    }

    public static Task getTask(int index) throws IndexOutOfBoundsException{
        if (index > size() || index <= 0) {
            throw new IndexOutOfBoundsException(index, size());
        }
        return tasks.get(index - 1);
    }
}
