public class Derek {
    public static void main(String[] args) {
        String welcomeMessage = String.join(
            "\n",
            "____________________________________________________________",
            "Hello! I'm DEREK",
            "What can I do for you?",
            "____________________________________________________________"
        );

        String exitMessage = String.join(
            "\n",
            "____________________________________________________________",
            "Bye. Hope to see you again soon!",
            "____________________________________________________________"
        );

        System.out.println(welcomeMessage);
        System.out.println(exitMessage);
    }
}
