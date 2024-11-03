package cn.edu.seu.core_java_volume;

import java.util.List;

public class TestA {
    public static void main(String[] args) {
        String all = String.join(", ", List.of("1", "2", "3", "4"));
        System.out.println(all);
        String sentence = "\uD835\uDD46"; // U+1D546
        System.out.println(sentence);
        System.out.println(0x1D546 + " - " + sentence.codePointAt(0));
        System.out.println(sentence.length());
        System.out.println(sentence.codePoints().count());

        var hello = new StringBuilder("Hello \uD83D\uDE00");
        System.out.println(hello.toString().indexOf("\uD83D\uDE00".codePointAt(0)));
        hello.insert(7, " Java");
        System.out.println(hello);
    }
}
