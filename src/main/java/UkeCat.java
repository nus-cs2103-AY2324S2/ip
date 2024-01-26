public class UkeCat {
    public static void main(String[] args) {
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
                    FileManager.updateTasks();
                    System.exit(0);
                }
                Responder.respond(Storage.words);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
