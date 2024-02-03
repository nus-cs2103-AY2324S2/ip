package tes.command;

/**
 * Represents a parser to react accordingly to the command given.
 */
public class Parser {
    /** User interface which deals with input and output */
    private Ui ui;

    /**
     * Constructs a parser to deal with command given.
     * @param ui User interface to deal with input and output.
     */
    public Parser(Ui ui) {
        this.ui = ui;
    }

    /**
     * Parses the command given and reacts accordingly.
     * If given "bye", the chatbot is closed.
     * If given "list", the lists of tasks stored is shown.
     * If given "unmark", the specific task is unmarked.
     * If given "mark", the specific task is marked.
     * If given "delete", the specific task is deleted form the list.
     * If given "deadline", a new task with a deadline is added to the list.
     * If given "event", a new task with a designated period is added to the list.
     * If given "todo", a new normal task is added to the list.
     * If given "find", a list of tasks containing the keyword is shown.
     * If commands other than the above are given, nothing will be done and the user is asked for the next command.
     */
    public void parse() {
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

                } else if (command.contains("find")) {
                    String[] split = command.split(" ");
                    this.ui.find(split[1]);
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

}
