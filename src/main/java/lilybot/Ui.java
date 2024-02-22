package lilybot;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Ui class handles interactions with users.
 */
public class Ui {

    private Task deletedTask;

    /**
     * Construct a Ui object.
     */
    public Ui() {

    }

    /**
     * Lists all the tasks to users.
     *
     * @param ls The taskList to be listed.
     * @return A string of all the tasks.
     */
    public String listTask(TaskList ls) {
        StringBuilder s = new StringBuilder("Here're the tasks in ur list: \n");
        int counter = 0;
        for (int i = 0; i < ls.getSize(); i++) {
            counter++;
            Task tk = ls.get(i);
            s.append(counter+ ". "
                    + tk.toString()
                    + "\n");
        }
        return s.toString();
    }

    /**
     * Finds the matching tasks.
     *
     * @param keyWord The keyWord to be matched.
     * @param ls The list of tasks to be searched in.
     * @return The tasks that contain the keyWord.
     */
    public String findMatchingTask(String keyWord, TaskList ls) {
        StringBuilder s = new StringBuilder("Here're the matching tasks in ur list: \n");
        AtomicInteger counter = new AtomicInteger(1);

        ls.getLs()
                .stream()
                .filter(t -> t.getDescription().contains(keyWord))
                .forEach(tk -> {
                    s.append(counter + ". " + tk.toString() + "\n");
                    counter.getAndIncrement();});

        return s.toString();
    }


    /**
     * Greets users.
     */
    public void sayHi() {
        String hi = "Helloo! I'm LilyBot ;)\nWhat's up'?\n";
        System.out.print(hi);
    }

    /**
     * Says goodbye to users.
     */
    public String sayBye() {
        String s = "Bye Bye. See u later! \n" ;
        return s;
    }

    /**
     * Prints to inform users that task is marked done.
     *
     * @param command The command entered byDate users.
     * @param taskList The existing taskList
     * @return A string to inform users that task is marked done.
     */
    public String markDone(String command, TaskList taskList) {
        int taskNum = Parser.parseInt(command);
        assert taskNum > 0 : "Task number should be at least 1.";

        Task task = taskList.get(taskNum - 1);
        task.mark();
        String taskString = task.toString();

        return "Good job! I've marked this task as done: \n"
                + "  "
                + taskString;
    }

    /**
     * Prints to inform users that task is marked not done.
     *
     * @param command The command entered byDate users.
     * @param taskList The existing taskList
     * @return A string to inform users that task is marked not done.
     */
    public String markNotDone(String command, TaskList taskList) {
        int taskNum = Parser.parseInt(command);
        assert taskNum > 0 : "Task number should be at least 1.";

        Task task = taskList.get(taskNum - 1);
        task.unmark();
        String taskString = task.toString();
       return "Okie, Marked this task as not done yet: \n"
               + "  "
               + taskString;
    }

    /**
     * Prints to inform users that task is added.
     *
     * @param task Specifics of the task to be added.
     * @param taskList The list to be added.
     * @return A statement to inform users that task is added.
     */
    private String printAdded(String task, TaskList taskList) {
        return "  Got it. I've added this task:"+ "\n"
                + "  " + task + "\n"
                + "  Now u have " + taskList.getSize() +
                " tasks in the list.";
    }

    /**
     * Adds three different types of tasks.
     *
     * @param command The command user entered.
     * @param ls The existing list.
     * @return A statement to inform user the action done.
     */
    protected String addTask(String command, TaskList ls) {
        String[] cmd = Parser.parseCommand(command);

        if (cmd[0].equals("todo")) {
            try {
                Task t = new ToDo(cmd[1]);
                ls.add(t);

                String taskString = t.toString();
                return printAdded(taskString, ls);

            } catch (Exception exc) {
                return invalidDescription();

            }
        }
        else if (cmd[0].equals("deadline")) {
            try {
                String[] date = cmd[1].split("/by", 2);
                try {
                    String d = date[1].trim();
                    Task t = new Deadline(date[0], d);
                    ls.add(t);
                    String taskString = t.toString();
                    return printAdded(taskString, ls);
                }
                catch (Exception exc) {
                    return invalidDdlFormat();
                }
            } catch (Exception exc) {
                return invalidDescription();

            }
        } else if (cmd[0].equals("event")) {
            try {
                String[] date = cmd[1].split("/", 3);
                try {
                    Task t = new Event(date[0],
                            date[1],
                            date[2]);
                    ls.add(t);
                    String taskString = t.toString();
                    return printAdded(taskString, ls);

                } catch (Exception exc) {
                    return invalidEventFormat();

                }
            } catch (Exception exc) {
                return invalidDescription();

            }
        } else {
            return invalidInput();

        }
    }


    /**
     * Prints to inform users that task is removed.
     *
     * @param command The command entered byDate users.
     * @param taskList The existing taskList
     * @return A string to inform users that task is removed.
     */
    public String taskRemoved(String command, TaskList taskList) {
        int taskNum = Parser.parseInt(command);
        assert taskNum > 0 : "Task number should be at least 1.";

        Task task = taskList.get(taskNum - 1);
        this.deletedTask = task;
        String taskString = task.toString();
        taskList.remove(taskNum - 1);

        return "Noted. The following task is removed:" + "\n"
                + "  " + taskString + "\n"
                + "  Now u have " + taskList.getSize() +
                " tasks in the list.";
    }


    /**
     * Undoes the previous command.
     *
     * @param lastCommand The previous command that user entered.
     * @param taskList The current taskList.
     * @return A statement to inform user what action is done.
     */
    protected String undoTask(String lastCommand, TaskList taskList) {
        if (lastCommand.equals("list")) {
            return "Nothing to undo cuz last command is 'list'";
        } else if (lastCommand.startsWith("find")) {
            return "Nothing to undo cuz last command is 'find'";
        } else if (lastCommand.startsWith("mark")) {
            return markNotDone(lastCommand, taskList);
        } else if (lastCommand.startsWith("unmark")) {
            return markDone(lastCommand, taskList);
        } else if (lastCommand.startsWith("delete")) {
            int taskNum = Parser.parseInt(lastCommand);
            taskList.add(taskNum - 1, this.deletedTask);

            return printAdded(deletedTask.toString(), taskList);
        } else if (lastCommand.startsWith("todo")
                || lastCommand.startsWith("deadline")
                || lastCommand.startsWith("event")) {
            int size = taskList.getSize();
            Task task = taskList.get(size - 1);
            taskList.remove(size - 1);

            String taskString = task.toString();
            return "Noted. The following task is removed:" + "\n"
                    + "  " + taskString + "\n"
                    + "  Now u have " + taskList.getSize() +
                    " tasks in the list.";
        } else {
            return "Unexpected command in undo.";
        }
    }


    /**
     * Informs users that the description entered is invalid.
     */
    public String invalidDescription() {
        return "Oops! Sorry, I don't know what that means. Description is empty";
    }

    /**
     * Informs users that the input entered is invalid.
     */
    public String invalidInput() {
        return "Oops! I don't understand the instruction.";
    }

    /**
     * Informs users that the number entered is invalid.
     */
    public String invalidInputNumber() {
        return "Plz tell me which task. I want a valid task number" + "\n";
    }

    /**
     * Informs users that the ddl for Deadline task entered is invalid.
     */
    public String invalidDdlFormat() {
        return "Plz enter a date for the deadline using '/byDate' \n"
                + "Also notice the format should be like this: yyyy-mm-dd'";
    }

    /**
     * Informs users that the date format for
     * the event entered is invalid.
     */
    public String invalidEventFormat() {
        return "Plz enter a date for the event using '/from' and '/to'";
    }

    /**
     * Informs users that the keyWord entered is invalid.
     */
    public String invalidKeyWord() {
        return "Plz enter a valid keyword";
    }

    /**
     * Informs users that no previous command.
     */
    protected String noLastCommand() {
        return "This is the first command. Unable to undo.";
    }

    /**
     * Informs users that there is unknown file format.
     */
    public static String botUnknownFormat(int i) {
        i = i + 1;
        return "Oops, I don't understand the file format \n"
                + "Line " + i + " in the given file will be ignored";
    }



}
