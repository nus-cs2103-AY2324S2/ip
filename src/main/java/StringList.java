import java.util.ArrayList;
import java.util.List;

public class StringList {
    private final List<String> list;

    public StringList() {
        this.list = new ArrayList<>();
    }

    public void add(String value) {
        this.list.add(value);
        Utils.encaseLines("added: " + value);
    }

    public void printList() {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < list.size(); i++) {
            output.append(i + 1).append(". ").append(list.get(i));

            if (i < list.size() - 1) {
                output.append("\n");
            }
        }

        Utils.encaseLines(output.toString());
    }
}
