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

            if (command.contains("deadline")) {
                String com = command.substring(9);
                String[] split = com.split(" /by ");
                this.ui.addDeadline(split[0], split[1]);
                continue;
            }

            if (command.contains("event")) {
                String com = command.substring(6);
                String[] split = com.split(" /from ");
                String realCommand = split[0];
                String[] time = split[1].split(" /to ");
                String from = time[0];
                String to = time[1];
                this.ui.addEvent(realCommand, from, to);
                continue;
            }

            String com = command.substring(5);
            this.ui.addToDo(com);
        }
    }
    public static void main(String[] args) {
        Tes chatbot = new Tes();
        chatbot.run();
    }
}
