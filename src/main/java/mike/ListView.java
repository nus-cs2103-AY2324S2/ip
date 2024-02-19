package mike;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * ListView class is responsible for filtering tasks to be viewed by the user.
 */
public class ListView {
    private static final int MAX_HAMMING_DISTANCE = 1;
    private final boolean isFuzzySearchOn;
    private final ListViewType type;
    private final String attribute;
    private LocalDate date;

    /**
     * Constructor.
     * @param type The type of list view, usually a field.
     * @param attribute THe value a field should take.
     * @throws MikeException
     */
    public ListView(ListViewType type, String attribute, boolean isFuzzySearchOn) throws MikeException {
        this.type = type;
        this.attribute = attribute;
        this.isFuzzySearchOn = isFuzzySearchOn;
        if (type.equals(ListViewType.DATE)) {
            try {
                this.date = LocalDate.parse(attribute);
            } catch (DateTimeParseException e) {
                throw new MikeException("Please enter a valid date in YYYY-MM-DD format.");
            }
        }
    }

    public ListView(ListViewType type, String attribute) throws MikeException {
        this(type, attribute, false);
    }
    public ListView(ListViewType type) throws MikeException {
        this(type, "", false);
    }
    /**
     * Getter.
     * @return The type of list view.
     */
    public ListViewType getType() {
        return type;
    }

    /**
     * Filters tasks by dates.
     * @param taskDate The task date.
     * @return True if the task date is permissible by the list view, otherwise false.
     */
    public boolean dateFilter(LocalDate taskDate) {
        return !type.equals(ListViewType.DATE) || date.equals(taskDate);
    }

    /**
     * Filters tasks by keyword. If the type of list view is not description then the task is kept,
     * i.e., return true.
     * @param description The task description.
     * @return True if the task description contains the keyword, otherwise false.
     */
    public boolean keywordFilter(String description) {
        boolean isViewTypeDescription = type.equals(ListViewType.DESCRIPTION);
        boolean isKeywordInDescription = description.contains(attribute);
        boolean isKeywordPartialInDescription = isPartialMatch(description, attribute);
        if (!isViewTypeDescription) {
            return true;
        }
        if (isKeywordInDescription) {
            return true;
        }
        if (isKeywordPartialInDescription && isFuzzySearchOn) {
            return true;
        }
        return false;
    }

    private boolean isPartialMatch(String description, String keyword) {
        // bitap algorithm taken from:
        // https://en.wikipedia.org/wiki/Bitap_algorithm
        String text = description;
        String pattern = keyword;
        char[] result = bitapFuzzyBitwiseSearch(text, pattern, MAX_HAMMING_DISTANCE);;
        return result != null;
    }

    private char[] bitapFuzzyBitwiseSearch(String text, String pattern, int k) {
        char[] result = null;
        int patternLength = pattern.length();
        long[] R;
        long[] patternMask = new long[Character.MAX_VALUE + 1];

        char[] t = text.toCharArray();
        char[] p = pattern.toCharArray();

        if (patternLength == 0) {
            return t;
        }
        if (patternLength > 31) {
            return null;
        }

        R = new long[k + 1];
        for (int i = 0; i <= k; ++i) {
            R[i] = ~1;
        }

        for (int i = 0; i <= Character.MAX_VALUE; ++i) {
            patternMask[i] = ~0;
        }
        for (int i = 0; i < patternLength; ++i) {
            patternMask[p[i]] &= ~(1L << i);
        }

        for (int i = 0; i < text.length(); ++i) {
            long oldRd = R[0];

            R[0] |= patternMask[t[i]];
            R[0] <<= 1;

            for (int d = 1; d <= k; ++d) {
                long tmp = R[d];

                R[d] = (oldRd & (R[d] | patternMask[t[i]])) << 1;
                oldRd = tmp;
            }

            if (0 == (R[k] & (1L << patternLength))) {
                result = text.substring(i - patternLength + 1, i + 1).toCharArray();
                break;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "LISTVIEW " + type + attribute;
    }
}
