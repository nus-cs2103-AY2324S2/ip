package nihao.util;

import java.io.IOException;
import java.time.LocalDateTime;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import nihao.action.task.DeadlineTask;
import nihao.action.task.EventTask;
import nihao.action.task.Task;
import nihao.action.task.TodoTask;
import nihao.handler.DateTimeHandler;

/**
 * Represents a custom TypeAdapter for serializing/deserializing JSON with Gson.
 */
public class TaskTypeAdapter extends TypeAdapter<Task> {
    @Override
    public void write(JsonWriter jsonWriter, Task task) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("type");
        if (task instanceof TodoTask) {
            jsonWriter.value(TodoTask.class.getSimpleName());
        } else if (task instanceof DeadlineTask) {
            jsonWriter.value(DeadlineTask.class.getSimpleName());
        } else if (task instanceof EventTask) {
            jsonWriter.value(EventTask.class.getSimpleName());
        }
        jsonWriter.name("taskName");
        jsonWriter.value(task.getTaskName());
        jsonWriter.name("isCompleted");
        jsonWriter.value(task.getIsCompleted());
        if (task instanceof TodoTask) {
        } else if (task instanceof DeadlineTask) {
            jsonWriter.name("by");
            jsonWriter.value(((DeadlineTask) task).getByString());
        } else if (task instanceof EventTask) {
            jsonWriter.name("from");
            jsonWriter.value(((EventTask) task).getFromString());
            jsonWriter.name("to");
            jsonWriter.value(((EventTask) task).getToString());
        }
        jsonWriter.endObject();
    }

    @Override
    public Task read(JsonReader jsonReader) throws IOException {
        Task ret = null;
        jsonReader.beginObject();
        String fieldName = null;
        JsonToken token = jsonReader.peek();
        if (token.equals(JsonToken.NAME)) {
            fieldName = jsonReader.nextName();
        }
        token = jsonReader.peek();
        String type = jsonReader.nextString();
        token = jsonReader.peek();
        fieldName = jsonReader.nextName();
        token = jsonReader.peek();
        String taskName = jsonReader.nextString();
        token = jsonReader.peek();
        fieldName = jsonReader.nextName();
        token = jsonReader.peek();
        boolean isCompleted = jsonReader.nextBoolean();

        switch (type) {
        case "TodoTask":
            ret = new TodoTask(taskName);
            ret.setIsCompleted(isCompleted);
            break;
        case "DeadlineTask":
            token = jsonReader.peek();
            if (token.equals(JsonToken.NAME)) {
                fieldName = jsonReader.nextName();
            }
            token = jsonReader.peek();
            LocalDateTime by = DateTimeHandler.deserialize(jsonReader.nextString());
            ret = new DeadlineTask(taskName, by);
            ret.setIsCompleted(isCompleted);
            break;
        case "EventTask":
            token = jsonReader.peek();
            fieldName = jsonReader.nextName();
            token = jsonReader.peek();
            LocalDateTime from = DateTimeHandler.deserialize(jsonReader.nextString());
            token = jsonReader.peek();
            fieldName = jsonReader.nextName();
            token = jsonReader.peek();
            LocalDateTime to = DateTimeHandler.deserialize(jsonReader.nextString());
            ret = new EventTask(taskName, from, to);
            ret.setIsCompleted(isCompleted);
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + type);
        }

        jsonReader.endObject();
        return ret;
    }
}
