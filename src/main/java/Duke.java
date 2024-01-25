import java.util.Scanner;
public class Duke {
    private static String logo = " _______  __                       __ \n"
            + "|     __||__|.-----..-----..---.-.|  |\n"
            + "|__     ||  ||  _  ||     ||  _  ||  |\n"
            + "|_______||__||___  ||__|__||___._||__|\n"
            + "             |_____|                  \n";
    private static String div = "\n" + "~~**~~";
    private static Scanner scanner = new Scanner(System.in);
    private static String[] list = new String[100];
    private static int index = 0;

    /**
     * Prints the list of inputs collected from the user.
     *
     */
    public static void commandList() {
        System.out.println(div);
        for (int i = 0; i < index; i++) {
            int listNum = i + 1;
            System.out.println(listNum + ". " + list[i]);

        }
        System.out.println(div);
    }

    /**
     * Checks with the user if the command is a typo of 'list'.
     *
     * @param input Input collected from the user.
     * @return True if input is a typo of 'list'.
     */
    public static boolean checkListTypo(String input) {
        if(!input.equals("list")) {
            signalSays("Did you mean 'list'? (y/n)");
            String isListCheck = scanner.nextLine();
            if(isListCheck.equals("y")) {
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
        list[index] = input;
        index += 1;
        signalSays("Added: " + input);
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
            if (userInput.equals("bye")) {
                System.out.println(div);
                break;
            }
            if (userInput.equals("list")) {
                commandList();
            }
            if(isPermutationMatch(userInput, "list")) {
                if (checkListTypo(userInput)) {
                    commandList();
                } else {
                    signalSays("Do you want to add " + userInput + "? (y/n)");
                    String addCommandCheck = scanner.nextLine();
                    if(addCommandCheck.equals("n")) {
                        signalSays("What else can I help you with?");
                    } else {
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
