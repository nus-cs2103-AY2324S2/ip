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
            //Checking if command mark or unmark
            String[] inputParts = original.split("\\s+");
            if (inputParts.length == 2 && (inputParts[0].equals("mark")|| inputParts[0].equals("unmark"))) {
                int inputInt = Integer.parseInt(inputParts[1]);

                String output = "____________________________________________________________\n"
                        + list.get(inputInt - 1).toggle()
                        + "____________________________________________________________\n";
                System.out.println(output);
            } else {
                //handle "list" and add
                if (original.equals("list")) {
                    StringBuilder listOutput = new StringBuilder("____________________________________________________________\n"
                            + "Here are the tasks in your list:\n");
                    for (int i = 0; i < list.size(); i++) {
                        listOutput.append(i + 1).append(". ").append(list.get(i).getStatusIcon()).append(" ").append(list.get(i).getDescription()).append("\n");
                    }
                    listOutput.append("____________________________________________________________\n");
                    System.out.print(listOutput);
                } else {
                    String output = "____________________________________________________________\n"
                            + "added: "
                            + original
                            + "\n____________________________________________________________\n";
                    System.out.print(output);
                    list.add(new Task(original));
                }
            }
            original = sc.nextLine();
        }
        System.out.print(outro);
    }
}
