package BinaryOperations;

public class BinaryOp {
    public static String binaryConverter(int num, int numOfBits){
        int max = (int) (Math.pow(2, numOfBits) - 1);
        if (num > max){
            System.out.println("Signed value should be less than " + max);
            return "";
        }
        StringBuilder binaryNumber = new StringBuilder();
        int counter = (int) Math.pow(2, numOfBits - 1);
        while (binaryNumber.length() != numOfBits){
            if (counter > num){
                binaryNumber.append("0");
            }
            else {
                binaryNumber.append("1");
                num = num - counter;
            }
            counter = counter /2;
        }
        return String.valueOf(binaryNumber);
    }
//    public static String binaryConverterSigned(int num, int numOfBits) {
//        int min = (int) (-1 * Math.pow(2, numOfBits - 1));
//        int max = (int) (Math.pow(2, numOfBits - 1 ) - 1);
//        if (num <  min|| num > max){
//            System.out.println("Signed value should be less than " + max +" and greater than "+min);
//            return "";
//        }
//        StringBuilder binaryNumber = new StringBuilder();
//
//        if (num < 0) {
//            // Convert negative number to two's complement representation
//            num = (1 << numOfBits) + num;
//        }
//
//        int counter = (int) Math.pow(2, numOfBits - 1);
//
//        while (binaryNumber.length() != numOfBits) {
//            if (counter > num) {
//                binaryNumber.append("0");
//            } else {
//                binaryNumber.append("1");
//                num = num - counter;
//            }
//            counter = counter / 2;
//        }
//        return String.valueOf(binaryNumber);
//    }

    public static String binaryConverterSigned(int num, int numOfBits) {
        StringBuilder binaryNumber = new StringBuilder();
        int min = (int) (-1 * Math.pow(2, numOfBits - 1));
        int max = (int) (Math.pow(2, numOfBits - 1 ) - 1);
        if (num <  min|| num > max){
            System.out.println("Signed value should be less than " + max +" and greater than "+min);
            return "";
        }
        String abs = binaryConverter(Math.abs(num), numOfBits);
        if (num >= 0){
            return abs;
        }
        boolean flag = false;
        for (int i = abs.length() - 1 ; i >= 0 ; i--) {
            char x = abs.charAt(i);
            if (x == '0' && flag){
                binaryNumber.insert(0,'1');
            } else if (x == '1' && flag) {
                binaryNumber.insert(0,'0');
            } else if (x == '1' && !flag) {
                flag = true;
                binaryNumber.insert(0,'1');
                continue;
            }
            else
                binaryNumber.insert(0,'0');

        }
        return String.valueOf(binaryNumber);
    }
    private static String fourBitsToHexadecimal(String binaryNum){
        StringBuilder result = new StringBuilder();
        int num = 0;
        for (int i = 0; i < binaryNum.length() ; i++) {
            num += (int) (Math.pow(2, binaryNum.length() - i - 1) * (binaryNum.charAt(i) - 48));
        }
        if (num >= 10) {
            char hexChar = (char) ('A' + (num - 10));
            result.append(hexChar);
        }
        else {
            result.append(num);
        }
        return String.valueOf(result);
    }

    public static String binaryToHexadecimal(String instruction){
        if (instruction == null)
            return "";
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < instruction.length(); i += 4) {
            String str = instruction.substring(i, i + 4);
            result.append(fourBitsToHexadecimal(str));
        }
        return String.valueOf(result);
    }
}