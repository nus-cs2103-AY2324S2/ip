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
            try {
                String command = this.ui.nextCommand();

                if (command.equals("bye")) {
                    this.ui.close();
                    break;
                } else if (command.equals("list")) {
                    this.ui.listTask();
                    continue;
                } else if (command.contains("unmark")) {
                    String[] split = command.split(" ");
                    int index = Integer.parseInt(split[1]);
                    this.ui.unmark(index - 1);
                    continue;
                } else if (command.contains("mark")) {
                    String[] split = command.split(" ");
                    int index = Integer.parseInt(split[1]);
                    this.ui.mark(index - 1);
                    continue;
                } else if (command.contains("delete")) {
                    String[] split = command.split(" ");
                    int index = Integer.parseInt(split[1]);
                    this.ui.delete(index);
                } else if (command.contains("deadline")) {
                    try {
                        if (command.equals("deadline")) {
                            throw new TesException("Stop wasting my time. Are you muted?!");
                        } else if (!command.contains("/by")) {
                            throw new TesException("I see an idiot with no time management in their little brain.");
                        }
                        String com = command.substring(9);
                        String[] split = com.split(" /by ");
                        this.ui.addDeadline(split[0], split[1]);
                    } catch (TesException e) {
                        Ui.printLine();
                        System.out.println("    " + e.getMessage());
                        Ui.printLine();
                    }

                    continue;
                } else if (command.contains("event")) {
                    try {
                        if (command.equals("event")) {
                            throw new TesException("Stop wasting my time. Are you muted?!");
                        } else if (!command.contains("/from") || !command.contains("/to")) {
                            throw new TesException("I see an idiot with no time management in their little brain.");
                        }
                        String com = command.substring(6);
                        String[] split = com.split(" /from ");
                        String realCommand = split[0];
                        String[] time = split[1].split(" /to ");
                        String from = time[0];
                        String to = time[1];
                        this.ui.addEvent(realCommand, from, to);
                    } catch (TesException e) {
                        Ui.printLine();
                        System.out.println("    " + e.getMessage());
                        Ui.printLine();
                    }

                    continue;
                } else if (command.contains("todo")) {
                    try {
                        if (command.equals("todo")) {
                            throw new TesException("Stop wasting my time. Are you muted?!");
                        }
                        String com = command.substring(5);
                        this.ui.addToDo(com);
                    } catch (TesException e) {
                        Ui.printLine();
                        System.out.println("    " + e.getMessage());
                        Ui.printLine();
                    }

                } else {
                    throw new TesException("Stop this nonsense. Come back with a smarter brain.");
                }
            } catch (TesException e) {
                Ui.printLine();
                System.out.println("    " + e.getMessage());
                Ui.printLine();
            }
        }
    }
    public static void main(String[] args){
        Tes chatbot = new Tes();
        chatbot.run();
    }
}
