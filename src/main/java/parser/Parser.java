package parser;

import java.util.*;
import exception.DukeException;
import tasks.Deadlines;
import tasks.Events;
import tasks.Task;
import tasks.ToDo;
import storage.Storage;
import actions.AddTask;
import actions.DeleteTask;
import actions.ListTask;
import actions.MarkTask;
import actions.UnmarkTask;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Parser {
    private Storage storage;
    
    public Parser(Storage storage) {
        this.storage = storage;
    }

    public String parse(String input) {
        ArrayList<Task> inventory = storage.load();
        try {
            if (input.equalsIgnoreCase("list")) {
                if (!input.trim().equals("list")) {
                    throw new DukeException("OOPS!!! This is an invalid call of list command.");
                } 
                else {
                    ListTask lister = new ListTask(storage);
                    return lister.list();
                }
            } 
            else if (input.startsWith("mark")) {
                if (input.trim().equals("mark")) {
                    throw new DukeException("OOPS!!! Invalid Command, highlight which task to mark");
                } else {
                    int index = Integer.parseInt(input.substring(5));
                    if (index < 1 || index > inventory.size()) {
                        throw new DukeException("OOPS!!! The task number you are trying to mark does not exist. ");
                    }
                    MarkTask marker = new MarkTask(storage, index);
                    return marker.mark();
                }
            } 
            else if (input.startsWith("unmark")) {
                if (input.trim().equals("unmark")) {
                    throw new DukeException("OOPS!!! Invalid Command, highlight which task to unmark");
                } else {
                    int index = Integer.parseInt(input.substring(7));
                    if (index < 1 || index > inventory.size()) {
                        throw new DukeException("OOPS!!! The task number you are trying to unmark does not exist. ");
                    }
                    UnmarkTask unmarker = new UnmarkTask(storage, index);
                    return unmarker.unmark();
                }
            } 
            else if (input.startsWith("todo")) {
                if (input.trim().equals("todo")) {
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                } else {
                    String task = input.substring(5);
                    ToDo todoTask = new ToDo(task);
                    AddTask adder = new AddTask(storage, todoTask);
                    adder.add();
                    return adder.toString();
                }
            }
            else if (input.startsWith("deadline")) {
                String valid_format = "Please input in format: *deadline* *TASK* /*by yyyy-mm-dd TIME*";
                if (input.trim().equals("deadline")) {
                    throw new DukeException("OOPS!!! The description of a deadline cannot be empty. " + valid_format);
                } 
                else if (!input.contains("/")) {
                    throw new DukeException("OOPS!!! Your deadline command's format is invalid. " + valid_format);
                } 
                else if (input.substring(9).split("/").length < 2) {
                    throw new DukeException("OOPS!!! Your deadline command's format is invalid. " + valid_format);
                }
                else {
                    String[] parts = input.substring(9).split("/");
                    LocalDateTime dateTime;
                    try {
                        String dateTimeString = parts[1].substring(3).trim();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                        dateTime = LocalDateTime.parse(dateTimeString, formatter);
                    }
                    catch (DateTimeParseException e) {
                        throw new DukeException("OOPS!!! The date format is invalid. " + valid_format);
                    }
                    Deadlines deadlineTask = new Deadlines(parts[0], parts[1], dateTime);
                    AddTask adder = new AddTask(storage, deadlineTask);
                    adder.add();
                    String temp = adder.toString();
                    
                    //Also include the tasks with the same deadline as the following task.
                    String similarities = "";
                    int counter = 1;
                    for (int i = 0; i < inventory.size() - 1; i++) {
                        Task t = inventory.get(i);
                        if (!t.identifier().equals("[D]")) {
                            continue;
                        }
                        if (t.getDeadline().equals(dateTime.toLocalDate())) {
                            similarities += counter + ": " + t.toString() + "\n";
                        }
                        counter++;
                    }
                    if (similarities.isBlank()) {
                        temp += "\n\n\nThere are no other deadlines on the same day as this Task's deadline :)";
                        return temp;
                    } else {
                        temp += "\n\n\nThe following are the tasks with the same deadlines!: \n" + similarities;
                        return temp;
                    }
                }
            }
            else if (input.startsWith("event")) {
                String valid_format = "Please input in the format: Event *TASK* /*from YYYY-DD-MM TIME* /*to YYYY-DD-MM TIME*";
                if (input.trim().equals("event")) {
                    throw new DukeException("OOPS!!! The description of a event cannot be empty. " + valid_format);
                }
                else if (!input.contains("/")) {
                    throw new DukeException("OOPS!!! Your event command's format is invalid. " + valid_format);
                } else if (input.substring(6).split("/").length < 3) {
                    throw new DukeException("OOPS!!! Your event command's format is invalid. " + valid_format);
                }  
                else { 
                    String[] parts = input.substring(6).split("/");
                    LocalDateTime dateTime1;
                    LocalDateTime dateTime2;
                    try {
                        String dateTimeString1 = parts[1].substring(5).trim();
                        String dateTimeString2 = parts[2].substring(3).trim();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                        dateTime1 = LocalDateTime.parse(dateTimeString1, formatter);
                        dateTime2 = LocalDateTime.parse(dateTimeString2, formatter);
                    }
                    catch (DateTimeParseException e) {
                        throw new DukeException("OOPS!!! The date format is invalid. " + valid_format);
                    }
                    Events eventTask = new Events(parts[0], parts[1], parts[2], dateTime1, dateTime2);
                    AddTask adder = new AddTask(storage, eventTask);
                    adder.add();
                    return adder.toString();
                }
            }
            else if (input.startsWith("delete")) {
                String valid_format = "Please input in format: delete *index*";
                if (input.trim().equals("delete")) {
                    throw new DukeException("OOPS!!! Invalid delete command " + valid_format);
                } else {
                    int index = Integer.parseInt(input.substring(7));
                    if (index < 1 || index > inventory.size()) {
                        throw new DukeException("OOPS!!! The task number you are trying to delete does not exist. ");
                    }
                    DeleteTask deleter = new DeleteTask(storage, index);
                    deleter.delete();
                    return deleter.toString();
                }
            }
            else {
                return "OOPS!!! I'm sorry, but that's an invalid command :-(";
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
