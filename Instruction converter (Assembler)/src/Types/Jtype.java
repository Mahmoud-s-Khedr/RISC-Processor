package Types;

import BinaryOperations.BinaryOp;

import java.util.List;

public class Jtype {
    public enum opCode {
        j(30),
        jal(31);
        int op;

        opCode(int op) {
            this.op = op;
        }
    }

    public static String execute(List <String> instructions, int index) {
        StringBuilder machineCode = new StringBuilder();
            String instruction = instructions.get(index);
            int op = 0;
            String[] parts = instruction.split(" ");
                String str = parts[0];
                op = opCode.valueOf(str).op;
                machineCode.append(BinaryOp.binaryConverter(op, 5));
                String label = parts[1];
                int targetIndex = -1;
                for (int j = 0; j < instructions.size(); j++) {
                    String x = instructions.get(j);
                    if (x.startsWith(label + ":")) {
                        targetIndex = j;
                        break;
                    }
                }
                if (targetIndex != -1) {
                    int currentAddress = index;
                    int targetAddress = targetIndex;
                    int offset = targetAddress - currentAddress;
                    String immConverted = BinaryOp.binaryConverterSigned(offset, 11);
                    machineCode.append(immConverted);
                } else {
                    System.out.println("Label not found: " + label);
                }
        return String.valueOf(machineCode);
    }
}