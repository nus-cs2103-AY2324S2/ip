public class Parser {
  private TaskList taskList;
  private Storage storage;

  public Parser(TaskList taskList, Storage storage) {
    this.taskList = taskList;
    this.storage = storage;
  }

  public void parseInput(String input) throws DukeException {
    String command = input.split(" ")[0];
    switch (command) {
      case "help":
        getListOfCommands();
        break;
      case "bye":
        break;
      case "list":
        taskList.printList();
        break;
      case "mark":
        processMarkCommand(input);
        break;
      case "unmark":
        processUnmarkCommand(input);
        break;
      case "delete":
        processDeleteCommand(input);
        break;
      case "event":
        processEventCommand(input);
        break;
      case "deadline":
        processDeadlineCommand(input);
        break;
      case "todo":
        processToDoCommand(input);
        break;
      default:
        throw new DukeException("I'm sorry, but I don't know what that means :-(");
    }
  }

  /*
   * Processes the mark command
   */
  private void processMarkCommand(String input) throws DukeException {
    if (input.trim().equals("mark")) {
      throw new DukeException("The index of a task cannot be empty.");
    }
    int markIndex = Integer.parseInt(input.split(" ")[1]);
    if (markIndex > taskList.getTaskCount()) {
      throw new DukeException("The index of a task cannot be greater than the number of tasks.");
    }
    if (markIndex <= 0) {
      throw new DukeException("The index of a task cannot be 0 or negative.");
    }
    Task taskToMark = taskList.getTask(markIndex);
    if (taskToMark == null) {
      throw new DukeException("The task at index " + markIndex + " does not exist.");
    }
    taskToMark.markAsDone();
    System.out.println("Nice! I've marked this task as done:");
    System.out.println(taskToMark);
    storage.saveTaskList(taskList.getTasksList());
  }

  /*
   * Processes the unmark command
   */
  private void processUnmarkCommand(String input) throws DukeException {
    if (input.trim().equals("unmark")) {
      throw new DukeException("The index of a task cannot be empty.");
    }
    int unmarkIndex = Integer.parseInt(input.split(" ")[1]);
    if (unmarkIndex > taskList.getTaskCount()) {
      throw new DukeException("The index of a task cannot be greater than the number of tasks.");
    }
    if (unmarkIndex <= 0) {
      throw new DukeException("The index of a task cannot be 0 or negative.");
    }
    Task taskToUnmark = taskList.getTask(unmarkIndex - 1);
    if (taskToUnmark == null) {
      throw new DukeException("The task at index " + unmarkIndex + " does not exist.");
    }
    taskToUnmark.unmarkAsDone();

    System.out.println("OK, I've marked this task as not done yet:");
    System.out.println(taskToUnmark);
    storage.saveTaskList(taskList.getTasksList());
  }

  /*
   * Processes the delete command
   */
  private void processDeleteCommand(String input) throws DukeException {
    if (input.trim().equals("delete")) {
      throw new DukeException("The index of a task cannot be empty.");
    }
    int deleteIndex = Integer.parseInt(input.split(" ")[1]);
    if (deleteIndex > taskList.getTaskCount()) {
      throw new DukeException("The index of a task cannot be greater than the number of tasks.");
    }
    if (deleteIndex <= 0) {
      throw new DukeException("The index of a task cannot be 0 or negative.");
    }
    Task taskToDelete = taskList.getTask(deleteIndex);
    if (taskToDelete == null) {
      throw new DukeException("The task at index " + deleteIndex + " does not exist.");
    }
    taskList.removeTask(deleteIndex);
    System.out.println("Noted. I've removed this task:");
    System.out.println(taskToDelete);
    storage.saveTaskList(taskList.getTasksList());
  }

  /*
   * Processes the event command
   */
  private void processEventCommand(String input) throws DukeException {
    Event newEvent = Event.createFromInput(input);
    taskList.addTask(newEvent);
    storage.saveTaskList(taskList.getTasksList());
  }

  /*
   * Processes the deadline command
   */
  private void processDeadlineCommand(String input) throws DukeException {
    Deadline newDeadline = Deadline.createFromInput(input);
    taskList.addTask(newDeadline);
    storage.saveTaskList(taskList.getTasksList());
  }

  /*
   * Processes the todo command
   */
  private void processToDoCommand(String input) throws DukeException {
    ToDo newToDo = ToDo.createFromInput(input);
    taskList.addTask(newToDo);
    storage.saveTaskList(taskList.getTasksList());
  }

  /*
   * Prints available commands
   */
  private void getListOfCommands() {
    System.out.println("Here are the available commands:");
    System.out.println("list");
    System.out.println("todo <description>");
    System.out.println("deadline <description> /by <date>");
    System.out.println("event <description> /from <date> /to <date>");
    System.out.println("delete <index>");
    System.out.println("mark <index>");
    System.out.println("unmark <index>");
    System.out.println("bye");
  }

}
