package jojo;
import exceptions.JojoException;
import exceptions.JojoTaskNoDescException;

/**
 * Handles the interactions with the user.
 */
public class Ui {

    /**
     * Prints a welcome message.
     */
    public String showWelcomeMessage() {
        return "Hello! I'm Jojo :)";
    }

    /**
     * Prints the starting prompt.
     */
    public String showStartingQn() {
        return "What can I do for you?";
    }


    /**
     * Prints break lines to show end of a command.
     */
    public void breakLines() {
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------------");
    }

    /**
     * Prints a list of tasks.
     * @param tl
     */
    public void printList(TaskList tl) {
        System.out.println("Here are the tasks in your list:");
        for (int j = 0; j < tl.size(); j++) { // printing out all items in the list
            String task = j + 1 + ". " + tl.get(j);
            System.out.println(task);
        }
    }

    /**
     * Prints the task when marked as done.
     * @param tl TaskList
     * @param cmd String
     * @throws JojoException when the task is invalid
     */
    public void printMark(TaskList tl, String cmd) throws JojoException {
        try {
            int taskNum = Parser.parseMark(cmd);
            tl.setDone(taskNum);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tl.taskToString(taskNum));
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            throw new JojoException("Hmm...seems like the task to mark does not exist. To mark a task, input the keyword followed by the task's no. in the list. E.g.: mark 3");
        }
    }

    /**
     * Prints the task when marked as undone.
     * @param tl TaskList
     * @param cmd String
     * @throws JojoException when the task is invalid
     */
    public void printUnmark(TaskList tl, String cmd) throws JojoException {
        try {
            int taskNum = Parser.parseUnmark(cmd);
            tl.setUndone(taskNum);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(tl.taskToString(taskNum));
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            throw new JojoException("Hmm...seems like the task to unmark does not exist. To unmark a task, input the keyword followed by the task's no. in the list. E.g.: unmark 3");
        }
    }

    /**
     * Prints the task when deleted.
     * @param tl TaskList
     * @param cmd String
     * @throws JojoException when the task is invalid
     */
    public void printDelete(TaskList tl, String cmd) throws JojoException {
        int taskNum = Parser.parseDelete(cmd);
        if (taskNum >= tl.size()) {
            throw new JojoException("Hmm...seems like the task to delete does not exist. To delete a task, input the keyword followed by the task's no. in the list. E.g.: delete 3");
        } else {
            System.out.println("Noted. I've removed this task:");
            System.out.println(tl.taskToString(taskNum));
            tl.deleteTask(taskNum);
            System.out.println("Now you have " + tl.size() + " tasks in the list.");
        }
    }

    /**
     * Prints the todo when created.
     * @param tl TaskList
     * @param cmd String
     * @throws JojoException when the todo is invalid
     */
    public void printToDo(TaskList tl, String cmd) throws JojoException {
        String test = Parser.parseToDoOrFind(cmd);
        if (test.strip().equals("")) {
            throw new JojoTaskNoDescException();
        } else {
            Task t = new ToDo(test);
            tl.addTask(t); // add task to list
            System.out.println("Got it. I've added this task:");
            System.out.println(t);
            System.out.println("Now you have " + tl.size() + " tasks in the list.");
        }
    }

    /**
     * Prints the deadline when created.
     * @param tl TaskList
     * @param cmd String
     * @throws JojoException when the deadline is invalid
     */
    public void printDeadline(TaskList tl, String cmd) throws JojoException {
        String test = Parser.parseDeadline(cmd);
        if (test.strip().equals("")) {
            throw new JojoTaskNoDescException();
        } else {
            Task t = new Deadline(Parser.parseDeadlineDesc(test), Parser.parseDeadlineBy(test));
            tl.addTask(t); // add task to list
            System.out.println("Got it. I've added this task:");
            System.out.println(t);
            System.out.println("Now you have " + tl.size() + " tasks in the list.");
        }
    }

    /**
     * Prints the event when created.
     * @param tl TaskList
     * @param cmd String
     * @throws JojoException when the event is invalid
     */
    public void printEvent(TaskList tl, String cmd) throws JojoException {
        String test = Parser.parseEventTest(cmd);
        if (test.strip().equals("")) {
            throw new JojoTaskNoDescException();
        } else {
            String[] ans = Parser.parseEvent(cmd);
            Task t = new Event(ans[0], ans[1], ans[2]);
            tl.addTask(t); // add task to list
            System.out.println("Got it. I've added this task:");
            System.out.println(t);
            System.out.println("Now you have " + tl.size() + " tasks in the list.");
        }
    }

    /**
     * Prints a list of task(s) that matches the keyword partially or fully.
     * @param tl TaskList
     * @param cmd String 
     * @throws JojoException when find is invalid
     */
    public void printFind(TaskList tl, String cmd) throws JojoException {
        String test = Parser.parseToDoOrFind(cmd);
        if (test.strip().equals("")) {
            throw new JojoTaskNoDescException();
        } else {
            System.out.println("Here are the matching tasks in the list:");
            int i = 1;
            for (int j = 0; j < tl.size(); j++) { // printing out all items in the list
                String taskDesc = tl.get(j).description.toLowerCase();
                if (taskDesc.contains(test.toLowerCase())) {
                    System.out.println(i + ". " + tl.get(j));
                    i++;
                }
            }
        }
    }

    /**
     * Prints an exit message.
     */
    public void showExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints an error message when the file does not load correctly.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }

    public String getResponse(String response) {
        return response;
    }
}
