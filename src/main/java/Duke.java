

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
    public static void main(String[] args) {
        System.out.println(greetingMessage);

        ChatbotUser user = new ChatbotUser();

        while(!user.getUserInput().equalsIgnoreCase("bye")) {
            user.inputMessage();
            String userMessage = user.getUserInput();
            String[] userMessageArr = getCommand(userMessage);
            String userCmd = userMessageArr[0];
            String cmdDetails = userMessageArr[1];

            if (userCmd.equalsIgnoreCase("list")) {
                list();
            } else if (userCmd.equalsIgnoreCase("mark") ||
                    userCmd.equalsIgnoreCase("unmark")) {

                int taskIndex = -1;
                // check if valid integer is entered
                try {
                    taskIndex = Integer.valueOf(cmdDetails);
                } catch (NumberFormatException e) {
                    printWithLines("Invalid integer input!\nEnter a number between 1 and " + numInStorage);
                }
                // check if index provided is too high
                if (taskIndex > numInStorage || taskIndex < 1) {
                    printWithLines("index invalid");
                } else {
                    if (userCmd.equalsIgnoreCase("unmark")) {
                        markNotDone(taskIndex - 1);
                    } else {
                        markDone(taskIndex - 1);
                    }
                }
            } else if (userCmd.equalsIgnoreCase("todo")) {
                ToDo newToDo = new ToDo(cmdDetails);
                addTask(newToDo);
            } else if (userCmd.equalsIgnoreCase("deadline")) {
                String[] splitDetails = cmdDetails.toLowerCase().split("/by ", 2);
                Deadline newDL = new Deadline(splitDetails[0], splitDetails[1]);
                addTask(newDL);
            } else if (userCmd.equalsIgnoreCase("event")){
                String[] splitDetails = cmdDetails.split("/from ", 2);
                String[] secondSplit = splitDetails[1].split("/to ", 2);
                Event newEvent = new Event(splitDetails[0], secondSplit[0], secondSplit[1]);
                addTask(newEvent);
            }
        }
        System.out.println(goodbyeMessage);
    }
}
