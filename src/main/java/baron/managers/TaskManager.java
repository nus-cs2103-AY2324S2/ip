package baron.managers;

import java.util.ArrayList;
import java.util.List;

import baron.dao.ClientDao;
import baron.dao.DeadlineDao;
import baron.dao.EventDao;
import baron.dao.TaskDao;
import baron.dao.TodoDao;
import baron.enums.Command;
import baron.enums.TaskType;
import baron.models.Client;
import baron.models.Deadline;
import baron.models.Event;
import baron.models.Task;
import baron.models.Todo;
import baron.utils.StringUtils;

// TODO: Stretch goal: add exceptions for marking and listing non-existent indexes

/**
 * Manages response handling given user's input.
 */
public class TaskManager {

    private final List<Task> tasks = new ArrayList<>();
    private final List<Client> clients = new ArrayList<>();
    private ClientDao clientDao = new ClientDao();
    /**
     * Initialises data from the different files so that it's all shown in 1 task list.
     */
    public TaskManager() {
        List<Todo> todos = TodoDao.getTodos();
        tasks.addAll(todos);
        List<Deadline> deadlines = DeadlineDao.getDeadlines();
        tasks.addAll(deadlines);
        List<Event> events = EventDao.getEvents();
        tasks.addAll(events);

        clients.addAll(clientDao.getItems());
    }

    /**
     * Handles user input.
     *
     * @param input Input given by user
     */
    public String handleInput(String input) {
        try {
            input = input.trim();
            String command = StringUtils.getCommand(input);
            // Decided to pass the entire input instead because otherwise we would have to parse the input into
            // command and value which would not be appropriate here since it includes a list() function too
            if (command.equals(TodoDao.NAME)) {
                return addTodo(input);
            } else if (command.equals(DeadlineDao.NAME)) {
                return addDeadline(input);
            } else if (command.equals(EventDao.NAME)) {
                return addEvent(input);
            } else if (command.equals(ClientDao.NAME)) {
                return addClient(input);
            } else if (command.equals(Command.LIST_TASKS.getCommand())) {
                return UiManager.list(this.tasks);
            } else if (command.equals(Command.LIST_CLIENTS.getCommand())) {
                return UiManager.list(this.clients);
            } else if (command.equals(Command.MARK.getCommand())) {
                return mark(input, true);
            } else if (command.equals(Command.UNMARK.getCommand())) {
                return mark(input, false);
            } else if (command.equals(Command.DELETE_TASK.getCommand())) {
                return delete(input);
            } else if (command.equals(Command.DELETE_CLIENT.getCommand())) {
                return deleteClient(input);
            } else if (command.equals(Command.BYE.getCommand())) {
                // Exits application
                System.exit(0);
            } else if (command.equals(Command.FIND.getCommand())) {
                return this.find(input);
            } else if (command.equals(Command.HELP.getCommand())) {
                return UiManager.help();
            } else {
                return "Command not recognized";
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ex.getMessage();
        }
        return "Something went wrong, please check the logs for details";
    }

    // The below 3 functions help route task to the corresponding data files
    private String addTodo(String input) {
        Todo todo = TodoDao.getFrom(input);
        TaskDao.add(TodoDao.NAME, todo);
        return this.addTask(todo);
    }

    private String addDeadline(String input) {
        Deadline deadline = DeadlineDao.getFrom(input);
        DeadlineDao.add(DeadlineDao.NAME, deadline);
        return this.addTask(deadline);
    }

    private String addEvent(String input) {
        Event event = EventDao.getFrom(input);
        EventDao.add(EventDao.NAME, event);
        return this.addTask(event);
    }

    private String addClient(String input) {
        Client client = clientDao.fromInputString(input);
        clientDao.add(client);
        clients.add(client);
        return UiManager.add(client, clients.size());
    }

    private int getItemIndex(String input, Command command) {
        int taskIndex = Integer.parseInt(StringUtils.getValueOfCommand(input, command.getCommand(), null)) - 1;
        if (taskIndex < 0) {
            throw new IllegalArgumentException("Error: Negative index should not be specified");
        }
        return taskIndex;
    }

    private String mark(String input, boolean isDone) {
        int taskIndex = getItemIndex(input, Command.MARK);
        assert (taskIndex >= 0 && taskIndex < this.tasks.size()) : "Accessing a task that doesn't exist";
        Task task = this.get(taskIndex);
        TaskType type = getTaskType(task);
        task = TaskDao.mark(task.getId(), type.getTaskType(), task, isDone);
        return UiManager.mark(task, isDone);
    }

    protected String delete(String input) {
        int taskIndex = getItemIndex(input, Command.DELETE_TASK);
        Task task = this.tasks.remove(taskIndex);
        TaskType type = getTaskType(task);
        TaskDao.delete(type.getTaskType(), task.getId());
        return UiManager.delete(task, this.tasks.size());
    }

    protected String deleteClient(String input) {
        int clientIndex = getItemIndex(input, Command.DELETE_CLIENT);
        Client client = this.clients.remove(clientIndex);
        clientDao.delete(client.getId());
        return UiManager.delete(client, this.clients.size());
    }

    /**
     * Prints out a list of tasks that match the search term (fuzzy search).
     *
     * @param input user input
     */
    private String find(String input) {
        String term = StringUtils.getValueOfCommand(input, Command.FIND.getCommand(), null);
        List<Task> filteredTasks = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = this.tasks.get(i);
            if (task.getName().toLowerCase().contains(term)) {
                filteredTasks.add(task);
            }
        }
        return UiManager.find(filteredTasks);
    }

    /**
     * Adds a task to the task list and returns formatted output, does not actually add it to the database.
     *
     * @param task Task to aadd
     * @return returns Bot response output
     */
    protected String addTask(Task task) {
        tasks.add(task);
        return UiManager.add(task, tasks.size());
    }

    /**
     * Gets the task in the list. Serves as an abstraction.
     * Useful for possible future if we want to change how we store our tasks in memory.
     *
     * @param i index of task to get
     * @return Task object
     */
    private Task get(int i) {
        return this.tasks.get(i);
    }

    /**
     * Determines type of task (todo, event) based off the command line string
     * and is used to determine which data file to modify
     * Converts the task to its string representation and takes the first 3 characters of the task
     * and thendetermines the type of task from this.
     *
     * @param task Task to check type of, e.g. [T][X} Wash dishes
     *              We take the string instead of task itself because
     * @return The type of task
     */
    private TaskType getTaskType(Task task) {
        String taskType = task.toString().substring(0, 3);
        System.out.println(taskType);
        if (taskType.equals("[T]")) {
            return TaskType.TODO;
        } else if (taskType.equals("[D]")) {
            return TaskType.DEADLINE;
        } else if (taskType.equals("[E]")) {
            return TaskType.EVENT;
        }
        return null;
    }

}
