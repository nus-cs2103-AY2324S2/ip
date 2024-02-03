import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // String logo = " ____        _        \n"
        //         + "|  _ \\ _   _| | _____ \n"
        //         + "| | | | | | | |/ / _ \\\n"
        //         + "| |_| | |_| |   <  __/\n"
        //         + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);
        String indent = "    ";
        String newLine = indent + "____________________________________________________________ \n";
        String greeting = indent + "Good day good sir! I am Chatimous Maximous, here to help you with your every need!\n";
        System.out.println(newLine + greeting);

        Scanner reader = new Scanner(System.in);
        String input = reader.nextLine();
        TaskList taskList = new TaskList();
        int idx;

        // if (input == "bye") {
        //     String goodbye = indent + "It has been a pleasure! I do hope to see you again!\n";
        //     System.out.println(new_line + goodbye + new_line);
        //     reader.close();
        // } else {
        //     System.out.println(new_line + indent + input + "\n" + new_line);
         
        // }
         
        while (input.contains("bye") == false) {
            if (input.equals("list")) {
                System.out.println(newLine + taskList.showList() + newLine);
            } else if (input.contains("unmark")){
                idx = Integer.valueOf(input.substring(7));
                taskList.unmarkTask(idx);
                System.out.println(newLine + "Don't fret sir! You'll get it soon. \n" + newLine);
            } else if (input.contains("mark")) {
                idx = Integer.valueOf(input.substring(5));
                taskList.markTask(idx);
                System.out.println(newLine + "A job well done sir! \n" + newLine);

            } else {
                Task task = new Task(input);
                taskList.addTask(task);
                System.out.println(newLine + indent + input + "\n" + newLine);
            }
            input = reader.nextLine();
        }
        String goodbye = indent + "It has been a pleasure! I do hope to see you again!\n";
        System.out.println(newLine + goodbye + newLine);
        reader.close();

    }
}
