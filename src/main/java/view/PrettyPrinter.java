package view;

/**
 * The PrettyPrinter class is responsible for printing messages in a pretty format.
 */
public class PrettyPrinter {
    private void printIndentedln(String message) {
        System.out.println("    " + message);
    }

    private void printHorizontalln() {
        printIndentedln("____________________________________________________________\n");
    }

    /**
     * Prints the message in a pretty format.
     * @param message The message to be printed.
     */
    public void print(String message) {
        String[] lines = message.split("\n");

        if (lines.length == 0) {
            return;
        }

        this.printHorizontalln();

        for (String line : lines) {
            this.printIndentedln(line);
        }

        this.printHorizontalln();
    }
}
