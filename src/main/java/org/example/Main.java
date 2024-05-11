package org.example;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner inputCfg = new Scanner(new File("input_cfg.txt"));
            PrintWriter outputCfg = new PrintWriter(new FileWriter("output_cfg.txt"));
            while (inputCfg.hasNextLine()) {
                String data = inputCfg.nextLine();
                switch (data) {
                    case "1" -> CFG_1.validate(inputCfg, outputCfg);
                    case "2" -> CFG_2.validate(inputCfg, outputCfg);
                    case "3" -> CFG_3.validate(inputCfg, outputCfg);
                    case "4" -> CFG_4.validate(inputCfg, outputCfg);

                }
            }
            inputCfg.close();
            outputCfg.close();

            System.out.println("Validation completed. Check output_cfg.txt for results.");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
