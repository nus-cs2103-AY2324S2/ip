package awex;

import java.io.IOException;
import java.util.Scanner;
import tasks.*;

public class Parser {
    public static String byeParser(Storage s, TaskList t, Ui ui) {
        try {
            s.store(t);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ui.farewell();
    }

    public static String listParser(String[] arr, TaskList tasks, Ui ui) {
        if (arr.length > 1) {
            return ui.allInstructions();
        } else if (tasks.isEmpty()) {
            return ui.emptyListMessage();
        } else {
            return ui.showListMessage(tasks);
        }
    }

    public static String findParser(String[] arr, TaskList tasks, Ui ui) {
        if (arr.length < 2) {
            return ui.allInstructions();
        } else if (tasks.isEmpty()) {
            return ui.emptyListMessage();
        } else {
            return ui.showFindMessage(tasks, arr[1]);
        }
    }

    public static String markAndDeleteParser(String input, String[] arr, TaskList tasks, Ui ui) {
        String[] array = input.split(" ");
        if (array.length != 2) {
            return ui.wrongMarkDeleteFormatMessage(arr[0]);
        } else {
            int i = Integer.parseInt(array[1]);
            int len = tasks.size();
            if (i == 0 || i > len) {
                return ui.wrongIndexMessage(i, len);
            } else {
                if (arr[0].equals("delete")) {
                    return ui.deleteTaskMessage(i, tasks);
                } else {
                    Task t = tasks.get(i - 1);
                    t.changeStatus(arr[0]);
                    return ui.changeStatusMessage(arr[0], t);
                }
            }
        }
    }

    public static String taskParser(String input, String[] arr, TaskList tasks, Ui ui) {
        Task t;
        if (arr[0].equals("todo")) {
            if (arr.length == 1) {
                return ui.failedTaskCreationMessage("todo");
            }
            t = TodoTask.of(arr[1]);
        } else if (arr[0].equals("deadline")) {
            String[] array = input.split("/");
            if (array.length != 2) {
                return ui.failedTaskCreationMessage("deadline");
            }
            t = DeadlineTask.of(arr[1]);
        } else if (arr[0].equals("event")) {
            String[] array = input.split("/");
            if (array.length != 3) {
                return ui.failedTaskCreationMessage("event");
            }
            t = EventTask.of(arr[1]);
        } else {
            return ui.allInstructions();
        }
        tasks.add(t);
        return ui.newTaskAddedMessage(tasks.size(), t);
    }
}