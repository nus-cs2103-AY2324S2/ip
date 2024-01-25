import java.util.Objects;

public class Lindi {
    private final static String[] taskList = new String[100];
    private static int taskListCount = 0;

    private static void parseInputAndExecute(String userInput) {
        class LocalFuncs {
            void executeList(){
                for (int i = 0; i < taskListCount; i++) {
                    System.out.println(i+1 + ". " + taskList[i]);
                }
            }

            void executeNone(String task){
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
        }

        switch (userInput) {
            case "list":
                new LocalFuncs().executeList();
                break;
            case "bye":
                new LocalFuncs().goodByeAndExit();
                break;
            default:
                new LocalFuncs().executeNone(userInput);
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
    public static void main(String[] args) {
        greeting();
        chatLoop();
    }
}
