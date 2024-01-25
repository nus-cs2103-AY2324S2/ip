package ChatbotRan;

class Util {
    private Util() {
    }

    static Integer parseNumber(String line, int spacePos) {
        try {
            return Integer.parseInt(line.substring(spacePos + 1));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static String[] parse(String line, int space, String... delims) {
        if (space == -1) {
            System.out.println("You've forgotten to write the contents of your task.");
            return null;
        } else {
            String[] texts = new String[delims.length + 1];
            int pos = space;
            int oldpos = pos;
            for (int i = 0; i < delims.length; i++) {
                String delim = delims[i];
                pos = line.indexOf(delim, oldpos);
                if (pos == -1) {
                    System.out.println("Missing delimiter " + delim + ".");
                    return null;
                } else {
                    texts[i] = line.substring(oldpos, pos).strip();
                    oldpos = pos + delim.length();
                }
            }
            texts[delims.length] = line.substring(oldpos).strip();
            //System.out.println(Arrays.toString(texts));
            return texts;

        }

    }
}
