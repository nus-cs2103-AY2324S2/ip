package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.*;
import duke.ui.Ui;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

        private String command;
        private boolean hasChanged = false;
        private Ui ui = new Ui();
        private TaskList tasks;
        protected Storage storage;
        protected Scanner scanner;
        protected boolean isRunning;

        public Parser(TaskList tasks, Storage storage, Scanner scanner, boolean isRunning) {
            this.tasks = tasks;
            this.storage = storage;
            this.scanner = scanner;
            this.isRunning = isRunning;
        }

        public boolean getRunningStatus() {
            return this.isRunning;
        }

        public void setCommand(String command) {
            this.command = command;
        }

        public void processCommand() throws DukeException {
            String[] words = command.split("\\s+");

            try {
                ui.printDashes();
                switch (words[0].toLowerCase()) {
                    case "bye":
                        ui.showGoodbyeMessage();
                        this.isRunning = false;
                        break;

                    case "list":
                        Ui.showTaskList(tasks);
                      //  try {
                            TaskList.getList(tasks);
                      //  } catch (DukeException e) {
                          //  System.out.println(e);
                      //  }
                        break;
                    case "mark":
                        if (words.length > 1) {
                            int index = Integer.parseInt(words[1]) - 1;
                            if (index >= 0 && index < tasks.size()) {
                                Task task = tasks.getTask(index);
                                tasks.markStatus(task);
                                storage.saveTasks(tasks);
                                hasChanged = true; //flag, no need to change
                            } else {
                                Ui.showError("You have not created a task " + words[1] + " yet!");
                            }
                        }
                        break;
                    case "unmark":
                        if (words.length > 1) {
                            int index = Integer.parseInt(words[1]) - 1;
                            if (index >= 0 && index < tasks.size()) {
                                Task task = tasks.getTask(index);
                                tasks.unmarkStatus(task);
                                storage.saveTasks(tasks);
                                hasChanged = true; //flag, no need to change
                            } else {
                                Ui.showError("You have not created a task " + words[1] + " yet!");
                            }
                        }
                        break;

                    case "delete":
                        if (words.length > 1) {
                            tasks.deleteTask(Integer.parseInt(words[1]) - 1);
                            Ui.printNumberOfTasks(tasks);
                            storage.saveTasks(tasks);
                            hasChanged = true;
                        }
                        break;

                    case "todo":
                        if (command.length() <= 5) {
                            ui.showError("You gotta enter some task TO DO!");
                        } else {
                            tasks.addToDoTask(new ToDo(command.substring(5).trim()));
                            storage.saveTasks(tasks);
                            ui.printNumberOfTasks(tasks);
                            hasChanged = true;
                        }
                        break;

                    case "deadline":
                        Matcher byMatcher = Pattern.compile("/by\\s+(\\S.+)").matcher(command);
                        String deadlineDescription = "";
                        Matcher deadlineDescMatcher = Pattern.compile("deadline\\s+(.+?)\\s*/by").matcher(command);
                        if (words.length > 1) {
                            if (deadlineDescMatcher.find()) {
                                deadlineDescription = deadlineDescMatcher.group(1).trim();
                            }
                        } else {
                            ui.showError("Please include both task description and deadline correctly!");
                            break;
                        }
                        String by = byMatcher.find() ? byMatcher.group(1).trim() : "";
                        tasks.addDeadlineTask(new Deadline(deadlineDescription, by));
                        storage.saveTasks(tasks);
                        Ui.printNumberOfTasks(tasks);
                        hasChanged = true;
                        break;
                    case "event":
                        Matcher fromMatcher = Pattern.compile("/from\\s+(\\S+[^/]+)").matcher(command);
                        Matcher toMatcher = Pattern.compile("/to\\s+(\\S.+)").matcher(command);
                        String eventDescription = "";
                        Matcher descriptionMatcher = Pattern.compile("event\\s+(.+?)\\s*/from").matcher(command);
                        if (words.length > 1) {
                            if (descriptionMatcher.find()) {
                                eventDescription = descriptionMatcher.group(1).trim();
                            }
                        } else {
                            Ui.showError("You didn't enter the details or duration!");
                            break;
                        }
                        String from = fromMatcher.find() ? fromMatcher.group(1).trim() : "";
                        String to = toMatcher.find() ? toMatcher.group(1).trim() : "";
                        tasks.addEventTask(new Event(eventDescription, from, to));
                        storage.saveTasks(tasks);
                        Ui.printNumberOfTasks(tasks);
                        hasChanged = true;
                        break;
                    case "find":
                        String taskToFind = command.substring(5).trim();
                        TaskList foundTasks = tasks.findTask(taskToFind);
                        if (foundTasks.size() > 0) {
                            ui.showFoundTasks(taskToFind);
                            tasks.getList(foundTasks);
                        } else {
                            ui.showNoTasksFound(taskToFind);
                        }
                        break;


                    default:
                        ui.showError("I don't know what you are saying :(");
                        break;
                }
            } catch (DukeException | IOException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.printDashes();
            }
        }
}
