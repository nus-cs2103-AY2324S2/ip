package objects;
import exception.DukeException;
import exception.InvalidCommandException;
import exception.InvalidIndexException;
import view.EncaseLines;

import static utils.DeadlineUtil.createDeadline;
import static utils.EventUtil.createEvent;
import static utils.FileUtil.getFile;
import static utils.InputUtil.getCommandType;
import static utils.InputUtil.parseIndex;

public class Commands {
    public static final String LIST = "list";
    public static final String MARK = "mark";
    public static final String UNMARK = "unmark";
    public static final String DELETE = "delete";
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String HELP = "help";

//    public static void processTask(TaskList tasks, String input) throws DukeException {
//        String command = getCommandType(input);
//        int i = parseIndex(input);
//
//        if (i == -1) {
//            throw new InvalidCommandException();
//
//        } else if (i < 0 || i > tasks.size() - 1) {
//            throw new InvalidIndexException();
//
//        } else {
//            switch (command) {
//            case MARK:
//                markTask(tasks, i);
//                break;
//            case UNMARK:
//                unmarkTask(tasks, i);
//                break;
//            case DELETE:
//                deleteTask(tasks, i);
//            }
//        }
//    }

    public static void createTask(TaskList tasks, String input) throws DukeException {
        String[] details = input.split(" ", 2);

        if (details.length < 2) {
            throw new InvalidCommandException();
        }

        Task task = null;
        String type = details[0];
        String taskDetails = details[1];

        switch (type) {
        case TODO:
            task = new ToDos(taskDetails);
            break;

        case DEADLINE:
            task = createDeadline(taskDetails);
            break;

        case EVENT:
            task = createEvent(taskDetails);
        }

        tasks.addTask(task);
        String o = String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.", task.toString(), tasks.size());
        EncaseLines.display(o);
    }

    public static void listTasks(TaskList tasks) {
        StringBuilder output = new StringBuilder();

        if (tasks.isEmpty()) {
            EncaseLines.display("List is empty!");
        } else {

            for (int i = 0; i < tasks.size(); i++) {
                output.append(String.format("%d. %s", i + 1, tasks.get(i)));

                if (i < tasks.size() - 1) {
                    output.append("\n");
                }
            }

            EncaseLines.display(output.toString());
        }
    }

    private static void unmarkTask(TaskList tasks, int i) throws DukeException {
        tasks.unmarkTask(i);
        Task t = tasks.get(i);

        String o = String.format("Nice! I've marked this task as not done yet: \n   %s",  t.toString());

        EncaseLines.display(o);
    }

    private static void markTask(TaskList tasks, int i) throws DukeException {
        tasks.markTask(i);
        Task t = tasks.get(i);

        String o = String.format("Nice! I've marked this task as done: \n   %s",  t.toString());

        EncaseLines.display(o);
    }

    public static void deleteTask(TaskList tasks, int i) throws DukeException {
        if (i < 0 || i >= tasks.size()) {
            throw new InvalidIndexException();
        }

        Task task = tasks.get(i);

        tasks.remove(i);
        String o = String.format("Noted. I've removed this task:\n %s\nNow you have %d tasks in the list.", task.toString(), tasks.size());
        EncaseLines.display(o);
    }

    public static void printHelp() {
        EncaseLines.display(getFile(FilePath.HELP_PATH));
    }
}