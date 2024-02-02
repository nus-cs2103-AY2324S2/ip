package Quacky;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Parses and processes user commands for the Quacky application. This class is responsible for
 * interpreting the string input from the user and executing the corresponding actions on the
 * application's task list and user interface.
 */
public class Parser {

    /**
     * Parses the given command string and performs actions based on the command. This method
     * supports various commands including adding, marking, and deleting tasks, as well as
     * listing tasks and exiting the application.
     *
     * @param command The command string entered by the user.
     * @param tasks The TaskList instance on which operations are performed based on the command.
     * @param ui The UI instance used for communicating messages back to the user.
     * @throws QuackyException If an error occurs during the processing of the command
     */
    public static void parseCommand(String command, TaskList tasks, UI ui) throws QuackyException {
        String[] keywords = command.split(" ", 2);
        String commandWord = keywords[0];
        switch (commandWord.toLowerCase()) {
            case "list": {
                ui.showList(tasks);
                break;
            }

            case "mark": {
                int taskNumber = Integer.parseInt(keywords[1]) - 1;
                try {
                    tasks.markCompleteTask(taskNumber);
                    ui.showMarkDone(tasks.printTask(taskNumber));
                } catch (QuackyException e) {
                    ui.showErrorMessage(e);
                } finally {
                    break;
                }

            }

            case "unmark": {
                int taskNumber = Integer.parseInt(keywords[1]) - 1;
                tasks.unmarkCompleteTask(taskNumber);
                ui.showUnmarkDone(tasks.printTask(taskNumber));
                break;
            }

            case "delete": {
                int taskNumber = Integer.parseInt(keywords[1]) - 1;
                tasks.deleteTask(taskNumber);
                ui.showDeleteTask(tasks.taskNumber(), tasks.printTask(taskNumber));
                break;
            }

            case "todo": {
                try {
                    if (command.trim().equals("todo")) {
                        throw new QuackyException("Quack? (Please provide a description for your task)");
                    }
                    Task newTask = new Todo(keywords[1]);
                    tasks.addTask(newTask);
                    ui.showAddTask(tasks.taskNumber(), newTask.toString());

                } catch (QuackyException e) {
                    ui.showErrorMessage(e);
                }
                break;
            }

            case "deadline": {
                try {
                    if (command.trim().equals("deadline")) {
                        throw new QuackyException("Quack? (Please provide a description for your task)");
                    }
                    String[] parts = command.substring(9).split(" /by ");
                    Task newTask = new Deadline(parts[0], LocalDate.parse(parts[1]));
                    tasks.addTask(newTask);
                    ui.showAddTask(tasks.taskNumber(), newTask.toString());

                } catch (QuackyException e) {
                    ui.showErrorMessage(e);
                }
                break;
            }
            case "event": {
                try {
                    if (command.trim().equals("event")) {
                        throw new QuackyException("Quack? (Please provide a description for your task)");
                    }
                    String[] parts = keywords[1].split(" /from | /to ");
                    Task newTask = new Event(parts[0], LocalDate.parse(parts[1]), LocalDate.parse(parts[2]));
                    tasks.addTask(newTask);
                    ui.showAddTask(tasks.taskNumber(), newTask.toString());

                } catch (QuackyException e) {
                    ui.showErrorMessage(e);
                }
                break;
            }
            case "bye": {
                ui.showFarewell();
                break;
            }
            default: {
                throw new QuackyException("Quack? (In confusion)");
            }
        }
    }
}