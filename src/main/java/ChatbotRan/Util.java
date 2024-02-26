package ChatbotRan;

class Util {
    private Util() {
    }

    public static String[] parse(String line, int space, String... delims) {
        if (space == -1) {
            throw new TaskException("You've forgotten to write the contents of your task.");
        } else {
            String[] texts = new String[delims.length + 1];
            int pos = space;
            int oldpos = pos;
            for (int i = 0; i < delims.length; i++) {
                String delim = delims[i];
                pos = line.indexOf(delim, oldpos);
                if (pos == -1) {
                    throw new TaskException("Missing delimiter " + delim + ".");
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
