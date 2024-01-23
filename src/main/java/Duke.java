public class Duke {
    private static String logo = "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣠⣤⣴⠶⠶⣦⣤⣤⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣴⡾⠟⠋⠉⠀⠀⠀⠀⠀⠀⠈⠉⠛⠿⣶⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣾⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⢿⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠻⣧⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⠋⠀⠀⠀⠀⠀⠀⠀⢀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⣷⡀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⢸⡏⠀⠀⠀⠀⠀⣠⣾⠟⠛⠛⠛⠻⢶⣄⠀⠀⠀⠀⠀⠀⣴⡾⠛⠛⢿⣧⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⣾⠁⠀⠀⠀⢀⣾⠏⠀⠀⠀⠀⠀⠀⠀⠙⣷⡀⠀⠀⠀⢸⡟⠀⠀⠀⠈⣿⡆⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⠀⠀⣼⠏⠀⠀⠀⠀⠀⣠⣤⡀⠀⠘⣷⣤⣶⢶⣿⣇⠀⣖⣶⡆⠘⣿⡀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⠀⢠⣿⠀⠀⠀⠀⠀⠸⣧⣿⣿⠀⣼⠏⠁⠀⠀⠀⠹⣧⠙⠛⠁⠀⣿⣧⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⠀⠈⣿⠀⠀⠀⠀⠀⠀⠈⠉⠀⠀⢻⣿⣶⠶⣶⣶⣿⠏⠀⠀⠀⣰⣿⡏⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⢰⡟⠀⠀⠀⠀⠹⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠛⠋⠉⠀⠀⠀⣠⣾⠋⢹⡆⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⢀⣿⠃⠀⠀⠀⠀⠀⠙⢷⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⠟⠻⢷⣄⢻⣦⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⢠⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠈⠛⣻⣶⠆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⣷⡙⢷⣄⠀\n" +
            "⠀⠀⠀⠀⢠⡿⠁⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⣴⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⠃⠈⢻⣆\n" +
            "⠀⠀⠀⢠⡿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠻⣦⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⠏⠀⠀⠀⣿\n" +
            "⠀⠀⠀⣿⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠻⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡟⠀⠀⠀⢠⣿\n" +
            "⠀⠀⢰⡟⠀⠀⠀⠀⠀⠀⠠⣤⣄⠀⠀⠀⠀⠀⠀⠀⠀⠹⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡇⠀⠀⠀⣾⠃\n" +
            "⠀⠀⢸⣷⠀⠀⠀⠀⠀⠀⠀⠈⠙⢿⣦⣀⠀⠀⠀⠀⠀⠀⢹⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⡇⠀⣠⣾⠏⠀\n" +
            "⢀⣴⡿⣿⡀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡿⠻⢷⣄⡀⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⠿⠿⣿⠃⠀⠀\n" +
            "⣿⡏⠀⢹⣧⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀⠀⠙⠿⣦⣤⣤⡿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⢿⣦⠀⠀\n" +
            "⠙⢿⣦⣀⠻⣧⡀⠀⠀⠀⠀⠀⢠⣼⣷⣄⠀⣀⣀⡀⠈⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣼⠏⢰⡿⢶⡆\n" +
            "⠀⠀⠉⠙⠛⠿⠿⣦⣀⢠⣶⣶⡿⠁⠀⠹⣿⠋⠉⢿⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⡿⠃⠀⠀⣠⡾⠃\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠈⠻⢿⣿⡦⠀⠀⠀⠀⠀⠀⠀⢸⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣴⣿⣋⣀⣤⣴⠟⠋⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠻⣦⣀⠀⠀⠀⠀⣰⡿⠁⠀⠀⠀⠀⠀⢀⣀⣠⣴⠿⠛⠉⠙⠛⠉⠉⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠛⠻⠶⠶⠟⠛⠛⠛⠛⠛⠛⠛⠛⠋⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠘⠦⣠⠀⠀⠠⠀⡀⠀⠀⣘⢦⠀⢤⡄⠀⠈⠡⡄⡀⠀⡀⣄⠈⠀⠀⠐⠀⠀⠀⠈⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠐⠶⠠⠄⠠⠀⠀⠤⠀⠤⠄⠀⡰⡉⠈⠱⠘⠀⡄⣀⠀⠀⠀⠠⠀⠭⠰⠶⠤⠆⠰⡶⠘⢤⠄⠀⢀⠀";
    private static void greeting() {
        System.out.println("Hello! I'm Pengu\n" + "What can I do for you? \n" + Duke.logo);
        System.out.println("―――――――――――――――――――――――――――――――――――");
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("―――――――――――――――――――――――――――――――――――");
    }
    public static void main(String[] args) {
        greeting();
        exit();
    }
}
