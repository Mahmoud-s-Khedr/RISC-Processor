package Types;

import BinaryOperations.BinaryOp;

import java.util.List;

public class Branches {
    public enum opCode {
        beq(14),
        bne(15),
        blt(16),
        bge(17);
        int op;
        opCode(int op) {
            this.op = op;
        }
    }
    public static String execute(List <String> instructions, int index) {
        StringBuilder machineCode = new StringBuilder();
        String instruction;
        String[] parts = null;
        String[] arr = null;
        int rs = 0;
        int rd = 0;

            instruction = instructions.get(index);
            int op = 0;
                parts = instruction.split(" ");
                String str = parts[0];
                op = opCode.valueOf(str).op;
                machineCode.append(BinaryOp.binaryConverter(op, 5));
                arr = parts[1].split(",");
                String label = arr[2];
                
                int targetIndex = -1;
                for (int j = 0; j < instructions.size(); j++) {
                    if (instructions.get(j).startsWith(label + ":")) {
                        targetIndex = j;
                        break;
                    }
                }
                if (targetIndex != -1) {
                    int currentAddress = index;
                    int targetAddress = targetIndex;
                    int offset = targetAddress - currentAddress;
                    String immConverted = BinaryOp.binaryConverterSigned(offset, 5);
                    machineCode.append(immConverted);
                } else {
                    System.out.println("Label not found: " + label);
                }
        rs = arr[0].trim().charAt(1) - 48;
        rd = arr[1].trim().charAt(1) - 48;
        machineCode.append(BinaryOp.binaryConverter(rs, 3));
        machineCode.append(BinaryOp.binaryConverter(rd, 3));

        return String.valueOf(machineCode);
    }
}