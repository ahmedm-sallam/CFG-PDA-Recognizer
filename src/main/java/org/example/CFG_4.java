////package org.example;
////import java.io.*;
////import java.util.*;
////
////
////public class CFG_4 {
////    // Define non-terminal symbol
////    static final String StartSymbol = "S";
////
////    // Check if the input string is accepted by the CFG
////    static boolean isAccepted(String input) {
////        Map<String, List<String>> productions = new HashMap<>();
////        productions.put(StartSymbol, Arrays.asList("aaaT", ""));
////        // Allow "T" to recur
////        productions.put("T", Arrays.asList("aabT", ""));  // Now "T" can repeat many times.
////
////        return generate(input, StartSymbol, productions);
////    }
////
////    // Generate strings based on CFG rules
////    static boolean generate(String input, String symbol, Map<String, List<String>> productions) {
////        if (input.isEmpty() && symbol.isEmpty()) {
////            return true;
////        }
////        if (symbol.isEmpty()) {
////            return input.isEmpty();
////        }
////
////        // Handle non-terminal symbols by looking up their productions
////        if (Character.isUpperCase(symbol.charAt(0))) {
////            List<String> expansions = productions.get(symbol);
////            if (expansions != null) {
////                for (String expansion : expansions) {
////                    if (generate(input, expansion, productions)) {
////                        return true;
////                    }
////                }
////            }
////        } else {
////            // Handle direct character matches and continue with the rest of the symbol string
////            if (!input.isEmpty() && input.charAt(0) == symbol.charAt(0)) {
////                return generate(input.substring(1), symbol.substring(1), productions);
////            }
////        }
////        return false;
////    }
////
////    // Method to validate input strings using the CFG and write results to output
////    public static void validate(Scanner inputCfg, PrintWriter outputCfg) {
////        outputCfg.println("Problem 4 ");
////        while (inputCfg.hasNextLine()) {
////            String str = inputCfg.nextLine();
////            if (str.equals("end")) break;
////            boolean accepted = isAccepted(str);
////            outputCfg.println((accepted ? "Accepted" : "Not accepted"));
////        }
////
////        outputCfg.println("End of problem 4");
////        outputCfg.println("--------------------");
////    }
////}
//package org.example;
//
//import java.io.*;
//import java.util.*;
//
//public class CFG_4 {
//    // Define non-terminal symbol
//    static final String StartSymbol = "S";
//
//    // Check if the input string is accepted by the CFG
//    static boolean isAccepted(String input) {
//        Map<String, List<String>> productions = new HashMap<>();
//        productions.put(StartSymbol, Arrays.asList("aaaT", ""));
//        // Allow "T" to recur
//        productions.put("T", Arrays.asList("aabT", "aab", ""));  // Now "T" can repeat many times.
//
//        return generate(input, StartSymbol, productions);
//    }
//
//    // Generate strings based on CFG rules
//    static boolean generate(String input, String symbol, Map<String, List<String>> productions) {
//        if (input.isEmpty() && symbol.isEmpty()) {
//            return true;
//        }
//        if (symbol.isEmpty()) {
//            return input.isEmpty();
//        }
//
//        // Handle non-terminal symbols by looking up their productions
//        if (Character.isUpperCase(symbol.charAt(0))) {
//            List<String> expansions = productions.get(symbol);
//            if (expansions != null) {
//                for (String expansion : expansions) {
//                    if (generate(input, expansion, productions)) {
//                        return true;
//                    }
//                }
//            }
//        } else {
//            // Handle direct character matches and continue with the rest of the symbol string
//            if (!input.isEmpty() && input.charAt(0) == symbol.charAt(0)) {
//                return generate(input.substring(1), symbol.substring(1), productions);
//            }
//        }
//        return false;
//    }
//
//
//    // Method to validate input strings using the CFG and write results to output
//    public static void validate(Scanner inputCfg, PrintWriter outputCfg) {
//        outputCfg.println("Problem 4 ");
//        while (inputCfg.hasNextLine()) {
//            String str = inputCfg.nextLine();
//            if (str.equals("end")) break;
//            boolean accepted = isAccepted(str);
//            outputCfg.println((accepted ? "Accepted" : "Not accepted"));
//        }
//
//        outputCfg.println("End of problem 4");
//        outputCfg.println("--------------------");
//    }
//}
