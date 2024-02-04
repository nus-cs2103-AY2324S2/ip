package Duke.activityAndUtility;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides utility methods to search within a list of strings and to print
 * activities from another list based on search results.
 */
public class searchList {

    /**
     * Finds and returns the indices of all strings in a list that contain a given substring.
     *
     * @param list   The ArrayList of strings to search through.
     * @param substr The substring to search for within each string of the list.
     * @return A List of integers representing the indices of strings that contain the substring.
     */
    public static List<Integer> findIndicesOfSubstring(ArrayList<String> list, String substr) {
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).contains(substr)) {
                indices.add(i); // Add index to the list if the string contains the substring
            }
        }
        return indices;
    }

    /**
     * Prints activities from an activity list that match a given substring search in a parallel search table.
     * This method utilizes {@code findIndicesOfSubstring} to identify matching indices and then prints
     * corresponding activities.
     *
     * @param searchTable  An ArrayList of strings used as a search table, typically containing searchable
     *                     attributes of activities.
     * @param activityList An ArrayList of Activity objects from which matched activities will be printed.
     * @param substr       The substring to search for within the searchTable.
     */
    public static void printList(ArrayList<String> searchTable, ArrayList<Activity> activityList, String substr) {
        List<Integer> indices = findIndicesOfSubstring(searchTable, substr); //get the index of required string
        if (!indices.isEmpty()) {
            System.out.format("\tHere's where I have: \n");
            System.out.println("\t____________________________________________________________");
            for (int i : indices) {
                activityList.get(i).printActivity();
            }
            System.out.println("\t____________________________________________________________");
        } else {
            System.out.println("\t____________________________________________________________");
            System.out.println("\tNothing can be found.");
            System.out.println("\t____________________________________________________________");
        }
    }
}
