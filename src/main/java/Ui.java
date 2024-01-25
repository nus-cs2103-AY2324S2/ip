public class Ui {

    private static final String LONG_LINE = "____________________________________________________________";

    public void greet() {
        // Print logo
        String logo = " __   ___  _____   __       __       __    __      ___\n"
                + "|  | /  / |_   _| |  |     |  |     |  |  |  |    / _ \\\n"
                + "|  |/  /    | |   |  |     |  |     |  |  |  |   / /_\\ \\\n"
                + "|  |\\  \\   _| |_  |  |___  |  |___  |  |__|  |  / _____ \\\n"
                + "|__| \\__\\ |_____| |______| |______|  \\______/  /_/     \\_\\\n";
        System.out.println(logo);

        // Greet
        System.out.println(Ui.LONG_LINE);
        System.out.println("Killua online. What's my next quest?");
        System.out.println(Ui.LONG_LINE);
    }

    public void exit() {
        // Exit
        System.out.println(Ui.LONG_LINE);
        System.out.println("Alright, I'm always one call away.");
        System.out.println(Ui.LONG_LINE);
    }

    public void add(Task task, Storage storage) {
        System.out.println(Ui.LONG_LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.getDescriptionStatus());
        System.out.println("Now you have " + storage.getItems().size() + " tasks in the list.");
        System.out.println(Ui.LONG_LINE);
    }

    public void list(Storage storage) {
        System.out.println(Ui.LONG_LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < storage.getItems().size(); i++) {
            Task nextTask = storage.getItems().get(i);
            System.out.println((i + 1) + ". " + nextTask.getDescriptionStatus());
        }
        System.out.println(Ui.LONG_LINE);
    }

    public void mark(Task task) {
        System.out.println(Ui.LONG_LINE);
        System.out.println(task.getMarkStatus());
        System.out.println(task.getDescriptionStatus());
        System.out.println(Ui.LONG_LINE);
    }

    public void print(String message) {
        System.out.println(Ui.LONG_LINE);
        System.out.println(message);
        System.out.println(Ui.LONG_LINE);
    }

    public void delete(Task task, Storage storage) {
        System.out.println(Ui.LONG_LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.getDescriptionStatus());
        System.out.println("Now you have " + (storage.getItems().size() - 1) + " tasks in the list.");
        System.out.println(Ui.LONG_LINE);
    }
}
