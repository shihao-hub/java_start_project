package cn.edu.seu.core_java_volume;

import java.io.Console;
import java.util.Scanner;

public class InputTest {
    public static void main(String[] args) {
        var in = new Scanner(System.in);
        System.out.println("1");
        var name = in.nextLine();
        System.out.println("2");
        var age = in.nextInt();
        System.out.println("3");

        // 在 IDEA 中无法启动
        // Console console = System.console();
        // String username = console.readLine("User name: ");
        // char[] password = console.readPassword("Password: ");

    }
}
