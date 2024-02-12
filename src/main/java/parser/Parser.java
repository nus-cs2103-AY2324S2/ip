package parser;

import javafx.util.Pair;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import java.time.LocalDate;

/**
 * The Parser class parses user input (a command) and executes the command accordingly
 */
public class Parser {

    private static String dividerText = "____________________________________________________________\n";

    /**
     * Takes the command as a string and the existing TaskList object
     * Processes the command and executes it on TaskList object
     * Returns an integer (0 or 1) to indicate if it is a terminating command
     *
     * @param input Command entered by user
     * @param existingTaskList User's current TaskList object
     * @return 0 or 1
     * @throws NumberFormatException If number cannot be parsed (a non-number is entered)
     * @throws IndexOutOfBoundsException If task number does not exist in list
     */
    public static Pair<Integer, String> parseAndExecuteCommand(String input, TaskList existingTaskList) {
        String[] splitInput = input.split(" ");
        String command = splitInput[0].toLowerCase();
        System.out.print(dividerText);
        String response = "default response";
        try {
            switch (command) {
            case "bye":
            case "exit":
                response = Ui.exit();
                return new Pair(0, response);

            case "list":
                response = existingTaskList.listTasks();
                return new Pair(1, response);

            case "mark":
            case "unmark":
                try {
                    int taskNum = Integer.parseInt(splitInput[1]);
                    if (command.equals("mark")) {
                        response = existingTaskList.markTaskDone(taskNum);
                    } else {
                        response = existingTaskList.markTaskUndone(taskNum);
                    }
                } catch (NumberFormatException e) {
                    System.out.print("Invalid task number entered\n");
                    response = "Invalid task number entered";
                } catch (IndexOutOfBoundsException e) {
                    System.out.print("Task does not exist! Type 'list' to check task numbers\n");
                    response = "Task does not exist! Type 'list' to check task numbers";
                }
                Storage.saveTaskList(existingTaskList);
                return new Pair(1, response);

            case "delete":
                try {
                    if (splitInput[1].equalsIgnoreCase("all")) {
                        response = existingTaskList.deleteAllTasks();
                    }
                    else {
                        int taskNum = Integer.parseInt(splitInput[1]);
                        response = existingTaskList.deleteTask(taskNum);
                    }
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                    System.out.print("Invalid task number entered\n");
                    System.out.print(dividerText);
                    response = "Invalid task number entered.";
                } catch (IndexOutOfBoundsException e) {
                    System.out.print("Task does not exist! Type 'list' to check task numbers\n");
                    System.out.print(dividerText);
                    response = "Task does not exist! Type 'list' to check task numbers";
                }
                Storage.saveTaskList(existingTaskList);
                return new Pair(1, response);

            case "todo":
                String todoDescription = input.replace("todo", "").trim();
                if (todoDescription.equals("")) { // missing description
                    System.out.print("Missing task description!\n");
                    System.out.print(dividerText);
                    response = "Missing task description!";
                } else if (todoDescription.contains("/by")) { // unnecessary due date
                    System.out.print("Todo task cannot have a due date!\n");
                    System.out.print(dividerText);
                    response = "Todo task cannot have a due date!";
                } else if (todoDescription.contains("/from") ||
                        todoDescription.contains("/to")) { // unnecessary from and to date
                    System.out.print("Todo task cannot have a from and to date!\n");
                    System.out.print(dividerText);
                    response = "Todo task cannot have a from and to date!";
                } else {
                    response = existingTaskList.addTodo(todoDescription);
                }
                Storage.saveTaskList(existingTaskList);
                return new Pair(1, response);

            case "deadline":
                String deadlineDescriptionDueDate = input.replace("deadline", "").trim();
                if (!deadlineDescriptionDueDate.contains(" /by ")) { // missing '/by' keyword
                    System.out.print("Missing due date!\n");
                    System.out.print(dividerText);
                    response = "Missing due date!";
                    return new Pair(1, response);
                }
                String[] deadlineDescriptionDueDateArray = input.replace("deadline", "")
                        .trim().split(" /by ");
                String deadlineDescription = deadlineDescriptionDueDateArray[0];
                String deadlineDueDate = deadlineDescriptionDueDateArray[1];
                if (deadlineDescription.equals("")) { // empty description
                    System.out.print("Missing task description!\n");
                    System.out.print(dividerText);
                    response = "Missing task description!";
                }
                else if (deadlineDueDate.equals("")) { // empty due date
                    System.out.print("Missing task due date!\n");
                    System.out.print(dividerText);
                    response = "Missing task due date!";
                }
                else if (deadlineDescription.contains("/from") ||
                        deadlineDescription.contains("/to")) { // unnecessary from and to date
                    System.out.print("Deadline task cannot have a from and to date!\n");
                    System.out.print(dividerText);
                    response = "Deadline task cannot have a from and to date!";
                }
                else {
                    try {
                        LocalDate deadlineDueDateLocal = LocalDate.parse(deadlineDueDate);
                        response = existingTaskList.addDeadline(deadlineDescription, deadlineDueDateLocal);
                    }
                    catch (Exception e) {
                        System.out.print("Incorrect formatting of due date! Ensure it is in yyyy-mm-dd format\n");
                        System.out.print(dividerText);
                        response = "Incorrect formatting of due date! Ensure it is in yyyy-mm-dd format";
                    }
                }
                Storage.saveTaskList(existingTaskList);
                return new Pair(1, response);

            case "event":
                String eventDescriptionFromTo = input.replace("event", "").trim();
                if (!eventDescriptionFromTo.contains(" /from ") ||
                        !eventDescriptionFromTo.contains(" /to ")) { // missing '/from' or '/to' keywords
                    System.out.print("Missing event from and/or to date!\n");
                    System.out.print(dividerText);
                    response = "Missing even from and/or to date!";
                    return new Pair(1, response);
                }
                String[] eventDescriptionFromToArray = input.replace("event", "")
                        .trim().split(" /from ");
                String eventDescription = eventDescriptionFromToArray[0];
                String eventFromTo = eventDescriptionFromToArray[1];
                String[] eventFromToArray;
                String eventFrom = "";
                String eventTo = "";
                if (eventFromTo.contains(" /to ")) {
                    eventFromToArray = eventDescriptionFromToArray[1].trim().split(" /to ");
                    eventFrom = eventFromToArray[0];
                    eventTo = eventFromToArray[1];
                }
                if (eventDescription.equals("")) { // missing description
                    System.out.print("Missing task description!\n");
                    System.out.print(dividerText);
                    response = "Missing task description!";
                } else if (eventFrom.equals("")) { // missing from date
                    System.out.print("Missing from date!\n");
                    System.out.print(dividerText);
                    response = "Missing from date!";
                } else if (eventTo.equals("")) { // missing to date
                    System.out.print("Missing to date!\n");
                    System.out.print(dividerText);
                    response = "Missing to date!";
                } else if (eventDescription.contains("/by")) { // unnecessary due date
                    System.out.print("Event task cannot have a due date!\n");
                    System.out.print(dividerText);
                    response = "Event task cannot have a due date!";
                } else {
                    try {
                        LocalDate eventFromDateLocal = LocalDate.parse(eventFrom);
                        LocalDate eventToDateLocal = LocalDate.parse(eventTo);
                        response = existingTaskList.addEvent(eventDescription, eventFromDateLocal, eventToDateLocal);
                    }
                    catch (Exception e) {
                        System.out.print("Incorrect formatting of due date! Ensure it is in yyyy-mm-dd format\n");
                        System.out.print(dividerText);
                        response = "Incorrect formatting of due date! Ensure it is in yyyy-mm-dd format";
                    }
                }
                Storage.saveTaskList(existingTaskList);
                return new Pair(1, response);

            case "find":
                try {
                    String keyword = splitInput[1];
                    response = existingTaskList.findAndPrintTasks(keyword);
                }
                catch (Exception e) {
                    System.out.println(e.getMessage());
                    response = e.getMessage();
                }

                return new Pair(1, response);

            case "help":
                System.out.print("LIST OF COMMANDS\n");
                System.out.print("Create todo: todo [task name] (eg: todo borrow book)\n");
                System.out.print("Create deadline: deadline [task name] /by [due date] " +
                        "(eg: deadline homework /by Sun 6pm)\n");
                System.out.print("Create event: event [task name] /from [from date] /to [to date] " +
                        "(eg: event concert /from Mon 6pm /to Mon 8pm)\n");
                System.out.print("List current tasks: list\n");
                System.out.print("Mark a task as complete: mark [task number on list] " +
                        "(eg: mark 2)\n");
                System.out.print("Mark a task as incomplete: unmark [task number on list] " +
                        "(eg: unmark 2)\n");
                System.out.print("Delete a task: delete [task number on list] " +
                        "(eg: delete 3)\n");
                System.out.print("Close Tam the Task Manager: bye\n");
                System.out.print(dividerText);
                response = "LIST OF COMMANDS\n"
                        + "Create todo: todo [task name] (eg: todo borrow book)\n"
                        + "Create deadline: deadline [task name] /by [due date] "
                        + "(eg: deadline homework /by Sun 6pm)\n"
                        + "Create event: event [task name] /from [from date] /to [to date] "
                        + "(eg: event concert /from Mon 6pm /to Mon 8pm)\n"
                        + "List current tasks: list\n"
                        + "Mark a task as complete: mark [task number on list] "
                        + "(eg: mark 2)\n"
                        + "Mark a task as incomplete: unmark [task number on list] "
                        + "(eg: unmark 2)\n"
                        + "Delete a task: delete [task number on list] "
                        + "(eg: delete 3)\n"
                        + "Close Tam the Task Manager: bye";
                return new Pair(1, response);

            default:
                System.out.print("Invalid command entered. Type 'help' for list of commands\n");
                System.out.print(dividerText);
                response = "Invalid command entered. Type 'help' for list of commands";
                return new Pair(1, response);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            response = e.getMessage();
            return new Pair(0, response);
        }
    }
}
