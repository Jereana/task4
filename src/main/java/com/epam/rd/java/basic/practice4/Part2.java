package com.epam.rd.java.basic.practice4;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {

    static final int SIZE = 10;

       public static void main(String[] args){
           
        String fileData = randomNumbers(SIZE);
        
        int[] arrayNumbers = new int[SIZE];
        String str;
        ArrayList<String> list = new ArrayList<>();
        Pattern pNumber = Pattern.compile("(\\b[0-9]+\\b)");

        try (FileOutputStream part2 = new FileOutputStream("part2.txt")) {

            part2.write(fileData.getBytes());
            part2.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } 
       try (FileOutputStream part2Sorted = new FileOutputStream("part2_sorted.txt");
             BufferedReader reader = new BufferedReader(new FileReader("part2.txt"))) {
        
            while ((str = reader.readLine()) != null) {
                if (!str.isEmpty()) {
                    list.add(str);
                }
            }
            if (!list.isEmpty()) {
                String[] stringArr = list.toArray(new String[0]);

                Matcher mNumber = pNumber.matcher(stringArr[0]);
                int index = 0;
                while (mNumber.find()) {
                    arrayNumbers[index] = Integer.parseInt(mNumber.group());
                    index++;
                }

                bubbleSort(arrayNumbers);
                part2Sorted.write(Arrays.toString(arrayNumbers).replaceAll("([\\[\\],])","").getBytes());
                part2Sorted.flush();
                String output = Arrays.toString(arrayNumbers).replace(",", "");
                output = output.substring(1, output.length() - 1);

                System.out.println("input ==> " + stringArr[0]);
                System.out.println("output ==> " + output);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } 
    }

    
    private static String randomNumbers(int size) {
        StringBuilder numbers = new StringBuilder();
        Random rand = new Random();
        int randNum;
        for (int j = 0; j < size; j++) {
            randNum = rand.nextInt(51);
            numbers.append(randNum).append(" ");
        }
    return numbers.toString().trim();
    }


    public static void bubbleSort(int[] array) {
        boolean sorted = false;
        int temp;
        while(!sorted) {
            sorted = true;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i+1]) {
                    temp = array[i];
                    array[i] = array[i+1];
                    array[i+1] = temp;
                    sorted = false;
                }
            }
        }
    }


}
