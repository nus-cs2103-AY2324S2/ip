import javax.xml.stream.events.Characters;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lindi {
    private final static Task[] taskList = new Task[100];
    private static int taskListCount = 0;

    private static void parseInputAndExecute(String userInput) {
        class LocalFuncs {
            void executeList(){
                for (int i = 0; i < taskListCount; i++) {
                    System.out.println(i+1 + ". " + taskList[i]);
                }
            }

            void executeNone(Task task){
                taskList[taskListCount++] = task;
                System.out.println("Added: " + task);
            }

            /**
             * Prints a goodbye message and Exits the program (return code 0)
             */
            void goodByeAndExit() {
                System.out.println("Bye. Hope to see you again soon!");
                printSeparator();
                System.exit(0); // exit with code 0, terminates program
            }

            /**
             * @param listIndex: listIndex as shown by the `list` command, starts from 1
             */
            void executeMark(int listIndex) {
                // Index starts from 1, up to 100
                if (listIndex < 1 || listIndex > taskListCount) {
                    System.out.println("Sorry, I can't find that task. Please enter a valid index\n" +
                            "You can see the tasks list by inputting \"list\"");
                }
                Task task = taskList[listIndex - 1];
                task.markDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("\t" + task.toString());
            }
            /**
             * @param listIndex: listIndex as shown by the `list` command, starts from 1
             */
            void executeUnmark(int listIndex) {
                // Index starts from 1, up to 100
                if (listIndex < 1 || listIndex > taskListCount) {
                    System.out.println("Sorry, I can't find that task. Please enter a valid index\n" +
                            "You can see the tasks list by inputting \"list\"");
                }
                Task task = taskList[listIndex - 1];
                task.unmarkDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("\t" + task.toString());
            }
        }
        String[] userInputTokens = userInput.split(" ", 3);
        System.out.println(userInputTokens.length);
        switch (userInputTokens[0]) {
            case "list":
                new LocalFuncs().executeList();
                break;
            case "unmark":
                // Now check if the second token 1) exists, 2) is a number. Else, treat it as a new task.
                if (userInputTokens.length == 2) { // check if there is only 1 argument after unmark command
                    // check if it is a number
                    if (isNumber(userInputTokens[1])) {
                        System.out.println("is number");
                        int taskIndex = Integer.parseInt(userInputTokens[1]);
                        new LocalFuncs().executeUnmark(taskIndex);
                    }
                }
                else {
                    new LocalFuncs().executeNone(new Task(userInput));
                }
                break;
            case "mark":
                // Now check if the second token 1) exists, 2) is a number. Else, treat it as a new task.
                if (userInputTokens.length == 2) { // check if there is only 1 argument after unmark command
                    // check if it is a number
                    if (isNumber(userInputTokens[1])) {
                        int taskIndex = Integer.parseInt(userInputTokens[1]);
                        new LocalFuncs().executeMark(taskIndex);
                    }
                }
                else {
                    new LocalFuncs().executeNone(new Task(userInput));
                }
                break;
            case "bye":
                new LocalFuncs().goodByeAndExit();
                break;
            default:
                new LocalFuncs().executeNone(new Task(userInput));
                break;
        }
    }


    /**
     * Prints a horizontal line for better readability
     */
    private static void printSeparator() {
        System.out.println("-".repeat(50));
    }

    /**
     * Prints a greeting message.
     */
    private static void greeting() {
        String name = "Lindi";  // Log It N Do It -> LINDI

        printSeparator();
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        printSeparator();
    }



    private static void chatLoop() {
        String userInput;
        while (true) { // This will not be an infinite loop, because goodByeAndExit() terminates the program when called
            userInput = System.console().readLine(); // Note that System.console() does not work with IDE run
            printSeparator();
            parseInputAndExecute(userInput);
            printSeparator();
        }
    }

    private static boolean isNumber(String s) {
        try {
            Integer.valueOf(s);
            System.out.println("true number");
            return true;
        } catch (NumberFormatException e) {
            System.out.println("false number");
            return false;
        }
    }
    public static void main(String[] args) {
        greeting();
        chatLoop();
    }
}
