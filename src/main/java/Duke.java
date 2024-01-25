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
        String new_line = indent + "____________________________________________________________ \n";
        String greeting = indent + "Good day good sir! I am Chatimous Maximous, here to help you with your every need!\n";
        System.out.println(new_line + greeting);

        Scanner reader = new Scanner(System.in);
        String input = reader.next();

        // if (input == "bye") {
        //     String goodbye = indent + "It has been a pleasure! I do hope to see you again!\n";
        //     System.out.println(new_line + goodbye + new_line);
        //     reader.close();
        // } else {
        //     System.out.println(new_line + indent + input + "\n" + new_line);
         
        // }
         
        while (input.contains("bye") == false) {
            System.out.println(new_line + indent + input + "\n" + new_line);
            input = reader.next();
        }
        String goodbye = indent + "It has been a pleasure! I do hope to see you again!\n";
        System.out.println(new_line + goodbye + new_line);
        reader.close();

    }
}
