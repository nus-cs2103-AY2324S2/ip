public class Ui {

    private static final String longLine = "____________________________________________________________";

    public void greet() {
        // Print logo
        String logo = " __   ___  _____   __       __       __    __      ___ \n"
                + "|  | /  / |_   _| |  |     |  |     |  |  |  |    / _ \\ \n"
                + "|  |/  /    | |   |  |     |  |     |  |  |  |   / /_\\ \\ \n"
                + "|  |\\  \\   _| |_  |  |___  |  |___  |  |__|  |  / _____ \\ \n"
                + "|__| \\__\\ |_____| |______| |______|  \\______/  /_/     \\_\\ \n";
        System.out.println(logo);

        // Greet
        System.out.println(Ui.longLine);
        System.out.println("Killua online. What's my next quest?");
        System.out.println(Ui.longLine);
    }

    public void exit() {
        // Exit
        System.out.println(Ui.longLine);
        System.out.println("Alright, I'm always one call away.");
        System.out.println(Ui.longLine);
    }

    public void add(String task) {
        System.out.println(Ui.longLine);
        System.out.println("added: " + task);
        System.out.println(Ui.longLine);
    }

    public void list(Storage storage) {
        System.out.println(Ui.longLine);
        for (int i = 0; i < storage.getItems().size(); i++) {
            System.out.println((i + 1) + ". " + storage.getItems().get(i));
        }
        System.out.println(Ui.longLine);
    }
}
