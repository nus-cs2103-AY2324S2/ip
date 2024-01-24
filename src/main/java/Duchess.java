public class Duchess {
    public static void main(String[] args) {
        String logo = " ____            __     \n"
                    + "|  _ \\ _   ______| |      ___  ___  ___ \n"
                    + "| | | | | | |  __| |__  /  _ \\/ __|/ __|  \n"
                    + "| |_| | |_| | |__| ___ |   __/\\__ \\\\__ \\  \n"
                    + "|____/ \\__,_|____|_| |_|\\ ___||___/|___/\n";
        horizontalLine();
        System.out.println(logo);
        horizontalLine();
        System.out.println("Hello! I'm Duchess.");
        System.out.println("What can I do for you today?");
        horizontalLine();
        System.out.println("Goodbye. Hope to see you again soon!");
        horizontalLine();
    }

    //Prints a Horizontal Line of 50 dashes
    public static void horizontalLine() {
        int lineLength = 50; // Specify the length of the line

        // Print the horizontal line
        for (int i = 0; i < lineLength; i++) {
            System.out.print("_");
        }

        System.out.println();
    }
}
