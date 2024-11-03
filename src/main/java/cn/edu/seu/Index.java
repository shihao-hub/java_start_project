package cn.edu.seu;

import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Index {
    public static void regexExample(){
        // 2024-11-01：看起来和 Python 的 re 一样使用。看样子正则表达式的实现是有标准的。

        String text = "Hello, my email is example@example.com and my phone number is 123-456-7890.";

        // 创建正则表达式模式
        String emailRegex = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}";
        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher emailMatcher = emailPattern.matcher(text);

        // 查找所有匹配的 email
        while (emailMatcher.find()) {
            System.out.println("Found email: " + emailMatcher.group());
        }

        // 匹配电话号码
        String phoneRegex = "\\d{3}-\\d{3}-\\d{4}";
        Pattern phonePattern = Pattern.compile(phoneRegex);
        Matcher phoneMatcher = phonePattern.matcher(text);

        // 查找所有匹配的电话号码
        while (phoneMatcher.find()) {
            System.out.println("Found phone number: " + phoneMatcher.group());
        }
    }



    public static void main(String[] args) {
        Consumer<String> print = System.out::println;

        // 局部内部类
        class Local {
            final String NAME = "Local class";

            void display() {
                System.out.println("Local display");
            }
        }
        Local local = new Local();

        local.display();
        System.out.println(local.NAME);

        print.accept(local.NAME);
    }
}
