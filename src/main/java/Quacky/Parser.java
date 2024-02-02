package Quacky;

import java.io.IOException;
import java.time.LocalDate;

public class Parser {

    public static void parseCommand(String command, TaskList tasks, UI ui) throws QuackyException {
        String[] keywords = command.split(" ", 2);
        String commandWord = keywords[0];
        switch (commandWord.toLowerCase()) {
            case "list": {
                ui.showList(tasks);
                break;
            }
            case "find": {
                String keyword = keywords[1];
                TaskList foundTasks = tasks.findTasksByKeyword(keyword);
                if (foundTasks.taskNumber() == 0) {
                    ui.say("No tasks found with the keyword: " + keyword);
                } else {
                    ui.showList(foundTasks);
                }
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