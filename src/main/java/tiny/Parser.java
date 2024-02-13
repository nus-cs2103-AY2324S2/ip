package tiny;

import tiny.exceptions.TinyException;
import tiny.tasks.Deadline;
import tiny.tasks.Event;
import tiny.tasks.Todo;

/**
 * Represents the parser for user commands.
 */
public class Parser {
    protected boolean isExit = false;
    protected String input;
    protected TaskList taskList;

    /**
     * Parses the user input into commands and return the appropriate response.
     *
     * @param input    The string input from the user
     * @param taskList The instance of the taskList which contains all the tasks.
     * @return The message to be displayed to the user.
     * @throws TinyException When input is invalid when parsing the respective
     *                       commands.
     */
    public String parse(String input, TaskList taskList) throws TinyException {
        this.input = input;
        this.taskList = taskList;

        try {
            if (input.equals("list")) {
                return taskList.list();
            } else if (isValidCommand(input, "mark", 4)) {
                return mark();
            } else if (isValidCommand(input, "unmark", 6)) {
                return unmark();
            } else if (isValidCommand(input, "todo", 4)) {
                return todo();
            } else if (isValidCommand(input, "deadline", 8)) {
                return deadline();
            } else if (isValidCommand(input, "event", 5)) {
                return event();
            } else if (isValidCommand(input, "delete", 6)) {
                return delete();
            } else if (isValidCommand(input, "find", 4)) {
                return find();
            } else if (input.equals("bye")) {
                isExit = true;
                return bye();
            } else {
                return cmdUnknown();
            }
        } catch (TinyException e) {
            throw e;
        }
    }

    public boolean isExit() {
        return isExit;
    }

    /**
     * Handles the "mark" command.
     *
     * @return The message to be displayed to the user.
     * @throws TinyException When input is invalid.
     */
    private String mark() throws TinyException {
        try {
            String[] s = input.split(" ");
            if (isValidCode(s, 2, "mark")) {
                return "OOPS! You need to type \"mark <number>\" to change the status to done!";
            }
            int ind = Integer.parseInt(s[1]);
            taskList.get(ind - 1).taskDone();
            assert taskList.get(ind - 1).taskIsDone() == true;
            return "Nice! I've marked this task as done:\n      " + taskList.get(ind - 1);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return "OOPS! You need to type \"mark <number>\" to change the status to done!";
        } catch (NullPointerException e) {
            return "OOPS! Please type a valid number! Type \"list\" to check the lists of tasks.";
        } catch (Exception e) {
            throw new TinyException("Something went wrong...");
        }
    }

    /**
     * Handles the “unmark" command.
     *
     * @return The message to be displayed to the user.
     * @throws TinyException When input is invalid.
     */
    private String unmark() throws TinyException {
        try {
            String[] s = input.split(" ");
            if (isValidCode(s, 2, "unmark")) {
                return "OOPS! You need to type \"unmark <number>\" to change the status not done!";
            }
            int ind = Integer.parseInt(s[1]);
            taskList.get(ind - 1).taskUndone();
            assert taskList.get(ind - 1).taskIsDone() == false;
            return "OK, I've marked this task as not done yet:\n      " + taskList.get(ind - 1);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return "OOPS! You need to type \"unmark <number>\" to change the status to not done!";
        } catch (NullPointerException e) {
            return "OOPS! Please type a valid number! Type \"list\" to check the lists of tasks.";
        } catch (Exception e) {
            throw new TinyException("Something went wrong...");
        }
    }

    /**
     * Handles the “todo” command.
     *
     * @return The message to be displayed to the user.
     * @throws TinyException When input is invalid.
     */
    private String todo() throws TinyException {
        try {
            String name = "";
            String[] st = input.split("");
            String[] s = input.split(" ");
            if (!s[0].equals("todo")) {
                return "OOPS! You need to type \"todo <description>\" to create a new todo!";
            } else {
                for (int i = 5; i < st.length; i++) {
                    name += st[i];
                }
                if (name == "") {
                    return "OOPS! The description of a todo cannot be empty.";
                } else {
                    taskList.add(new Todo(name));
                    return "Got it. I've added this task:\n      " + taskList.get(taskList.size() - 1)
                            + "\nNow you have " + taskList.size() + " task(s) in the list.";
                }
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return "OOPS! You need to type \"todo <description>\" to create a new todo!";
        } catch (Exception e) {
            throw new TinyException("Something went wrong...");
        }
    }

    /**
     * Handles the deadline command.
     *
     * @return The message to be displayed to the user.
     * @throws TinyException When input is invalid.
     */
    private String deadline() throws TinyException {
        try {
            String name = "";
            String[] st = input.split("/by ");
            String[] s = input.split(" ");
            if (!s[0].equals("deadline")) {
                return "OOPS! You need to type \"deadline <description> /by <yyyy-mm-dd> <time>\" "
                        + "to create a new deadline!";
            } else {
                name = st[0].substring(9);
                taskList.add(new Deadline(name.trim(), st[1]));
                return "Got it. I've added this task:\n" + "      " + taskList.get(taskList.size() - 1)
                        + "\nNow you have " + taskList.size() + " task(s) in the list.";
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return "OOPS! You need to type \"deadline <description> /by <yyyy-mm-dd> <time>\" "
                    + "to create a new deadline!";
        } catch (TinyException e) {
            throw e;
        }
    }

    /**
     * Handles the “event” command.
     *
     * @return The message to be displayed to the user.
     * @throws TinyException When input is invalid.
     */
    private String event() throws TinyException {
        try {
            String name = "";
            String[] s = input.split(" ");
            if (!s[0].equals("event")) {
                return "OOPS! You need to type \"event <description> /from <start date> /to <end date>\" "
                        + "to create a new deadline!";
            } else {
                String[] from = input.split("/from ");
                String[] fromTo = from[1].split("/to ");
                name = from[0].substring(5);
                taskList.add(new Event(name.trim(), fromTo[0].trim(), fromTo[1].trim()));
                return "Got it. I've added this task:\n" + "      " + taskList.get(taskList.size() - 1)
                        + "\nNow you have " + taskList.size() + " task(s) in the list.";
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return "OOPS! You need to type \"event <description> /from <start date> /to <end date>\" "
                    + "to create a new deadline!";
        } catch (Exception e) {
            throw new TinyException("Something went wrong...");
        }
    }

    /**
     * Handles the “delete” command.
     *
     * @return The message to be displayed to the user.
     * @throws TinyException When input is invalid.
     */
    private String delete() throws TinyException {
        try {
            String[] s = input.split(" ");
            if (s.length != 2 || !s[0].equals("delete")) {
                return "OOPS! You need to type \"delete <number>\" to delete the task!";
            }
            int ind = Integer.parseInt(s[1]);
            String output = "Noted. I've removed this task:"
                    + "\n      " + taskList.get(ind - 1).toString()
                    + "\nNow you have " + (taskList.size() - 1) + " task(s) in the list.";
            taskList.delete(ind - 1);
            return output;
        } catch (NumberFormatException e) {
            return "OOPS! You need to type \"delete <number>\" to delete the task!";
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            return "OOPS! Please type a valid number! Type \"list\" to check the lists of tasks.";
        } catch (Exception e) {
            throw new TinyException("Something went wrong...");
        }
    }

    /**
     * Handles the “find” command.
     *
     * @return The message to be displayed to the user.
     * @throws TinyException When input is invalid.
     */
    private String find() throws TinyException {
        try {
            String[] s = input.split(" ");
            if (s.length != 2 || !s[0].equals("find")) {
                return "OOPS! You need to type \"find <keyword>\" to find the task(s)!";
            }
            return taskList.find(s[1]);
        } catch (Exception e) {
            throw new TinyException("Something went wrong...");
        }
    }

    private boolean isValidCode(String[] commandInput, int length, String command) {
        return commandInput.length != length || !commandInput[0].equals(command);
    }

    /**
     * Handles the “bye” command.
     *
     * @return The message to be displayed to the user.
     */
    private String bye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Handles the case where no command is recognised.
     *
     * @return The message to be displayed to the user.
     */
    private String cmdUnknown() {
        return "I'm sorry, but I don't know what that means :-(";
    }

    /**
     * Checks if the user input contains a command.
     *
     * @return True if the command from the user is the same as expected, otherwise
     *         False.
     */
    private boolean isValidCommand(String input, String name, int len) {
        return input.length() >= len && input.substring(0, len).equals(name);
    }
}
