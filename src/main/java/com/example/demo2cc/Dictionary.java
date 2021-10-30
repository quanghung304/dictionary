package com.example.demo2cc;
import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Dictionary {
    List<Word> list = new ArrayList<>();
    List<String> list2 = new ArrayList<>();
    static final String PATH = "newanhviet.txt";
    /**
     * Ghi du lieu tu list ra file
     */
    public void ExportFile() throws IOException {
        File file = new File("newanhviet.txt");
        FileOutputStream fos = new FileOutputStream(file);
        try {
            for (String s: list2) {
                s = s + "\n";
                byte[] bytesArray = s.getBytes();
                fos.write(bytesArray);
                fos.flush();
            }
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        fos.close();
    }
    /**
     * Them tu vao danh sach theo dung vi tri.
     */
    public void addWord(Word word) {
        String newword = word.getWord();
        if(!compare(list.get(0).getWord(), newword)) {
            list.add(0,word);
            list2.add(0,word.getWord() + "\t"+ "/" + word.getPronounce()+"/" +"\t" + "* " + word.getData());
        }
        else if(compare(list.get(list.size()-1).getWord(), newword)) {
            list.add(word);
            list2.add(word.getWord() + "\t"+ "/" + word.getPronounce()+"/" +"\t" + "* " + word.getData());
        }
        else {
            for (int i = 0;i<list.size();i++) {
                if(compare(list.get(i).getWord(), newword) && compare(newword, list.get(i + 1).getWord())) {
                    list.add(i+1,word);
                    list2.add(i+1,word.getWord() + "\t"+ "/" + word.getPronounce()+"/" +"\t" + "* " + word.getData());
                }
            }
        }
    }
    /**
     * kiem tra s1 co nho hon s2 hay khong?.
     */
    public static boolean compare(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        int len = Math.min(len1,len2);
        for(int i = 0;i < len;i++) {
            if(s1.charAt(i) < s2.charAt(i)) {
                return true;
            }
            if (s1.charAt(i) > s2.charAt(i)) {
                return false;
            }
        }

        return len1 < len2;
    }
    /**
     * Tim kiem tu trong danh sach, thong bao neu khong tim thay
     */
    public String findWord(String word) {
        for (Word value : list) {
            if (Objects.equals(word, value.getWord())) {
                return value.showWord();
            }
        }
        return "dont found";
    }
    /**
     *
     */
    public void remove(String word) {
        for (int i = 0; i < list.size(); i++) {
            if(Objects.equals(word, list.get(i).getWord())) {
                list.remove(i);
                list2.remove(i);
                break;
            }
        }
    }
    /**
     * Sua tu
     */
    public void fixword(String word, String proun, String mean) {
        for (int i = 0; i < list.size(); i++) {
            if(Objects.equals(word, list.get(i).getWord())) {
                list.get(i).setPronounce(proun);
                list.get(i).setData(mean);
                String s = word + "\t"+ "/" + proun+"/" +"\t" + "* " + mean;
                list2.set(i,s);
                break;
            }
        }
    }
    public Dictionary(String PATH) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(PATH);
        try {
            Scanner scanner = new Scanner(fileInputStream);
            String word_String, word_spelling, word_explain;
            while(scanner.hasNextLine() )
            {

                String oneLine = scanner.nextLine();
                list2.add(oneLine);
                word_String = oneLine.substring(0, oneLine.indexOf("\t"));
                word_spelling = oneLine.substring(oneLine.indexOf("\t") +  1, oneLine.indexOf("*"));
                String s = oneLine.substring(oneLine.indexOf("*"));
                word_explain = "";
                for (int i =0; i < s.length();i++) {
                    if(s.charAt(i)=='*') {
                        word_explain = word_explain + "\n" + s.charAt(i);
                    }
                    else if(s.charAt(i)=='-') {
                        word_explain = word_explain + "\n" + "\t" + s.charAt(i);
                    }
                    else if(s.charAt(i)=='+') {
                        word_explain = word_explain + ": ";
                    }
                    else if(s.charAt(i)=='=') {
                        word_explain = word_explain + "\n" + "\t" + "\t" + "VD: ";
                    }
                    else {
                        word_explain = word_explain + s.charAt(i);
                    }
                }
                Word word = new Word();
                word.setWord(word_String);
                word.setPronounce(word_spelling);
                word.setData(word_explain);
                list.add(word);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //check
    public boolean checkWord(String s) {
        for (Word w: list) {
            if(Objects.equals(w.getWord(), s)) {
                return true;
            }
        }
        return false;
    }
    //sameword
    public boolean check(String s1, String s2) {
        if(s1.length() > s2.length()) {
            return false;
        }
        else {
            for(int i = 0; i < s1.length();i++) {
                if(s1.charAt(i)!=s2.charAt(i)) {
                    return false;
                }
            }
        }
        return true;
    }
    //ListSame
    public List<Word> sameWord(String s) {
        int index = 0;
        List<Word> samelist = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if(check(s, list.get(i).getWord())) {
                index = i;
                break;
            }
        }
        for(int i = index; i < index + 20; i++) {
            if(check(s, list.get(i).getWord())) {
                samelist.add(list.get(i));
            }
            else {
                return samelist;
            }
        }
        return samelist;
    }
}