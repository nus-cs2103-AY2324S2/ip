package util;

import action.task.DeadlineTask;
import action.task.EventTask;
import action.task.Task;
import action.task.TodoTask;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

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
            jsonWriter.value(((DeadlineTask) task).getBy());
        } else if (task instanceof EventTask) {
            jsonWriter.name("from");
            jsonWriter.value(((EventTask) task).getFrom());
            jsonWriter.name("to");
            jsonWriter.value(((EventTask) task).getTo());
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
            String by = jsonReader.nextString();
            ret = new DeadlineTask(taskName, by);
            ret.setIsCompleted(isCompleted);
            break;
        case "EventTask":
            token = jsonReader.peek();
            fieldName = jsonReader.nextName();
            token = jsonReader.peek();
            String from = jsonReader.nextString();
            token = jsonReader.peek();
            fieldName = jsonReader.nextName();
            token = jsonReader.peek();
            String to = jsonReader.nextString();
            ret = new EventTask(taskName, from, to);
            ret.setIsCompleted(isCompleted);
            break;
        }

        jsonReader.endObject();
        return ret;
    }
}