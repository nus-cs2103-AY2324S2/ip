public class InputHandler {
    public void handleInput(String userInput, Chatbot bot) {
        if (userInput.equals("list")) {
            bot.displayTasks();
        } else {
            bot.addTask(userInput);
        }
    }
}
