import java.util.Scanner;
public class Duke {

    public static class Task {
        protected String description;
        protected boolean isDone;
        protected int index;

        public Task(String description, int index) {
            this.description = description;
            this.index = index;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public String getTask() {
            return " [" + getStatusIcon() + "] " + this.description;
        }

        public String commandMark() {
            String response = this.isDone == true
                    ? "Task " + (this.index + 1) + " is already done! Yay!\n"
                    : "Nice! I've marked this task as done:\n";
            this.isDone = true;
            return response + getTask();
        }

        public String commandUnmark() {
            String response = this.isDone == false
                    ? "Task " + (this.index + 1) + " is not done yet!\n"
                    : "OK, I've marked this task as undone:\n";
            this.isDone = false;
            return response + getTask();
        }
    }

    private static String logo = " _______  __                       __ \n"
            + "|     __||__|.-----..-----..---.-.|  |\n"
            + "|__     ||  ||  _  ||     ||  _  ||  |\n"
            + "|_______||__||___  ||__|__||___._||__|\n"
            + "             |_____|                  \n";
    private static String div = "\n" + "~~**~~";
    private static Scanner scanner = new Scanner(System.in);
    private static Task[] taskList = new Task[100];
    private static int index = 0;



    /**
     * Checks with the user if the input is a typo of a command.
     *
     * @param input Input collected from the user.
     * @param command Command to check.
     * @return True if input is a typo of the command.
     */
    public static boolean checkCommandTypo(String input, String command) {
        if(!input.equals(command)) {
            signalSays("Did you mean '"+ command + "'? (y/n)");
            String isCommandCheck = scanner.nextLine();
            if(isCommandCheck.equals("y")) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * Check if the input is a permutation of the original.
     *
     * @param input Input collected from the user.
     * @param original String to compare the input to.
     * @return True if input is a permutation of original.
     */
    public static boolean isPermutationMatch(String input, String original) {
        // Check if user input is a permutation match
        char[] userInputArray = input.toCharArray();
        char[] originalArray = original.toCharArray();

        // Sort the arrays to compare
        java.util.Arrays.sort(userInputArray);
        java.util.Arrays.sort(originalArray);

        return java.util.Arrays.equals(userInputArray, originalArray);
    }

    /**
     * Adds input to the list.
     *
     * @param input Input collected from the user.
     */
    public static void commandAdded(String input) {
//        list[index] = input;
//        checkDone[index] = false;
        taskList[index] = new Task(input, index);
        index += 1;
        signalSays("Added: " + input);
    }

    /**
     * Prints the list of inputs collected from the user.
     *
     */
    public static void commandList() {
        System.out.println(div);
        System.out.println("Here is your tasklist!");
        for (int i = 0; i < index; i++) {
            System.out.println((i + 1) + ". " + taskList[i].getTask());
        }
        System.out.println(div);
    }


    /**
     * Prints Signal's response enclosed in the dividers.
     *
     * @param response The message that is printed.
     */
    public static void signalSays(String response) {
        System.out.println(div);
        System.out.println(response);
        System.out.println(div);
    }


    public static void main(String[] args) {
        System.out.println("Hello! My name is\n" + logo);
        System.out.println("How can I help?");
        System.out.println(div);


        while(true) {
            String userInput = scanner.nextLine();
            String[] inputArray = userInput.split(" ");

            if (userInput.equals("bye")) {
                // Exit program
                System.out.println(div);
                break;
            } else if (userInput.equals("")) { // input is blank
                signalSays("Brevity is the soul of wit, but you have to tell me something still!");
            } else if (inputArray.length == 2 && inputArray[0].equals("mark")) {
                // Mark item at index as done
                int itemIndex = Integer.parseInt(inputArray[1]) - 1;
                signalSays(taskList[itemIndex].commandMark());
            } else if (inputArray.length == 2 && inputArray[0].equals("unmark")) {
                // Mark item at index as done
                int itemIndex = Integer.parseInt(inputArray[1]) - 1;
                signalSays(taskList[itemIndex].commandUnmark());
            } else if (inputArray.length == 2 && (isPermutationMatch(inputArray[0], "mark") || isPermutationMatch(inputArray[0], "unmark"))) {
                if (checkCommandTypo(inputArray[0], "mark")) { // command mark typo
                    int itemIndex = Integer.parseInt(inputArray[1]) - 1;
                    signalSays(taskList[itemIndex].commandMark());
                } else if (checkCommandTypo(inputArray[0], "unmark")) { // command unmark typo
                    int itemIndex = Integer.parseInt(inputArray[1]) - 1;
                    signalSays(taskList[itemIndex].commandUnmark());
                } else {
                    signalSays("Do you want to add " + userInput + "? (y/n)");
                    String addCommandCheck = scanner.nextLine();
                    if (addCommandCheck.equals("n")) {
                        signalSays("What else can I help you with?");
                    } else if (addCommandCheck.equals("y")) {
                        commandAdded(userInput);
                    }
                }
            } else if (userInput.equals("list")) {
                commandList();
            } else if (isPermutationMatch(userInput, "list")) {
                if (checkCommandTypo(userInput, "list")) {
                    commandList();
                } else {
                    signalSays("Do you want to add " + userInput + "? (y/n)");
                    String addCommandCheck = scanner.nextLine();
                    if(addCommandCheck.equals("n")) {
                        signalSays("What else can I help you with?");
                    } else if(addCommandCheck.equals("y")) {
                        commandAdded(userInput);
                    }
                }
            }
            else {
                commandAdded(userInput);
            }
        }

        System.out.println("Bye! Hope you come back soon :D");
        System.out.println(div);

    }
}
