public class UkeCat {
    private final Ui ui;

    public UkeCat() {
        ui = new Ui();
    }

    public void run() {
        ui.welcome();
        FileManager.loadTasks();
        try {
            // Read user input
            while (true) {
                Parser.parse();
                if (Storage.words[0].equals("bye")) {
                    ui.bye();
                    Parser.closeScanner();
                    System.exit(0);
                }
                ui.respond(Storage.words);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        new UkeCat().run();
    }
}
