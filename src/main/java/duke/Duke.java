package duke;

import java.util.Arrays;

public class Duke {

    private static String horzLine = "____________________________________________________________";
    private static String chatbotName = "Destiny";
    private static String greetingMessage = horzLine
            + "\nGreetings! I'm " + chatbotName + "\nHow may I serve you?\n"
            + horzLine;
    private static String goodbyeMessage = horzLine
            + "\nBye. Hope to see you again soon!\n"
            + horzLine;

    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList(storage.loadData());
        parser = new Parser();
    }

    public static void printWithLines(String message) {
        System.out.println(horzLine);
        System.out.println(message);
        System.out.println(horzLine);
    }

    public void run() {
        System.out.println(greetingMessage);

        while (!ui.getUserInput().equalsIgnoreCase("bye")) {
            ui.inputMessage();
            String userMessage = ui.getUserInput();

            if (userMessage.equalsIgnoreCase("bye")) {
                break;
            }

            String[] userMessageArr = parser.getCommand(userMessage);
            String userCmd = userMessageArr[0];
            String cmdDetails = userMessageArr[1];

            // checks if command is valid
            try {
                AcceptedCmds testCommand = AcceptedCmds.valueOf(userCmd.toLowerCase());
            } catch (IllegalArgumentException e) {
                printWithLines("Please enter a valid command\nThe list of valid commands are as follows:\n"
                        + Arrays.asList(AcceptedCmds.values()));
                continue;
            }

            if (userCmd.equalsIgnoreCase("list")) {
                list();
            } else if (userCmd.equalsIgnoreCase("mark")
                    || userCmd.equalsIgnoreCase("unmark")) {
                try {
                    String possInteger = parser.getCmdDetails(userCmd, cmdDetails);
                    int taskIndex = Integer.valueOf(possInteger);

                    if (userCmd.equalsIgnoreCase("unmark")) {
                        markNotDone(taskIndex);
                    } else {
                        markDone(taskIndex);
                    }
                } catch (DukeException e) {
                    printWithLines(e.getMessage());
                } catch (NumberFormatException e) {
                    printWithLines((tasks.size() != 0
                            ? "Invalid input type\nEnter a number between 1 and " + tasks.size()
                            : "Invalid input type\nCan't mark or unmark either cause the list is empty"));
                }
            } else if (userCmd.equalsIgnoreCase("delete")) {
                try {
                    String possInteger = parser.getCmdDetails(userCmd, cmdDetails);
                    int taskIndex = Integer.valueOf(possInteger);
                    tasks.delete(taskIndex);
                } catch (DukeException e) {
                    printWithLines(e.getMessage());
                } catch (NumberFormatException e) {
                    printWithLines((tasks.size() != 0
                            ? "Invalid input type\nEnter a number between 1 and " + tasks.size()
                            : "Invalid input type\nCan't mark or unmark either cause the list is empty"));
                }
            } else if (userCmd.equalsIgnoreCase("todo")) {
                try {
                    String possToDo = parser.getCmdDetails(userCmd, cmdDetails);
                    ToDo newToDo = new ToDo(possToDo);
                    tasks.addTask(newToDo);
                } catch (DukeException e) {
                    printWithLines(e.getMessage());
                }
            } else if (userCmd.equalsIgnoreCase("deadline")) {
                try {
                    String possDlDetails = parser.getCmdDetails(userCmd, cmdDetails);
                    String[] splitDetails = possDlDetails.toLowerCase().split("/by ", 2);
                    try {
                        Deadline newDL = new Deadline(splitDetails[0], splitDetails[1]);
                        tasks.addTask(newDL);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        printWithLines("After entering the deadline task name,\n"
                                + "add '/by' followed by your desired deadline");
                    }
                } catch (DukeException e) {
                    printWithLines(e.getMessage());
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
                            tasks.addTask(newEvent);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            printWithLines("After entering your desired start time,\n"
                                    + "add '/to' followed by your desired end time");
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        printWithLines("After entering the event task name,\n"
                                + "add '/from' followed by your desired start time");
                    }
                } catch (DukeException e) {
                    printWithLines(e.getMessage());
                }
            }
            storage.saveData(tasks);
        }
        System.out.println(goodbyeMessage);
    }


    public void list() {
        if (tasks.size() == 0) {
            printWithLines("There's nothing in your list so far");
            return;
        }
        System.out.println(horzLine);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            int j = i + 1;
            System.out.println(j + ". " + tasks.get(i).toString());
        }
        System.out.println(horzLine);
    }

    public void markDone(int index) throws DukeException {
        if (tasks.size() == 0) {
            throw new DukeException("Nothing is in the list yet");
        }
        if (index < 1 || index > tasks.size()) {
            throw new DukeException("Please enter a number between 1 and " + tasks.size());
        }
        tasks.get(index - 1).markAsDone();
        printWithLines("Nice! I've marked this task as done:\n  " + tasks.get(index - 1).toString());
    }

    public void markNotDone(int index) throws DukeException {
        if (tasks.size() == 0) {
            throw new DukeException("Nothing is in the list yet");
        }
        if (index < 1 || index > tasks.size()) {
            throw new DukeException("Please enter a number between 1 and " + tasks.size());
        }
        tasks.get(index - 1).markAsUndone();
        printWithLines("OK, I've marked this task as not done yet:\n  " + tasks.get(index - 1).toString());
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
