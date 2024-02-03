package zoe;

/**
 * Processes input from user
 */
public class Parser {
    public TaskList tl;
    protected Ui ui;

    public Parser(TaskList tl) {
        this.tl = tl;
        this.ui = new Ui();
    }
    public void process(String command) {
        int idx = command.indexOf(" ");
        if (idx > -1) {
            String[] str = command.split(" ", 2);
            carryOutLongCommand(str[0], str[1]);
        } else {
            carryOutShortCommand(command);
        }
    }

    /**
     * Carries out short commands like list
     * If invalid command like empty todo is given, calls the respective responses
     * @param command
     */
    public void carryOutShortCommand(String command) {
        if (command.equals("list")) {
            for (int i = 0; i < tl.getSize(); i++) {
                System.out.println(String.format("%d.%s",
                        i + 1, tl.get(i).getStatus()));
            }
        } else {
            if (command.equals("todo")) {
                ui.todoDescription();
            } else {
                ui.invalidCommand();
            }
        }
    }

    /**
     * Carries out long commands to deal with the different forms of task
     * @param command
     * @param commandDescription
     */
    public void carryOutLongCommand(String command, String commandDescription) {
        if (command.equals("todo")) {
            ToDo td = new ToDo(commandDescription);
            tl.add(td);

        } else if (command.equals("deadline")) {
            Deadline dl = new Deadline(commandDescription);
            tl.add(dl);

        } else if (command.equals("event")) {
            Event e = new Event(commandDescription);
            tl.add(e);

        } else if (tl.isValid(Integer.parseInt(commandDescription))) {
            int i = Integer.parseInt(commandDescription);

            if (command.equals("mark")) {
                tl.markAsDone(i);
            } else if (command.equals("unmark")) {
                tl.unmark(i);
            } else {
                tl.delete(i);
            }

        } else if (!tl.isValid(Integer.parseInt(commandDescription))){
            ui.invalidIndex(tl.getSize());
        } else {
            ui.invalidCommand();
        }
    }
}
