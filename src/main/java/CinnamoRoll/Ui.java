package CinnamoRoll;

import java.time.format.DateTimeFormatter;
class Ui {
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private static final String logo = "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⣀⡀⠀⠀⢀⣀⣀⣀⣀⣀⣄⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⡶⠟⠛⠉⠉⣩⠽⠗⠛⠉⠉⠉⠉⠉⠉⠉⠉⠙⠛⠻⠶⢦⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣠⡾⠛⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠓⢦⣀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⣀⣴⡮⠗⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠳⣤⢤⣤⣀⠀\n" +
            "⣀⣀⣀⣀⣤⣴⣶⠿⠋⠉⠀⠀⠀⠀⠀⠀⢀⡠⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠢⠈⠙⢷\n" +
            "⠉⠉⠉⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⠟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⠋⠀⠀⢀⣴⣿⣶⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀⠀⠀⣾⣿⣿⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢣⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡗⠦⡤⠤⣝⠿⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⠀⠀⠀⣰⣿⣿⣆⠀⠀⠀⠀⠈⣧⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⠞⠹⣷⣀⣀⣠⠞⠀⠀⠀⠀⠀⠀⠀⢦⣠⣤⣄⠀⢀⣠⠿⣿⣭⣷⣀⠀⣿⣿⣿⡿⠀⠀⠀⠀⠀⢻⠀\n" +
            "⠀⠀⠀⠀⠀⠀⣀⣠⣤⠞⠋⠁⠀⠀⠙⢷⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠁⣼⠎⠉⠁⠈⠉⠉⠛⠿⣟⠁⠀⠀⠀⠀⣠⣼⡶\n" +
            "⣤⣤⣴⠶⠾⠛⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠉⠛⠷⢦⣄⣀⡀⠀⠀⠀⠀⠀⠀⠀⢠⣟⣽⣶⠀⠀⠀⠀⠀⠀⠀⠈⠳⡶⢶⣴⠟⠉⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⡶⠞⠛⠋⠛⠒⠦⡍⠉⣹⠛⠑⠚⠛⠉⠙⠛⠻⢿⣿⣦⣤⣄⣆⠀⠀⠀⠀⠀⣿⣾⣿⠆⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⡿⠋⠀⠀⠀⠀⠀⠀⠀⣸⡿⠃⠀⠀⠀⠀⠀⠀⠀⣀⣼⡏⢛⣻⣿⡿⢷⣾⣷⠀⠀⣯⠿⠋⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⡿⠀⠀⠀⣷⠀⠀⠀⠀⣼⠏⠀⠀⠀⠀⠀⢒⣶⣶⣾⡟⣙⣷⡼⠿⣿⠀⠀⠈⠻⣤⣾⠻⡀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⡇⠀⠀⠀⣿⣤⣶⣤⣼⡏⠀⠀⠀⠀⠀⠀⠺⣷⡏⠘⣿⣿⡿⣷⣤⣿⣧⣀⣀⣴⣏⣀⣀⠙⠦⣄⣀⡀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣷⡀⠀⠀⢸⡏⠁⠃⢹⣷⡄⠀⠀⠀⠀⠀⣰⠿⠀⣠⣿⡿⠃⠹⢿⡞⢻⡏⢩⠟⠋⠉⢹⣧⠀⠀⠉⠉⠙\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠷⣤⣀⢸⣧⠀⠀⠀⠉⠁⠀⠀⠀⠀⢠⡏⠀⣠⣟⡛⠇⠀⠀⣾⠷⣾⠟⠀⠀⠀⠀⢀⣿⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⢿⡏⠀⠀⠀⠀⢀⡖⠀⠀⠸⣧⠞⠉⠛⠻⡆⠀⢠⠏⠀⠀⠀⠀⠀⠀⠀⣸⡇⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠒⠒⠒⠚⠻⢦⣄⣀⣴⠾⠿⢶⣶⠶⠶⠶⠶⠶⠤⠿⣶⣿⣶⠶⠶⠿⠷⠶⠶⠾⠯⠤⠀⠀⠀⠀⠀";

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
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Process and respond to the user's request in terms of input string
     */
    void respondUser(TaskList tasks, String str) throws Exception {
        tasks.respondUser(str);
    }
}