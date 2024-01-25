public class Duke {
    public static void main(String[] args) {
        // Name of the bot
        String botName = "WannaBeSkynet";

        // Default Greeting on start-up of the bot
        String defaultGreeting = " Hello! I'm " + botName + "\n Let's get started!";

        // Separator line for beauty
        String sepLine = "____________________________________________________________";

        // Initial
        System.out.println(sepLine + "\n" + defaultGreeting + "\n" + sepLine);

        // Starting Operation
        Operator operator = new Operator();
        operator.startBotEngine();
    }
}
