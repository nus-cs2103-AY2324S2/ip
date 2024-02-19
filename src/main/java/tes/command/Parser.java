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
     *
     * @param command Command given.
     */
    public String parse(String command) {
        try {
            if (command.equals("list")) {
                return this.ui.listTask();
            } else if (command.startsWith("unmark")) {
                String indexInString = command.substring(7);
                int index = Integer.parseInt(indexInString);
                return this.ui.unmark(index - 1);
            } else if (command.startsWith("mark")) {
                String indexInString = command.substring(5);
                int index = Integer.parseInt(indexInString);
                return this.ui.mark(index - 1);
            } else if (command.startsWith("delete")) {
                String indexInString = command.substring(7);
                int index = Integer.parseInt(indexInString);
                return this.ui.delete(index);
            } else if (command.startsWith("deadline")) {
                try {
                    if (command.equals("deadline")) {
                        throw new TesException("Stop wasting my time. Are you muted?!");
                    } else if (!command.contains("/by")) {
                        throw new TesException("I see an idiot with no time management in their little brain.");
                    }
                    String deadlineTask = command.substring(9);
                    String[] descriptionAndPeriod = deadlineTask.split(" /by ");
                    String description = descriptionAndPeriod[0];
                    String period = descriptionAndPeriod[1];
                    return this.ui.addDeadline(description, period);
                } catch (TesException e) {
                    return e.getMessage();
                }
            } else if (command.startsWith("event")) {
                try {
                    if (command.equals("event")) {
                        throw new TesException("Stop wasting my time. Are you muted?!");
                    } else if (!command.contains("/from") || !command.contains("/to")) {
                        throw new TesException("I see an idiot with no time management in their little brain.");
                    }
                    String eventTask = command.substring(6);
                    String[] descriptionAndPeriod = eventTask.split(" /from ");
                    String description = descriptionAndPeriod[0];
                    String period = descriptionAndPeriod[1];
                    String[] fromAndTo = period.split(" /to ");
                    String from = fromAndTo[0];
                    String to = fromAndTo[1];
                    return this.ui.addEvent(description, from, to);
                } catch (TesException e) {
                    return e.getMessage();
                }
            } else if (command.startsWith("todo")) {
                try {
                    if (command.equals("todo")) {
                        throw new TesException("Stop wasting my time. Are you muted?!");
                    }
                    String description = command.substring(5);
                    return this.ui.addToDo(description);
                } catch (TesException e) {
                    return e.getMessage();
                }
            } else if (command.contains("find")) {
                String keyword = command.substring(5);
                return this.ui.find(keyword);
            } else {
                throw new TesException("Stop this nonsense. Come back with a smarter brain.");
            }
        } catch (TesException e) {
            return e.getMessage();
        }

    }
}
