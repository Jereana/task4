package com.epam.rd.java.basic.practice4;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part4 implements Iterable <String>{


    Pattern pSentence = Pattern.compile("([\\p{L}]([^?!.\\\\(]|\\([^\\\\)]*\\))*[.?!])");
    private static Matcher mSentence;

    public Part4 (String input){
       mSentence = pSentence.matcher(input.replace(System.lineSeparator()," "));
    }

    //@Override
    public Iterator<String> iterator() {

        return new IteratorImpl();
    }

    private static class IteratorImpl implements Iterator<String> {

        @Override
        public boolean hasNext() {
          return mSentence.find();
        }

        @Override
        public String next() {
           return mSentence.group();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {

        String input = Part1.returnInput("part4.txt", "cp1251");
        Part4 myObject = new Part4(input);

        Iterator<String> iterator = myObject.iterator();
        while (iterator.hasNext())
           System.out.print(iterator.next() + "\n");

    }
}
