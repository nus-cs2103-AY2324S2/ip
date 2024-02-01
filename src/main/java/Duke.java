import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = "\t  __   __    ____  ____  ____  ____\n"
                + "\t / _\\ (  )  (  __)(  _ \\(  __)(    \\\n"
                + "\t/    \\/ (_/\\ ) _)  )   / ) _)  ) D (\n"
                + "\t\\_/\\_/\\____/(__)  (__\\_)(____)(____/\n";

        System.out.println(logo);
        final String name = "Alfred";
        TaskList taskList = new TaskList();
        OutputMessage output = new OutputMessage(name);
        output.greet();
        Command.command(taskList);
        output.leave();
    }
}
