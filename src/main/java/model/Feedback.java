package model;

public class Feedback {
    private boolean isBye;
    private String feedback;

    public Feedback(boolean isBye, String feedback) {
        this.isBye = isBye;
        this.feedback = feedback;
    }

    public boolean getIsBye() {
        return this.isBye;
    }

    public String toString() {
        return this.feedback;
    }
}
