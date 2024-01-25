public class Duke {
    public static void main(String[] args) {
        String logo = 
                        " _____   _____  _    _ \n"
                        + "|  __ \\ / ____|| |  | |\n"
                        + "| |__) | (___  | |__| |\n"
                        + "|  _  / \\___ \\ |  __  |\n"
                        + "| | \\ \\ ____) || |  | |\n"
                        + "|_|  \\_|_____/ |_|  |_|\n";
        System.out.println(layer("Hello! I'm \n" + logo));
        System.out.println(layer("Bye. Hope to see you again soon!"));
    }

    public static String layer(String s) {
        String line = "____________________________________________________________";
        return line + "\n" + s + "\n" + line; 
    }
}
