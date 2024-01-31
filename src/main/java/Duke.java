

public class Duke {
    static String horzLine = "____________________________________________________________";
    static String chatbotName = "Destiny";
    static String greetingMessage = horzLine
            + "\nGreetings! I'm " + chatbotName + "\nHow may I serve you?\n"
            + horzLine;
    static String goodbyeMessage = horzLine +
            "\nBye. Hope to see you again soon!\n"
            + horzLine;

    static Task[] taskStorage = new Task[100];
    static int numInStorage = 0;

    public static void printWithLines(String message) {
        System.out.println(horzLine);
        System.out.println(message);
        System.out.println(horzLine);
    }

    public static void addTask(Task newTask) {
        taskStorage[numInStorage] = newTask;
        numInStorage++;
        printWithLines("Got it. I've added this task:\n   " + newTask.toString() +
                "\nNow you have " + numInStorage + (numInStorage > 1 ? " tasks ": " task ") +
                "in the list.");
    }
    public static void list() {
        System.out.println(horzLine);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < numInStorage; i++) {
            int j = i + 1;
            System.out.println(j + ". " + taskStorage[i].toString());
        }
        System.out.println(horzLine);
    }

    public static void markDone(int index) {
        taskStorage[index].markAsDone();
        printWithLines("Nice! I've marked this task as done:\n  " + taskStorage[index].toString());
    }

    public static void markNotDone(int index) {
        taskStorage[index].markAsUndone();
        printWithLines("OK, I've marked this task as not done yet:\n  " + taskStorage[index].toString());
    }

    public static String[] getCommand(String userMessage) {
        String[] result = new String[2];
        Boolean foundSplit = false;
        for (int i = 0; i < userMessage.length(); i++) {
            if (userMessage.charAt(i) == ' ') {
                result[0] = userMessage.substring(0, i);
                result[1] = userMessage.substring(i + 1, userMessage.length());
                foundSplit = true;
                break;
            }
        }
        if (!foundSplit) {
            result[0] = userMessage;
        }
        return result;
    }

    public static String getCmdDetails(String cmd, String details) throws DukeException {
        if (details == null || details.trim().length() == 0) {
            throw new DukeException("Please enter a description for the " + cmd + " command");
        }
        return details;
    }
    public static void main(String[] args) {
        System.out.println(greetingMessage);

        ChatbotUser user = new ChatbotUser();

        while(!user.getUserInput().equalsIgnoreCase("bye")) {
            user.inputMessage();
            String userMessage = user.getUserInput();

            if (userMessage.equalsIgnoreCase("bye")) {
                break;
            }

            String[] userMessageArr = getCommand(userMessage);
            String userCmd = userMessageArr[0];
            String cmdDetails = userMessageArr[1];

            // checks if command is valid
            try {
                AcceptedCmds testCommand = AcceptedCmds.valueOf(userCmd.toLowerCase());
            } catch (IllegalArgumentException e) {
                printWithLines("Please enter a valid command\nThe list of valid commands are as follows:\n" +
                        java.util.Arrays.asList(AcceptedCmds.values()));
                continue;
            }

            if (userCmd.equalsIgnoreCase("list")) {
                list();
            } else if (userCmd.equalsIgnoreCase("mark") ||
                    userCmd.equalsIgnoreCase("unmark")) {
                int taskIndex = -1;

                try {
                    String possInteger = getCmdDetails(userCmd, cmdDetails);
                    taskIndex = Integer.valueOf(possInteger);
                    // check if index provided is too high
                    if (taskIndex > numInStorage || taskIndex < 1) {
                        printWithLines("Please enter a number between 1 and " + numInStorage);
                    } else {
                        if (userCmd.equalsIgnoreCase("unmark")) {
                            markNotDone(taskIndex - 1);
                        } else {
                            markDone(taskIndex - 1);
                        }
                    }
                } catch (DukeException e) {
                    printWithLines(e.getMessage());
                } catch (NumberFormatException e) {
                    printWithLines("Invalid integer input!\nEnter a number between 1 and " + numInStorage);
                }
            } else if (userCmd.equalsIgnoreCase("todo")) {
                try {
                    String possToDo = getCmdDetails(userCmd, cmdDetails);
                    ToDo newToDo = new ToDo(possToDo);
                    addTask(newToDo);
                } catch (DukeException e) {
                    printWithLines(e.getMessage());
                }
            } else if (userCmd.equalsIgnoreCase("deadline")) {
                try {
                    String possDLDetails = getCmdDetails(userCmd, cmdDetails);
                    String[] splitDetails = possDLDetails.toLowerCase().split("/by ", 2);
                    try {
                        Deadline newDL = new Deadline(splitDetails[0], splitDetails[1]);
                        addTask(newDL);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        printWithLines("After entering the deadline task name,\n" +
                                "add '/by' followed by your desired deadline");
                    }
                } catch (DukeException e) {
                    printWithLines(e.getMessage());
                }

            } else if (userCmd.equalsIgnoreCase("event")){
                try {
                    String possEventDetails = getCmdDetails(userCmd, cmdDetails);
                    String[] splitDetails = possEventDetails.split("/from ", 2);
                    String[] secondSplitDetails = new String[2];
                    try {
                        secondSplitDetails = splitDetails[1].split("/to ", 2);
                        try {
                            Event newEvent = new Event(splitDetails[0], secondSplitDetails[0], secondSplitDetails[1]);
                            addTask(newEvent);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            printWithLines("After entering your desired start time,\n" +
                                    "add '/to' followed by your desired end time");
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        printWithLines("After entering the event task name,\n" +
                                "add '/from' followed by your desired start time");
                    }
                } catch (DukeException e) {
                    printWithLines(e.getMessage());
                }
            }
        }
        System.out.println(goodbyeMessage);
    }
}
