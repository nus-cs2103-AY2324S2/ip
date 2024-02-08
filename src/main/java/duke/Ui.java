package duke;
import exceptions.DukeException;
import exceptions.DukeTaskNoDescException;

public class Ui {

    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Jojo :)");
    }

    public void showStartingQn() {
        System.out.println("What can I do for you?");
    }

    public void breakLines() {
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------------");
    }

    public void printList(duke.TaskList tl) {
        System.out.println("Here are the tasks in your list:");
        for (int j = 0; j < tl.size(); j++) { // printing out all items in the list
            String task = j + 1 + ". " + tl.get(j);
            System.out.println(task);
        }
    }

    public void printMark(duke.TaskList tl, String cmd) throws DukeException {
        try {
            int taskNum = Parser.parseMark(cmd);
            tl.setDone(taskNum);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tl.taskToString(taskNum));
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            throw new DukeException("Hmm...seems like the task to mark does not exist. To mark a task, input the keyword followed by the task's no. in the list. E.g.: mark 3");
        }
    }

    public void printUnmark(duke.TaskList tl, String cmd) throws DukeException {
        try {
            int taskNum = Parser.parseUnmark(cmd);
            tl.setUndone(taskNum);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(tl.taskToString(taskNum));
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            throw new DukeException("Hmm...seems like the task to unmark does not exist. To unmark a task, input the keyword followed by the task's no. in the list. E.g.: unmark 3");
        }
    }

    public void printDelete(duke.TaskList tl, String cmd) throws DukeException {
        int taskNum = Parser.parseDelete(cmd);
        if (taskNum >= tl.size()) {
            throw new DukeException("Hmm...seems like the task to delete does not exist. To delete a task, input the keyword followed by the task's no. in the list. E.g.: delete 3");
        } else {
            System.out.println("Noted. I've removed this task:");
            System.out.println(tl.taskToString(taskNum));
            tl.deleteTask(taskNum);
            System.out.println("Now you have " + tl.size() + " tasks in the list.");
        }
    }

    public void printToDo(TaskList tl, String cmd) throws DukeException {
        String test = Parser.parseToDoTest(cmd);
        if (test.strip().equals("")) {
            throw new DukeTaskNoDescException();
        } else {
            Task t = new ToDo(Parser.parseToDo(cmd));
            tl.addTask(t); // add task to list
            System.out.println("Got it. I've added this task:");
            System.out.println(t);
            System.out.println("Now you have " + tl.size() + " tasks in the list.");
        }
    }

    public void printDeadline(TaskList tl, String cmd) throws DukeException {
        String test = Parser.parseDeadlineTest(cmd);
        if (test.strip().equals("")) {
            throw new DukeTaskNoDescException();
        } else {
            String s = Parser.parseDeadline(cmd);
            Task t = new Deadline(Parser.parseDeadlineDesc(s), Parser.parseDeadlineBy(s));
            tl.addTask(t); // add task to list
            System.out.println("Got it. I've added this task:");
            System.out.println(t);
            System.out.println("Now you have " + tl.size() + " tasks in the list.");
        }
    }

    public void printEvent(TaskList tl, String cmd) throws DukeException {
        String test = Parser.parseEventTest(cmd);
        if (test.strip().equals("")) {
            throw new DukeTaskNoDescException();
        } else {
            String[] ans = Parser.parseEvent(cmd);
            Task t = new Event(ans[0], ans[1], ans[2]);
            tl.addTask(t); // add task to list
            System.out.println("Got it. I've added this task:");
            System.out.println(t);
            System.out.println("Now you have " + tl.size() + " tasks in the list.");
        }
    }

    public void showExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }
}
