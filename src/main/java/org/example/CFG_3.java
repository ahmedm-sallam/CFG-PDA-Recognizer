package org.example;

import java.io.*;
import java.util.*;

public class CFG_3 {
    // Define non-terminal symbol
    static final String StartSymbol = "S";

    // Check if the input string is accepted by the CFG
    static boolean isAccepted(String input) {
        Map<String, List<String>> productions = new HashMap<>();
        productions.put(StartSymbol, Arrays.asList("a" + StartSymbol + "a", "b" + StartSymbol + "b", "", "aa", "bb","a","b"));

        return generate(input, StartSymbol, productions);
    }

    // Generate strings based on CFG rules
    static boolean generate(String input, String symbol, Map<String, List<String>> productions) {
        if (input.isEmpty() && symbol.isEmpty()) {
            return true; // Perfect match
        }
        if (symbol.isEmpty() || input.isEmpty()) {
            return false; // Extra input left without matching symbols
        }

        List<String> expansions = productions.get(symbol);
        if (expansions != null) {
            for (String expansion : expansions) {
                if (expansion.isEmpty() && input.isEmpty()) {
                    return true; // Handle epsilon
                } else if (expansion.equals("a") && input.equals("a")) {
                    return true; // Direct match for "a"
                } else if (expansion.equals("b") && input.equals("b")) {
                    return true; // Direct match for "b"
                } else if (expansion.equals("aa") && input.equals("aa")) {
                    return true; // Direct match for "aa"
                } else if (expansion.equals("bb") && input.equals("bb")) {
                    return true; // Direct match for "bb"
                } else if (expansion.startsWith("a") && expansion.endsWith("a") && input.startsWith("a") && input.endsWith("a")) {
                    if (input.length() >= 3 && generate(input.substring(1, input.length() - 1), StartSymbol, productions)) {
                        return true; // Recursive call for "aSa"
                    }
                } else if (expansion.startsWith("b") && expansion.endsWith("b") && input.startsWith("b") && input.endsWith("b")) {
                    if (input.length() >= 3 && generate(input.substring(1, input.length() - 1), StartSymbol, productions)) {
                        return true; // Recursive call for "bSb"
                    }
                }
            }
        }
        return false;
    }


    // Method to validate input strings using the CFG and write results to output
    public static void validate(Scanner inputCfg, PrintWriter outputCfg) {
        outputCfg.println("Problem 3 - Palindrome: ");
        while (inputCfg.hasNextLine()) {
            String str = inputCfg.nextLine();
            if (str.equals("end")) break;
            boolean accepted = isAccepted(str);
            outputCfg.println((accepted ? "Accepted" : "Not accepted"));
        }

        outputCfg.println("End of problem 3");
        outputCfg.println("--------------------");
    }
}
