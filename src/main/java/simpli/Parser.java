package simpli;

import java.util.Arrays;

public final class Parser {
    private static final int MAX_TOKENS = 6;

    public String[] parse(String content) {
        String[] parsedTokens = new String[MAX_TOKENS];
        Arrays.fill(parsedTokens, "");

        String[] tokens = content.split(" ");

        parsedTokens[0] = tokens[0];  // command token

        int index = 1;
        for (int i = 1; i < tokens.length; i++) {
            if (!tokens[i].contains("/")) {
                parsedTokens[index] += parsedTokens[index].isEmpty() ? "" : " ";
                parsedTokens[index] += tokens[i];
            } else {
                index++;
                parsedTokens[index] = tokens[i].substring(1);
                index++;
            }
        }

        return parsedTokens;
    }
}