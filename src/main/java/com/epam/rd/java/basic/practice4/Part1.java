package com.epam.rd.java.basic.practice4;

import java.io.*;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

public class Part1 {

    public static void main(String[] args) {
        String input = returnInput("part1.txt", "cp1251");
        
        Pattern pWord = Pattern.compile("(\\b[\\p{L}]+\\b)");
            Matcher mWord = pWord.matcher(input);
            StringBuilder wordsWith4MoreLetters = new StringBuilder();

            while (mWord.find()) {
                if (mWord.group().length() >= 4) {
                    wordsWith4MoreLetters.append("|");
                    wordsWith4MoreLetters.append(mWord.group());
                }
            }
            Matcher mword = pWord.matcher(wordsWith4MoreLetters.toString());

            String currentWord;
            while (mword.find()) {
                currentWord = mword.group();
                input = input.replace(currentWord, currentWord.substring(2));
            }

        System.out.println(input);
    }
    
   public static String returnInput(String fileName, String encoding){
        StringBuilder sb = new StringBuilder();
        Charset cs = encoding == null ? Charset.defaultCharset() : Charset.forName(encoding);
        try {
            Scanner scanner = new Scanner(new File(fileName), cs.name());
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine()).append(System.lineSeparator());
            }
            scanner.close();
            return sb.toString().trim();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return sb.toString();
    }
}

