package parser;
public class Util {
        public static int findNthDividerIndex(String input,char c, int n) {
            int count = 0;
            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == c) {
                    count++;
                    if (count == n) {
                        return i; // Return the index of the nth repetition
                    }
                }
            }
            return -1; // Return -1 if the character is not repeated n times
        }


}
