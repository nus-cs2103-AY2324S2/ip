

public class Ui {


    //Print opening greeting
    public void printOpeningGreeting() {
        String logo = " ____            __\n"
                + "|  _ \\ _   ______| |      ___  ___  ___\n"
                + "| | | | | | |  __| |__  /  _ \\/ __|/ __|\n"
                + "| |_| | |_| | |__| ___ |   __/\\__ \\\\__ \\\n"
                + "|____/ \\__,_|____|_| |_|\\ ___||___/|___/\n";
        printHorizontalLine();
        System.out.println(logo);
        printHorizontalLine();
        System.out.println("Hello! I'm Duchess.");
        System.out.println("What can I do for you today?");
        printHorizontalLine();
    }


    //Prints closing greeting
    public void printClosingGreeting() {
        printHorizontalLine();
        System.out.println("Farewell. Hope to see you again soon, my dear!");
        printHorizontalLine();
    }

    //Prints a Horizontal Line of 50 dashes
    public void printHorizontalLine() {
        int lineLength = 50; // Specify the length of the line

        // Print the horizontal line
        for (int i = 0; i < lineLength; i++) {
            System.out.print("_");
        }

        System.out.println();
    }
}
