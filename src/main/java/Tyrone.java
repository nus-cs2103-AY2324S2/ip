import java.io.PrintWriter;
import java.util.Scanner;

public class Tyrone {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        PrintWriter writer = new PrintWriter(System.out, true);
        String logo =
                "\t████████╗██╗   ██╗██████╗  ██████╗ ███╗   ██╗███████╗\n" +
                        "\t╚══██╔══╝╚██╗ ██╔╝██╔══██╗██╔═══██╗████╗  ██║██╔════╝\n" +
                        "\t██║    ╚████╔╝ ██████╔╝██║   ██║██╔██╗ ██║█████╗  \n" +
                        "\t██║     ╚██╔╝  ██╔══██╗██║   ██║██║╚██╗██║██╔══╝  \n" +
                        "\t██║      ██║   ██║  ██║╚██████╔╝██║ ╚████║███████╗\n" +
                        "\t╚═╝      ╚═╝   ╚═╝  ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚══════╝\n";
        writer.println(logo +
                "\tYo, what's crackin' fam! I'm Tyrone, your digital homie.\n" +
                "\tWhat's the word? So I can help you out today.\n" +
                "\n\t____________________________________________________________\n");
        TodoList todoList = new TodoList();
        while (true) {
            String cmd = reader.nextLine();
            if (cmd.equals("bye")) {
                writer.println(Tyrone.formatStringOutput("Peace out! Crossin' my fingers for a speedy reunion, ya feel?"));
                break;
            } else if (cmd.equals("list")) {
                writer.println(Tyrone.formatStringOutput(todoList.toString()));
            } else {
                todoList.addItem(cmd);
                writer.println(Tyrone.formatStringOutput("Got it added homie: " + cmd));
            }
        }
    }

    public static String formatStringOutput(String content) {
        return ("\n\t____________________________________________________________\n" +
                "\t" + content + "\n" +
                "\n\t____________________________________________________________\n");
    }
}
