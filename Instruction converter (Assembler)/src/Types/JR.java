package Types;

import BinaryOperations.BinaryOp;
public class JR {

    public static String execute(String instruction) {
        StringBuilder machineCode = new StringBuilder("0001000000");
        String[] parts = instruction.split(" ");
        int rs = parts[1].trim().charAt(1) - 48;
        machineCode.append(BinaryOp.binaryConverter(rs, 3));
        machineCode.append("000");
        return String.valueOf(machineCode);
    }
}
