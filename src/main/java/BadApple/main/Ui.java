package BadApple.main;

public class Ui {
    public static void showWelcome() {
        String logo = " __      __.__    .__  __             _________                          \n" +
                "/  \\    /  \\  |__ |__|/  |_  ____    /   _____/__________    ____  ____  \n" +
                "\\   \\/\\/   /  |  \\|  \\   __\\/ __ \\   \\_____  \\\\____ \\__  \\ _/ ___\\/ __ \\ \n" +
                " \\        /|   Y  \\  ||  | \\  ___/   /        \\  |_> > __ \\\\  \\__\\  ___/ \n" +
                "  \\__/\\  / |___|  /__||__|  \\___  > /_______  /   __(____  /\\___  >___  >\n" +
                "       \\/       \\/              \\/          \\/|__|       \\/     \\/    \\/ ";


        System.out.println("Welcome to");
        System.out.println(logo);
        System.out.println("You booted up your laptop and wonder what to do...");
    }

    /**
     * Creates Files needed for BadApple to function.
     * (i.e. data/whiteSpace.txt)
     */
    public static void askToCreateFile() {
        System.out.println("You've been living here for as long as... wait, no headspace detected?");
        System.out.println("Would you like to enter White Space? \n" +
                "Only 'yes' will create the required files" );
    }

    public static void sayGoodbye() {
        System.out.println("--------------------------------");
        System.out.println("Everything is going to be okay.");
    }
}
