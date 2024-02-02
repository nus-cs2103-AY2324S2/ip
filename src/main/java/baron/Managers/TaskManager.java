package baron.Managers;

import baron.Dao.DeadlineDao;
import baron.Dao.EventDao;
import baron.Dao.TaskDao;
import baron.Dao.TodoDao;
import baron.Enums.Commands;
import baron.Enums.TaskType;
import baron.Models.Deadline;
import baron.Models.Event;
import baron.Models.Task;
import baron.Models.Todo;
import baron.Utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

// TODO: Stretch goal: add exceptions for marking and listing non-existent indexes
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

  private Task get(int i) {
    return this.tasks.get(i);
  }

  protected void add(Task task) {
    tasks.add(task);
    UIManager.add(task, tasks.size());
  }

  protected void delete(String input) {
    int i = Integer.parseInt(StringUtils.getValueOfCommand(input, Commands.DELETE.getCommand(), null)) - 1;
    Task task = this.tasks.remove(i);
    TaskType type = getTaskType(task.toString());
    TaskDao.delete(type.getCommand(), task.getId());
    UIManager.delete(task, this.tasks.size());
  }

  protected static String getCommand(String input) {
    return input.split(" ")[0];
  }

  protected static String getValue(String input) {
    if (input.split(" ").length <= 1) throw new IllegalArgumentException("Value expected but not found");
    return input.split(" ")[1];
  }

  /**
   * Handles user input
   * @param input Input given by user
   */
  public void handleInput(String input) {
    try {
      input = input.trim();
      String command = getCommand(input);
      // Decided to pass the entire input instead because otherwise we would have to parse the input into command and value
      // which would not be appropriate here since it includes a list() function too
      if (command.equals(TodoDao.NAME)) {
        addTodo(input);
      } else if (command.equals(DeadlineDao.NAME)) {
        addDeadline(input);
      } else if (command.equals(EventDao.NAME)) {
        addEvent(input);
      } else if (command.equals(Commands.LIST.getCommand())) {
        UIManager.list(this.tasks);
      } else if (command.equals(Commands.MARK.getCommand())) {
        mark(input, true);
      } else if (command.equals(Commands.UNMARK.getCommand())) {
        mark(input, false);
      }  else if (command.equals(Commands.DELETE.getCommand())) {
        delete(input);
      } else if (command.equals(Commands.BYE.getCommand())) {
        return;
      } else if (command.equals("text")) {
        EventDao.getEvents();
      } else if (command.equals(Commands.FIND.getCommand())) {
        this.find(input);
      } else {
        throw new IllegalArgumentException("Command not recognized");
      }
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }

  }

  /**
   * Prints out a list of tasks that match the search term (fuzzy search)
   * 
   * @param input user input
   */
  private void find(String input) {
    String term = StringUtils.getValueOfCommand(input, Commands.FIND.getCommand(), null);
    List<Task> filteredTasks = new ArrayList<>();
    for (int i = 0; i < this.tasks.size(); i++) {
      Task task = this.tasks.get(i);
      if (task.getName().contains(term)) {
        filteredTasks.add(task);;
      }
    }
    UIManager.find(filteredTasks);
  }

  private void addTodo(String input) {
    Todo todo = TodoDao.getFrom(input);
    TaskDao.add(TodoDao.NAME, todo);
    this.add(todo);
  }

  private void addDeadline(String input) {
    Deadline deadline = DeadlineDao.getFrom(input);
    DeadlineDao.add(DeadlineDao.NAME, deadline);
    this.add(deadline);
  }

  private void addEvent(String input) {
    Event event = EventDao.getFrom(input);
    EventDao.add(EventDao.NAME, event);
    this.add(event);
  }

  private void mark(String input, boolean isDone) {
    int taskIndex = Integer.parseInt(StringUtils.getValueOfCommand(input, Commands.MARK.getCommand(), null)) - 1;
    Task task = this.get(taskIndex);
    TaskType type = getTaskType(task.toString());
    task = TaskDao.mark(task.getId(), type.getCommand(), task, isDone);
    UIManager.mark(task, isDone);
  }

  /**
   * Determines type of task (todo, event) based off the command line string
   * Very simplistic, TODO: Validate against inputs with [T[ to avoid invalid clasification
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
