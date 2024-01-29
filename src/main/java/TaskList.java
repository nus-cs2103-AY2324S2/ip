import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TaskList implements Iterable<Task>{
    protected List<Task> tasks;


    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Returns an iterator over elements of type {@code Task}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }

    public void addToDo(String description, boolean isDone) {
        try {
            if (description.isEmpty()) {
                throw new ChatBotParameterException("Missing description for ToDo \n" +
                        "try: todo <todo_name>");
            }
            this.addTask(new ToDo(description, isDone));
        } catch (ChatBotParameterException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addToDo(String parameters) {
        this.addToDo(parameters, false);
    }


    public void addDeadline(String description, String by, boolean isDone) {
        try {
            LocalDateTime byDateTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("d/M/yyyy Hmm"));
            this.addTask(new Deadline(description, byDateTime, isDone));
        } catch (DateTimeParseException e) {
            System.out.println("Wrong Date Time format " + by);
        }
    }

    public void addDeadline(String parameters) {
        try {
            if (parameters.isEmpty()) {
                throw new ChatBotParameterException("There is no description and by for Deadline \n" +
                        "try: deadline <deadline_name> /by <by>");
            }
            String[] parametersArr = parameters.split(" /by ");
            if (parametersArr.length == 1) {
                throw new ChatBotParameterException("Missing description or by for Deadline \n" +
                        "try: deadline <deadline_name> /by <by>");
            }
            this.addDeadline(parametersArr[0], parametersArr[1], false);
        } catch (ChatBotParameterException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addEvent(String parameters) {
        try {
            if (parameters.isEmpty()) {
                throw new ChatBotParameterException("There is no description and from and to for Event \n" +
                        "try: event <event_name> /by <from> /to <to>");
            }
            String[] parametersArr = parameters.split(" /from | /to ");
            if (parametersArr.length < 3) {
                throw new ChatBotParameterException("Missing description and/or from and/or to for Event \n" +
                        "try: event <event_name> /by <from> /to <to>");
            }
            this.addEvent(parametersArr[0], parametersArr[1], parametersArr[2], false);
        } catch (ChatBotParameterException e) {
            System.out.println(e.getMessage());
        }

    }

    public void addEvent(String description, String from, String to, boolean isDone) {
        this.addTask(new Event(description, from, to, isDone));
}
