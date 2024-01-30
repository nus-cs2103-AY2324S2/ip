import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.io.*;
public class Duke {
    public static class Task {
        String action;
        Boolean isDone;



        public Task(String action, Boolean isDone) {
            this.action = action;
            this.isDone = isDone;
        }
        @Override
        public String toString() {
            if (isDone) {
                return "[X]" + action;
            } else {
                return "[ ]" + action;
            }
        }
        public void mark() {
            isDone = true;
        }
        public void unmark() {
            isDone = false;
        }

        public String
        export() {
            return toString();
        }
    }
    public static class Event extends Task {
        String from;
        String to;
        public Event(String input, boolean isDone, String from, String to) {
            super(input, isDone);
            this.from = from;
            this.to = to;
        }
        @Override
        public String toString() {
            String s = super.toString();
            return "[E]" + s + "(from" + from + "to" + to + ")";
        }
        @Override
        public String export() {
            String s = super.toString();
            return "[E]" + s + "/from" + from + "/to" + to ;
        }
    }
    public static class Todo extends Task {
        public Todo(String input, boolean isDone) {
            super(input, isDone);
        }
        @Override
        public String toString() {
            String s = super.toString();
            return "[T]" + s;
        }
        @Override
        public String export() {
            return toString();
        }
    }
    public static class Deadline extends Task {
        String by;
        public Deadline(String input, boolean isDone, String by) {
            super(input, isDone);
            this.by = by;
        }
        @Override
        public String toString() {
            String s = super.toString();
            return "[D]" + s + "(by:" + by +")";
        }

        @Override
        public String export() {
            String s = super.toString();
            return "[D]" + s + "/by" + by ;
        }

    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Task> List = new ArrayList<>();
        int length;
        System.out.println("Hello! I'm Dukey.");
	    System.out.println("What can I do for you?");

        String input ;
        String mark = "mark (\\d+)";
        String unmark = "unmark (\\d+)";
        String delete = "delete (\\d+)";
        String todo = "todo";
        String deadline = "deadline";
        String event =  "event";
        String by = "/by";
        String from = "/from";
        String to = "/to";
        String todo2 = "[T]";
        String deadline2 = "[D]";
        String event2 = "[E]";
        String unmarked = "[ ]";
        String marked = "[X]";
        Pattern pMark = Pattern.compile(mark);
        Pattern pUnmark = Pattern.compile(unmark);
        Pattern pTodo = Pattern.compile(todo);
        Pattern pDeadline = Pattern.compile(deadline);
        Pattern pEvent = Pattern.compile(event);
        Pattern pBy = Pattern.compile(by);
        Pattern pFrom = Pattern.compile(from);
        Pattern pTo = Pattern.compile(to);
        Pattern pDelete = Pattern.compile(delete);
        Pattern pTodo2 = Pattern.compile(todo2);
        Pattern pDeadline2 = Pattern.compile(deadline2);
        Pattern pEvent2 = Pattern.compile(event2);
        Pattern pUnmarked = Pattern.compile(unmarked);
        Pattern pMarked = Pattern.compile(marked);

        String folderName = "data";
        String fileName = "data.txt";
        File folder = new File(folderName);
        if (!folder.exists()) {
            folder.mkdir();
        }
        File file = new File(folder, fileName);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String currentLine;
                int currLine = 0;
                while ((currentLine = reader.readLine()) != null) {
                    Matcher mTodo2 = pTodo2.matcher(currentLine);
                    Matcher mEvent2 = pEvent2.matcher(currentLine);
                    Matcher mDeadline2 = pDeadline2.matcher(currentLine);
                    Matcher mUnmarked = pUnmarked.matcher(currentLine);
                    Matcher mMarked = pMarked.matcher(currentLine);
                    if (mTodo2.find()) {
                        if (mMarked.find()) {
                            Todo n = new Todo(currentLine.substring(7), true );
                            n.mark();
                            List.add(n);
                        } else if (mUnmarked.find()){
                            Todo n = new Todo(currentLine.substring(7), false);
                            n.unmark();
                            List.add(n);
                        }
                    } else if (mDeadline2.find()) {
                        int finalIndex = currentLine.indexOf(by) + by.length();
                        String dL = currentLine.substring(finalIndex);

                        String newInput = currentLine.substring(currentLine.indexOf(deadline) + deadline.length(), currentLine.indexOf(by));

                        if (mMarked.find()) {
                            Deadline n = new Deadline(newInput, true, dL);
                            n.mark();
                            List.add(n);
                        } else if (mUnmarked.find()){
                            Deadline n = new Deadline(newInput, false, dL);
                            n.unmark();
                            List.add(n);
                        }

                    } else if (mEvent2.find()) {
                        int startIndex = currentLine.indexOf(from);
                        int startIndexTo = currentLine.indexOf(to);
                        String subFrom = currentLine.substring(startIndex + from.length(), startIndexTo);

                        String subTo = currentLine.substring(startIndexTo + to.length());

                        String newInput = currentLine.substring(currentLine.indexOf(event) + event.length() + 3, startIndex);

                        if (mMarked.find()) {
                            Event n = new Event(newInput, true, subFrom, subTo);
                            n.mark();
                            List.add(n);
                        } else if (mUnmarked.find()){
                            Event n = new Event(newInput, false, subFrom, subTo);
                            n.unmark();
                            List.add(n);
                        }

                    }
                    currLine = currLine + 1;
                }
                length = currLine;
            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
                return;
            }
        } else {
            length = 0;
        }


        while (true) {
            input = scanner.nextLine();
            Matcher mMark = pMark.matcher(input);
            Matcher mUnmark = pUnmark.matcher(input);
            Matcher mTodo = pTodo.matcher(input);
            Matcher mDeadline = pDeadline.matcher(input);
            Matcher mEvent = pEvent.matcher(input);
            Matcher mBy = pBy.matcher(input);
            Matcher mFrom = pFrom.matcher(input);
            Matcher mTo = pTo.matcher(input);
            Matcher mDelete = pDelete.matcher(input);
            if (input.equals("reset")) {
                List.clear();
                length = 0;
                try (PrintWriter writer = new PrintWriter(file)) {
                    writer.print(""); // This clears all lines in the file
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                System.out.println("List cleared!");

            } else if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                if (length == 0) {
                    System.out.println("You have no tasks in your list!");
                } else {
                    System.out.println("Here are your tasks in your list:");
                    for (int x = 0; x < length; x++) {
                        Task item = List.get(x);
                        int numeric = x + 1;
                        System.out.println(numeric + "." + item.toString());
                    }
                }
            } else if (mDelete.find()) {
                String captured = mDelete.group(1);
                int number = Integer.parseInt(captured);
                if (number > 0 && number < length + 1 ) {
                    Task t = List.get(number - 1);
                    List.remove(t);
                    length = length - 1;
                    System.out.println("OK! I have deleted this task:");
                    System.out.println(t);

                    int lineToDelete = number - 1;

                    // Read the content of the file
                    ArrayList<String> lines = new ArrayList<>();

                    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        String currentLine;
                        int currLine = 0;
                        while ((currentLine = reader.readLine()) != null) {
                            // Check if the line should be deleted
                            if (currLine != lineToDelete) {
                                lines.add(currentLine);

                            }
                            currLine = currLine + 1;
                        }
                    } catch (IOException e) {
                        System.err.println("Error reading file: " + e.getMessage());
                        return;
                    }

                    // Write the modified content back to the file
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                        for (String modifiedLine : lines) {
                            writer.write(modifiedLine);
                            writer.newLine(); // Add a newline character after each line
                        }
                    } catch (IOException e) {
                        System.err.println("Error writing to file: " + e.getMessage());
                    }
                } else {
                    System.out.println("Please input a valid number.");
                }

            } else if (mUnmark.find()) {
                String captured = mUnmark.group(1);
                int number = Integer.parseInt(captured);
                Task t;
                if (number > 0 && number < length + 1) {
                    t = List.get(number - 1);
                    t.unmark();
                    System.out.println("Oh no! I have marked this as not done:");
                    System.out.println(t);
                    int lineToUnmark = number - 1;

                    // Read the content of the file
                    ArrayList<String> lines = new ArrayList<>();

                    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        String currentLine;
                        int currLine = 0;
                        while ((currentLine = reader.readLine()) != null) {
                            // Check if the line should be deleted
                            if (currLine == lineToUnmark) {
                               currentLine=t.export();

                            }
                            lines.add(currentLine);
                            currLine = currLine + 1;
                        }
                    } catch (IOException e) {
                        System.err.println("Error reading file: " + e.getMessage());
                        return;
                    }

                    // Write the modified content back to the file
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                        for (String modifiedLine : lines) {
                            writer.write(modifiedLine);
                            writer.newLine(); // Add a newline character after each line
                        }
                    } catch (IOException e) {
                        System.err.println("Error writing to file: " + e.getMessage());
                    }
                } else {
                    System.out.println("Please input a valid number.");
                }

            } else if (mMark.find()) {
                String captured = mMark.group(1);
                int number = Integer.parseInt(captured);
                Task t;
                if (number > 0 && number < length + 1) {
                    t = List.get(number -1);
                    t.mark();
                    System.out.println("Nice! I have marked this as done:");
                    System.out.println(t);
                    int lineToMark = number - 1;

                    // Read the content of the file
                    ArrayList<String> lines = new ArrayList<>();

                    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        String currentLine;
                        int currLine = 0;
                        while ((currentLine = reader.readLine()) != null) {
                            // Check if the line should be deleted
                            if (currLine == lineToMark) {
                                currentLine = t.export();
                            }
                            lines.add(currentLine);
                            currLine = currLine + 1;
                        }
                    } catch (IOException e) {
                        System.err.println("Error reading file: " + e.getMessage());
                        return;
                    }

                    // Write the modified content back to the file
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                        for (String modifiedLine : lines) {
                            writer.write(modifiedLine);
                            writer.newLine(); // Add a newline character after each line
                        }
                    } catch (IOException e) {
                        System.err.println("Error writing to file: " + e.getMessage());
                    }

                } else {
                    System.out.println("Please input a valid number.");
                }
            } else if (mTodo.find()) {
                String newInput = input.replace(todo, "");
                Todo n = new Todo(newInput, false);
                if (newInput.trim().equals("")) {
                    System.out.println("Task cannot be empty!");
                } else {

                    List.add(n);
                    length = length + 1;
                    System.out.println("OK, I have added this task :");
                    System.out.println(n);
                    System.out.println("You now have " + length + " items in the list.");
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                        writer.write(n.export());
                        writer.newLine();
                    } catch (IOException e) {
                        System.err.println("Error writing to file: " + e.getMessage());
                    }
                }
            } else if (mEvent.find()) {
                if (mFrom.find() && mTo.find()) {
                    int startIndex = input.indexOf(from);
                    int startIndexTo = input.indexOf(to);
                    String subFrom = input.substring(startIndex + from.length(), startIndexTo);
                    String subTo = input.substring(startIndexTo + to.length());
                    String newInput = input.substring(input.indexOf(event) + event.length(), startIndex);
                    if (newInput.trim().equals("")) {
                        System.out.println("Task cannot be empty!");
                    } else {
                        Event n = new Event(newInput, false, subFrom, subTo);
                        List.add(n);
                        length = length + 1;
                        System.out.println("OK, I have added this task :");
                        System.out.println(n);
                        System.out.println("You now have " + length + " items in the list.");
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                            writer.write(n.export());
                            writer.newLine();
                        } catch (IOException e) {
                            System.err.println("Error writing to file: " + e.getMessage());
                        }
                    }
                } else {
                    System.out.println("pls input your start and end of the event.");
                }

            } else if (mDeadline.find()) {
                if (mBy.find()) {
                    int finalIndex = input.indexOf(by) + by.length();
                    String dL = input.substring(finalIndex);
                    String newInput = input.substring(input.indexOf(deadline) + deadline.length(), input.indexOf(by));
                    if (newInput.trim().equals("")) {
                        System.out.println("Task cannot be empty!");
                    } else {
                        Deadline n = new Deadline(newInput, false, dL);
                        List.add(n);
                        length = length + 1;
                        System.out.println("OK, I have added this task :");
                        System.out.println(n);
                        System.out.println("You now have " + length + " items in the list.");
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                            writer.write(n.export());
                            writer.newLine();
                        } catch (IOException e) {
                            System.err.println("Error writing to file: " + e.getMessage());
                        }
                    }
                } else {
                    System.out.println("please include a deadline");
                }

            } else {
                System.out.println("Sorry, no idea what u talking about lulz");
            }

        }

    }
}
