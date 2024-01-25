import java.util.InputMismatchException;

public class Mike {
    private final TaskList taskList;
    private final Ui ui;

    Mike() {
        this.taskList = new TaskList();
        this.ui = new Ui();
    }

    public void run() {
        this.ui.displayWelcome();
        boolean exitSeen = false;
        while (!exitSeen) {
            try {
                String userInput = ui.scanInput();
                ui.displayLine();

                // Parse the user input.
                String[] commands = userInput.split(" ", 2);
                int numberOfCommands = commands.length;
                String primaryCommand = commands[0];

                switch (primaryCommand) {
                    case "bye": {
                        if (numberOfCommands != 1) {
                            throw new MikeException("Usage: bye");
                        }

                        ui.farewell();
                        exitSeen = true;
                        break;
                    }
                    case "list": {
                        if (numberOfCommands != 1) {
                            throw new MikeException("Usage: list");
                        }
                        if (taskList.isEmpty()) {
                            throw new MikeException("You have no more tasks Sulley...");
                        }
                        ui.display("You and I are a team.\nHere is the task list:");
                        ui.display(taskList);
                        break;
                    }
                    case "mark": {
                        if (numberOfCommands != 2) {
                            throw new MikeException("Usage: mark [number]");
                        }

                        String argument = commands[1];
                        try {
                            int taskIndex = Integer.parseInt(argument) - 1;
                            /*
                            TODO:
                                1. Check that taskIndex is in the correct bound.
                                2. Check that task is or is not done.
                             */
                            Task task = taskList.get(taskIndex);
                            task.markAsDone();
                            String message = "Nice! I've marked this task as done:\n  " + task;
                            ui.display(message);
                        } catch (InputMismatchException e) {
                            throw new MikeException(
                                    String.format("One, two, three, four, get the kid back through the door!\n" +
                                            "'%s' is not an integer Sulley...", argument));
                        } catch (IndexOutOfBoundsException e) {
                            throw new MikeException("That number is too big Sull.");
                        }
                        break;
                    }
                    case "unmark": {
                        if (numberOfCommands != 2) {
                            throw new MikeException("Usage: unmark [number]");
                        }

                        String argument = commands[1];
                        try {
                            int taskIndex = Integer.parseInt(argument) - 1;
                            /*
                            TODO:
                                1. Check that taskIndex is in the correct bound.
                                2. Check that task is or is not done.
                             */
                            Task task = taskList.get(taskIndex);
                            task.markAsNotDone();

                            String message = "I've marked this task as not done:\n  " + task;
                            ui.display(message);
                        } catch (InputMismatchException e) {
                            throw new MikeException(
                                    String.format("One, two, three, four, get the kid back through the door!\n" +
                                            "'%s' is not an integer Sulley...", argument));
                        } catch (IndexOutOfBoundsException e) {
                            throw new MikeException("That number is too big Sull.");
                        }
                        break;
                    }
                    case "todo": {
                        if (numberOfCommands != 2) {
                            throw new MikeException("The description is missing.\nUsage: todo [description]");
                        }

                        String description = commands[1].strip();

                        if (description.isBlank()) {
                            throw new MikeException("The description is missing.\nUsage: todo [description]");
                        }

                        Task newTask = new Todo(description);
                        taskList.add(newTask);

                        String message =
                                "Got it, I've added this task:\n  "
                                + newTask + "\n"
                                + "Now you have " + taskList.size() + " tasks in the list.";
                        ui.display(message);
                        break;
                    }
                    case "deadline": {
                        if (numberOfCommands != 2) {
                            throw new MikeException("Usage: deadline [description] /by [date]");
                        }

                        String arguments = commands[1];
                        String[] descriptionAndDate = arguments.split("/by", 3);
                        int numberOfArguments = descriptionAndDate.length;

                        if (numberOfArguments != 2) {
                            throw new MikeException("Usage: deadline [description] /by [date]");
                        }

                        String description = descriptionAndDate[0].strip();
                        String date = descriptionAndDate[1].strip();

                        if (description.isBlank()) {
                            throw new MikeException("The description is missing Sull.\nUsage: deadline [description] /by [date]");
                        } else if (date.isBlank()) {
                            throw new MikeException("The date is missing Sull.\nUsage: deadline [description] /by [date]");
                        }

                        Task newTask = new Deadline(description, date);
                        taskList.add(newTask);
                        /*
                        TODO:
                            1. Refactor to enforce DRY principle.
                         */
                        String message =
                                "Got it, I've added this task:\n  "
                                + newTask + "\n"
                                + "Now you have " + taskList.size() + " tasks in the list.";
                        ui.display(message);
                        break;

                    }
                    case "event": {
                        if (numberOfCommands != 2) {
                            throw new MikeException("Usage: event [description] /from [date] /to [date]");
                        }

                        String arguments = commands[1];
                        String[] descriptionAndDates = arguments.split("/from", 3);

                        if (descriptionAndDates.length != 2) {
                            throw new MikeException("Usage: event [description] /from [date] /to [date]");
                        }

                        String description = descriptionAndDates[0].strip();
                        String dates = descriptionAndDates[1];

                        if (description.isBlank()) {
                            throw new MikeException("The description is missing Sull.\nUsage: event [description] /from [date] /to [date]");
                        }

                        String[] fromAndTo = dates.split("/to", 3);

                        if (fromAndTo.length != 2) {
                            throw new MikeException("Usage: event [description] /from [date] /to [date]");
                        }

                        String from = fromAndTo[0].strip();
                        String to = fromAndTo[1].strip();

                        if (from.isBlank()) {
                            throw new MikeException("Required argument missing in '/from [date]'.\nUsage: event [description] /from [date] /to [date]");
                        } else if (to.isBlank()) {
                            throw new MikeException("Required argument missing in '/to [date]'.\nUsage: event [description] /from [date] /to [date]");
                        }

                        Task newTask = new Event(description, from, to);
                        taskList.add(newTask);

                        String message =
                                "Got it, I've added this task:\n  "
                                + newTask + "\n"
                                + "Now you have " + taskList.size() + " tasks in the list.";
                        ui.display(message);
                        break;
                    }
                    default: {
                        throw new MikeException("That is the weirdest thing you've ever said.");
                    }
                }
            } catch (MikeException e) {
                ui.displayError(e.getMessage());
            } finally {
                ui.displayLine();
            }
        }
    }

    /**
     * Main method that runs the program.
     * @param args n/a
     */
    public static void main(String[] args) {
        new Mike().run();
    }
}
