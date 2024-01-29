public class Ui {

    private static final String LINE_SEPARATOR = "____________________________________________________________";

    public void printSeparator() {
        System.out.println(LINE_SEPARATOR);
    }
    public void introduce() {
        String logo = " ____              _    \n"
                + "|  _ \\  ___   ___ | | __      ╱|、\n"
                + "| | | |/ _ \\ / _ \\| |/ /    (˚ˎ 。7  \n"
                + "| |_| | |_| | |_| |   <      |、˜〵 \n"
                + "|____/ \\___/ \\___/|_|\\_\\     じしˍ,)ノ\n";
        printSeparator();
        System.out.println("Hello from Dook! :D meow\n" + logo);
        System.out.println("What can I do for you? uwu");
        printSeparator();
    }
}
