import java.util.Objects;

public class Processor {
    public static void process (String input, StringList taskList) {
        if (Objects.equals(input, "list")) {
            taskList.printList();
        } else {
            taskList.add(input);

        }
    }
}
