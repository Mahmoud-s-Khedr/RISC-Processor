package Types;

import BinaryOperations.BinaryOp;
public class LUI {

    public static String execute(String instruction) {
        StringBuilder machineCode = new StringBuilder("10010");
        String[] parts = instruction.split(" ");
        int imm = Integer.valueOf(parts[1]);
        machineCode.append(BinaryOp.binaryConverter(imm, 11));
        return String.valueOf(machineCode);
    }
}
