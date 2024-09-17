package chatbot;

import java.io.Serializable;

import chatbot.exceptions.InvalidArgumentException;

class FixedTask extends Task implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int duration;

    public FixedTask(String desc, String duration) throws InvalidArgumentException {
        super(desc);
        try {
            this.duration = Integer.parseInt(duration);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException();
        }
    }

    @Override
    public String toString() {
        return String.format("[F]%s (for: %sh)", super.toString(), duration);
    }
}
