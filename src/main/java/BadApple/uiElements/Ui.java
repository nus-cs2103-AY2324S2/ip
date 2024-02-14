package BadApple.uiElements;


public class Ui {
    public static String showWelcome() {
        String logo = " __      __.__    .__  __             _________                          \n" +
                "/  \\    /  \\  |__ |__|/  |_  ____    /   _____/__________    ____  ____  \n" +
                "\\   \\/\\/   /  |  \\|  \\   __\\/ __ \\   \\_____  \\\\____ \\__  \\ _/ ___\\/ __ \\ \n" +
                " \\        /|   Y  \\  ||  | \\  ___/   /        \\  |_> > __ \\\\  \\__\\  ___/ \n" +
                "  \\__/\\  / |___|  /__||__|  \\___  > /_______  /   __(____  /\\___  >___  >\n" +
                "       \\/       \\/              \\/          \\/|__|       \\/     \\/    \\/ ";


        System.out.println("Welcome to" + "\n" + logo + "\n" + "You booted up your laptop and wonder what to do...");

        return "Welcome to" + "\n" + logo + "\n" + "You booted up your laptop and wonder what to do...";
    }
}
