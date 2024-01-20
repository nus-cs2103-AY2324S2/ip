import java.util.Scanner;

/**
 * Main Class for our Chat bot
 */
public class Duke {

    /**
     * Main method
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("    Hello! I'm Hanxiao. \n  What can I do for you?");

        while (true) {
            Command cmd = commandDistributor(sc.nextLine());
            cmd.reply();
        }
    }

    /**
     * From input to determine which type of command to generate
     * @param input scanner's result
     * @return the generated command for execute
     */
    private static Command commandDistributor(String input) {
        String[] inputs = input.split(" ");
        if (inputs[0].equals("bye")) {
            return new Bye();
        } else if (inputs[0].equals("list")) {
            return new List();
        } else if (inputs[0].equals("mark")) {
            return new Mark(Integer.parseInt(inputs[1])-1);
        } else if (inputs[0].equals("unmark")) {
            return new Unmark(Integer.parseInt(inputs[1])-1);
        } else {
            return new Add(inputs[0]);
        }
    }
}
