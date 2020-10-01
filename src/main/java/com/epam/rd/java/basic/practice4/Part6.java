package com.epam.rd.java.basic.practice4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part6 {
    
private static final String LATIN = "\\b[a-zA-Z]+\\b";
    private static final String CYRL = "\\b[а-яА-ЯєЄїЇёЁіІҐґ]+\\b";

    public static void main(String[] args) {
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String currentString;
        String input = Part1.returnInput("part6.txt", "cp1251");
        try {
            do {
                currentString = reader.readLine();
                if (currentString.equals("Cyrl")) {
                    System.out.println("Cyrl: " + returnOutput(input, CYRL));
                } else if (currentString.equals("Latn")) {
                    System.out.println("Latn: " + returnOutput(input, LATIN));
                } else {
                    if (currentString.length() != 0 && !currentString.equals("stop"))
                        System.out.println("smth: Incorrect input");
                }
            } while (!currentString.equals("stop"));
            reader.close();
        } catch (IOException e) {
            e.getMessage();
        }

    }

    public static String returnOutput(String input, String lettersType) {
        StringBuilder resultString = new StringBuilder();
        Pattern pCyrl = Pattern.compile(lettersType);
        Matcher mCyrl = pCyrl.matcher(input);
        while (mCyrl.find()) {
            resultString.append(mCyrl.group()).append(" ");
        }
        return resultString.toString();
    }

}
