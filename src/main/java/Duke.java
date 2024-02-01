import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> al = new ArrayList<>();

        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Emis!\n \tWhat can I do for you?");
        System.out.println("\tEmis is happy to help with printing a list of tasks with the command 'list'.");
        System.out.println("\tEmis is happy to add todos with the command 'todo (insert task here)'.");
        System.out.println("\tEmis is happy to add deadlines with the command 'deadline /by (insert deadline here)'.");
        System.out.println("\tEmis is happy to add events with the command 'event (insert event name) /from (insert start time) /to (insert end time)'.");
        System.out.println("\tEmis can mark tasks as done with the command 'mark (task no)'.");
        System.out.println("\tEmis can mark tasks as undone with the command 'unmark (task no)'.");
        System.out.println("\tTo stop talking to Emis, please say 'bye'.");


        // extract saved tasks from ./data/emis.txt
        String saved_Tasks = "./data/emis.txt";
        try {
            Scanner sT = new Scanner(new File(saved_Tasks));
            while (sT.hasNextLine()) {
                // read from file, add to arraylist al
                String input = sT.nextLine();
                String[] data = input.split("\\|");
                String taskType = data[0].trim();
                String retrieveStatus = data[1].trim();
                boolean convertStatus = retrieveStatus.equals("1");
                String retrieveDescription = data[2].trim();

                if (taskType.equals("T")) {
                    al.add(new ToDo(convertStatus, retrieveDescription));

                } else if (taskType.equals("D")) {
                    String finishBy = data[3].trim();
                    al.add(new Deadline(convertStatus, retrieveDescription, finishBy));

                } else if (taskType.equals("E")) {
                    String startFrom = data[3].trim();
                    String endBy = data[4].trim();
                    al.add(new Event(convertStatus, retrieveDescription, startFrom, endBy));

                } else {
                    System.out.println("wrong format! no such task type");
                }
            } 
            sT.close();

        } catch (FileNotFoundException FNFe) {
            // file does not exist yet, so create
            try {
                File emisTxt = new File(saved_Tasks);
                emisTxt.getParentFile().mkdirs();
                if (emisTxt.createNewFile()) {
                    System.out.println("File created");
                } else {
                    System.out.println("File already exists");
                }
            } catch (IOException IOe) {
                System.out.println("An error occurred while creating the file.");
            }
        } 
        /*
        catch () {
            // handle folder-does-not-exist-yet case
            // handle data file being corrupted (i.e. content not in expected format)
        }
        */

        // read user input
        while (sc.hasNextLine()) {
            String user_input = sc.nextLine();
            Integer spaceIndex = user_input.indexOf(" ");

            if (spaceIndex == -1) {
                if (user_input.equals("bye")) {
                    exit();

                } else if (user_input.equals("list")) {
                    print_list(al);
                } else {
                    System.out.println("Emis does not know what that means :(");
                }
            } else {
                
                String action = user_input.substring(0, user_input.indexOf(" "));
                try {
                    Integer taskNo = Integer.parseInt(user_input.substring(spaceIndex + 1));
                    if (taskNo <= 0 || taskNo > al.size()) {
                        System.out.println("Task does not exist! Please try again.");

                    } else {
                        // to mark as done or undone
                        Task t = al.get(taskNo - 1);
                
                        if (action.equals("mark")) {
                            t.markAsDone();
                            al.set(taskNo - 1, t);
                            updateTasks(al);

                        } else if (action.equals("unmark")) {
                            t.markAsUndone();
                            al.set(taskNo - 1, t);
                            updateTasks(al);
                        } else if (action.equals("delete")) {
                            deleteTask(al, taskNo - 1);
                            updateTasks(al);
                        }
                    }
                } catch (NumberFormatException nfe) {
                    String d = user_input.substring(spaceIndex + 1);
                    // check if invalid, eg empty after blank
                    if (d == null || d.isEmpty()) {
                        System.out.println("Emis is judging you for not stating what you want");
                    } else {
                        // todo, deadline, event
                        System.out.println("\t____________________________________________________________");
                        System.out.println("\tGot it. I've added this task:");
                        if (action.equals("todo")) {
                            ToDo td = new ToDo(d);
                            al.add(td);
                            updateTasks(al);
                            System.out.println("\t\t" + td.toString());

                        } else if (action.equals("deadline")) {
                            int slashIndex = d.indexOf("/by");
                            String des = d.substring(0, slashIndex);
                            String by = d.substring(slashIndex + 3);
                            Deadline dl = new Deadline(des, by);
                            al.add(dl);
                            updateTasks(al);
                            System.out.println("\t\t" + dl.toString());

                        } else if (action.equals("event")) {
                            int slashIndex_from = d.indexOf("/from");
                            int slashIndex_to = d.indexOf("/to");
                            String des = d.substring(0, slashIndex_from);
                            String from = d.substring(slashIndex_from + 5, slashIndex_to);
                            String to = d.substring(slashIndex_to + 3);
                            Event eve = new Event(des, from, to);
                            al.add(eve);
                            updateTasks(al);
                            System.out.println("\t\t" + eve.toString());

                        }
                        System.out.println("\tNow you have " + al.size() + " tasks in the list.");
                        System.out.println("\t____________________________________________________________");
                    }
                }
            }
        }
        sc.close();
    }

    public static void exit() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
        System.exit(0);
    }

    public static void print_list(ArrayList<Task> al) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < al.size(); i++) {
            System.out.println("\t\t" + (i + 1) + "." + al.get(i).toString());
        }
        System.out.println("\t____________________________________________________________");
    }

    public static void deleteTask(ArrayList<Task> al, int taskNo) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t\t" + al.get(taskNo).toString());
        al.remove(taskNo);
        System.out.println("\tNow you have " + al.size() + " tasks in the list.");
        System.out.println("\t____________________________________________________________");
    }

    public static void updateTasks(ArrayList<Task>al) {
        try {
            FileWriter fw = new FileWriter("./data/emis.txt");
            String s = "";
            for (int i = 0; i < al.size(); i++) {
                s += al.get(i).storeString();
                s += "\n";
            }
            fw.write(s);
            fw.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException IOe) {
            System.out.println("Error occurred while writing to the file.");
            IOe.printStackTrace();
        }
    }

    public static class Task {
        protected String description;
        protected boolean isDone;
    
        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public Task(boolean isDone, String description) {
            this.description = description;
            this.isDone = isDone;
        }
    
        public String getStatusIcon() {
            return (isDone ? "X" : " ");
        }
    
        public void markAsDone() {
            this.isDone = true;
            System.out.println("\t____________________________________________________________");
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t\t[" + this.getStatusIcon() + "] " + this.description);
            System.out.println("\t____________________________________________________________");
        }
    
        public void markAsUndone() {
            this.isDone = false;
            System.out.println("\t____________________________________________________________");
            System.out.println("\tOK, I've marked this task as not done yet:");
            System.out.println("\t\t[" + this.getStatusIcon() + "] " + this.description);
            System.out.println("\t____________________________________________________________");
        }

        public String storeString() {
            // done = 1, not done = 0
            int status = isDone ? 1 : 0;
            return status + " | " + this.description;
        }
    
        @Override
        public String toString() {
            return "[" + this.getStatusIcon() + "] " + this.description;
        }
    }

    // Deadline class
    public static class Deadline extends Task {
        protected String by;
    
        public Deadline(String description, String by) {
            super(description);
            this.by = by;
        }

        public Deadline(boolean isDone, String description, String by) {
            super(isDone, description);
            this.by = by;
        }

        @Override
        public String storeString() {
            return "D | " + super.storeString() + " | " + this.by;
        }
    
        @Override
        public String toString() {
            return"[D]" + super.toString() + " (by: " + this.by + ")";
        }
    }

    // ToDo class
    public static class ToDo extends Task {
        public ToDo(String description) {
            super(description);
        }

        public ToDo(boolean isDone, String description) {
            super(isDone, description);
        }

        @Override
        public String storeString() {
            return "T | " + super.storeString();
        }
    
        @Override
        public String toString() {
            return"[T]" + super.toString();
        }
    }

    public static class Event extends Task {
        protected String from;
        protected String to;
    
        public Event(String description, String from, String to) {
            super(description);
            this.from = from;
            this.to = to;
        }

        public Event(boolean isDone, String description, String from, String to) {
            super(isDone, description);
            this.from = from;
            this.to = to;
        }

        @Override
        public String storeString() {
            return "E | " + super.storeString() + " | " + this.from + " | " + this.to;
        }
    
        @Override
        public String toString() {
            return"[E]" + super.toString() + "(from: " + this.from + " to: " + this.to + ")";
        }
    }
}
