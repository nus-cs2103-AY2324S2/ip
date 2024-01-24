public class BartenderBobException extends Exception{
    String firstWord;
    public BartenderBobException() {
    }
    public BartenderBobException(String firstWord) {
        this.firstWord = firstWord;
    }
    public static void invalidInput(String userInput) {
        System.out.println("What? I can't understand " + userInput + " =(");
    }
    public void displayError() {
        switch (firstWord) {
            case "mark":
                System.out.println("Hmm, please provide a valid task number to mark.");
                break;
            case "unmark":
                System.out.println("Hmm, please provide a valid task number to unmark.");
                break;
            case "delete":
                System.out.println("Hmm, please provide a valid task number to delete.");
                break;
            case "todo":
                System.out.println("Hmm, did you add in a description for the todo?");
                break;
            case "deadline":
                System.out.println("Hey, this is an invalid format for deadline!");
                break;
            case "event":
                System.out.println("Hey, this is an invalid format for event!");
                break;
        }
    }
    public void storageOutOfBounds() {
        System.out.println("The index is out of bounds!");
    }
}
