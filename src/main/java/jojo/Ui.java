package jojo;
import exceptions.JojoException;
import exceptions.JojoTaskNoDescException;

/**
 * Handles the interactions with the user.
 */
public class Ui {

    /**
     * Returns a welcome message.
     */
    public String showWelcomeMessage() {
        return "Hello! I'm Jojo :)";
    }

    /**
     * Returns the starting prompt.
     */
    public String showStartingQn() {
        return "What can I do for you?";
    }


    /**
     * Returns break lines to show end of a command.
     */
    public String breakLines() {
        StringBuilder sb = new StringBuilder();
        sb.append("-----------------------------------------------------------------------");
        sb.append(System.lineSeparator());
        sb.append("-----------------------------------------------------------------------");
        return sb.toString();
    }

    /**
     * Returns a list of tasks.
     * @param tl TaskList
     */
    public String ListToString(TaskList tl) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:");
        for (int j = 0; j < tl.size(); j++) { // printing out all items in the list
            sb.append(System.lineSeparator());
            String task = j + 1 + ". " + tl.get(j);
            sb.append(task);
        }
        return sb.toString();
    }

    /**
     * Returns the task when marked as done.
     * @param tl TaskList
     * @param cmd String
     * @throws JojoException when the task is invalid
     */
    public String MarkToString(TaskList tl, String cmd) throws JojoException {
        try {
            StringBuilder sb = new StringBuilder();
            int taskNum = Parser.parseMark(cmd);
            tl.setDone(taskNum);
            sb.append("Nice! I've marked this task as done:");
            sb.append(System.lineSeparator());
            sb.append(tl.taskToString(taskNum));
            return sb.toString();
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            throw new JojoException("Hmm...seems like the task to mark does not exist. To mark a task, input the " +
                    "keyword followed by the task's no. in the list. E.g.: mark 3");
        }
    }

    /**
     * Returns the task when marked as undone.
     * @param tl TaskList
     * @param cmd String
     * @throws JojoException when the task is invalid
     */
    public String UnmarkToString(TaskList tl, String cmd) throws JojoException {
        try {
            StringBuilder sb = new StringBuilder();
            int taskNum = Parser.parseUnmark(cmd);
            tl.setUndone(taskNum);
            sb.append("OK, I've marked this task as not done yet:");
            sb.append(System.lineSeparator());
            sb.append(tl.taskToString(taskNum));
            return sb.toString();
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            throw new JojoException("Hmm...seems like the task to unmark does not exist. To unmark a task, input the " +
                    "keyword followed by the task's no. in the list. E.g.: unmark 3");
        }
    }

    /**
     * Returns the task when deleted.
     * @param tl TaskList
     * @param cmd String
     * @throws JojoException when the task is invalid
     */
    public String DeleteToString(TaskList tl, String cmd) throws JojoException {
        StringBuilder sb = new StringBuilder();
        try {
            int taskNum = Parser.parseDelete(cmd);
            sb.append("Noted. I've removed this task:");
            sb.append(System.lineSeparator());
            sb.append(tl.taskToString(taskNum));
            sb.append(System.lineSeparator());
            tl.deleteTask(taskNum);
            sb.append("Now you have ");
            sb.append(tl.size());
            sb.append(" tasks in the list.");
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            throw new JojoException("Hmm...seems like the task to delete does not exist. To delete a task, input the " +
                    "keyword followed by the task's no. in the list. E.g.: delete 3");
        }
        return sb.toString();
    }

    /**
     * Returns the todo when created.
     * @param tl TaskList
     * @param cmd String
     * @throws JojoException when the todo is invalid
     */
    public String ToDoToString(TaskList tl, String cmd) throws JojoException {
        String test = Parser.parseToDoOrFind(cmd);
        StringBuilder sb = new StringBuilder();
        if (test.strip().equals("")) {
            throw new JojoTaskNoDescException();
        } else {
            Task t = new ToDo(test);
            tl.addTask(t); // add task to list
            sb.append("Got it. I've added this task:");
            sb.append(System.lineSeparator());
            sb.append(t);
            sb.append(System.lineSeparator());
            sb.append("Now you have ");
            sb.append(tl.size());
            sb.append(" tasks in the list.");
        }
        return sb.toString();
    }

    /**
     * Returns the deadline when created.
     * @param tl TaskList
     * @param cmd String
     * @throws JojoException when the deadline is invalid
     */
    public String DeadlineToString(TaskList tl, String cmd) throws JojoException {
        String test = Parser.parseDeadline(cmd);
        StringBuilder sb = new StringBuilder();
        if (test.strip().equals("")) {
            throw new JojoTaskNoDescException();
        } else {
            Task t = new Deadline(Parser.parseDeadlineDesc(test), Parser.parseDeadlineBy(test));
            tl.addTask(t); // add task to list
            sb.append("Got it. I've added this task:");
            sb.append(System.lineSeparator());
            sb.append(t);
            sb.append(System.lineSeparator());
            sb.append("Now you have ");
            sb.append(tl.size());
            sb.append(" tasks in the list.");
        }
        return sb.toString();
    }

    /**
     * Returns the event when created.
     * @param tl TaskList
     * @param cmd String
     * @throws JojoException when the event is invalid
     */
    public String EventToString(TaskList tl, String cmd) throws JojoException {
        String test = Parser.parseEventTest(cmd);
        StringBuilder sb = new StringBuilder();
        if (test.strip().equals("")) {
            throw new JojoTaskNoDescException();
        } else {
            String[] ans = Parser.parseEvent(cmd);
            Task t = new Event(ans[0], ans[1], ans[2]);
            tl.addTask(t); // add task to list
            sb.append("Got it. I've added this task:");
            sb.append(System.lineSeparator());
            sb.append(t);
            sb.append(System.lineSeparator());
            sb.append("Now you have ");
            sb.append(tl.size());
            sb.append(" tasks in the list.");
        }
        return sb.toString();
    }

    /**
     * Returns a list of task(s) that matches the keyword partially or fully.
     * @param tl TaskList
     * @param cmd String 
     * @throws JojoException when find is invalid
     */
    public String FindToString(TaskList tl, String cmd) throws JojoException {
        StringBuilder sb = new StringBuilder();
        String test = Parser.parseToDoOrFind(cmd);
        if (test.strip().equals("")) {
            throw new JojoTaskNoDescException();
        } else {
            sb.append("Here are the matching tasks in the list:");
            int i = 1;
            for (int j = 0; j < tl.size(); j++) { // printing out all items in the list
                String taskDesc = tl.get(j).description.toLowerCase();
                if (taskDesc.contains(test.toLowerCase())) {
                    sb.append(System.lineSeparator());
                    sb.append(i);
                    sb.append(". ");
                    sb.append(tl.get(j));
                    i++;
                }
            }
        }
        return sb.toString();
    }

    /**
     * Returns an exit message.
     */
    public String showExitMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns an error message when the file does not load correctly.
     */
    public String showLoadingError() {
        return "Error loading tasks from file.";
    }

}
