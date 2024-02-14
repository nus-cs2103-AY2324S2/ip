package main;

import Objects.Deadline;
import Objects.Event;
import Objects.Task;
import Objects.Todo;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Parser {
    Storage storage;

    public static void input(TaskList taskList) {
        Scanner sc = new Scanner(System.in);
        boolean isChanged = false;
        while (true) {
            if(isChanged) {
                try {
                    Storage.writeToFile(taskList);
                    isChanged = false;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            String str = sc.nextLine();
            String[] splitStr = str.split("\\s+",2);

            switch (splitStr[0].toLowerCase()) {
                case "bye":
                    Ui.bye();
                    return;
                case "list":
                    taskList.printList();
                    break;
                case "mark":
                    taskList.mark(Integer.parseInt(splitStr[1])-1);
                    isChanged = true;
                    break;
                case "unmark":
                    taskList.unmark(Integer.parseInt(splitStr[1])-1);
                    isChanged = true;
                    break;
                case "delete":
                    taskList.remove(Integer.parseInt(splitStr[1])-1);
                    isChanged = true;
                    break;
                case "todo":
                    if (splitStr.length == 2) {
                        Task todo1 = new Todo(splitStr[1],false);
                        taskList.getList().add(todo1);
                        isChanged = true;
                        Ui.success(todo1);
                    } else {
                        Ui.error();
                    }
                    break;

                case "deadline":
                    if (splitStr.length == 2) {
                        String[] splitStrDeadline = splitStr[1].split("/by", 2);
                        if (splitStrDeadline.length == 2) {
                            Task deadline1 = new Deadline(splitStrDeadline[0],false, LocalDate.parse(splitStrDeadline[1].trim()));
                            taskList.getList().add(deadline1);
                            isChanged = true;
                            Ui.success(deadline1);
                        } else {
                            Ui.deadlineError();
                        }
                    } else {
                        Ui.error();
                    }
                    break;
                case "event":
                    if (splitStr.length == 2) {
                        String[] splitStrEvent = splitStr[1].split("/from|/to");
                        if (splitStrEvent.length == 3) {
                            Task event1 = new Event(splitStrEvent[0],false, LocalDate.parse(splitStrEvent[1].trim()), LocalDate.parse(splitStrEvent[2].trim()));
                            taskList.getList().add(event1);
                            isChanged = true;
                            Ui.success(event1);
                        } else {
                            Ui.eventError();
                        }
                    } else {
                        Ui.error();
                    }
                    break;
                case "find":
                    if (splitStr.length == 2) {
                        taskList.printList(taskList.findList(splitStr[1]));
                    } else {
                        Ui.error();
                    }
                    break;

                default:
                    System.out.println("huh? what did you say?");
                    break;
            }
        }
    }
}
