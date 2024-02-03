/*
 * Deals with iteractions with the user
 */

public class Ui {
    public void message() {
        // Displaying Duke logo and initial message
        String logo = " KASSIM ";
        System.out.println("YOO I AM " + logo);
        System.out.println("What can I do for you?");
    }

    public void errorEncounter(String errorMessge) {
        System.out.println("Error: " + errorMessge);
    }
}
