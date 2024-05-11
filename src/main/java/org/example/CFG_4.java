
package org.example;

import java.io.*;
import java.util.*;

public class CFG_4 {
    // Define non-terminal symbols
    static final String StartSymbol = "S";
    static final String RecursionSymbol = "T";

    // Check if the input string is accepted by the CFG
    static boolean isAccepted(String input) {
        Map<String, List<String>> productions = new HashMap<>();
        productions.put(StartSymbol, Arrays.asList("aaa" + RecursionSymbol));
        productions.put(RecursionSymbol, Arrays.asList("aa" + RecursionSymbol + "b", ""));

        return generate(input, StartSymbol, productions);
    }

    // Generate strings based on CFG rules
    static boolean generate(String input, String symbol, Map<String, List<String>> productions) {
        if (input.isEmpty() && symbol.isEmpty()) {
            return true;
        } else if (symbol.isEmpty()) {
            return input.isEmpty();
        }

        List<String> expansions = productions.get(symbol);
        if (expansions != null) {
            for (String expansion : expansions) {
                if (tryExpansion(input, expansion, productions)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Attempt to match and recursively generate strings from expansions
    static boolean tryExpansion(String input, String expansion, Map<String, List<String>> productions) {
        if (expansion.isEmpty()) {
            return input.isEmpty();
        }

        int inputIndex = 0, expansionIndex = 0;
        while (expansionIndex < expansion.length() && inputIndex <= input.length()) {
            if (Character.isUpperCase(expansion.charAt(expansionIndex))) {
                // Recursive call for non-terminal symbols
                String nextSymbol = "";
                while (expansionIndex < expansion.length() && Character.isUpperCase(expansion.charAt(expansionIndex))) {
                    nextSymbol += expansion.charAt(expansionIndex);
                    expansionIndex++;
                }

                // Check all possible splits of the input
                for (int splitIndex = inputIndex; splitIndex <= input.length(); splitIndex++) {
                    if (generate(input.substring(inputIndex, splitIndex), nextSymbol, productions)
                            && tryExpansion(input.substring(splitIndex), expansion.substring(expansionIndex), productions)) {
                        return true;
                    }
                }
                return false; // No valid split was found for this non-terminal
            } else if (inputIndex < input.length() && input.charAt(inputIndex) == expansion.charAt(expansionIndex)) {
                // Match the terminal character
                inputIndex++;
                expansionIndex++;
            } else {
                return false;
            }
        }

        return inputIndex == input.length() && expansionIndex == expansion.length();
    }

    // Method to validate input strings using the CFG and write results to output
    public static void validate(Scanner inputCfg, PrintWriter outputCfg) {
        outputCfg.println("Problem 4 - accepting a language a^(2n+3) b^n");
        while (inputCfg.hasNextLine()) {
            String str = inputCfg.nextLine();
            if (str.equals("end")) break;
            boolean accepted = isAccepted(str);
            outputCfg.println((accepted ? "Accepted" : "Not accepted"));
        }

        outputCfg.println("End of problem 4");
        outputCfg.println("--------------------");
    }
}
