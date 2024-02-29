package actions;

import Duke.DukeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Parses the user input and uses appropriate handle functions to deal with the user input
 */
public class Parser {
        private final String input;
        private TaskList tasklist;
        private Ui ui;

        /**
         * Instantiates a parser object
         */
        public Parser(String input, TaskList tasklist, Ui ui) {
            assert input !=null : "please enter command";
            this.input = input;
            this.tasklist = tasklist;
            this.ui = ui;
        }

        /**
         * Parses the user input and handles them
         * @param tasklist a task list object
         * @param ui a ui object
         * @return returns isExit boolean that indicates if the program has reach termination
         */
        public String parse(String input) throws DukeException{
            String[] parts = input.split(" ", 2);
            String command = parts[0].toLowerCase();
            boolean isExit = false;

            switch(command) {
            case"bye":
                    isExit = true;
                    return ui.Outro();
            case "list" :
                    ArrayList<Task> tasks = tasklist.getTasks();
                    return ui.printList(tasks);
            case "mark":
                    return handleMarkCommand(tasklist, ui, parts);
            case "unmark" :
                    return handleUnmarkCommand(tasklist, ui, parts);
            case "delete" :
                    return handleDeleteCommand(tasklist, ui, parts);
            case "todo" :
                    return handleTodoCommand(tasklist, ui, parts);
            case "deadline" :
                    return handleDeadlineCommand(tasklist, ui, parts);
            case "event" :
                    return handleEventCommand(tasklist, ui, parts);
            case "find" :
                    return handleFindCommand(tasklist, ui, parts);
            case "update" :
                return handleUpdateCommand(tasklist, ui, parts);
            default:
                return ui.printInputError();
            }
        }

        public String handleMarkCommand(TaskList taskList, Ui ui, String[] parts) {
            assert parts != null && parts.length >= 2 : "Missing components for mark command";
            int num = Integer.parseInt(parts[1]);
            Task current = taskList.getTask(num -1);
          
            assert current != null : "No such task";
            taskList.markTask(current);
            return ui.printMarkMessage(current);
        }

        public String handleUnmarkCommand(TaskList taskList, Ui ui, String[] parts) {
            assert parts != null && parts.length >= 2 : "Missing components for unmark command";
            int num = Integer.parseInt(parts[1]);
            Task current = taskList.getTask(num -1);
          
            assert current != null : "No such task";
            taskList.unmarkTask(current);
            return ui.printUnmarkMessge(current);
    }

        public String handleDeleteCommand(TaskList taskList, Ui ui, String[] parts) {
            assert parts != null && parts.length >= 2 : "Missing components for delete command";
            int num = Integer.parseInt(parts[1]);
            Task current = taskList.getTask(num -1);
          
            assert current != null : "No such task";
            taskList.deleteTask(num-1);
            return ui.printDeleteMessage(current, taskList.getArraySize());
          
        }

        public String handleTodoCommand(TaskList taskList, Ui ui, String[] parts) {
            assert parts !=null : "Input should not be null";
            String task = parts[1];
            Task todo = new Todo(task, false);
            try {
               if (task.isEmpty()) {
                       throw new DukeException("Don't forget the description!");
               } else {
                       taskList.addTask(todo);
               }

           } catch (DukeException e) {
                   System.out.println(ui.printError(e.getMessage()));
           }
            return ui.printAddMessage(todo, taskList.getArraySize());
        }

        public String updateTodoCommand(TaskList taskList, Ui ui, String[] parts, int index) {
            assert parts !=null : "Input should not be null";
            try {
                String task = parts[1];
                if (task.isEmpty()) {
                    throw new DukeException("Don't forget the description!");
                } else {
                    Task todo = new Todo(task, false);
                    taskList.setTask(todo, index);
                }

            } catch (DukeException e) {
                System.out.println(ui.printError(e.getMessage()));
            }
            return ui.printUpdate();
        }

        public String handleDeadlineCommand(TaskList taskList, Ui ui, String[] parts) throws DukeException{
            assert parts !=null : "Input should not be null";
            String task = getTask(parts[1]);
            String time = getTime(parts[1]);
            String[] timeparts = time.split("by");
            LocalDateTime by = parseToLocalDate(timeparts[1].trim());
            Task deadline = new Deadline(task, false, by);
            taskList.addTask(deadline);

            try {
                if (task.isEmpty()) {
                        throw new DukeException("Don't forget the description!");
                } else if (time.isEmpty()) {
                        throw new DukeException("Don't forget the deadline!");
                } else {
                        taskList.addTask(deadline);
                }
            } catch (DukeException e) {
                    System.out.println(ui.printError(e.getMessage()));
            }
            return  ui.printAddMessage(deadline, taskList.getArraySize());
        }

        public String updateDeadlineCommand(TaskList taskList, Ui ui, String[] parts, int index) {
            assert parts !=null : "Input should not be null";
            try {
                String task = getTask(parts[1]);
                String time = getTime(parts[1]);

                if (task.isEmpty()) {
                    throw new DukeException("Don't forget the description!");
                } else if (time.isEmpty()) {
                    throw new DukeException("Don't forget the deadline!");
                } else {
                    String[] timeparts = time.split("by");
                    LocalDateTime by = parseToLocalDate(timeparts[1].trim());

                    Task deadline = new Deadline(task, false, by);
                    taskList.setTask(deadline, index);
                }
            } catch (DukeException e) {
                ui.printError(e.getMessage());
            }
            return ui.printUpdate();
        }

        public String handleEventCommand(TaskList taskList, Ui ui, String[] parts) throws DukeException{
            assert parts !=null : "Input should not be null";
            String task = getTask(parts[1]);
            String time = getTime(parts[1]);
            String[] timeparts = time.split("from");
            String[] dateParts = timeparts[1].trim() .split("/to");

            LocalDateTime from = parseToLocalDate(dateParts[0].trim());
            LocalDateTime to = parseToLocalDate(dateParts[1].trim());

            Task event = new Event(task, false, from, to);

            try {
                  if (task.isEmpty()) {
                          throw new DukeException("Don't forget the description!");
                  } else if (time.isEmpty()) {
                          throw new DukeException("Don't forget the deadline!");
                  } else if (dateParts[0].isEmpty() || dateParts[1].isEmpty()) {
                          throw new DukeException("Don't forget to include start and end time!");
                  } else {
                          taskList.addTask(event);

                          ui.printAddMessage(event, taskList.getArraySize());
                  }
            } catch (DukeException e) {
                    System.out.println(ui.printError(e.getMessage()));
            }
            return ui.printAddMessage(event, taskList.getArraySize());
        }

        public String updateEventCommand(TaskList taskList, Ui ui, String[] parts, int index) {
            assert parts !=null : "Input should not be null";
            try {
                String task = getTask(parts[1]);
                String time = getTime(parts[1]);
                String[] timeparts = time.split("from");
                String[] dateParts = timeparts[1].trim() .split("/to");

                if (task.isEmpty()) {
                    throw new DukeException("Don't forget the description!");
                } else if (time.isEmpty()) {
                    throw new DukeException("Don't forget the deadline!");
                } else if (dateParts[0].isEmpty() || dateParts[1].isEmpty()) {
                    throw new DukeException("Don't forget to include start and end time!");
                } else {
                    LocalDateTime from = parseToLocalDate(dateParts[0].trim());
                    LocalDateTime to = parseToLocalDate(dateParts[1].trim());

                    Task event = new Event(task, false, from, to);
                    taskList.setTask(event, index);

                    ui.printUpdate();
                }
            } catch (DukeException e) {
                System.out.println(ui.printError(e.getMessage()));
            }
            return ui.printUpdate();
        }

        public String handleUpdateCommand(TaskList taskList, Ui ui, String[] parts) {
            String[] commandParts = parts[1].split(" ", 2);
            int index = Integer.parseInt(commandParts[0]) - 1;

            Parser newParser = new Parser(commandParts[1],taskList,ui);

            String[] taskParts = commandParts[1].split(" ", 2);

            if(commandParts[1].startsWith("todo")) {
                return newParser.updateTodoCommand(taskList, ui, taskParts, index);
            } else if (commandParts[1].startsWith("deadline")) {
                return newParser.updateDeadlineCommand(taskList, ui, taskParts, index);
            } else {
                return newParser.updateEventCommand(taskList, ui, taskParts, index);
            }
        }

        /**
         * Gets the task description
         * @param part a part of the string representation of the task
         * @return the task description
         */
        public static String getTask(String part) {
            int end = part.indexOf("/");
            if (end == -1) {
                    end = part.length();
            }

            String task = part.substring(0, end);
            return task.trim();
        }

        /**
         * Gets the time components of a task
         * @param message the user input
         * @return the part of the string representation of the task that indicates time
         */
        public static String getTime(String message) {
            int start = message.indexOf("/");
            String time = message.substring(start + 1);
            return time;
        }

        /**
         * Parses a string format of the date and time of a task
         * @param time the string representation of the date and time
         * @return A local date time object of the date and time
         * @throws DukeException
         */
        public static LocalDateTime parseToLocalDate(String time) throws DukeException {
            try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                    return LocalDateTime.parse(time, formatter);
            } catch (Exception e)  {
                    throw new DukeException("Please make sure your date and time are formatted as 'd/M/yyyy HHmm'.");
            }
        }

        public String handleFindCommand(TaskList taskList, Ui ui, String[] parts) {
            assert parts !=null : "Input should not be null";
            if(parts.length < 2 ) {
                    System.out.println("Oops what keyword are you searching for?");
            }
            List<Task> findTasks = taskList.find(parts[1]);
            return ui.printFindList(findTasks);
        }

}
