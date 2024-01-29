package duke;

import static java.lang.Integer.parseInt;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.loadTaskslist());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList(new ArrayList<Task>());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
            Parser parser = new Parser(ui.readCommand());

            String commandWord = parser.getCommandWord();
            switch (commandWord) {
            case "bye":
                isExit = true;
                ui.showBye();
                break;
            case "list":
                ui.showTaskList(tasks);
                break;
            case "mark":
                Task markTask = tasks.markDone(parser.getIndex());
                ui.showDone(markTask);
                break;
            case "unmark":
                Task unmarkTask = tasks.unmarkDone(parser.getIndex());
                ui.showUnmark(unmarkTask);
                break;
            case "delete":
                Task deleteTask = tasks.deleteTask(parser.getIndex());
                ui.showDelete(deleteTask, tasks.getSize());
                break;
            case "todo":
                Task task = new Todo(parser.getDescription());
                tasks.addTask(task);
                ui.showAddTask(task, tasks.getSize());
                break;
            case "deadline":
                Task deadlineTask = new Deadline(parser.getDescription(), parser.getBy());
                tasks.addTask(deadlineTask);
                ui.showAddTask(deadlineTask, tasks.getSize());
                break;
            case "event":
                Task eventTask = new Event(parser.getDescription(), parser.getFromTo()[0], parser.getFromTo()[1]);
                tasks.addTask(eventTask);
                ui.showAddTask(eventTask, tasks.getSize());
                break;
            default:
                ui.commandNotUnderstood();
            }

            } catch (IndexOutOfBoundsException e) {
                ui.showError("The index of a task cannot be empty. \n\t" +
                    "Please use the following format: mark <index>");
            } catch (NumberFormatException e) {
                ui.showError("Please enter a valid number to modify task");
            } catch (DateTimeParseException e) {
                ui.showError("Please enter a valid date in the format dd-mm-yyyy");
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }

        try {
            storage.writeToFile(tasks);
        } catch (IOException e) {
            ui.showError("Failed to save tasks to file");
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }


//    public static void main(String[] args) throws duke.DukeException {
//
//
//
//
//
//        List<duke.Task> tasksList = new ArrayList<duke.Task>();
//
//
////        try {
////            File file = new File("data/duke.txt");
////            if (!file.exists()) {
////                boolean isCreated = false;
////                isCreated = file.getParentFile().mkdirs();
////                isCreated = file.createNewFile();
////
////                if (!isCreated) {
////                    throw new duke.DukeException("Error: File could not be created.");
////                }
////            }
////            else {
////                Scanner s = new Scanner(file);
////                while (s.hasNext()) {
////                    String line = s.nextLine();
////                    duke.Task task = getTask(line);
////                    tasksList.add(task);
////                }
////                s.close();
////            }
////        } catch (FileNotFoundException e) {
////            System.out.println("Error: File could not be found.");
////        } catch (IOException e) {
////            System.out.println("Error: File could not be created.");
////        }
//
//
//        System.out.println("DevGPT:\n\tHello! I'm DevGPT\nWhat can I do for you?");
//        Scanner scanner = new Scanner(System.in);
//        boolean isExit = false;
//        while (!isExit) {
//            try {
//                while (true) {
//                    System.out.println("User:");
//                    String input = scanner.nextLine();
//
//                    if (input.contains("bye")) {
//                        isExit = true;
//                        break;
//                    }
//
//                    String command = input.split(" ")[0];
//
//                    if (command.equals("todo")) {
//                        if (input.split(" ").length == 1) {
//                            throw new duke.DukeException("The description of a todo cannot be empty. \n\t" +
//                                "Please use the following format: todo <description>");
//                        }
//                        String details = input.split(" ", 2)[1];
//                        duke.Task task = new duke.Todo(details);
//                        tasksList.add(task);
//                        System.out.println("DevGPT:\n\t" + " Got it. I've added this task: \n\t\t" + task.toString());
//                        System.out.println("DevGPT:\n\t Now you have " + tasksList.size() + " tasks in the list.");
//                    } else if (command.equals("deadline")) {
//                        if (input.split(" ").length <= 3) {
//                            throw new duke.DukeException("The deadline and date for a task cannot be empty. \n\t" +
//                                "Please use the following format: deadline <description> /by <date>");
//                        } else if (!input.contains("/by")) {
//                            throw new duke.DukeException("Invalid command for deadline. \n\t" +
//                                "Please use the following format: deadline <description> /by <date>");
//                        }
//                        String details = input.split(" ", 2)[1];
//                        String description = details.split("/by ")[0].trim();
//                        String by = details.split("/by ")[1].trim();
//                        duke.Task task = new duke.Deadline(description, getInputDateFormat(by));
//                        tasksList.add(task);
//                        System.out.println("DevGPT:\n\t" + " Got it. I've added this task: \n\t\t" + task.toString());
//                        System.out.println("DevGPT:\n\t Now you have " + tasksList.size() + " tasks in the list.");
//                    } else if (command.equals("event")) {
//
//                        // parser part
//                        if (input.split(" ").length <= 3) {
//                            throw new duke.DukeException("The date for an event cannot be empty. \n\t" +
//                                "Please use the following format: event <description> /from <date> /to <date>");
//                        } else if (!input.contains("/from") || !input.contains("/to")) {
//                            throw new duke.DukeException("Invalid command for event. \n\t" +
//                                "Please use the following format: event <description> /from <date> /to <date>");
//                        }
//                        String details = input.split(" ", 2)[1];
//                        String description = details.split("/from")[0].trim();
//                        String from = details.split("/from")[1].split("/to")[0].trim();
//                        String to = details.split("/to")[1].trim();
//
//                        duke.Task task = new duke.Event(description, getInputDateFormat(from), getInputDateFormat(to));
//                        tasksList.add(task);
//                        System.out.println("DevGPT:\n\t" + " Got it. I've added this task: \n\t\t" + task.toString());
//                        System.out.println("DevGPT:\n\t Now you have " + tasksList.size() + " tasks in the list.");
//                    } else if (command.equals("list")) {
//                        System.out.println("DevGPT:\n\t Here are the tasks in your list:");
//                        for (int i = 0; i < tasksList.size(); i++) {
//                            duke.Task task = tasksList.get(i);
//                            System.out.println("\t\t" + (i + 1) + ". " + task.toString());
//                        }
//                    } else if (command.equals("unmark")) {
//                        String[] split = input.split(" ");
//                        if (split.length == 1) {
//                            throw new duke.DukeException("The index of a task cannot be empty. \n\t" +
//                                "Please use the following format: unmark <index>");
//                        } else if (parseInt(split[1]) > tasksList.size() || parseInt(split[1]) < 1) {
//                            throw new duke.DukeException("No such task exists.");
//                        }
//                        int index = parseInt(split[1]);
//                        duke.Task task = tasksList.get(index - 1);
//                        task.markAsUndone();
//                        System.out.println("DevGPT:\n\t Got it! I've marked this task as not done yet:");
//                        System.out.println("\t\t" + task.toString());
//                    } else if (command.equals("mark")) {
//                        String[] split = input.split(" ");
//                        if (split.length == 1) {
//                            throw new duke.DukeException("The index of a task cannot be empty. \n\t" +
//                                "Please use the following format: mark <index>");
//                        } else if (parseInt(split[1]) > tasksList.size() || parseInt(split[1]) < 1) {
//                            throw new duke.DukeException("No such task exists.");
//                        }
//                        int index = Integer.parseInt(split[1]);
//                        duke.Task task = tasksList.get(index - 1);
//                        task.markAsDone();
//                        System.out.println("DevGPT:\n\t Nice! I've marked this task as done:");
//                        System.out.println("\t\t" + task.toString());
//                    } else if (command.equals("delete")) {
//                        String[] split = input.split(" ");
//                        if (split.length == 1) {
//                            throw new duke.DukeException("The index of a task cannot be empty. \n\t" +
//                                "Please use the following format: delete <index>");
//                        } else if (parseInt(split[1]) > tasksList.size() || parseInt(split[1]) < 1) {
//                            throw new duke.DukeException("No such task exists.");
//                        }
//                        int index = Integer.parseInt(split[1]);
//                        duke.Task task = tasksList.remove(index - 1);
//                        System.out.println("DevGPT:\n\t Poof! I've removed this task:");
//                        System.out.println("\t\t" + task.toString());
//                        System.out.println("DevGPT:\n\t Now you have " + tasksList.size() + " tasks in the list.");
//                    } else {
//                        throw new duke.DukeException(
//                            "Your message is not understood. Please use the following:\n\t1. todo <description>" +
//                                "\n\t2. deadline <description> /by <dd-mm-yyyy>\n\t3. event <description> /from <dd-mm-yyyy> /to <dd-mm-yyyy>" +
//                                "\n\t4. list\n\t5. mark <index>\n\t6. unmark <index>\n\t7. delete <index>\n\t8. bye");
//                    }
//
//                }
//
//                scanner.close();
//                System.out.println("DevGPT:\n\tBye. Hope to see you again soon!");
//                writeToFile(tasksList);
//
//            } catch (NumberFormatException e) {
//                System.out.println("DevGPT:\n\t" + "Input error: Please enter a valid number to modify task");
//            } catch (duke.DukeException e) {
//                System.out.println("DevGPT:\n\t" + e.getMessage());
//            } catch (DateTimeParseException e) {
//                System.out.println("DevGPT:\n\t" + "Input error: Please enter a valid date in the format dd-mm-yyyy");
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }







}