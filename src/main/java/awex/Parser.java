package awex;

import java.io.IOException;
import java.util.Scanner;
import tasks.*;

public class Parser {
    public static void parse(TaskList tasks, Ui ui) throws IOException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String next = sc.nextLine();
            String[] arr = next.split(" ", 2);
            if (next.equals("bye")) {
                break;
            } else if (next.equals("list")) {
                if (arr.length > 1) {
                    ui.allInstructions();
                } else if (tasks.isEmpty()) {
                    ui.emptyListMessage();
                } else {
                    ui.showListMessage(tasks);
                }
            } else if (arr[0].equals("find")) {
                if (arr.length < 2) {
                    ui.allInstructions();
                } else if (tasks.isEmpty()) {
                    ui.emptyListMessage();
                } else {
                    ui.showFindMessage(tasks, arr[1]);
                }
            } else if (arr[0].equals("mark") || arr[0].equals("unmark") || arr[0].equals("delete")) {
                String[] array = next.split(" ");
                if (array.length != 2) {
                    ui.wrongMarkDeleteFormatMessage(arr[0]);
                } else {
                    int i = Integer.parseInt(array[1]);
                    int len = tasks.size();
                    if (i == 0 || i > len) {
                        ui.wrongIndexMessage(i, len);
                    } else {
                        if (arr[0].equals("delete")) {
                            ui.deleteTaskMessage(i, tasks);
                        } else {
                            Task t = tasks.get(i - 1);
                            t.changeStatus(arr[0]);
                            ui.changeStatusMessage(arr[0], t);
                        }
                    }
                }
            } else {
                Task t;
                if (arr[0].equals("todo")) {
                    if (arr.length == 1) {
                        ui.failedTaskCreationMessage("todo");
                        continue;
                    }
                    t = TodoTask.of(arr[1]);
                } else if (arr[0].equals("deadline")) {
                    String[] array = next.split("/");
                    if (array.length != 2) {
                        ui.failedTaskCreationMessage("deadline");
                        continue;
                    }
                    t = DeadlineTask.of(arr[1]);
                } else if (arr[0].equals("event")) {
                    String[] array = next.split("/");
                    if (array.length != 3) {
                        ui.failedTaskCreationMessage("event");
                        continue;
                    }
                    t = EventTask.of(arr[1]);
                } else {
                    ui.allInstructions();
                    continue;
                }
                tasks.add(t);
                ui.newTaskAddedMessage(tasks.size(), t);
            }
        }
    }
}