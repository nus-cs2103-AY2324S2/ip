import java.util.Objects;
import java.util.Scanner;
import java.util.SimpleTimeZone;

public class Detective {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name = "Detective";
        String line = "____________________________________________________________";
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
                default: //add tasks to the myList
                    if (count >= 100) {
                        System.out.println(line + "\n" + "The list is full, add failed");
                        input = sc.nextLine();
                    } else {
                        switch (inputContent[0]) {
                            case "todo":
                                ToDo toDoTask = new ToDo(inputContent[1]);
                                myList[count++] = toDoTask;
                                System.out.println(line);
                                System.out.println("Got it. I've added this task:");
                                System.out.println(toDoTask);
                                System.out.println("Now you have " + count + " tasks in the list.");
                                System.out.println(line);
                                input = sc.nextLine();
                                break;
                            case "deadline":
                                String[] ddlInfo = inputContent[1].split(" /");
                                String ddlName = ddlInfo[0];
                                String ddlBy = ddlInfo[1].split("by ")[1];
                                Deadline deadlineTask = new Deadline(ddlName, ddlBy);
                                myList[count++] = deadlineTask;
                                System.out.println(line);
                                System.out.println("Got it. I've added this task:");
                                System.out.println(deadlineTask);
                                System.out.println("Now you have " + count + " tasks in the list.");
                                System.out.println(line);
                                input = sc.nextLine();
                                break;
                            case "event":
                                String[] evtInfo = inputContent[1].split(" /");
                                String evtName = evtInfo[0];
                                String evtFrom = evtInfo[1].split("from ")[1];
                                String evtTo = evtInfo[2].split("to ")[1];
                                Event eventTask = new Event(evtName, evtFrom, evtTo);
                                myList[count++] = eventTask;
                                System.out.println(line);
                                System.out.println("Got it. I've added this task:");
                                System.out.println(eventTask);
                                System.out.println("Now you have " + count + " tasks in the list.");
                                System.out.println(line);
                                input = sc.nextLine();
                                break;
                            default:
                                myList[count++] = new Task(input);
                                System.out.println(line + "\n" + "added: " + input + "\n" + line);
                                input = sc.nextLine();
                        }
                    }
            }
        }
        System.out.println(line + "\nBye. Hope to see you again soon!\n" + line);
    }
}