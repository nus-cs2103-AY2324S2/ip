    public class Duke {
    public static void main(String[] args) {

        Duke.greet();

        Duke.exit();

    }

    public static void greet() {
        String logo = "▀█▀ ▄▀█ █▀ █▄▀ █▄█ ▄▀█ █▀█ █▀█ █▀▀ █▀█\n"
                + "░█░ █▀█ ▄█ █░█ ░█░ █▀█ █▀▀ █▀▀ ██▄ █▀▄\n";

        System.out.println("Hello from\n" + logo);
    }

    public static void exit() {
        String bye = "█▀▀ █▀█ █▀█ █▀▄ █▄▄ █▄█ █▀▀ █\n"
                + "█▄█ █▄█ █▄█ █▄▀ █▄█ ░█░ ██▄ ▄\n";

        System.out.println("Exiting TaskYapper...\n" + bye);
    }
}
