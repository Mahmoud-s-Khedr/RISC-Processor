package Types;

import BinaryOperations.BinaryOp;
public class Jtype {
    public enum opCode {
        j(30),
        jal(31);
        int op;

        opCode(int op) {
            this.op = op;
        }
    }

    public static String execute(String instructions, int index) {
        String[] lines = String.valueOf(instructions).split("\\r?\\n"); // Split input into lines
        StringBuilder machineCode = new StringBuilder();

            String instruction = lines[index];
            instruction = instruction.toLowerCase().trim();
            int op = 0;
            String[] parts = instruction.split(" ");
                String str = parts[0];
                op = opCode.valueOf(str).op;
                machineCode.append(BinaryOp.binaryConverter(op, 5));
                String label = parts[1];
                int targetIndex = -1;
                for (int j = 0; j < lines.length; j++) {
                    String x = lines[j].trim();
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