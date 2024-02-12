package baron.managers;

import java.util.ArrayList;
import java.util.List;

import baron.dao.DeadlineDao;
import baron.dao.EventDao;
import baron.dao.TaskDao;
import baron.dao.TodoDao;
import baron.enums.Commands;
import baron.enums.TaskType;
import baron.models.Deadline;
import baron.models.Event;
import baron.models.Task;
import baron.models.Todo;
import baron.utils.StringUtils;

// TODO: Stretch goal: add exceptions for marking and listing non-existent indexes

/**
 * Manages response handling given user's input
 */
public class TaskManager {

    private final List<Task> tasks = new ArrayList<>();

    /**
     * Initialises data from the different files so that it's all shown in 1 task list
     */
    public TaskManager() {
        List<Todo> todos = TodoDao.getTodos();
        tasks.addAll(todos);
        List<Deadline> deadlines = DeadlineDao.getDeadlines();
        tasks.addAll(deadlines);
        List<Event> events = EventDao.getEvents();
        tasks.addAll(events);
    }

    protected static String getValue(String input) {
        if (input.split(" ").length <= 1) {
            throw new IllegalArgumentException("Value expected but not found");
        }
        return input.split(" ")[1];
    }

    /**
     * Handles user input
     *
     * @param input Input given by user
     */
    public String handleInput(String input) {
        try {
            input = input.trim();
            String command = getCommand(input);
            // Decided to pass the entire input instead because otherwise we would have to parse the input into
            // command and value which would not be appropriate here since it includes a list() function too
            if (command.equals(TodoDao.NAME)) {
                return addTodo(input);
            } else if (command.equals(DeadlineDao.NAME)) {
                return addDeadline(input);
            } else if (command.equals(EventDao.NAME)) {
                return addEvent(input);
            } else if (command.equals(Commands.LIST.getCommand())) {
                return UiManager.list(this.tasks);
            } else if (command.equals(Commands.MARK.getCommand())) {
                return mark(input, true);
            } else if (command.equals(Commands.UNMARK.getCommand())) {
                return mark(input, false);
            } else if (command.equals(Commands.DELETE.getCommand())) {
                return delete(input);
            } else if (command.equals(Commands.BYE.getCommand())) {
                // Exits application
                System.exit(0);
            } else if (command.equals(Commands.FIND.getCommand())) {
                return this.find(input);
            } else {
                return "Command not recognized";
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ex.getMessage();
        }
        return "Something went wrong, please check the logs for details";
    }

    protected static String getCommand(String input) {
        return input.split(" ")[0];
    }

    private String addTodo(String input) {
        Todo todo = TodoDao.getFrom(input);
        TaskDao.add(TodoDao.NAME, todo);
        return this.add(todo);
    }

    private String addDeadline(String input) {
        Deadline deadline = DeadlineDao.getFrom(input);
        DeadlineDao.add(DeadlineDao.NAME, deadline);
        return this.add(deadline);
    }

    private String addEvent(String input) {
        Event event = EventDao.getFrom(input);
        EventDao.add(EventDao.NAME, event);
        return this.add(event);
    }

    private String mark(String input, boolean isDone) {
        int taskIndex = Integer.parseInt(StringUtils.getValueOfCommand(input, Commands.MARK.getCommand(), null)) - 1;
        assert (taskIndex >= 0 && taskIndex < this.tasks.size()) : "Accessing a task that doesn't exist";
        Task task = this.get(taskIndex);
        TaskType type = getTaskType(task.toString());
        task = TaskDao.mark(task.getId(), type.getCommand(), task, isDone);
        return UiManager.mark(task, isDone);
    }

    protected String delete(String input) {
        int i = Integer.parseInt(StringUtils.getValueOfCommand(input, Commands.DELETE.getCommand(), null)) - 1;
        Task task = this.tasks.remove(i);
        TaskType type = getTaskType(task.toString());
        TaskDao.delete(type.getCommand(), task.getId());
        return UiManager.delete(task, this.tasks.size());
    }

    /**
     * Prints out a list of tasks that match the search term (fuzzy search)
     *
     * @param input user input
     */
    private String find(String input) {
        String term = StringUtils.getValueOfCommand(input, Commands.FIND.getCommand(), null);
        List<Task> filteredTasks = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = this.tasks.get(i);
            if (task.getName().contains(term)) {
                filteredTasks.add(task);
            }
        }
        return UiManager.find(filteredTasks);
    }

    protected String add(Task task) {
        tasks.add(task);
        return UiManager.add(task, tasks.size());
    }

    private Task get(int i) {
        return this.tasks.get(i);
    }

    /**
     * Determines type of task (todo, event) based off the command line string
     * Very simplistic, TODO: Validate against inputs with [T[ to avoid invalid clasification
     *
     * @param input the formatted line, e.g. [T][X} Wash dishes
     * @return The type of task
     */
    private TaskType getTaskType(String input) {
        if (input.contains("[T]")) {
            return TaskType.TODO;
        } else if (input.contains("[D]")) {
            return TaskType.DEADLINE;
        } else if (input.contains("[E]")) {
            return TaskType.EVENT;
        }
        return null;
    }

}
