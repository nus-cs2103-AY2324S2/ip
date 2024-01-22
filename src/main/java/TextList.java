import org.w3c.dom.Text;

public class TextList {
    private static final String[] list = new String[100];
    private static int index = 0;

    public TextList() {}

    public static void addToList(String inputs) {
        System.out.println("┌────────" + "─".repeat(inputs.length()) + "─┐");
        System.out.println("  added: " + inputs);
        System.out.println("└────────" + "─".repeat(inputs.length()) + "─┘");
        TextList.list[TextList.index] = inputs;
        TextList.index++;
    }

    public static void printList() {
        System.out.println("  ┌─   list");
        for (int i = 0; i < index; i++) {
            System.out.println("  " + (i+1) + ": " + TextList.list[i]);
        }
        System.out.println("  └─   end of list");
    }
}
