package cn.edu.seu;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

class BaseMain {

}


class MainHelper extends BaseMain {
    protected static String arrayToString(Object array) {
        // (N)!: if else 必然是需要用的，但是将其足够抽象，放置到一个地方（比如工厂模式），才是最佳的
        if (array instanceof Object[]) {
            return Arrays.deepToString((Object[]) array);
        } else if (array instanceof int[]) {
            return Arrays.toString((int[]) array);
        } else if (array instanceof long[]) {
            return Arrays.toString((long[]) array);
        } else if (array instanceof double[]) {
            return Arrays.toString((double[]) array);
        } else if (array instanceof float[]) {
            return Arrays.toString((float[]) array);
        } else if (array instanceof char[]) {
            return Arrays.toString((char[]) array);
        } else if (array instanceof byte[]) {
            return Arrays.toString((byte[]) array);
        } else if (array instanceof short[]) {
            return Arrays.toString((short[]) array);
        } else {
            return "Unsupported array type";
        }
    }
}

public class Main extends MainHelper {
    public static boolean isArray(Object obj) {
        // (Q)!: 该函数内容由 gpt 提供，所以 Java 只能这样吗？ NO -> e.getClass().isArray()
        return obj instanceof Object[] || obj instanceof int[] || obj instanceof double[] ||
                obj instanceof float[] || obj instanceof long[] || obj instanceof short[] ||
                obj instanceof byte[] || obj instanceof char[] || obj instanceof boolean[];
    }

    public static <E> String deepPrint(ArrayList<E> list) {
        /* 目前主要为了打印一维数组 */
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (E e : list) {
            String insertVal = e.toString();
            if (e.getClass().isArray()) {
                insertVal = arrayToString(e);
            }
            sb.append(insertVal).append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.printf("Hello and welcome! %s", "123\n");
        for (int i = 1; i <= 5; i++) {
            System.out.println("i = " + i);
        }

        ArrayList<String[]> arrayList = new ArrayList<>(Arrays.asList(new String[]{"1", "2"}, new String[]{"3", "4"}));
        System.out.println(arrayList);
        System.out.println(Main.deepPrint(arrayList));
        System.out.println(arrayList.stream().map(Arrays::toString).collect(Collectors.joining(", ")));
    }
}