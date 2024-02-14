package lite.gui;


import lite.Chatbot;

public class Lite {
    private Chatbot chatbot;
    public Lite() {
        this.chatbot = new Chatbot();
        chatbot.start();
    }
    /**
     * Outputs the LITE logo
     */
    public String getResponse(String input) {
        return chatbot.getResponse(input);
    }
}