
package org.example;

import java.io.*;
import java.util.*;

public class CFG_2 {
    // Define non-terminal symbol
    static final String StartSymbol = "S";

    // Check if the input string is accepted by the CFG
    static boolean isAccepted(String input) {
        Map<String, List<String>> productions = new HashMap<>();
        productions.put(StartSymbol, Arrays.asList( StartSymbol + "aab", "a" + StartSymbol + "ab",
                "aa" + StartSymbol + "b", "aab" + StartSymbol,"aab", "baa", "aba", ""));

        return generate(input, StartSymbol, productions);
    }

    // Generate strings based on CFG rules
    static boolean generate(String input, String symbol, Map<String, List<String>> productions) {
        if (input.isEmpty() && symbol.isEmpty()) {
            return true; // Perfect match
        }
        if (symbol.isEmpty()) {
            return false; // Extra input left without matching symbols
        }

        List<String> expansions = productions.get(symbol);
        if (expansions != null) {
            for (String expansion : expansions) {
                if (expansion.isEmpty() && input.isEmpty()) {
                    return true; // Handle epsilon
                } else if (expansion.equals("aab") && input.startsWith("aab")) {
                    String remaining = input.substring(3);
                    if (remaining.isEmpty() || generate(remaining, StartSymbol, productions)) {
                        return true;
                    }
                } else if (expansion.equals("aba") && input.startsWith("aba")) {
                    String remaining = input.substring(3);
                    if (remaining.isEmpty() || generate(remaining, StartSymbol, productions)) {
                        return true;
                    }
                } else if (!expansion.equals("aab") && !expansion.equals("aba") && checkMatch(input, expansion, productions)) {
                    return true; // If match found
                }
            }
        }
        return false;
    }

    // Check for a match based on the current expansion rule
    static boolean checkMatch(String input, String expansion, Map<String, List<String>> productions) {
        if (input.equals(expansion)) {
            return true; // Direct match for non-empty productions
        } else if (expansion.contains(StartSymbol)) {
            int index = expansion.indexOf(StartSymbol);
            String prefix = expansion.substring(0, index);
            String suffix = expansion.substring(index + 1);
            if (input.startsWith(prefix) && input.endsWith(suffix)) {
                String middleInput = input.substring(prefix.length(), input.length() - suffix.length());
                return generate(middleInput, StartSymbol, productions);
            }
        }
        return false;
    }



    // Method to validate input strings using the CFG and write results to output
    public static void validate(Scanner inputCfg, PrintWriter outputCfg) {
        outputCfg.println("Problem 2 - accepting a number of a's is twice the number of \n" +
                "b's.:");
        while (inputCfg.hasNextLine()) {
            String str = inputCfg.nextLine();
            if (str.equals("end")) break;
            boolean accepted = isAccepted(str);
            outputCfg.println((accepted ? "Accepted" : "Not accepted"));
        }

        outputCfg.println("End of Problem 2");
        outputCfg.println("--------------------");
    }
}
