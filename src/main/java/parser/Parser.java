/**
 * This is a static parser method for Balkan Bot.
 * The command of the user is interpreted by the parser,
 * following which the task list is altered
 * and the ui prints out the corresponding output text.
 */

package parser;

import gui.GuiUi;
import storage.Storage;
import task.TaskList;
import task.Task;
import task.ToDo;
import task.Deadline;
import task.Event;
import task.InvalidDateException;
import task.InvalidInputException;

import java.util.Arrays;

public class Parser {
    protected static int current = 0;
    protected static String output = "";
    protected static Task[] tasks;
    protected static GuiUi guiUi;
    protected static Storage storage;

    /**
     * Parses the command inputted by the user.
     *
     * @param input    Input of the user
     * @param taskList TaskList that contains all the current tasks
     * @param guiUi    UI that generates text responses for the user
     * @return A boolean that indicates whether to terminate the program
     * true: continue running program
     * false: terminate the program and store the task list into the text file
     */
    public static String parse(String input, TaskList taskList, GuiUi guiUi, Storage storage) {
        tasks = taskList.getTaskList();
        Parser.guiUi = guiUi;
        Parser.storage = storage;

        switch (input) {
            case "help":
                output = guiUi.printHelp();
                break;
            case "bye":
                storage.save(taskList);
                output = guiUi.printByeMessage();
                break;
            case "list":
                output = guiUi.printTaskList(generateList().toString());
                break;
            default:
                String[] brokenCommand = input.split("\\s+");
                assert brokenCommand.length > 0 : "Command not processed correctly, input lost after processing";
                String advancedCommand = brokenCommand[0];
                String[] details = Arrays.copyOfRange(brokenCommand, 1, brokenCommand.length);
                switch (advancedCommand) {
                    case "find": {
                        find(brokenCommand, taskList);
                        break;
                    }
                    case "mark": {
                        mark(brokenCommand);
                        break;
                    }
                    case "unmark": {
                        unmark(brokenCommand);
                        break;
                    }
                    case "todo": {
                        todo(details);
                        break;
                    }
                    case "deadline": {
                        deadline(details);
                        break;
                    }
                    case "event": {
                        event(details);
                        break;
                    }
                    case "delete": {
                        delete(brokenCommand);
                        break;
                    }
                    default: {
                        output = "OOPS!!! I'm sorry, but I don't know what that means :-(";
                        break;
                    }
                }
                break;
        }
        taskList.updateTaskList(new TaskList(tasks));
        return output;
    }

    /**
     * Changes the value of current which indicates the current index of the task list.
     *
     * @param current current index of the task list
     */
    public static void updateCurrent(int current) {
        assert current >= 0 : "Index provided for updateCurrent() method is less than 0";
        Parser.current = current;
    }

    private static StringBuilder generateList() {
        StringBuilder listOutput = new StringBuilder();
        assert tasks.length <= 100 : "Task List Exceeded Limit of 100";
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] == null) {
                break;
            } else {
                listOutput.append(i + 1).append(". ").append(tasks[i].toString()).append("\n");
            }
        }

        return listOutput;
    }

    private static void find(String[] brokenCommand, TaskList taskList) {
        if (brokenCommand.length != 2) {
            output = guiUi.printFindEmptyError();
        } else {
            String match = brokenCommand[1];
            Task[] findOutput = taskList.find(match);
            output = guiUi.printFindOutput(findOutput);
        }
    }

    private static void mark(String[] brokenCommand) {
        if (brokenCommand.length < 2) {
            output = guiUi.printMarkEmptyNumberError();
        } else {
            try {
                int index = Integer.parseInt(brokenCommand[1]) - 1;
                if (index < 0) {
                    throw new NumberFormatException();
                }
                tasks[index].mark();
                output = guiUi.printTaskMarked(tasks[index].toString());
            } catch (NumberFormatException e) {
                output = guiUi.printMarkNANError();
            }
        }
    }

    private static void unmark(String[] brokenCommand) {
        if (brokenCommand.length < 2) {
            output = guiUi.printUnmarkEmptyNumberError();
        } else {
            try {
                int index = Integer.parseInt(brokenCommand[1]) - 1;
                if (index < 0) {
                    throw new NumberFormatException();
                }
                tasks[index].unmark();
                output = guiUi.printTaskUnmarked(tasks[index].toString());
            } catch (NumberFormatException e) {
                output = guiUi.printUnmarkNANError();
            }
        }
    }

    private static void todo(String[] details) {
        String taskDescription = String.join(" ", details);
        try {
            tasks[current] = new ToDo(taskDescription);
            current++;
            output = guiUi.printComplexTask(tasks, current);
        } catch (InvalidInputException e) {
            output = e.toString();
        }
    }

    private static void deadline(String[] details){
        StringBuilder taskDescription = new StringBuilder();
        StringBuilder deadline = new StringBuilder();
        boolean isDeadline = false;
        for (String currentString : details) {
            if (isDeadline) {
                deadline.append(currentString).append(" ");
            } else if (currentString.equals("/by")) {
                isDeadline = true;
            } else {
                taskDescription.append(currentString).append(" ");
            }
        }
        try {
            tasks[current] = new Deadline(taskDescription.toString(), deadline.toString());
            current++;
            output = guiUi.printComplexTask(tasks, current);
        } catch (InvalidInputException | InvalidDateException e) {
            output = e.toString();
        }
    }

    private static void event(String[] details) {
        StringBuilder taskDescription = new StringBuilder();
        StringBuilder from = new StringBuilder();
        StringBuilder to = new StringBuilder();
        boolean isFrom = false;
        boolean isTo = false;
        for (String currentString : details) {
            if (isTo) {
                to.append(currentString).append(" ");
            } else if (isFrom) {
                if (currentString.equals("/to")) {
                    isTo = true;
                } else {
                    from.append(currentString).append(" ");
                }
            } else if (currentString.equals("/from")) {
                isFrom = true;
            } else {
                taskDescription.append(currentString).append(" ");
            }
        }
        try {
            tasks[current] = new Event(taskDescription.toString(), from.toString(), to.toString());
            current++;
            output = guiUi.printComplexTask(tasks, current);
        } catch (InvalidInputException | InvalidDateException e) {
            output = e.toString();
        }
    }

    private static void delete(String[] brokenCommand) {
        if (brokenCommand.length < 2) {
            output = "OOPS!!! The number for the delete command cannot be empty.";
        } else {
            try {
                Task deletedTask = null;
                int index = Integer.parseInt(brokenCommand[1]) - 1;
                if (index < 0) {
                    throw new NumberFormatException();
                }
                for (int i = 0; i < tasks.length; i++) {
                    Task currentTask = tasks[i];
                    if (currentTask == null) {
                        if (i <= index) {
                            throw new ArrayIndexOutOfBoundsException();
                        }
                        break;
                    } else if (i >= index) {
                        if (i == index) {
                            deletedTask = currentTask;
                        }
                        tasks[i] = tasks[i + 1];
                    }
                }
                current--;
                assert deletedTask != null;
                output = guiUi.printDeletion(deletedTask, current);
            } catch (NumberFormatException e) {
                output = "OOPS!!! The input after the delete command has to be a positive integer.";
            } catch (ArrayIndexOutOfBoundsException e) {
                output = "OOPS!!! The input for delete is out of bounds.";
            }
        }
    }
}
