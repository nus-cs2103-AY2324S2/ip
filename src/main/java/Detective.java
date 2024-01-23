import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Detective {
    static final String line = "____________________________________________________________";
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String name = "Detective";
        ArrayList<Task> myList = new ArrayList<>();

        System.out.println(line + "\nHello! I'm [" + name + "]" + "\nWhat can I do for you?\n" + line);
        String input = sc.nextLine();
        while (!Objects.equals(input, "bye")) {
            String[] inputContent = input.split(" ", 2);
            switch (inputContent[0]) {
                case "list":
                    System.out.println(line);
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < myList.size(); i++) {
                        System.out.println((i + 1) + "." + myList.get(i));
                    }
                    System.out.println(line);
                    input = sc.nextLine();
                    break;
                case "mark":
                    int markTaskNum;
                    try {
                        markTaskNum = Integer.parseInt(inputContent[1]) - 1;
                        myList.get(markTaskNum).markAsDone();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        handleException("Please tell me mark which one!");
                        input = sc.nextLine();
                        break;
                    } catch (IndexOutOfBoundsException e) {
                        handleException("I haven't record this task!");
                        input = sc.nextLine();
                        break;
                    }
                    System.out.println(line);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(myList.get(markTaskNum));
                    System.out.println(line);
                    input = sc.nextLine();
                    break;
                case "unmark":
                    int unmarkTaskNum;
                    try {
                        unmarkTaskNum = Integer.parseInt(inputContent[1]) - 1;
                        myList.get(unmarkTaskNum).markAsNotDone();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        handleException("Please tell me unmark which one!");
                        input = sc.nextLine();
                        break;
                    } catch (IndexOutOfBoundsException e) {
                        handleException("I haven't record this task!");
                        input = sc.nextLine();
                        break;
                    }
                    System.out.println(line);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(myList.get(unmarkTaskNum));
                    System.out.println(line);
                    input = sc.nextLine();
                    break;
                case "delete":
                    int deleteTaskNum;
                    Task needDelete;
                    try {
                        deleteTaskNum = Integer.parseInt(inputContent[1]) - 1;
                        needDelete = myList.get(deleteTaskNum);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        handleException("Please tell me delete which one!");
                        input = sc.nextLine();
                        break;
                    } catch (IndexOutOfBoundsException e) {
                        handleException("I haven't record this task!");
                        input = sc.nextLine();
                        break;
                    }
                    myList.remove(deleteTaskNum);
                    System.out.println(line);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(needDelete);
                    System.out.println("Now you have " + myList.size() + " tasks in the list.");
                    System.out.println(line);
                    input = sc.nextLine();
                    break;
                default: //add tasks to myList
                    switch (inputContent[0]) {
                        case "todo":
                            ToDo toDoTask;
                            try {
                                toDoTask = new ToDo(inputContent[1]);
                            } catch (ArrayIndexOutOfBoundsException e) {
                                handleException("What do you want to do?");
                                input = sc.nextLine();
                                break;
                            }
                            myList.add(toDoTask);
                            System.out.println(line);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(toDoTask);
                            System.out.println("Now you have " + myList.size() + " tasks in the list.");
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
                                handleException("What is the deadline?");
                                input = sc.nextLine();
                                break;
                            }
                            myList.add(deadlineTask);
                            System.out.println(line);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(deadlineTask);
                            System.out.println("Now you have " + myList.size() + " tasks in the list.");
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
                                handleException("What is the event?");
                                input = sc.nextLine();
                                break;
                            }
                            myList.add(eventTask);
                            System.out.println(line);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(eventTask);
                            System.out.println("Now you have " + myList.size() + " tasks in the list.");
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
        System.out.println(line + "\nBye. Hope to see you again soon!\n" + line);
    }

    private static void handleException(String message) {
        System.out.println(line);
        System.out.println("OOPS!!! " + message + " qwq");
        System.out.println(line);
    }
}