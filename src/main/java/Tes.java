public class Tes {
    private Ui ui;

    public Tes(){
        this.ui = new Ui();
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
                this.ui.listTask();
                continue;
            }

            this.ui.addTask(command);
        }
    }
    public static void main(String[] args) {
        Tes chatbot = new Tes();
        chatbot.run();
    }
}
