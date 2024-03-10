package tars;

import java.time.LocalDate;

/**
 * Handles String input and returns as a LocalDate
 */
public class InputHandler {
    public LocalDate formatDeadline(String[] data) throws ArrayIndexOutOfBoundsException{


        try {
            if (data[1].substring(3).length() == 1) {
                data[1] = "0" + data[1].substring(3);
            } else {
                data[1] = data[1].substring(3);
            }
            if (data[2].length() == 1) {
                data[2] = "0" + data[2];
            }
        } catch (Exception e) {
            System.out.println("Please enter valid date format for deadline: dd/mm/yyyy");
        }

        String deadline = data[3] + "-" + data[2] + "-" + data[1];
        return LocalDate.parse(deadline);
    }
}
