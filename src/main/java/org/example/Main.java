package org.example;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws Exception {
        String testClassName = "org.example.Test";
        Class<?> testClass = Class.forName(testClassName); // convert string classname to class
        Object test = testClass.newInstance(); // invoke empty constructor

        String methodName = "";

        methodName = "add2";

        Method setNameMethod = test.getClass().getMethod(methodName, int.class, int.class);
        int result = (int) setNameMethod.invoke(test, 1, 2); // pass arg
        System.out.println(result);
    }
}
