public class Duke {
    public static final String botName = "Raphael";
    public static void main(String[] args) {
        String logo = "\n"
                + "  _____                _                   _ \n"
                + " |  __ \\              | |                 | |\n"
                + " | |__) | __ _  _ __  | |__    __ _   ___ | |\n"
                + " |  _  / / _` || '_ \\ | '_ \\  / _` | / _ \\| |\n"
                + " | | \\ \\| (_| || |_) || | | || (_| ||  __/| |\n"
                + " |_|  \\_\\\\__,_|| .__/ |_| |_| \\__,_| \\___||_|\n"
                + "               | |                           \n"
                + "               |_|                           \n";
        System.out.println(logo);
        Ui.start();
        Ui.end();
    }
}
