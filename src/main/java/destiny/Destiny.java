package destiny;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import gui.Main;
import javafx.application.Application;



/**
 * The main class that contains functions involved with running the chatbot.
 */
public class Destiny {

    public static final String CHATBOT_NAME = "Destiny";
    public static final String GREETING_MSG = "\nGreetings! I'm " + CHATBOT_NAME + "\nHow may I serve you?\n";
    public static final String GOODBYE_MSG = "\nBye. Hope to see you again soon!\n";



    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    /**
     * Initializes the instance variables of the Destiny class.
     */
    public Destiny() {
        storage = new Storage();
        tasks = new TaskList(storage.loadData());
        parser = new Parser();
    }


    /**
     * Main code for the running of the Destiny chatbot which interacts with the graphical user interface.
     *
     * @param input The input given by the user.
     * @return Appropriate string response from Destiny.
     */
    public String getResponse(String input) {
        if (input.equalsIgnoreCase("bye")) {
            return GOODBYE_MSG;
        }

        String[] userMessageArr = parser.getCommand(input);
        String userCmd = userMessageArr[0];
        String cmdDetails = userMessageArr[1];
        String result = "";

        // checks if command is valid
        try {
            AcceptedCmds testCommand = AcceptedCmds.valueOf(userCmd.toLowerCase());
        } catch (IllegalArgumentException e) {
            String errorMsg = "Please enter a valid command\nThe list of valid commands are as follows:\n"
                    + Arrays.asList(AcceptedCmds.values());
            return errorMsg;
        }


        if (userCmd.equalsIgnoreCase("list")) {
            return list();
        }

        if (userCmd.equalsIgnoreCase("find")) {
            try {
                String searchStr = parser.getCmdDetails(userCmd, cmdDetails);
                return find(searchStr);
            } catch (DukeException e) {
                return e.getMessage();
            }
        }

        // group of commands that edit the list of tasks
        if (userCmd.equalsIgnoreCase("mark")
                || userCmd.equalsIgnoreCase("unmark")) {
            try {
                String possInteger = parser.getCmdDetails(userCmd, cmdDetails);
                int taskIndex = Integer.valueOf(possInteger);

                if (userCmd.equalsIgnoreCase("unmark")) {
                    result = markNotDone(taskIndex);
                } else {
                    result = markDone(taskIndex);
                }
            } catch (DukeException e) {
                result = e.getMessage();
            } catch (NumberFormatException e) {
                result = (tasks.size() != 0
                        ? "Invalid input type\nEnter a number between 1 and " + tasks.size()
                        : "Invalid input type\nCan't mark or unmark either cause the list is empty");
            }
        } else if (userCmd.equalsIgnoreCase("delete")) {
            try {
                String possInteger = parser.getCmdDetails(userCmd, cmdDetails);
                int taskIndex = Integer.valueOf(possInteger);
                result = tasks.delete(taskIndex);
            } catch (DukeException e) {
                result = e.getMessage();
            } catch (NumberFormatException e) {
                result = (tasks.size() != 0
                        ? "Invalid input type\nEnter a number between 1 and " + tasks.size()
                        : "Invalid input type\nCan't mark or unmark either cause the list is empty");
            }
        } else if (userCmd.equalsIgnoreCase("todo")) {
            try {
                String possToDo = parser.getCmdDetails(userCmd, cmdDetails);
                ToDo newToDo = new ToDo(possToDo);
                result = tasks.addTask(newToDo);
            } catch (DukeException e) {
                result = e.getMessage();
            }
        } else if (userCmd.equalsIgnoreCase("deadline")) {
            try {
                String possDlDetails = parser.getCmdDetails(userCmd, cmdDetails);
                String[] splitDetails = possDlDetails.toLowerCase().split("/by ", 2);
                try {
                    Deadline newDL = new Deadline(splitDetails[0], splitDetails[1]);
                    result = tasks.addTask(newDL);
                } catch (ArrayIndexOutOfBoundsException e) {
                    result = "After entering the deadline task name,\n"
                            + "add '/by' followed by your desired deadline";
                }
            } catch (DukeException e) {
                result = e.getMessage();
            }

        } else if (userCmd.equalsIgnoreCase("event")) {
            try {
                String possEventDetails = parser.getCmdDetails(userCmd, cmdDetails);
                String[] splitDetails = possEventDetails.split("/from ", 2);
                String[] secondSplitDetails = new String[2];
                try {
                    secondSplitDetails = splitDetails[1].split("/to ", 2);
                    try {
                        Event newEvent = new Event(splitDetails[0], secondSplitDetails[0], secondSplitDetails[1]);
                        result = tasks.addTask(newEvent);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        result = "After entering your desired start time,\n"
                                + "add '/to' followed by your desired end time";
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    result = "After entering the event task name,\n"
                            + "add '/from' followed by your desired start time";
                }
            } catch (DukeException e) {
                result = e.getMessage();
            }
        }
        storage.saveData(tasks);

        return result;
    }

    /**
     * Collates each task in the TaskList 'tasks' into a string.
     *
     * @return String of all elements in tasklist.
     */
    public String list() {
        if (tasks.size() == 0) {
            return "There's nothing in your list so far";
        }
        String listStr = "Here are the tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            int j = i + 1;
            listStr += "\n " + j + ". " + tasks.get(i).toString();
        }
        return listStr;
    }

    /**
     * Finds tasks in the TaskList that contain the given search string.
     *
     * @param searchStr Keyword to find elements in the TaskList.
     */
    public String find(String searchStr) {
        List<Task> matches = tasks.getTaskList().stream().filter(task -> task.getDescription().contains(searchStr))
                .collect(Collectors.toList());

        if (tasks.size() == 0) {
            return "There's nothing in your list so far";
        }

        if (matches.size() == 0) {
            return "There's nothing in your list that matches your search";
        }

        String results = "Here are the matching tasks in your list:";
        for (int i = 0; i < matches.size(); i++) {
            int j = i + 1;
            results += "\n" + j + ". " + matches.get(i).toString();
        }
        return results;
    }

    /**
     * Marks the indicated task as done.
     * @param index The task number displayed by the list.
     * @return Message for successful mark.
     * @throws DukeException If 0 < index < tasks.size().
     */
    public String markDone(int index) throws DukeException {
        if (tasks.size() == 0) {
        }
        if (index < 1 || index > tasks.size()) {
            throw new DukeException("Please enter a number between 1 and " + tasks.size());
        }
        tasks.get(index - 1).markAsDone();
        return "Nice! I've marked this task as done:\n  " + tasks.get(index - 1).toString();
    }

    /**
     * Marks the indicated task as not done.
     * @param index The task number displayed by the list.
     * @return Message for successful unmark.
     * @throws DukeException If 0 < index < tasks.size().
     */
    public String markNotDone(int index) throws DukeException {
        if (tasks.size() == 0) {
            throw new DukeException("Nothing is in the list yet");
        }
        if (index < 1 || index > tasks.size()) {
            throw new DukeException("Please enter a number between 1 and " + tasks.size());
        }
        tasks.get(index - 1).markAsUndone();
        return "OK, I've marked this task as not done yet:\n  " + tasks.get(index - 1).toString();
    }

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
