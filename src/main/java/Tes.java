public class Tes {
    private Ui ui;

    public Tes(){
        this.ui = new Ui();
    }

    /**
     * Initialize the chatbot.
     */
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

            if (command.contains("unmark")) {
                String[] split = command.split(" ");
                int index = Integer.parseInt(split[1]);
                this.ui.unmark(index - 1);
                continue;
            }

            if (command.contains("mark")) {
                String[] split = command.split(" ");
                int index = Integer.parseInt(split[1]);
                this.ui.mark(index - 1);
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
