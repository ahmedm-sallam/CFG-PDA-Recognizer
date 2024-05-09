
package org.example;

import java.io.*;
import java.util.*;

public class CFG_1 {
    // Define non-terminal symbol
    static final String StartSymbol = "S";

    // Check if the input string is accepted by the CFG
    static boolean isAccepted(String input) {
        Map<String, List<String>> productions = new HashMap<>();
        productions.put(StartSymbol, Arrays.asList("a" + StartSymbol + "b", "ab", ""));

        return generate(input, StartSymbol, productions);
    }

    // Generate strings based on CFG rules
    static boolean generate(String input, String symbol, Map<String, List<String>> productions) {
        if (input.isEmpty() && symbol.isEmpty()) {
            return true;
        }
        if (!input.isEmpty() && symbol.isEmpty()) {
            return false;
        }

        List<String> expansions = productions.get(symbol);
        if (expansions != null) {
            for (String expansion : expansions) {
                if (expansion.isEmpty()) {
                    if (input.isEmpty()) {
                        return true; // Epsilon matches empty input
                    }
                } else if (expansion.equals("ab") && input.equals("ab")) {
                    return true; // Direct match
                } else if (input.startsWith("a") && input.endsWith("b") && expansion.equals("a" + StartSymbol + "b")) {
                    return generate(input.substring(1, input.length() - 1), StartSymbol, productions);
                }
            }
        }
        return false;
    }

    // Method to validate input strings using the CFG and write results to output
    public static void validate(Scanner inputCfg, PrintWriter outputCfg) {
        outputCfg.println("Problem 1 - Equal number of 'a's and 'b's: ");
        while (inputCfg.hasNextLine()) {
            String str = inputCfg.nextLine();
            if (str.equals("end")) break;
            boolean accepted = isAccepted(str);
            outputCfg.println((accepted ? "Accepted" : "Not accepted"));
        }

        outputCfg.println("End of problem 1");
        outputCfg.println("--------------------");
    }
}
