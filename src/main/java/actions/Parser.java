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

        /**
         * Instantiates a parser object
         */
        public Parser(String input) {
            this.input = input;
        }

        /**
         * Parses the user input and handles them
         * @param tasklist a task list object
         * @param ui a ui object
         * @return returns isExit boolean that indicates if the program has reach termination
         */
        public boolean parse(TaskList tasklist, Ui ui) {
                String[] parts = input.split(" ", 2);
                String command = parts[0].toLowerCase();
                boolean isExit = false;

                switch(command) {
                case"bye":
                        isExit = true;
                        break;
                case "list" :
                        ArrayList<Task> tasks = tasklist.getTasks();
                        ui.printList(tasks);
                        break;
                case "mark":
                        handleMarkCommand(tasklist, ui, parts);
                        break;
                case "unmark" :
                        handleUnmarkCommand(tasklist, ui, parts);
                        break;
                case "delete" :
                        handleDeleteCommand(tasklist, ui, parts);
                        break;
                case "todo" :
                        handleTodoCommand(tasklist, ui, parts);
                        break;
                case "deadline" :
                        handleDeadlineCommand(tasklist, ui, parts);
                        break;
                case "event" :
                        handleEventCommand(tasklist, ui, parts);
                        break;
                default:
                        ui.printInputError();


                }
                return isExit;
        }

        public void handleMarkCommand(TaskList taskList, Ui ui, String[] parts) {
                int num = Integer.parseInt(parts[1]);
                Task current = taskList.getTask(num -1);
                taskList.markTask(current);
                ui.printMarkMessage(current);
        }

        public void handleUnmarkCommand(TaskList taskList, Ui ui, String[] parts) {
                int num = Integer.parseInt(parts[1]);
                Task current = taskList.getTask(num -1);
                taskList.unmarkTask(current);
                ui.printUnmarkMessge(current);
        }

        public void handleDeleteCommand(TaskList taskList, Ui ui, String[] parts) {
                int num = Integer.parseInt(parts[1]);
                Task current = taskList.getTask(num -1);
                taskList.deleteTask(num-1);
                ui.printDeleteMessage(current, taskList.getArraySize());
        }

        public void handleTodoCommand(TaskList taskList, Ui ui, String[] parts) {
               try {
                       String task = parts[1];
                       if (task.isEmpty()) {
                               throw new DukeException("Don't forget the description!");
                       } else {
                               Task todo = new Todo(task, false);
                               taskList.addTask(todo);
                               ui.printAddMessage(todo, taskList.getArraySize());
                       }

               } catch (DukeException e) {
                       ui.printError(e.getMessage());
               }
        }

        public void handleDeadlineCommand(TaskList taskList, Ui ui, String[] parts) {
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
                                taskList.addTask(deadline);
                                ui.printAddMessage(deadline, taskList.getArraySize());
                        }
                } catch (DukeException e) {
                        ui.printError(e.getMessage());
                }
        }

        public void handleEventCommand(TaskList taskList, Ui ui, String[] parts) {
                try {
                        String task = getTask(parts[1]);
                        String time = getTime(parts[1]);
                        String[] timeparts = time.split("from");
                        String[] dateParts = timeparts[1].trim() .split("/to");
                        if (task.isEmpty()) {
                                throw new DukeException("Don't forget the description!");
                        } else if (time.isEmpty()) {
                                throw new DukeException("Don't forget the deadline!");
                        } else if (dateParts[0].isEmpty()) {
                                throw new DukeException("You forgot the start time!");
                        } else if (dateParts[1].isEmpty()) {
                                throw new DukeException("You forgot the end time!");
                        } else {
                                LocalDateTime from = parseToLocalDate(dateParts[0].trim());
                                LocalDateTime to = parseToLocalDate(dateParts[1].trim());
                                Task event = new Event(task, false, from, to);
                                taskList.addTask(event);
                                ui.printAddMessage(event, taskList.getArraySize());
                        }
                }catch (DukeException e) {
                        ui.printError(e.getMessage());
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

        public void handleFindCommand(TaskList taskList, Ui ui, String[] parts) {
                if(parts.length < 2 ) {
                        ui.printError("Oops what keyword are you searching for?");
                } else {
                        List<Task> findTasks = taskList.find(parts[1]);
                        ui.printFindList(findTasks);
                }

        }

}
