package com.epam.rd.java.basic.practice4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.ResourceBundle;

public class Part5 {

    public static void main(String[] args) {
        String language = "en";
        String country = "US";

        Locale localeEn = new Locale(language,country);
        ResourceBundle bundleEn = ResourceBundle.getBundle("resources_en",localeEn);

        Locale localeRu = new Locale("ru", "RU");
        ResourceBundle bundleRu = ResourceBundle.getBundle("resources_ru",localeRu);
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String currentString;

        try {
            do {
                currentString = reader.readLine();
                    final String[] inputArray = currentString.split("\\s*(\\s|,|!|'|-|\\.)\\s*");
                    if (inputArray.length == 2) {
                        if (inputArray[1].equals("ru")) {
                            String resString = bundleRu.getString(inputArray[0]);
                            System.out.println(resString);
                        } else if (inputArray[1].equals("en")) {
                            String resString = bundleEn.getString(inputArray[0]);
                            System.out.println(resString);
                        } else {
                            System.out.println("Incorrect input");
                        }
                    }else {
                        if (inputArray.length != 0 && !inputArray[0].equals("stop"))
                            System.out.println("Incorrect input");
                    }

            } while (!currentString.equals("stop"));
            reader.close();
        } catch (IOException e){
            e.getMessage();
        }
    }

}
