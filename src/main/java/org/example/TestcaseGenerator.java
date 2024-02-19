package org.example;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestcaseGenerator {

    public static void main(String[] args) {
        // Thông tin về hàm
        String functionName = "add";
        String inputType = "int, int";
        String inputValues = "2, 3";
        String expectedOutput = "5";

        // Tạo testcase và kiểm thử
        generateAndTest(functionName, inputType, inputValues, expectedOutput);
    }

    private static void generateAndTest(String functionName, String inputType, String inputValues, String expectedOutput) {
        try {
            // Chia các đầu vào thành mảng
            String[] inputTypes = inputType.split(", ");
            String[] inputs = inputValues.split(", ");
            Object[] convertedInputs = new Object[inputTypes.length];

            // Chuyển đổi đầu vào từ kiểu String sang kiểu tương ứng
            for (int i = 0; i < inputTypes.length; i++) {
                convertedInputs[i] = convertToType(inputs[i], inputTypes[i]);
            }

            // Gọi hàm bằng reflection
            Object result = invokeFunctionByName(functionName, convertedInputs);

            // Kiểm tra kết quả
            System.out.println("Expected: " + expectedOutput);
            System.out.println("Actual: " + result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Object convertToType(String value, String type) {
        switch (type) {
            case "int":
                return Integer.parseInt(value);
            // Thêm các trường hợp cho các kiểu dữ liệu khác nếu cần
            default:
                return value;
        }
    }

    private static Object invokeFunctionByName(String functionName, Object... args)
            throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> clazz = Caculator.class; // Đặt tên lớp của bạn
        Class<?>[] parameterTypes = new Class[args.length];

        // Lấy kiểu dữ liệu của các đối số
        for (int i = 0; i < args.length; i++) {
            parameterTypes[i] = args[i].getClass();
        }
        System.out.println(args);

        // Tìm phương thức theo tên và kiểu đối số
        Method method = clazz.getMethod(functionName, parameterTypes);

        // Gọi phương thức với các đối số đã chuyển đổi
        return method.invoke(clazz.getDeclaredConstructor().newInstance(), args);
    }
}

