package com.epam.rd.java.basic.practice4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Part3 {

    public static void main(String[] args) {
        final Map<String, String> patterns = new HashMap<>();

        patterns.put("char", "(?>\\s|^)\\p{L}{1}(?= |$)");
        patterns.put("int", "(?<![-.])\\b[0-9]+\\b(?!\\.[0-9])");
        patterns.put("double", "\\d+\\.\\d+|\\.\\d+");
        patterns.put("String", "\\p{L}{2,}(?![\\d\\p{L}\\w])"); 
        
        StringBuilder inputSb = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String currentString;
        boolean correctInput = true;
        String consoleInput = "";
        try {
            do {
                currentString = reader.readLine();
                if (patterns.containsKey(currentString) || currentString.equals("stop")) {
                        inputSb.append(currentString).append(" ");
                    } else {
                        System.out.println("Incorrect input");
                        correctInput = false;
                        break;
                    }
            } while (!currentString.equals("stop"));
            consoleInput = inputSb.toString();
            if (consoleInput.contains("stop")) {
                consoleInput = consoleInput.replace("stop","");
            }
            reader.close();
        } catch (IOException e){
            e.getMessage();
        }

        if (correctInput) {
            String fileInput = Part1.returnInput("part3.txt", "cp1251");

            Pattern pWord = Pattern.compile("(\\b[\\p{L}]+\\b)");
            Matcher mWord = pWord.matcher(consoleInput);

            StringBuilder typeOutSb = new StringBuilder();

            while (mWord.find()) {

                if (patterns.containsKey(mWord.group())) {
                    Pattern pType = Pattern.compile(patterns.get(mWord.group()));
                    Matcher mType = pType.matcher(fileInput);
                    while (mType.find()) {
                        typeOutSb.append(mType.group().trim()).append(" ");
                    }
                }
                System.out.println(typeOutSb.toString());
            }
        }
    }
}
