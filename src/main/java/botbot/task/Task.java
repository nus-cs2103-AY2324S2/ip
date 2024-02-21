package botbot.task;

import botbot.exception.InvalidDateException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Task {
    String taskName;
    Boolean done = false;
    public static Task EMPTY_TASK = new Task("");

    public Task(String task) {
        this.taskName = task;
    }
    public String getName() {
        return this.taskName;
    }
    public void mark() {
        this.done = true;
    }
    public void unmark() { this.done = false; }

    /**
     * Returns string format of a task
     * @return String rep
     */
    public String getRep() {
        // Returns representation of the task (including done)
        String doneChar = (this.done) ? "X" : " ";
        return String.format("[%s] %s", doneChar, this.taskName);
    }

    /**
     * Returns format for saving into file
     * @return Save rep
     */
    public String fileRep() {
        return String.format("%s|%d", taskName, done ? 1 : 0);
    }

    /**
     * Reads file rep of a task and converts to Task obj
     * @param s File rep of task
     * @return Task object
     * @throws InvalidDateException
     */
    public static Task parseTask(String s) throws InvalidDateException {
        String[] arr = s.split("\\|", 3);
        Task task = EMPTY_TASK;
        switch(arr[0]) {
            case "T":
                task = new ToDo(arr[1]);
                break;
            case "D":
                task = new Deadline(arr[1], arr[2].split("\\|", 2)[1]);
                break;
            case "E":
                task = new Event(arr[1], arr[2].split("\\|", 3)[1], arr[2].split("\\|", 3)[2]);
                break;
        }
        if (arr[2].startsWith("1")) {
            task.mark();
        }
        return task;
    }
    public static DateTimeFormatter TIME_FORMAT_IN = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static DateTimeFormatter TIME_FORMAT_OUT = DateTimeFormatter.ofPattern("HH:mm yyyy-MM-dd");
    public static LocalDateTime parseDate(String s) throws InvalidDateException {
        try{
            return LocalDateTime.parse(s, Task.TIME_FORMAT_IN);
        } catch (Exception e) {
            throw new InvalidDateException();
        }
    }
}
