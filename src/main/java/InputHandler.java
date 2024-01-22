public class InputHandler {
    public void handleInput(String userInput, Chatbot bot) {
        if (userInput.equals("list")) {
            bot.displayTasks();
        } else if (userInput.startsWith("mark")) {
            int taskIdx = Integer.parseInt(userInput.split(" ")[1]);
            bot.markTask(taskIdx);
        }
        else if (userInput.startsWith("unmark")) {
            int taskIdx = Integer.parseInt(userInput.split(" ")[1]);
            bot.unmarkTask(taskIdx);
        } else {
            bot.addTask(userInput);
        }
    }
}
