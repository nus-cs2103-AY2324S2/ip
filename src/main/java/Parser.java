/**
 * Deals with making sense (parsing) of the user command.
 */
public class Parser {
    /**
     * Parses the user input and returns the corresponding Command
     *
     * @param userInput the user input command string
     * @return the Command corresponding to the input string.
     */
    static Command parse(String userInput) {
        // if command called, will be index 0, rest of string (args) in index 1
        String[] userInputTokens = userInput.split(" ", 2);
        String commandWord = userInputTokens[0];
        String args = userInputTokens.length == 2 ? userInputTokens[1] : null;

        switch (commandWord) {
            case "list":
                return new ListCommand();

            case "unmark":
                return prepareUnmark(args);

            case "mark":
                return prepareMark(args);

            case "delete":
                return prepareDelete(args);

//            case "todo":
//                return new CreateToDoCommand();
//
//            case "event":
//                return new CreateEventCommand(userInput);
//
//            case "deadline":
//                return new CreateDeadlineCommand(userInput);

            case "bye":
                return new ExitCommand();

            default:
                return new InvalidCommand("Uhh, English please? Haha, just kidding...\n" +
                        "but for reals I didn't really understand that :(");
        }
    }

    private static int getIndexFromArgs(String args) throws NumberFormatException {
        return Integer.parseInt(args);
    }

    private static Command prepareUnmark(String args) {
        try {
            int listIndex = getIndexFromArgs(args);
            return new UnmarkCommand(listIndex);
        } catch (NumberFormatException e) {
            return new InvalidCommand("I'm pretty sure I gave you the indexes in base 10...\n" +
                    "there shouldn't be any characters!! :(");
        }
    }

    private static Command prepareMark(String args) {
        try {
            int listIndex = getIndexFromArgs(args);
            return new MarkCommand(listIndex);
        } catch (NumberFormatException e) {
            return new InvalidCommand("I'm pretty sure I gave you the indexes in base 10...\n" +
                    "there shouldn't be any characters!! :(");
        }
    }

    private static Command prepareDelete(String args) {
        try {
            int listIndex = getIndexFromArgs(args);
            return new DeleteCommand(listIndex);
        } catch (NumberFormatException e) {
            return new InvalidCommand("I'm pretty sure I gave you the indexes in base 10...\n" +
                    "there shouldn't be any characters!! :(");
        }
    }
//    static boolean executeList(){
//        taskList.forEach(System.out::println);
//        if (taskList.size() == 0) {
//            System.out.println("There are no tasks for you today. Enjoy :)");
//        }
//    }
//
//    static boolean executeCreateTask(String s) {
//        Task task;
//        try{
//            task = Task.create(s);
//        } catch (CreateTaskException e) {
//            System.out.println(e.getMessage());
//            return;
//        }
//        taskList.add(task);
//        System.out.println("Got it. I've added this task:\n\t" + task +
//                "\nNow you have " + taskList.size() + " tasks in the list.");
//        saveToFile();
//    }
//
//    /**
//     * Prints a goodbye message and Exits the program (return code 0)
//     */
//    void goodByeAndExit() {
//        System.out.println("Bye. Hope to see you again soon!");
//        printSeparator();
//        System.exit(0); // exit with code 0, terminates program
//    }
//
//    void executeModifyTask(String s) {
//        String[] sTokens = s.split(" ", 3); // expect exactly 2 substrings for a valid command input
//        if (sTokens.length == 1) {
//            System.out.println("Uh oh ! You have to give me the index of the task you want to modify :)");
//            return;
//        } else if (sTokens.length == 3) {
//            System.out.println("Umm... is it that hard to give me JUST the index? :(");
//            return;
//        }
//
//        try {
//            int listIndex = Integer.parseInt(sTokens[1]);
//            if (listIndex < 1 || listIndex > taskList.size()) {
//                System.out.println("Sorry, I can't find that task. Please enter a valid index\n" +
//                        "You can see the tasks list by inputting \"list\"");
//                return;
//            }
//            Task task = taskList.get(listIndex - 1);
//            switch (sTokens[0]) {
//                case "mark":
//                    task.markDone();
//                    System.out.println("Nice! I've marked this task as done:\n\t" + task);
//                    break;
//                case "unmark":
//                    task.unmarkDone();
//                    System.out.println("Nice! I've marked this task as not done yet:\n\t" + task);
//                    break;
//                case "delete":
//                    taskList.remove(listIndex - 1);
//                    System.out.println("Okay. I've deleted this task:\n\t" + task +
//                            "\nNow you have " + taskList.size() + " tasks in the list.");
//                    break;
//                default:
//                    throw new IllegalArgumentException("Shouldn't be in this function unless these 3 commands");
//            }
//        } catch (NumberFormatException e) {
//            System.out.println("I'm pretty sure I gave you the indexes in base 10...\n" +
//                    "there shouldn't be any characters!! :(");
//        }
//        saveToFile();
//    }
//    public static Command parse(String userInput) {
//        return new CreateToDoCommand(userInput);
//    }
}