import java.util.List;
import java.util.Scanner;

public class gops {
    public static class Todo {
        protected String itemDescription;
        protected boolean todoStatus = false;
        public Todo(String itemDescription) {
            this.itemDescription = itemDescription;
        }
        public String doneOrNot() {
            if (todoStatus) {
                return "X";
            } else {
                return " ";
            }
        }
        public void Printer() {
            System.out.println("  " + "[T] " + "[" + this.doneOrNot() + "] " + itemDescription);
        }
        public String stringPrinter() {
            return " " + "[T] " + "[" + this.doneOrNot() + "] " + itemDescription;
        }
    }

    public static class Deadline extends Todo {
        protected String todoDescription;
        protected boolean todoStatus = false;
        protected String dayTodoBy;
        public Deadline(String todoDescription, String dayTodoBy) {
            super(todoDescription);
            this.dayTodoBy = dayTodoBy;
        }
        @Override
        public void Printer() {
            System.out.println("  " + "[D] " + "[" + this.doneOrNot() + "] " + itemDescription + "(by:" + dayTodoBy + ")");
        }
        @Override
        public String stringPrinter() {
            return " " + "[D] " + "[" + this.doneOrNot() + "] " + itemDescription + "(by:" + dayTodoBy + ")";
        }

    }

    public static class Event extends Todo {
        protected String todoDescription;
        protected boolean todoStatus = false;
        protected String startBy;
        protected String endBy;
        public Event(String todoDescription, String startBy, String endBy) {
            super(todoDescription);
            this.startBy = startBy;
            this.endBy  = endBy;
        }
        @Override
        public void Printer() {
            System.out.println("  " + "[E] " + "[" + this.doneOrNot() + "] " + itemDescription + "(from:" + startBy + "to:" + endBy + ")");
        }

        @Override
        public String stringPrinter() {
            return " " + "[E] " + "[" + this.doneOrNot() + "] " + itemDescription + "(from:" + startBy + "to:" + endBy + ")";
        }

    }


    public static void main(String[] args) {
        Scanner inputTaker = new Scanner(System.in);
        Todo[] toDoList = new Todo[100];
        int messageCount = 0;

        System.out.println("Hello! I'm gops");
        System.out.println("What can I do for you?");

        String userReply = inputTaker.nextLine();
        while (userReply != null && !userReply.equals("bye")) {
            if (userReply.contains("todo")) {
                String[] splitter = userReply.split(" ", 2);
                try {
                    if (splitter.length == 1) {
                        throw new gopsException();
                    }
                    Todo todoObject = new Todo(splitter[1]);
                    toDoList[messageCount] = todoObject;
                    messageCount += 1;
                    System.out.println("Got it. I've added this task:");
                    todoObject.Printer();
                    System.out.println("Now you have " + messageCount + " tasks in the list.");
                    userReply = inputTaker.nextLine();
                } catch (gopsException e) {
                    System.out.println("Please follow the format for setting todo\ntodo [your-task-here]");
                    userReply = inputTaker.nextLine();
                }
            }  else if (userReply.contains("deadline")) {
                String[] firstSplitter = userReply.split(" ", 2);
                try {
                    if (firstSplitter.length == 1) {
                        throw new gopsException();
                    }
                    String[] seocndSplitter = firstSplitter[1].split("/by", 2);
                    Deadline deadlineObject = new Deadline(seocndSplitter[0], seocndSplitter[1]);
                    toDoList[messageCount] = deadlineObject;
                    messageCount += 1;
                    System.out.println("Got it. I've added this task:");
                    deadlineObject.Printer();
                    System.out.println("Now you have " + messageCount + " tasks in the list.");
                    userReply = inputTaker.nextLine();
                } catch (gopsException e) {
                    System.out.println("Please follow the format for setting deadlines\ndeadline [your-task-here] /by [deadline-of-your-task]");
                    userReply = inputTaker.nextLine();
                }
            } else if (userReply.contains("event")) {
                String[] firstSplitter = userReply.split(" ", 2);
                try {
                    if (firstSplitter.length == 1) {
                        throw new gopsException();
                    }
                    String[] secondSplitter = firstSplitter[1].split("/from", 2);
                    String[] thirdSplitter = secondSplitter[1].split("/to", 2);
                    Event eventObject = new Event(secondSplitter[0], thirdSplitter[0], thirdSplitter[1]);
                    toDoList[messageCount] = eventObject;
                    messageCount += 1;
                    System.out.println("Got it. I've added this task:");
                    eventObject.Printer();
                    System.out.println("Now you have " + messageCount + " tasks in the list.");
                    userReply = inputTaker.nextLine();
                } catch (gopsException e) {
                    System.out.println("Please follow the format for setting events\nevent [your-task-here] /from [start-time-of-your-task] /by [end-time-of-your-task]");
                    userReply = inputTaker.nextLine();
                }
            } else if (userReply.contains("unmark")) {
                try {
                    if (userReply.length() == 6) {
                        throw new gopsException();
                    }
                    int toDoListIndex = Integer.parseInt(userReply.substring(userReply.length() - 1)) - 1;
                    toDoList[toDoListIndex].todoStatus = false;
                    System.out.println("OK! I've marked this task as not done yet:");
                    System.out.println("[" + toDoList[toDoListIndex].doneOrNot() + "] " + toDoList[toDoListIndex].itemDescription);
                    userReply = inputTaker.nextLine();
                } catch (gopsException e) {
                    System.out.println("Please follow the format for unmarking tasks\nunmark [task-number]");
                    userReply = inputTaker.nextLine();
                }
            }
            else if (userReply.contains("mark")) {
                try {
                    if (userReply.length() == 4) {
                        throw new gopsException();
                    }
                    int toDoListIndex =  Integer.parseInt(userReply.substring(userReply.length() -1)) - 1;
                    toDoList[toDoListIndex].todoStatus = true;
                    System.out.println("Nice! I've marked this task as done:");
                    toDoList[toDoListIndex].Printer();
                    userReply = inputTaker.nextLine();
                } catch (gopsException e) {
                    System.out.println("Please follow the format for marking tasks as done\nmark [task-number]");
                    userReply = inputTaker.nextLine();
                }
            }

            else if (userReply.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < messageCount; i++) {
                    System.out.println(i + 1 + toDoList[i].stringPrinter());
                }
                userReply = inputTaker.nextLine();
            } else if (userReply.contains("delete")) {
                try {
                    if (userReply.length() == 6) {
                        throw new gopsException();
                    }
                    String[] splitter = userReply.split(" ", 2);
                    int listIndex = Integer.parseInt(splitter[1]);
                    Todo deletedTask = toDoList[listIndex - 1];
                    toDoList[listIndex - 1] = null;
                    for (int i = listIndex; i < messageCount; i++) {
                        toDoList[i - 1] = toDoList[i];
                    }
                    System.out.println("I've deleted this task");
                    System.out.println(" " + deletedTask.stringPrinter());
                    messageCount -= 1;
                    System.out.println("Here are the remaining " + messageCount + " task/s in your list:");
                    for (int i = 0; i < messageCount; i++) {
                        System.out.println(i + 1 + toDoList[i].stringPrinter());
                    }
                    userReply = inputTaker.nextLine();
                } catch (gopsException e) {
                    System.out.println("Please follow the format for deleting tasks\ndelete [task-number]");
                    userReply = inputTaker.nextLine();
                }
            } else {
                System.out.println("Please choose from the available prompts\n[todo/deadline/event/mark/unmark/list/bye]");
                userReply = inputTaker.nextLine();
            }
        }
        if (userReply.equals("bye")) {
            System.out.print("Bye. Hope to see you again soon!");
        }
    }
}