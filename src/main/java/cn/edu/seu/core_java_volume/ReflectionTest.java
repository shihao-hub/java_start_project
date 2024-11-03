package cn.edu.seu.core_java_volume;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ReflectionTest {
    public final static String INDENT = "    ";

    public static void main(String[] args) throws ClassNotFoundException {
        class Local {
            String getClassName(String[] args) {
                String res;
                if (args.length > 0) {
                    res = args[0];
                } else {
                    System.out.println("输入正确的类名（例如：java.lang.Double）");
                    var in = new Scanner(System.in);
                    res = in.next();
                    in.close();
                }
                return res;
            }

            void _commonPrintModifiers(Object obj) {
                if (!(obj instanceof Class || obj instanceof Constructor)) {
                    throw new IllegalArgumentException("参数必须是 Class 和 Constructor 的实例");
                }
                try {
                    var method = obj.getClass().getMethod("getModifiers");
                    String modifiers = Modifier.toString((int) method.invoke(obj));
                    if (!modifiers.isEmpty()) {
                        System.out.print(modifiers + " ");
                    }
                } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }

            void printModifiers(Class<?> cls) {
                _commonPrintModifiers(cls);
            }

            void printModifiers(Constructor<?> constructor) {
                _commonPrintModifiers(constructor);
            }

            void _commonPrintParameterTypes(Object obj) {
                if (!(obj instanceof Method || obj instanceof Constructor)) {
                    throw new IllegalArgumentException("参数必须是 Method 和 Constructor 的实例");
                }
                try {
                    var method = obj.getClass().getMethod("getParameterTypes");
                    System.out.print("(");
                    var paramTypes = (Class<?>[]) method.invoke(obj);
                    for (int i = 0; i < paramTypes.length; ++i) {
                        //(Q)!: 这个很好，实现了添加逗号的功能
                        if (i > 0) {
                            System.out.print(", ");
                        }
                        System.out.print(paramTypes[i].getName());
                    }
                    System.out.println(");");
                } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }

            void _printParameterTypes(Method method) {
                _commonPrintParameterTypes(method);
            }

            void _printParameterTypes(Constructor<?> constructor) {
                _commonPrintParameterTypes(constructor);
            }

            void _printIndent() {
                System.out.print(INDENT);
            }

            void _printIndent(int n) {
                for (int i = 0; i < n; ++i) {
                    _printIndent();
                }
            }

            void printConstructors(Class<?> cls) {
                var constructors = cls.getDeclaredConstructors();
                Arrays.stream(constructors).forEach(e -> {
                    // 1. 打印缩进
                    // 2. 打印 public 等
                    // 3. 打印构造函数名
                    // 4. 打印参数
                    _printIndent();
                    printModifiers(e);
                    System.out.print(e.getName());
                    _printParameterTypes(e);
                });
            }

            void printMethods(Class<?> cls) {
                Arrays.stream(cls.getDeclaredMethods()).forEach(e -> {
                    // 1. 打印缩进
                    // 2. 打印 public 等
                    // 3. 打印返回值
                    // 4. 打印函数名
                    // 5. 打印参数
                    _printIndent();
                    printModifiers(cls);
                    System.out.print(e.getReturnType() + " ");
                    System.out.print(e.getName());
                    _printParameterTypes(e);
                });
            }

            void printFields(Class<?> cls) {
                // 1. 打印缩进
                // 2. 打印 public 等
                // 3. 打印参数类型
                // 4. 打印参数名
                Arrays.stream(cls.getDeclaredFields()).forEach(e -> {
                    _printIndent();
                    printModifiers(cls);
                    System.out.print(e.getType() + " ");
                    System.out.print(e.getName() + ";\n");
                });
            }
        }

        var local = new Local();

        String name = local.getClassName(args);

        // 该函数强制要求异常处理
        var cls = Class.forName(name);
        var superCls = cls.getSuperclass();

        local.printModifiers(cls);
        System.out.print("class " + name);
        Optional.ofNullable(superCls).ifPresent(e -> {
            if (e == Object.class) {
                return;
            }
            System.out.print(" extends " + e.getName());
        });

        System.out.println(" {");

        local.printConstructors(cls);
        System.out.println();

        local.printMethods(cls);
        System.out.println();

        local.printFields(cls);

        System.out.println("}");


    }
}
