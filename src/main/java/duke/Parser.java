package duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

/**
 * Parser class coordinates the ui, tasklist and storage to interpret user input
 * @author Cedric
 */
public class Parser {
    private TaskList taskList = new TaskList();
    private Storage storage = new Storage();
    /**
     * Constructor for parser checks for the storage file/folder existence then moves information to tasklist
     */
    public Parser() {
        storage.check();
        String currentLine;
        int currLine = 0;
        while ((currentLine = storage.read(currLine)) != null) {
            Matcher mTodo2 = pTodo2.matcher(currentLine);
            Matcher mEvent2 = pEvent2.matcher(currentLine);
            Matcher mDeadline2 = pDeadline2.matcher(currentLine);
            Matcher mUnmarked = pUnmarked.matcher(currentLine);
            Matcher mMarked = pMarked.matcher(currentLine);
            if (mTodo2.find()) {
                if (mMarked.find()) {
                    Todo n = new Todo(currentLine.substring(6), true);
                    n.mark();
                    taskList.add(n);
                } else if (mUnmarked.find()) {
                    Todo n = new Todo(currentLine.substring(6), false);
                    n.unmark();
                    taskList.add(n);
                }
            } else if (mDeadline2.find()) {
                int finalIndex = currentLine.indexOf("/by") + 3;
                String dL = currentLine.substring(finalIndex);
                // Define the format of the input string
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime ldt = null;
                try {
                    ldt = LocalDateTime.parse(dL, formatter);
                } catch (DateTimeParseException e) {
                }
                String newInput = currentLine.substring(currentLine.indexOf("deadline") + 7, currentLine.indexOf("/by"));

                if (mMarked.find()) {
                    Deadline n = new Deadline(newInput, true, ldt);
                    n.mark();
                    taskList.add(n);
                } else if (mUnmarked.find()) {
                    Deadline n = new Deadline(newInput, false, ldt);
                    n.unmark();
                    taskList.add(n);
                }
            } else if (mEvent2.find()) {
                int startIndex = currentLine.indexOf("/from");
                int startIndexTo = currentLine.indexOf("/to");
                String subFrom = currentLine.substring(startIndex + 5, startIndexTo);

                String subTo = currentLine.substring(startIndexTo + 3);

                String newInput = currentLine.substring(currentLine.indexOf("event") + 7, startIndex);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime ldt = null;
                LocalDateTime ldt2 = null;
                try {
                    ldt = LocalDateTime.parse(subFrom, formatter);
                    ldt2 = LocalDateTime.parse(subTo, formatter);
                } catch (DateTimeParseException e) {
                }
                if (mMarked.find()) {
                    Event n = new Event(newInput, true, ldt, ldt2);
                    n.mark();
                    taskList.add(n);
                } else if (mUnmarked.find()) {
                    Event n = new Event(newInput, false, ldt, ldt2);
                    n.unmark();
                    taskList.add(n);
                }

            }
            currLine = currLine + 1;
        }
    }
    private Pattern pFind = Pattern.compile("find");
    private Pattern pMark = Pattern.compile("mark (\\d+)");
    private Pattern pUnmark = Pattern.compile("unmark (\\d+)");
    private Pattern pTodo = Pattern.compile("todo");
    private Pattern pDeadline = Pattern.compile("deadline");
    private Pattern pEvent = Pattern.compile("event");
    private Pattern pBy = Pattern.compile("/by");
    private Pattern pFrom = Pattern.compile("/from");
    private Pattern pTo = Pattern.compile("/to");
    private Pattern pDelete = Pattern.compile("delete (\\d+)");
    private Pattern pTodo2 = Pattern.compile("[T]");
    private Pattern pDeadline2 = Pattern.compile("[D]");
    private Pattern pEvent2 = Pattern.compile("[E]");
    private Pattern pUnmarked = Pattern.compile("[ ]");
    private Pattern pMarked = Pattern.compile("[X]");
    /**
     * Receives input from Ui then interprets it before calling functions from tasklist, ui and storage
     */
    public String interpret(String input) {

        Matcher mMark = pMark.matcher(input);
        Matcher mUnmark = pUnmark.matcher(input);
        Matcher mTodo = pTodo.matcher(input);
        Matcher mDeadline = pDeadline.matcher(input);
        Matcher mEvent = pEvent.matcher(input);
        Matcher mBy = pBy.matcher(input);
        Matcher mFrom = pFrom.matcher(input);
        Matcher mTo = pTo.matcher(input);
        Matcher mDelete = pDelete.matcher(input);
        Matcher mFind = pFind.matcher(input);
        if (input.equals("reset")) {
            taskList.clear();
            storage.clear();
            return "List cleared!";
        } else if (input.equals("bye")) {
            return "Bye. Hope to see you again soon!";
        } else if (input.equals("list")) {
            if (taskList.getLength() == 0) {
                return "You have no tasks in your list!";
            } else {
                String result = "Here are your tasks in your list:\n";
                for (int x = 0; x < taskList.getLength(); x++) {
                    Task item = taskList.get(x);
                    int numeric = x + 1;
                    result = result + numeric + "." + item.toString() + "\n";
                }
                return result;

            }
        } else if (mFind.find()) {
            String newInput = input.replace("find", "").trim();
            if (newInput.trim().equals("")) {
                return "Search cannot be empty!";
            } else {
                int currentLine = 1;
                ArrayList<Task> tasks = taskList.find(newInput);
                if (tasks.size() == 0) {
                    return "You have no matching tasks in your list!";
                } else {
                    String result = "Here are the matching tasks in your list:\n";
                    for (int i =0; i < tasks.size(); i++) {
                        Task task = tasks.get(i);
                        result = result + currentLine + ". " + task.toString() + "\n";
                        currentLine = currentLine + 1;
                    }
                    return result;
                }

            }

        } else if (mDelete.find()) {
            String captured = mDelete.group(1);
            int number = Integer.parseInt(captured);
            if (number > 0 && number <= taskList.getLength()) {
                Task t = taskList.delete(number-1);
                String result = "OK! I have deleted this task:\n";
                result = result + t.toString();
                storage.delete(number-1);
                return result;
            } else {
                return "Please input a valid number.";
            }

        } else if (mUnmark.find()) {
            String captured = mUnmark.group(1);
            int number = Integer.parseInt(captured);
            Task t;
            if (number > 0 && number <= taskList.getLength()) {
                t = taskList.get(number - 1);
                t.unmark();
                String result = "Oh no! I have marked this as not done:\n";
                result = result + t.toString();
                storage.edit(number-1, t.export());
                return result;
            } else {
                return "Please input a valid number.";
            }
        } else if (mMark.find()) {
            String captured = mMark.group(1);
            int number = Integer.parseInt(captured);
            Task t;
            if (number > 0 && number <= taskList.getLength()) {
                t = taskList.get(number -1);
                t.mark();
                String result = "Nice! I have marked this as done:\n";
                result = result + t.toString();
                storage.edit(number-1, t.export());
                return result;
            } else {
                return "Please input a valid number.";
            }
        } else if (mTodo.find()) {
            String newInput = input.replace("todo", "");
            Todo n = new Todo(newInput, false);
            if (newInput.trim().equals("")) {
                return "Task cannot be empty!";
            } else {
                taskList.add(n);
                String result = "OK, I have added this task :\n";
                result = result + n.toString() + "\n";
                result = result + "You now have " + taskList.getLength() + " items in the list.";
                storage.add(n.export());
                return result;
            }
        } else if (mEvent.find()) {
            if (mFrom.find() && mTo.find()) {
                int startIndex = input.indexOf("/from");
                int startIndexTo = input.indexOf("/to");

                String subFrom = input.substring(startIndex + 5, startIndexTo).trim();
                String subTo = input.substring(startIndexTo + 3).trim();
                String newInput = input.substring(input.indexOf("event") + 5, startIndex);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime ldt;
                LocalDateTime ldt2;
                try {
                    // Parse the string into a LocalDate object
                    ldt = LocalDateTime.parse(subFrom, formatter);
                    ldt2 = LocalDateTime.parse(subTo, formatter);
                } catch (DateTimeParseException e) {
                    // Handle parsing exceptions
                    return "Please enter a valid date/time";
                }
                if (newInput.trim().equals("")) {
                    return "Task cannot be empty!";
                } else {
                    Event n = new Event(newInput, false, ldt, ldt2);
                    taskList.add(n);
                    String result = "OK, I have added this task :\n";
                    result = result + n.toString() + "\n";
                    result = result + "You now have " + taskList.getLength() + " items in the list.";
                    storage.add(n.export());
                    return result;
                }
            } else {
                return"pls input your start and end of the event.";
            }
        } else if (mDeadline.find()) {
            if (mBy.find()) {
                int finalIndex = input.indexOf("/by") + 3;
                String dL = input.substring(finalIndex);
                String newInput = input.substring(input.indexOf("deadline") + 8, input.indexOf("/by"));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" yyyy-MM-dd HH:mm");
                LocalDateTime ldt;
                try {
                    // Parse the string into a LocalDate object
                    ldt = LocalDateTime.parse(dL, formatter);
                } catch (DateTimeParseException e) {
                    return "Please enter a valid date/time";
                }
                if (newInput.trim().equals("")) {
                    return "Task cannot be empty!";
                } else {
                    Deadline n = new Deadline(newInput, false, ldt);
                    taskList.add(n);
                    String result = "OK, I have added this task :\n";
                    result = result + n.toString() + "\n";
                    result = result + "You now have " + taskList.getLength() + " items in the list.";
                    storage.add(n.export());
                    return result;
                }
            } else {
                return "please include a deadline";
            }

        } else {
            return "Sorry, no idea what u talking about lulz";
        }

    }
}
