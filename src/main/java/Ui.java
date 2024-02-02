public class Ui {
    private static final String LINE = "____________________________________________________________";
    static final String logo = "┓┏┓  •  ┓  \n"
            + "┃┫ ┏┓┓┏┓┣┓╋\n"
            + "┛┗┛┛┗┗┗┫┛┗┗\n"
            + "       ┛\n";
    static void speak(String message) {
        System.out.println(LINE);
        System.out.println(message.replaceAll("(?m)^", "    "));
        System.out.println(LINE);
    }
}
