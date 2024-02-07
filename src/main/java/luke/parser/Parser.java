package luke.parser;

import luke.Storage;
import luke.exception.DateException;
import luke.exception.FileException;
import luke.exception.LukeException;
import luke.exception.TaskException;
import luke.task.*;
import luke.ui.Ui;

public class Parser {
    private String input;
    private Ui ui;
    private Storage storage;
    public Parser(String input) {
        this.input = input;

    }

    public void parse(TaskList list, Ui ui, Storage storage) {
        if (isExit()) {
            return;
        } else if (this.input.equals("list")) {
            ui.printList(list);
        } else if (this.input.contains("mark")) {
            try {
                if (input.equals("mark") || input.equals("unmark")) {
                    throw new LukeException("Hold up!! There must be a task to " + input + "!\n"
                            + "Please enter an index after " + input + ".");
                }

                try {
                    String[] instruction = input.split(" ");
                    String markOrUnmark = instruction[0];

                    if (!markOrUnmark.equals("mark") && !markOrUnmark.equals("unmark")) {
                        throw new LukeException("Hold up!! I am sorry, but I don't know what you mean by that :'(");
                    }

                    int index = Integer.parseInt(instruction[1]) - 1;  // array is 0-indexed

                    if (index >= list.size()) {
                        throw new LukeException("Hold up!! There is no such task in the list.\n"
                                + "Please enter a valid index after " + input.split(" ")[0] + ".");
                    }

                    if (markOrUnmark.equals("mark")) {
                        list.get(index).markAsDone();
                        ui.printTaskMarked(list.get(index));

                    } else if (markOrUnmark.equals("unmark")) {
                        list.get(index).markAsUndone();
                        ui.printTaskMarked(list.get(index));

                    } else {
                        throw new LukeException("Hold up!! I am sorry, but I don't know what you mean by that :'(");
                    }

                    storage.writeTask(list);

                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    throw new LukeException("Hold up!! Please enter a valid index after "
                            + input.split(" ")[0] + ".");
                } catch (FileException e) {
                    ui.getErrorMessage(e.getMessage());
                }

            } catch (LukeException e) {
                ui.getErrorMessage(e.getMessage());
            }
        } else if (input.contains("delete")) {
            try {
                if (input.equals("delete")) {
                    throw new LukeException("Hold up!! There must be a task to delete!\n"
                            + "Please enter an index after " + input + ".");
                }

                try {
                    String[] instruction = input.split(" ");
                    String delete = instruction[0];

                    if (!delete.equals("delete")) {
                        throw new LukeException("Hold up!! I am sorry, but I don't know what you mean by that :'(");
                    }

                    int index = Integer.parseInt(instruction[1]) - 1;  // array is 0-indexed

                    if (index >= list.size()) {
                        throw new LukeException("Hold up!! There is no such task in the list.\n"
                                + "Please enter a valid index after delete.");
                    }

                    Task removedTask = list.get(index);
                    list.remove(index);
                    ui.printTaskDeleted(removedTask, list.size());

                    storage.writeTask(list);

                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    throw new LukeException("Hold up!! Please enter a valid index after delete.");
                } catch (FileException e) {
                    ui.getErrorMessage(e.getMessage());
                }

            } catch (LukeException e) {
                ui.getErrorMessage(e.getMessage());
            }

        } else {
            try {
                if (input.equals("todo") || input.equals("deadline") || input.equals("event")) {
                    throw new LukeException("Hold up!! The description of a " + input + " cannot be empty.\n"
                            + "Please enter a description after your " + input + ".");
                }

                String[] instruction = input.split(" ");
                String type = instruction[0];

                if (type.equals("todo")) {
                    // 5 is the index after "todo ", so starts from index 5
                    String description = input.substring(5);
                    list.add(new Todo(description));

                } else if (type.equals("deadline")) {
                    try {
                        // 9 is the index after "deadline ", so starts from index 9
                        // -1 to remove the space before "/by"
                        String description = input.substring(9, input.indexOf("/by") - 1);
                        // +4 to remove the "/by " and start from the index after "/by "
                        String by = input.substring(input.indexOf("/by") + 4);
                        list.add(new Deadline(description, by));

                    } catch (StringIndexOutOfBoundsException e) {
                        throw new TaskException("Hold up!! The description of a deadline cannot be empty.\n"
                                + "Please follow this format: deadline <description> /by <date/day/time>.");
                    }

                } else if (type.equals("event")) {
                    try {
                        // 6 is the index after "event ", so starts from index 6
                        // -1 to remove the space before "/from"
                        String description = input.substring(6, input.indexOf("/from") - 1);
                        // +6 to remove the "/from " and start from the index after "/from "
                        // -1 to remove the space before "/to"
                        String from = input.substring(input.indexOf("/from") + 6, input.indexOf("/to") - 1);
                        // +4 to remove the "/to " and start from the index after "/to "
                        String to = input.substring(input.indexOf("/to") + 4);
                        list.add(new Event(description, from, to));
                    } catch (StringIndexOutOfBoundsException e) {
                        throw new TaskException("Hold up!! The description of an event cannot be empty.\n"
                                + "Please follow this format: event <description> "
                                + "/from <date/day/time> /to <date/day/time>.");
                    }
                } else {
                    throw new LukeException("Hold up!! I am sorry, but I don't know what you mean by that :'(");

                }
                storage.writeTask(list);
                ui.printTaskAdded(list.get(list.size() - 1), list.size());

            } catch (LukeException | TaskException | FileException e) {
                ui.getErrorMessage(e.getMessage());
            } catch (DateException e) {
                ui.getErrorMessage(e.getMessage() + "\nPlease enter the date in proper format such as dd/MM/yyyy or"
                        + " yyyy-MM-dd\nYou can also enter the time in 24-hour format such as HH[:MM] " +
                        "after the date");
            }
        }
    }

    public void setInput(String input) {
        this.input = input;
    }

    public boolean isExit() {
        return input.equals("bye");
    }
}
