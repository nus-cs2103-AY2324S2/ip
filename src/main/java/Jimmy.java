public class Jimmy {
    String[] list = new String[100];
    int counter = 0;

    public void greetUser() {
        System.out.println("Hello! I'm Jimmy \nWhat can I do for you?");
    }
    public void storeUserInput(String input) {
        list[counter] = input;
        System.out.println("added: " + input);
        counter++;
    }
    public void displayList() {
        for (int i = 0; i < counter; i++) {
            System.out.println((i + 1) + ": " + list[i]);
        }
    }
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
