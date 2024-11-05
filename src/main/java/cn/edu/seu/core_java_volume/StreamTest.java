package cn.edu.seu.core_java_volume;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
    public static class User {
        private static int nextId = 1;


        private static void _checkIdRange(int id) {
            if (id <= 0) {
                throw new IllegalArgumentException("字段 id 的值不合法");
            }
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        private int id;
        private String name;

        public User() {
            this.id = nextId;
            nextId += 1;
        }

        // public User(int id, String name) {
        //     _checkIdRange(id);
        //     this.id = id;
        //     this.name = name;
        // }


        public User(String name) {
            this();
            this.name = name;
        }
    }

    public static void main(String[] args) {
        // BigInteger x = BigInteger.ZERO;
        int x = 0;
        var integers = Stream.iterate(x, e -> e < 10, e -> {
            System.out.println(e);
            // return e.add(BigInteger.ONE);
            return e + 1;
        });

        System.out.println(integers.count());

        var userDB = List.of(
                new User("111"),
                new User("222a"),
                new User("333"),
                new User("444a"),
                new User("555"),
                new User("666a")
        );
        var users = userDB.stream().parallel().filter(e -> {
            if (e.getName().contains("a")) {
                return true;
            }
            if (e.getName().equals("111")) {
                return true;
            }
            return false;
        });
        System.out.println(users.collect(Collectors.toList()));

        System.out.println((long) Math.pow(2, 63) - 1);
    }
}
