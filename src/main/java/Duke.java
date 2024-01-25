// @author Tan Qin Yong
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Greeting.print();
        String line = "------------------------------------";

        Scanner sc = new Scanner(System.in);

        // initialise tasks array
        TaskList taskList = new TaskList();
        boolean exit = false;

        while (!exit) {
            String fullCommand = sc.nextLine();
            String[] commandArr = fullCommand.split(" ");
            String command = commandArr[0].toLowerCase();

            switch (command) {
                case "bye": {
                    System.out.println("Goodbye! Till we meet again ~");
                    exit = true;
                    break;
                }
                case "list": {
                    System.out.println(line);
                    taskList.printAllTasks();
                    System.out.println(line);
                    break;
                }
                case "delete": {
                    System.out.println(line);

                    try {
                        int taskNo = Integer.parseInt(commandArr[1]);
                        taskList.deleteTask(taskNo);
                    } catch(NumberFormatException nfe) {
                        System.out.println("Oh dear! Please enter a number instead ^.^");
                        break;
                    } catch(NullPointerException | IndexOutOfBoundsException e) {
                        System.out.println("Oopsies! Please enter a valid task number ^.^");
                        break;
                    }

                    System.out.println(line);
                    break;
                }
                case "mark": {
                    System.out.println(line);
                    System.out.println("Great job agent 47. Marking this task as DONE: ");

                    try {
                        int taskNo = Integer.parseInt(commandArr[1]);
                        taskList.markDoneAtInd(taskNo);
                    } catch(NumberFormatException nfe) {
                        System.out.println("Oh dear! Please enter a number instead ^.^");
                        break;
                    } catch(NullPointerException | IndexOutOfBoundsException e) {
                        System.out.println("Oopsies! Please enter a valid task number ^.^");
                        break;
                    }

                    System.out.println(line);
                    break;
                }
                case "unmark": {
                    System.out.println(line);
                    System.out.println("Alright, marking this task as NOT DONE :( : ");

                    try {
                        int taskNo = Integer.parseInt(commandArr[1]);
                        taskList.markNotDoneAtInd(taskNo);
                    } catch(NumberFormatException nfe) {
                        System.out.println("Oh dear! Please enter a number instead ^.^");
                        break;
                    } catch(NullPointerException | IndexOutOfBoundsException e) {
                        System.out.println("Oopsies! Please enter a valid task number ^.^");
                        break;
                    }

                    System.out.println(line);
                    break;
                }
                case "deadline": {
                    System.out.println(line);

                    fullCommand = fullCommand.replace("deadline", "")
                                             .replace("/by", "/");
                    String[] splitCommand = fullCommand.split("/");

                    try {
                        String taskDescription = splitCommand[0].trim();
                        if (taskDescription.isEmpty()) {
                            System.out.println("Oh dear! The description of task cannot be empty!");
                            break;
                        }
                        String byDate = splitCommand[1].trim();
                        Deadline dl = new Deadline(taskDescription, byDate);
                        taskList.addTask(dl);
                    } catch (IndexOutOfBoundsException ie) {
                        System.out.println("OH NOES!! Do enter the deadline correctly with: /by [DEADLINE]. ");
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Uh oh. Something went wrong with your input!");
                        break;
                    }

                    System.out.println(line);
                    break;
                }
                case "event": {
                    System.out.println(line);

                    fullCommand = fullCommand.replace("event", "")
                                             .replace("/from", "/")
                                             .replace("/to", "/");
                    String[] splitCommand = fullCommand.split("/");

                    try {
                        String taskDescription = splitCommand[0].trim();
                        if (taskDescription.isEmpty()) {
                            System.out.println("Oh dear! The description of task cannot be empty!");
                            break;
                        }
                        String from = splitCommand[1].trim();
                        String to = splitCommand[2].trim();
                        Event event = new Event(taskDescription, from, to);
                        taskList.addTask(event);
                    } catch(IndexOutOfBoundsException ie) {
                        System.out.println("OH NOES!! Do enter the event dates correctly with: /from [start] /to [end]. ");
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Uh oh. Something went wrong with your input!");
                        break;
                    }

                    System.out.println(line);
                    break;
                }
                case "todo": {
                    System.out.println(line);

                    fullCommand = fullCommand.replace("todo", "").trim();

                    if (fullCommand.isEmpty()) {
                        System.out.println("Oh dear! The description of a todo cannot be empty!");
                        break;
                    }

                    ToDo toDo = new ToDo(fullCommand);
                    taskList.addTask(toDo);

                    System.out.println(line);
                    break;
                }
                default: {
                    System.out.println("Sorry, unknown command given. Please try again.");
                }
            }

        }


    }
}
