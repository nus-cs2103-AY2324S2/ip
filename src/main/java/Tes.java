public class Tes {
    private static final String line = "    _______________________________________________________________";
    private Ui ui;
    private Storage storage;

    public Tes(){
        this.ui = new Ui();
        this.storage = new Storage();
    }

    public void run() {
        this.ui.greet();

        while (true) {
            String command = this.ui.nextCommand();

            if (command.equals("bye")) {
                this.ui.close();
                break;
            }

            if (command.equals("list")) {
                System.out.println(line);
                for (int i = 1; i <= this.storage.getSize(); i++) {
                    System.out.println("    " + i + ". "  + this.storage.getTask(i));
                }
                System.out.println(line);
                continue;
            }

            this.ui.respond(command);
            this.storage.addTask(command);
        }
    }
    public static void main(String[] args) {
        Tes chatbot = new Tes();
        chatbot.run();
    }
}
