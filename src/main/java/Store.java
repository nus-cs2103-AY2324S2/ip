public class Store {
    private String[] storage = new String[100];
    private int count = 0;

    public String addText(String text) {
        storage[count++] = text;
        return "added: " + text;
    }
    public void displayStore() {
        for (int i = 0; i < count; ++i) {
            System.out.println((i + 1) + ". " + storage[i]);
        }
    }


}