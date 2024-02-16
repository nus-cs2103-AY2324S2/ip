package lilybot;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Ui class handles interactions with users.
 */
public class Ui {

    private Task deletedTask;

    /**
     * Constructor of Ui class.
     */
    public Ui() {

    }

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

    public String findMatchingTask(String keyWord, TaskList ls) {
        StringBuilder s = new StringBuilder("Here're the matching tasks in ur list: \n");
        AtomicInteger counter = new AtomicInteger(1);
//        for (int i = 0; i < ls.getSize(); i++) {
//            Task tk = ls.get(i);
//            if (tk.getDescription().contains(keyWord)) {
//                counter++;
//                s.append(counter + ". " + tk.toString() + "\n");
//            }
//        }

        ls.getLs()
                .stream()
                .filter(t -> t.getDescription().contains(keyWord))
                .forEach(tk -> {
                    s.append(counter + ". " + tk.toString() + "\n");
                    counter.getAndIncrement();});

        return s.toString();
    }


    /**
     * Greet users.
     */
    public void sayHi() {
        String hi = "Helloo! I'm LilyBot ;)\nWhat's up'?\n";
        System.out.print(hi);
    }

    /**
     * Say goodbye to users.
     */
    public String sayBye() {
        String s = "Bye Bye. See u later! \n" ;
        return s;
    }

    /**
     * Print to inform users that task is marked done.
     * @param command The command entered by users.
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
     * Print to inform users that task is marked not done.
     *
     * @param command The command entered by users.
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
     * Print to inform users that task is added.
     *
     * @param task Specifics of the task to be added.
     */
    private String printAdded(String task, TaskList taskList) {
        return "  Got it. I've added this task:"+ "\n"
                + "  " + task + "\n"
                + "  Now u have " + taskList.getSize() +
                " tasks in the list.";
    }

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
                String[] date = cmd[1].split("/by",
                        2);
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
        }
        else {
            return invalidInput();

        }
    }


    /**
     * Print to inform users that task is removed.
     *
     * @param command The command entered by users.
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
     * Inform users that the description entered is invalid.
     */
    public String invalidDescription() {
        return "Oops! Sorry, I don't know what that means. Description is empty";
    }

    /**
     * Inform users that the input entered is invalid.
     */
    public String invalidInput() {
        return "Oops! I don't understand the instruction.";
    }

    /**
     * Inform users that the number entered is invalid.
     */
    public String invalidInputNumber() {
        return "Plz tell me which task. I want a valid task number" + "\n";
    }

    /**
     * Inform users that the ddl for Deadline task entered is invalid.
     */
    public String invalidDdlFormat() {
        return "Plz enter a date for the deadline using '/by' \n"
                + "Also notice the format should be like this: yyyy-mm-dd'";
    }

    /**
     * Inform users that the date format for
     * the event entered is invalid.
     */
    public String invalidEventFormat() {
        return "Plz enter a date for the event using '/from' and '/to'";
    }

    public String invalidKeyWord() {
        return "Plz enter a valid keyword";
    }

    protected String noLasCommand() {
        return "This is the first command. Unable to undo.";
    }

    /**
     * Inform users that there is unknown file format.
     */
    public static String botUnknownFormat(int i) {
        i = i + 1;
        return "Oops, I don't understand the file format \n"
                + "Line " + i + " in the given file will be ignored";
    }



}
