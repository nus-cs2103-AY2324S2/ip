import java.util.*;
public class    Duke {
    public static void main(String[] args) {
        String intro = "____________________________________________________________\n"
                + "        Hello! I'm sibehupzcoder9000\n"
                + "        What you want sia\n"
                + "____________________________________________________________\n";
        String outro = "____________________________________________________________\n"
                + "        wow so ur gg to leave me...\n"
                + "____________________________________________________________\n";

        Scanner sc= new Scanner(System.in);
        System.out.println(intro);

        String original = sc.nextLine();
        ArrayList<Task> list = new ArrayList<>();

        while (!original.equals("bye")) {

            String[] inputParts = original.split("\\s+");

            //handle mark || unmark
            if (inputParts.length == 2 && (inputParts[0].equals("mark")|| inputParts[0].equals("unmark"))) {
                int inputInt = Integer.parseInt(inputParts[1]);

                String output = "____________________________________________________________\n"
                        + list.get(inputInt - 1).toggle()
                        + "____________________________________________________________\n";
                System.out.println(output);
            } else if (original.equals("list")) {
                //handle "list"
                StringBuilder listOutput = new StringBuilder("____________________________________________________________\n"
                        + "Here are the tasks in your list:\n");
                for (int i = 0; i < list.size(); i++) {
                    listOutput.append(i + 1).append(". ").append(list.get(i).toString()).append("\n");
                }
                listOutput.append("____________________________________________________________\n");
                System.out.print(listOutput);
            } else if (inputParts[0].equals("todo")) {
                //handle "todoo"
                Task task = new ToDo(original.replace("todo ", ""));
                list.add(task);
                String output = "____________________________________________________________\n"
                        + "Got it. I've added this task:\n"
                        + task
                        + "\n Now you have " + (list.size()) + " tasks in the list.\n"
                        + "____________________________________________________________\n";
                System.out.print(output);
            } else if (inputParts[0].equals("deadline")) {
                //handle "deadline"
                String[] parts = original.replace("deadline ", "").split(" /");
                Task task = new Deadline(parts[0], parts[1].replace("by ", ""));
                list.add(task);
                String output = "____________________________________________________________\n"
                        + "Got it. I've added this task:\n"
                        + task
                        + "\n Now you have " + (list.size()) + " tasks in the list.\n"
                        + "____________________________________________________________\n";
                System.out.print(output);
            } else if (inputParts[0].equals("event")) {
                //handle event
                String[] parts = original.replace("event ", "").split(" /");
                Task task = new Event(parts[0], parts[1].replace("from ", ""), parts[2].replace("to ", ""));
                list.add(task);
                String output = "____________________________________________________________\n"
                        + "Got it. I've added this task:\n"
                        + task
                        + "\n Now you have " + (list.size()) + " tasks in the list.\n"
                        + "____________________________________________________________\n";
                System.out.print(output);
            }
            original = sc.nextLine();
        }
        System.out.print(outro);
    }
}
