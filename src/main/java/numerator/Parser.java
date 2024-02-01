package numerator;

import numerator.exceptions.NumeratorException;
import numerator.exceptions.parser.InputFormatException;
import numerator.exceptions.parser.InputNotRecognisedException;
import numerator.exceptions.parser.TaskIndexOutOfBoundsException;
import numerator.task.Task;
import numerator.task.TaskList;

import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static boolean parseArguments(String input, TaskList taskList, Storage storage) throws NumeratorException {
        if (input.equals("bye")) {
            return true;
        } else if (input.startsWith("mark")) {
            try {
                Pattern p = Pattern.compile("mark (\\d+)");
                Matcher m = p.matcher(input);
                if (!m.find()) {
                    throw new InputFormatException("Please use the format: mark <task number>");
                } else {
                    int taskNum = Integer.parseInt(m.group(1)) - 1;
                    taskList.markAsDone(taskNum);
                    Ui.printMessage("Nice! I've marked this task as done:");
                    taskList.printTask(taskNum);
                }
                storage.save(taskList);
            } catch (IndexOutOfBoundsException e) {
                throw new TaskIndexOutOfBoundsException("Task does not exist");
            }

        } else if (input.startsWith("unmark")) {
            try {
                Pattern p = Pattern.compile("unmark (\\d+)");
                Matcher m = p.matcher(input);
                if (!m.find()) {
                    throw new InputFormatException("Please use the format: unmark <task number>");
                } else {
                    int taskNum = Integer.parseInt(m.group(1)) - 1;
                    taskList.markAsUndone(taskNum);

                    Ui.printMessage("OK, I've marked this task as not done yet:");
                    taskList.printTask(taskNum);
                    storage.save(taskList);
                }
            } catch (IndexOutOfBoundsException e) {
                throw new TaskIndexOutOfBoundsException("Task does not exist");
            }
        } else if (input.startsWith("delete")) {
            try {
                Pattern p = Pattern.compile("delete (\\d+)");
                Matcher m = p.matcher(input);
                if (!m.find()) {
                    throw new InputFormatException("Please use the format: delete <task number>");
                } else {
                    int taskNum = Integer.parseInt(m.group(1)) - 1;
                    Task t = taskList.removeTask(taskNum);
                    Ui.printMessage("Noted. I've removed this task:");
                    Ui.printMessage(t.toString() + "\n");
                    Ui.printMessage("Now you have " + taskList.getSizeAsString() + " tasks in the list");
                    storage.save(taskList);
                }
            } catch (IndexOutOfBoundsException e) {
                throw new TaskIndexOutOfBoundsException("Task does not exist");
            }
        } else if (input.startsWith("todo")) {
            Pattern p = Pattern.compile("todo (\\S+.*)");
            Matcher m = p.matcher(input);
            if (!m.find()) {
                throw new InputFormatException("Please use the format: todo <task>");
            } else {
                String taskDesc = m.group(1);
                Task t = taskList.addToDo(taskDesc);
                taskList.printAddTask(t);
                storage.save(taskList);
            }

        } else if (input.startsWith("deadline")) {
            Pattern p = Pattern.compile("deadline (\\S+.*) /by (\\S+.*)");
            Matcher m = p.matcher(input);
            if (!m.find()) {
                throw new InputFormatException("Please use the format: deadline <task> /by <date>");
            } else {
                String taskDesc = m.group(1);
                String by = m.group(2);
                try {
                    Task t = taskList.addDeadline(taskDesc, by);
                    taskList.printAddTask(t);
                    storage.save(taskList);
                } catch (DateTimeParseException e) {
                    throw new InputFormatException("The date should be in the format: yyyy/MM/dd");
                }
            }

        } else if (input.startsWith("event")) {
            Pattern p = Pattern.compile("event (\\S+.*) /from (\\S+.*) /to (\\S+.*)");
            Matcher m = p.matcher(input);
            if (!m.find()) {
                throw new InputFormatException("Please use the format: event <task> /from <date> /to <date>");
            } else {
                String taskDesc = m.group(1);
                String from = m.group(2);
                String to = m.group(3);
                Task t = taskList.addEvent(taskDesc, from, to);
                taskList.printAddTask(t);
                storage.save(taskList);
            }
        } else if (input.equals("list")) {
            Ui.printMessage(taskList.toString());
        } else {
            throw new InputNotRecognisedException("Input not recognised");
        }
        return false;
    }
}
