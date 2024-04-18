package Types;

import BinaryOperations.BinaryOp;
public class Load_And_Store {
    public enum opCode {
        lw(12),
        sw(13);
        int op;

        opCode(int op) {
            this.op = op;
        }
    }

    public static String execute(String instruction) {
        StringBuilder machineCode = new StringBuilder();
        int op = 0;
        int rs = 0;
        int rd = 0;
        int imm = 0;
        String[] parts = instruction.split(" ");
        String str = parts[0];
        op = opCode.valueOf(str).op;

        String[] arr1 = parts[1].split(",");
        rd = arr1[0].trim().charAt(1) - 48;

        String[] arr2 = arr1[1].split("\\(");
        rs = arr2[1].charAt(1) - 48;
        imm = Integer.parseInt(arr2[0]);
        machineCode.append(BinaryOp.binaryConverter(op, 5));
        String stringConverted = BinaryOp.binaryConverterSigned(imm, 5);
        if (stringConverted.equals(""))
            return "";
        machineCode.append(stringConverted);
        machineCode.append(BinaryOp.binaryConverter(rs, 3));
        machineCode.append(BinaryOp.binaryConverter(rd, 3));
        return String.valueOf(machineCode);
    }
}