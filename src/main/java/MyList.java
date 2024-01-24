import java.util.*;
public class MyList {
    private List<String> items;

    public MyList() {
        this.items = new ArrayList<>();
    }

    public String addItem(String item) {
        this.items.add(item);
        return "added: " + item;
    }

    public String getItems() {
        StringBuilder stringBuilder = new StringBuilder();
        int index = 1;

        for (String item : this.items) {
            stringBuilder.append(index).append(". ").append(item).append("\n");
            index++;
        }

        return stringBuilder.toString();
    }
}
