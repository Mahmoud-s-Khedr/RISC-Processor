package Types;

import BinaryOperations.BinaryOp;

public class Itype {

    public enum opCode {
        andi(4),
        ori(5),
        xori(6),
        addi(7),
        sll(8),
        srl(9),
        sra(10),
        ror(11);
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
        String stringConverted = null;
        String[] parts = instruction.split(" ");
        String str = parts[0];
        String[] arr = instruction.substring(parts[0].length(), instruction.length()).split(",");
        op = opCode.valueOf(str).op;
        rs = arr[1].trim().charAt(1) - 48;
        rd = arr[0].trim().charAt(1) - 48;
        try {
            imm = Integer.valueOf(arr[2].trim());
        }catch (Exception e){
            System.out.println("I type shouldn't have 3 registers it has 2 reg and 1 imm value");
            return "";
        }
        machineCode.append(BinaryOp.binaryConverter(op, 5));
        if (op == 7) {
            stringConverted = BinaryOp.binaryConverterSigned(imm, 5);
        }
        else
            stringConverted = BinaryOp.binaryConverter(imm, 5);
        if (stringConverted.equals(""))
            return "";

        machineCode.append(stringConverted);

        machineCode.append(BinaryOp.binaryConverter(rs, 3));
        machineCode.append(BinaryOp.binaryConverter(rd, 3));
        return String.valueOf(machineCode);
    }
}