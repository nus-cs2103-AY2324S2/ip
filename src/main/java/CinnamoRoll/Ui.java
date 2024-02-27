package CinnamoRoll;

/**
 * Deals with user input where it greets, helps exit, and pass the user
 * command to the tasklist and parser class
 */
class Ui {

    private static final String logo = "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⣀⡀⠀⠀⢀⣀⣀⣀⣀⣀⣄⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
            + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⡶⠟⠛⠉⠉⣩⠽⠗⠛⠉⠉⠉⠉⠉⠉⠉⠉⠙⠛⠻⠶⢦⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
            + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣠⡾⠛⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠓⢦⣀⠀⠀⠀⠀⠀⠀\n"
            + "⠀⠀⠀⠀⠀⠀⠀⠀⣀⣴⡮⠗⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠳⣤⢤⣤⣀⠀\n"
            + "⣀⣀⣀⣀⣤⣴⣶⠿⠋⠉⠀⠀⠀⠀⠀⠀⢀⡠⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠢⠈⠙⢷\n"
            + "⠉⠉⠉⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⠟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
            + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⠋⠀⠀⢀⣴⣿⣶⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠀⠀⠀\n"
            + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀⠀⠀⣾⣿⣿⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢣⠀⠀\n"
            + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡗⠦⡤⠤⣝⠿⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⠀⠀⠀⣰⣿⣿⣆⠀⠀⠀⠀⠈⣧⠀\n"
            + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⠞⠹⣷⣀⣀⣠⠞⠀⠀⠀⠀⠀⠀⠀⢦⣠⣤⣄⠀⢀⣠⠿⣿⣭⣷⣀⠀⣿⣿⣿⡿⠀⠀⠀⠀⠀⢻⠀\n"
            + "⠀⠀⠀⠀⠀⠀⣀⣠⣤⠞⠋⠁⠀⠀⠙⢷⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠁⣼⠎⠉⠁⠈⠉⠉⠛⠿⣟⠁⠀⠀⠀⠀⣠⣼⡶\n"
            + "⣤⣤⣴⠶⠾⠛⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠉⠛⠷⢦⣄⣀⡀⠀⠀⠀⠀⠀⠀⠀⢠⣟⣽⣶⠀⠀⠀⠀⠀⠀⠀⠈⠳⡶⢶⣴⠟⠉⠀⠀\n"
            + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⡶⠞⠛⠋⠛⠒⠦⡍⠉⣹⠛⠑⠚⠛⠉⠙⠛⠻⢿⣿⣦⣤⣄⣆⠀⠀⠀⠀⠀⣿⣾⣿⠆⠀⠀⠀\n"
            + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⡿⠋⠀⠀⠀⠀⠀⠀⠀⣸⡿⠃⠀⠀⠀⠀⠀⠀⠀⣀⣼⡏⢛⣻⣿⡿⢷⣾⣷⠀⠀⣯⠿⠋⠀⠀⠀⠀\n"
            + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⡿⠀⠀⠀⣷⠀⠀⠀⠀⣼⠏⠀⠀⠀⠀⠀⢒⣶⣶⣾⡟⣙⣷⡼⠿⣿⠀⠀⠈⠻⣤⣾⠻⡀⠀⠀⠀⠀⠀\n"
            + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⡇⠀⠀⠀⣿⣤⣶⣤⣼⡏⠀⠀⠀⠀⠀⠀⠺⣷⡏⠘⣿⣿⡿⣷⣤⣿⣧⣀⣀⣴⣏⣀⣀⠙⠦⣄⣀⡀⠀\n"
            + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣷⡀⠀⠀⢸⡏⠁⠃⢹⣷⡄⠀⠀⠀⠀⠀⣰⠿⠀⣠⣿⡿⠃⠹⢿⡞⢻⡏⢩⠟⠋⠉⢹⣧⠀⠀⠉⠉⠙\n"
            + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠷⣤⣀⢸⣧⠀⠀⠀⠉⠁⠀⠀⠀⠀⢠⡏⠀⣠⣟⡛⠇⠀⠀⣾⠷⣾⠟⠀⠀⠀⠀⢀⣿⠀⠀⠀⠀⠀\n"
            + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⢿⡏⠀⠀⠀⠀⢀⡖⠀⠀⠸⣧⠞⠉⠛⠻⡆⠀⢠⠏⠀⠀⠀⠀⠀⠀⠀⣸⡇⠀⠀⠀⠀⠀\n"
            + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠒⠒⠒⠚⠻⢦⣄⣀⣴⠾⠿⢶⣶⠶⠶⠶⠶⠶⠤⠿⣶⣿⣶⠶⠶⠿⠷⠶⠶⠾⠯⠤⠀⠀⠀⠀⠀";

    /**
     * Greet the user by printing out the logo and saying hi!
     */
    void greetUser() {
        System.out.println(logo);
        System.out.println("Hello! I'm CinnamoRoll!" + "\n" + "What can I do for you? \n");
    }

    /**
     * Saying bye to the user who attempts to exit the chat!
     */
    void exitChat() {
        System.out.println("See you again! Hope you had great time with me >.<");
    }

    /**
     * Process and respond to the user's request in terms of input string
     */
    boolean respondUser(TaskList tasks, String str) throws Exception {
        if (str.equals("bye")) {
            return true;
        } else {
            tasks.respondUser(str);
            return false;
        }
    }
}
