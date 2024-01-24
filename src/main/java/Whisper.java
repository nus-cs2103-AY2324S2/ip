public class Whisper {
    public static void main(String[] args) {
        String line = "-------------------------------------------------";
        String name = "Whisper";
        String welcomeMsg = "Hello! I'm " + name + " , your personal chatbot!\n" +
                    "What can I do for you?\n";
        String byeMsg = "Bye. Hope to see you soon!\n";
        System.out.println(line + "\n" + welcomeMsg + line);
        System.out.println(byeMsg + line);
    }
}