package sylvia.state;

import sylvia.util.RandomAccessQueue;

/**
 * Represents the history of chat messages that have been sent.
 */
public class UserChatHistory {
    private static final int MAX_HISTORY_SIZE = 100;
    /** Deque for history */
    private RandomAccessQueue<String> chatHistory;

    /**
     * Constructs a UserChatHistory object with the default empty chat history and
     * future chat history.
     */
    public UserChatHistory() {
        // this.chatHistory = new String[MAX_HISTORY_SIZE];
        this.chatHistory = new RandomAccessQueue<>(MAX_HISTORY_SIZE);
    }

    /**
     * Adds the given chat message to the chat history.
     *
     * @param chatMessage The chat message to be added to the chat history.
     */
    public void addChatToHistory(String chatMessage) {
        chatHistory.addLast(chatMessage);
    }

    /**
     * Returns true if there are chat messages in the chat history.
     *
     * @return True if there are chat messages in the chat history, false otherwise.
     */
    public boolean hasHistory() {
        return !chatHistory.isEmpty();
    }

    /**
     * Returns the previous chat message in the chat history.
     *
     * @return The previous chat message in the chat history.
     */
    public String getPreviousChat() {
        return chatHistory.traverseUp();
    }

    /**
     * Returns the next chat message in the chat history.
     *
     * @return The next chat message in the chat history.
     */
    public String getNextChat() {
        return chatHistory.traverseDown();
    }

}
