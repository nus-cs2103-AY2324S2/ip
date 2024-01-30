public class UkeCat {

    private Ui ui;

    public UkeCat() {
        ui = new Ui();
    }

    public void run() {

        FileManager.loadTasks();

        try {
            // Welcome msg
            System.out.println(Storage.LINE + Storage.WELCOME + Storage.LINE);
            // Read user input
            while (true) {
                Parser.parse();
                if (Storage.words[0].equals("bye")) {
                    System.out.println(Storage.BYE);
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
