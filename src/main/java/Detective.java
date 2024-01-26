import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Detective {
    static final String line = "____________________________________________________________";
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String name = "Detective";
        boolean isContinue = true;
        ArrayList<Task> myList = new ArrayList<>();
        String currentDir = System.getProperty("user.dir");
        Path currentPath = Paths.get(currentDir);
        Path dataPath = currentPath.resolve("data");
        Path dataFile = dataPath.resolve("data.txt");

        try {
            Files.createDirectories(dataPath);
            Files.createFile(dataFile);
            printMessage("The data folder and data.txt has been created. >v<");
        } catch (IOException e1) {
            try {
                myList = deserializeTask(Files.readAllLines(dataFile));
            } catch (IOException e2) {
                handleException("Read failed!");
            }
            printMessage("The data has been read. >v<");
        }

        printMessage("Hello! I'm [" + name + "]", "What can I do for you?");

        while (isContinue) {
            String input = sc.nextLine();
            String[] inputContent = input.split(" ", 2);
            switch (inputContent[0]) {
                case "bye":
                    try {
                        Files.write(dataFile, serializeTask(myList));
                    } catch (IOException e) {
                        handleException("Write failed!");
                    }
                    printMessage("Bye. Hope to see you again soon!");
                    isContinue = false;
                    break;
                case "list":
                    System.out.println(line);
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < myList.size(); i++) {
                        System.out.println((i + 1) + "." + myList.get(i));
                    }
                    System.out.println(line);
                    break;
                case "mark":
                    int markTaskNum;
                    try {
                        markTaskNum = Integer.parseInt(inputContent[1]) - 1;
                        myList.get(markTaskNum).markAsDone();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        handleException("Please tell me mark which one!");
                        break;
                    } catch (IndexOutOfBoundsException e) {
                        handleException("I haven't record this task!");
                        break;
                    }
                    printMessage("Nice! I've marked this task as done:",
                            myList.get(markTaskNum).toString());
                    break;
                case "unmark":
                    int unmarkTaskNum;
                    try {
                        unmarkTaskNum = Integer.parseInt(inputContent[1]) - 1;
                        myList.get(unmarkTaskNum).markAsNotDone();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        handleException("Please tell me unmark which one!");
                        break;
                    } catch (IndexOutOfBoundsException e) {
                        handleException("I haven't record this task!");
                        break;
                    }
                    printMessage("OK, I've marked this task as not done yet:",
                            myList.get(unmarkTaskNum).toString());
                    break;
                case "delete":
                    int deleteTaskNum;
                    Task needDelete;
                    try {
                        deleteTaskNum = Integer.parseInt(inputContent[1]) - 1;
                        needDelete = myList.get(deleteTaskNum);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        handleException("Please tell me delete which one!");
                        break;
                    } catch (IndexOutOfBoundsException e) {
                        handleException("I haven't record this task!");
                        break;
                    }
                    myList.remove(deleteTaskNum);
                    printMessage("Noted. I've removed this task:",
                            needDelete.toString(),
                            "Now you have " + myList.size() + " tasks in the list.");
                    break;
                default: //add tasks to myList
                    switch (inputContent[0]) {
                        case "todo":
                            ToDo toDoTask;
                            try {
                                toDoTask = new ToDo(inputContent[1]);
                            } catch (ArrayIndexOutOfBoundsException e) {
                                handleException("What do you want to do?");
                                break;
                            }
                            myList.add(toDoTask);
                            printMessage("Got it. I've added this task:",
                                    toDoTask.toString(),
                                    "Now you have " + myList.size() + " tasks in the list.");
                            break;
                        case "deadline":
                            Deadline deadlineTask;
                            try {
                                String[] ddlInfo = inputContent[1].split(" /");
                                String ddlName = ddlInfo[0];
                                String ddlBy = ddlInfo[1].split("by ")[1];
                                deadlineTask = new Deadline(ddlName, ddlBy);
                            } catch (ArrayIndexOutOfBoundsException e) {
                                handleException("What is the deadline?");
                                break;
                            }
                            myList.add(deadlineTask);
                            printMessage("Got it. I've added this task:",
                                    deadlineTask.toString(),
                                    "Now you have " + myList.size() + " tasks in the list.");
                            break;
                        case "event":
                            Event eventTask;
                            try {
                                String[] evtInfo = inputContent[1].split(" /");
                                String evtName = evtInfo[0];
                                String evtFrom = evtInfo[1].split("from ")[1];
                                String evtTo = evtInfo[2].split("to ")[1];
                                eventTask = new Event(evtName, evtFrom, evtTo);
                            } catch (ArrayIndexOutOfBoundsException e) {
                                handleException("What is the event?");
                                break;
                            }
                            myList.add(eventTask);
                            printMessage("Got it. I've added this task:",
                                    eventTask.toString(),
                                    "Now you have " + myList.size() + " tasks in the list.");
                            break;
                        default:
                            printMessage("OOPS!!! Sorry, but I don't know what that means. qwq");
                    }

            }
        }
    }

    private static void handleException(String message) {
        printMessage("OOPS!!! " + message + " qwq");
    }

    private static void printMessage(String... messages) {
        System.out.println(line);
        for (String message: messages) {
            System.out.println(message);
        }
        System.out.println(line);
    }

    private static List<String> serializeTask(List<Task> tasks) {
        List<String> serializedTask = new ArrayList<>();
        for (Task task : tasks) {
            serializedTask.add(task.toString()); // 这里假设 Task 类已经正确实现了 toString 方法
        }
        return serializedTask;
    }

    private static ArrayList<Task> deserializeTask(List<String> lines) {
        ArrayList<Task> task = new ArrayList<>();
        for (String line : lines) {
            task.add(Task.stringToTask(line));
        }
        return task;
    }

}