package cn.edu.seu.core_java_volume;


import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Arrays;

public class DateTest {
    public static void printLint(int[] a){
        System.out.println(Arrays.toString(a));
    }

    public static void main(String[] args) {
        var localDate = LocalDate.now();
        System.out.printf("%tF\n", localDate);
        var out = new PrintWriter(System.out, true, StandardCharsets.UTF_8);
        out.println("123");
        printLint(new int[]{1, 2, 3,});
    }
}
