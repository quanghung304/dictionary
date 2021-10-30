package com.example.demo2cc;

import java.io.IOException;

public class DicTest {
    public static void main(String[] args) throws IOException {
        Dictionary dictionary = new Dictionary(Dictionary.PATH);
        System.out.println(dictionary.list2.size());
        System.out.println(dictionary.list.size());
        Word w = new Word("a fortiori11","moi","concacd");
        dictionary.addWord(w);
        dictionary.ExportFile();
        System.out.println("OK");
    }
}
