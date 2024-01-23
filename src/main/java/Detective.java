import java.util.Objects;
import java.util.Scanner;

public class Detective {
    static final String line = "____________________________________________________________";
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String name = "Detective";
        Task[] myList = new Task[100];
        int count = 0;

        System.out.println(line + "\nHello! I'm [" + name + "]" + "\nWhat can I do for you?\n" + line);
        String input = sc.nextLine();
        while (!Objects.equals(input, "bye")) {
            String[] inputContent = input.split(" ", 2);
            switch (inputContent[0]) {
                case "list":
                    System.out.println(line);
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < 100; i++) {
                        if (myList[i] == null) {
                            break;
                        }
                        System.out.println((i + 1) + "." + myList[i]);
                    }
                    System.out.println(line);
                    input = sc.nextLine();
                    break;
                case "mark":
                    int markTaskNum = Integer.parseInt(inputContent[1]) - 1;
                    myList[markTaskNum].markAsDone();
                    System.out.println(line);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(myList[markTaskNum]);
                    System.out.println(line);
                    input = sc.nextLine();
                    break;
                case "unmark":
                    int unmarkTaskNum = Integer.parseInt(inputContent[1]) - 1;
                    myList[unmarkTaskNum].markAsNotDone();
                    System.out.println(line);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(myList[unmarkTaskNum]);
                    System.out.println(line);
                    input = sc.nextLine();
                    break;
                default: //add tasks to myList
                    if (count >= 100) {
                        System.out.println(line);
                        System.out.println("The list is full, add failed");
                        System.out.println(line);
                        input = sc.nextLine();
                    } else {
                        switch (inputContent[0]) {
                            case "todo":
                                ToDo toDoTask;
                                try {
                                    toDoTask = new ToDo(inputContent[1]);
                                } catch (ArrayIndexOutOfBoundsException e) {
                                    handleEmptyTaskException("What do you want to do?");
                                    input = sc.nextLine();
                                    break;
                                }
                                myList[count++] = toDoTask;
                                System.out.println(line);
                                System.out.println("Got it. I've added this task:");
                                System.out.println(toDoTask);
                                System.out.println("Now you have " + count + " tasks in the list.");
                                System.out.println(line);
                                input = sc.nextLine();
                                break;
                            case "deadline":
                                Deadline deadlineTask;
                                try {
                                    String[] ddlInfo = inputContent[1].split(" /");
                                    String ddlName = ddlInfo[0];
                                    String ddlBy = ddlInfo[1].split("by ")[1];
                                    deadlineTask = new Deadline(ddlName, ddlBy);
                                } catch (ArrayIndexOutOfBoundsException e) {
                                    handleEmptyTaskException("What is the deadline?");
                                    input = sc.nextLine();
                                    break;
                                }
                                myList[count++] = deadlineTask;
                                System.out.println(line);
                                System.out.println("Got it. I've added this task:");
                                System.out.println(deadlineTask);
                                System.out.println("Now you have " + count + " tasks in the list.");
                                System.out.println(line);
                                input = sc.nextLine();
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
                                    handleEmptyTaskException("What is the event?");
                                    input = sc.nextLine();
                                    break;
                                }
                                myList[count++] = eventTask;
                                System.out.println(line);
                                System.out.println("Got it. I've added this task:");
                                System.out.println(eventTask);
                                System.out.println("Now you have " + count + " tasks in the list.");
                                System.out.println(line);
                                input = sc.nextLine();
                                break;
                            default:
                                System.out.println(line);
                                System.out.println("OOPS!!! Sorry, but I don't know what that means. qwq");
                                System.out.println(line);
                                input = sc.nextLine();
                        }
                    }
            }
        }
        System.out.println(line + "\nBye. Hope to see you again soon!\n" + line);
    }

    private static void handleEmptyTaskException(String message) {
        System.out.println(line);
        System.out.println("OOPS!!! " + message + " qwq");
        System.out.println(line);
    }
}