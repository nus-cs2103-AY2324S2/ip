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
            return "  " + "[T] " + "[" + this.doneOrNot() + "] " + itemDescription;
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
            return "  " + "[D] " + "[" + this.doneOrNot() + "] " + itemDescription + "(by:" + dayTodoBy + ")";
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
            return "  " + "[E] " + "[" + this.doneOrNot() + "] " + itemDescription + "(from:" + startBy + "to:" + endBy + ")";
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
                Todo todoObject = new Todo(splitter[1]);
                toDoList[messageCount] = todoObject;
                messageCount += 1;
                System.out.println("Got it. I've added this task:");
                todoObject.Printer();
                System.out.println("Now you have " + messageCount + " tasks in the list.");
                userReply = inputTaker.nextLine();
            }  else if (userReply.contains("deadline")) {
                String[] firstSplitter = userReply.split(" ", 2);
                String[] seocndSplitter = firstSplitter[1].split("/by", 2);
                Deadline deadlineObject = new Deadline(seocndSplitter[0], seocndSplitter[1]);
                toDoList[messageCount] = deadlineObject;
                messageCount += 1;
                System.out.println("Got it. I've added this task:");
                deadlineObject.Printer();
                System.out.println("Now you have " + messageCount + " tasks in the list.");
            userReply = inputTaker.nextLine();
            } else if (userReply.contains("event")) {
                String[] firstSplitter = userReply.split(" ", 2);
                String[] secondSplitter = firstSplitter[1].split("/from", 2);
                String[] thirdSplitter = secondSplitter[1].split("/to", 2);
                Event eventObject = new Event(secondSplitter[0], thirdSplitter[0], thirdSplitter[1]);
                toDoList[messageCount] = eventObject;
                messageCount += 1;
                System.out.println("Got it. I've added this task:");
                eventObject.Printer();
                System.out.println("Now you have " + messageCount + " tasks in the list.");
                userReply = inputTaker.nextLine();
            } else if (userReply.contains("unmark")) {
                int toDoListIndex =  Integer.parseInt(userReply.substring(userReply.length() -1)) - 1;
                toDoList[toDoListIndex].todoStatus = false;
                System.out.println("OK! I've marked this task as not done yet:");
                System.out.println("[" + toDoList[toDoListIndex].doneOrNot() + "] " + toDoList[toDoListIndex].itemDescription);
                userReply = inputTaker.nextLine();
            }
            else if (userReply.contains("mark")) {
                int toDoListIndex =  Integer.parseInt(userReply.substring(userReply.length() -1)) - 1;
                toDoList[toDoListIndex].todoStatus = true;
                System.out.println("Nice! I've marked this task as done:");
                toDoList[toDoListIndex].Printer();
                userReply = inputTaker.nextLine();
            }

            else if (userReply.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < messageCount; i++) {
                    System.out.println(i + 1 + toDoList[i].stringPrinter());
                }
                userReply = inputTaker.nextLine();
            } else {
                Todo toDo = new Todo(userReply);
                toDoList[messageCount] = toDo;
                messageCount += 1;
                System.out.println("added: " + userReply);
                userReply = inputTaker.nextLine();
            }
        }
        if (userReply.equals("bye")) {
            System.out.print("Bye. Hope to see you again soon!");
        }
    }
}