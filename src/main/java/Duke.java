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
        Parser parser = new Parser();
        output.greet();
        runProgram(parser, taskList);
        output.leave();
    }

    public static void runProgram(Parser parser, TaskList taskList){
        Scanner scanner = new Scanner(System.in);
        try {
            Command command = parser.parse(scanner.nextLine());
            while (!command.getType().equals(Parser.Cmd.bye)) {
                command.run(taskList);
                command = parser.parse(scanner.nextLine());
            }
        }catch(IllegalArgumentException e){
            OutputMessage.informInvalidCommand();
        }
    }
}
