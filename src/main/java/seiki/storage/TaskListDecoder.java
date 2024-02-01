package seiki.storage;

import static seiki.common.DateTime.DATE_TIME_FORMATTER;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import seiki.data.TaskList;
import seiki.data.exception.SeikiException;
import seiki.data.task.Deadline;
import seiki.data.task.Event;
import seiki.data.task.Task;
import seiki.data.task.ToDo;

public class TaskListDecoder {

    public static TaskList decodeTaskList(List<String> encodedTaskList) throws SeikiException {
        final ArrayList<Task> decodedTasks = new ArrayList<>();
        for (String encodedTask : encodedTaskList) {
            decodedTasks.add(decodeTaskFromString(encodedTask));
        }
        return new TaskList(decodedTasks);
    }

    public static Task decodeTaskFromString(String encodedTask) throws SeikiException {
        StringTokenizer st = new StringTokenizer(encodedTask, "|");

        String taskType = st.nextToken().trim();
        boolean isDone = "1".equals(st.nextToken().trim());
        String taskTitle = st.nextToken().trim();
        Task task;

        switch (taskType) {
        case "T":
            task = new ToDo(taskTitle, isDone);
            break;
        case "D":
            LocalDateTime dateTime = LocalDateTime.parse(st.nextToken().trim(), DATE_TIME_FORMATTER);
            task = new Deadline(taskTitle, dateTime, isDone);
            break;
        case "E":
            String[] dateTimeArr = st.nextToken().split("-");
            LocalDateTime startDateTime = LocalDateTime.parse(dateTimeArr[0].trim(), DATE_TIME_FORMATTER);
            LocalDateTime endDateTime = LocalDateTime.parse(dateTimeArr[1].trim(), DATE_TIME_FORMATTER);
            task = new Event(taskTitle, startDateTime, endDateTime, isDone);
            break;
        default:
            throw new SeikiException("Invalid task type");
        }

        return task;
    }
}
