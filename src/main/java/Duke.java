import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Duke {
    static String horzLine = "____________________________________________________________";
    static String chatbotName = "Destiny";
    static String greetingMessage = horzLine
            + "\nGreetings! I'm " + chatbotName + "\nHow may I serve you?\n"
            + horzLine;
    static String goodbyeMessage = horzLine +
            "\nBye. Hope to see you again soon!\n"
            + horzLine;

    static ArrayList<Task> taskStorage = new ArrayList<>(100);

    final static String dataPath = System.getProperty("user.dir") + "/data";
    final static String fileName = "duke.txt";

    public static void loadData() {
        File directory = new File(dataPath);
        File file = new File(dataPath + "/" + fileName);
        ArrayList<Task> tempTaskStorage = new ArrayList<>(100);

        // check if data directory and file exist already
        if (!directory.exists()){
            directory.mkdir();
            try {
                file.createNewFile();
                System.out.println("First initialization, creating new save file...");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("Data file missing, creating new save file...");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                Scanner s = new Scanner(file);
                while (s.hasNextLine()) {
                    String newEntry = s.nextLine();
                    String[] entryDetails = newEntry.split(" \\| ");
                    Task newTask;
                    switch (entryDetails[0]) {
                        case "T":
                            newTask = new ToDo(entryDetails[1], entryDetails[2]);
                            tempTaskStorage.add(newTask);
                            break;
                        case "D":
                            newTask = new Deadline(entryDetails[1], entryDetails[2], entryDetails[3]);
                            tempTaskStorage.add(newTask);
                            break;
                        case "E":
                            newTask = new Event(entryDetails[1], entryDetails[2], entryDetails[3], entryDetails[4]);
                            tempTaskStorage.add(newTask);
                            break;
                    }
                }
                taskStorage.addAll(tempTaskStorage);
            } catch (DukeException | ArrayIndexOutOfBoundsException e) {
                try {
                    file.delete();
                    file.createNewFile();
                    System.out.println("Data file corrupted, creating new save file...");
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void saveData() {
        File directory = new File(dataPath);
        File file = new File(dataPath + "/" + fileName);
        // reset file
        try {
            file.delete();
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            for (Task tsk: taskStorage) {
                if (tsk instanceof ToDo) {
                    writer.write("T | " + tsk.getStatusAsNum() + " | " + tsk.getDescription()
                            + System.lineSeparator());
                } else if (tsk instanceof Deadline) {
                    writer.write("D | " + tsk.getStatusAsNum() + " | " + tsk.getDescription()
                            + " | " + ((Deadline) tsk).getBy()
                            + System.lineSeparator());
                } else if (tsk instanceof Event) {
                    writer.write("E | " + tsk.getStatusAsNum() + " | " + tsk.getDescription()
                            + " | " + ((Event) tsk).getFrom() + " | " + ((Event) tsk).getTo()
                            + System.lineSeparator());
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printWithLines(String message) {
        System.out.println(horzLine);
        System.out.println(message);
        System.out.println(horzLine);
    }

    public static void addTask(Task newTask) {
        taskStorage.add(newTask);
        printWithLines("Got it. I've added this task:\n   " + newTask.toString() +
                "\nNow you have " + taskStorage.size() + (taskStorage.size() > 1 ? " tasks ": " task ") +
                "in the list.");
    }

    public static void list() {
        if (taskStorage.size() == 0) {
            printWithLines("There's nothing in your list so far");
            return;
        }
        System.out.println(horzLine);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskStorage.size(); i++) {
            int j = i + 1;
            System.out.println(j + ". " + taskStorage.get(i).toString());
        }
        System.out.println(horzLine);
    }

    public static void delete(int index) throws DukeException {
        if (taskStorage.size() == 0) {
            throw new DukeException("Nothing is in the list yet");
        }
        if (index < 1 || index > taskStorage.size()) {
            throw new DukeException("Please enter a number between 1 and " + taskStorage.size());
        }
        Task tempTask = taskStorage.get(index - 1);
        taskStorage.remove(index - 1);
        printWithLines("Noted. I've removed this task:\n   " + tempTask.toString() +
                "\nNow you have " + taskStorage.size() + (taskStorage.size() > 1 ? " tasks ": " task ") +
                "in the list.");
    }

    public static void markDone(int index) throws DukeException {
        if (taskStorage.size() == 0) {
            throw new DukeException("Nothing is in the list yet");
        }
        if (index < 1 || index > taskStorage.size()) {
            throw new DukeException("Please enter a number between 1 and " + taskStorage.size());
        }
        taskStorage.get(index - 1).markAsDone();
        printWithLines("Nice! I've marked this task as done:\n  " + taskStorage.get(index - 1).toString());
    }

    public static void markNotDone(int index) throws DukeException {
        if (taskStorage.size() == 0) {
            throw new DukeException("Nothing is in the list yet");
        }
        if (index < 1 || index > taskStorage.size()) {
            throw new DukeException("Please enter a number between 1 and " + taskStorage.size());
        }
        taskStorage.get(index - 1).markAsUndone();
        printWithLines("OK, I've marked this task as not done yet:\n  " + taskStorage.get(index - 1).toString());
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
        return details.trim();
    }
    public static void main(String[] args) {
        loadData();
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
                try {
                    String possInteger = getCmdDetails(userCmd, cmdDetails);
                    int taskIndex = Integer.valueOf(possInteger);

                    if (userCmd.equalsIgnoreCase("unmark")) {
                        markNotDone(taskIndex);
                    } else {
                        markDone(taskIndex);
                    }
                } catch (DukeException e) {
                    printWithLines(e.getMessage());
                } catch (NumberFormatException e) {
                    printWithLines((taskStorage.size() != 0 ?
                            "Invalid input type\nEnter a number between 1 and " + taskStorage.size() :
                            "Invalid input type\nCan't mark or unmark either cause the list is empty"));
                }
            } else if (userCmd.equalsIgnoreCase("delete")) {
                try {
                    String possInteger = getCmdDetails(userCmd, cmdDetails);
                    int taskIndex = Integer.valueOf(possInteger);
                    delete(taskIndex);
                } catch (DukeException e) {
                    printWithLines(e.getMessage());
                } catch (NumberFormatException e) {
                    printWithLines((taskStorage.size() != 0 ?
                            "Invalid input type\nEnter a number between 1 and " + taskStorage.size() :
                            "Invalid input type\nCan't mark or unmark either cause the list is empty"));
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
            saveData();
        }
        System.out.println(goodbyeMessage);
    }
}
