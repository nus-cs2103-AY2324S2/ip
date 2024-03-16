package BadApple.uiElements;


public class Ui {
    public static String showWelcome() {
        String logo = " __      __.__    .__  __             _________                          \n" +
                "/  \\    /  \\  |__ |__|/  |_  ____    /   _____/__________    ____  ____  \n" +
                "\\   \\/\\/   /  |  \\|  \\   __\\/ __ \\   \\_____  \\\\____ \\__  \\ _/ ___\\/ __ \\ \n" +
                " \\        /|   Y  \\  ||  | \\  ___/   /        \\  |_> > __ \\\\  \\__\\  ___/ \n" +
                "  \\__/\\  / |___|  /__||__|  \\___  > /_______  /   __(____  /\\___  >___  >\n" +
                "       \\/       \\/              \\/          \\/|__|       \\/     \\/    \\/ ";

        return "Welcome to" + "\n" + logo + "\n" + "You booted up your laptop and wonder what to do...";
    }

    public static String noWhiteSpaceFile() {
        String message = "You've been living here for as long as you can... wait, there's no headspace? \n";
        String askPerms = "Would you like to enter White Space? \n" +
                "Only 'yes' will create the required files";
        return message + askPerms;
    }
}
