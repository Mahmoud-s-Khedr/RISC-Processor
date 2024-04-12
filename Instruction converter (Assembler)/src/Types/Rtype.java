package Types;

import BinaryOperations.BinaryOp;
public class Rtype {

    public enum function {
        and(0),
        or(1),
        xor(2),
        nor(3),
        add(0),
        sub(1),
        slt(2),
        sltu(3);
        int fn;
        function(int fn) {
            this.fn = fn;
        }
    }

    public enum opCode {
        and(0),
        or(0),
        xor(0),
        nor(0),
        add(1),
        sub(1),
        slt(1),
        sltu(1);
        int op;
        opCode(int op) {
            this.op = op;
        }
    }

    public static String execute(String instruction) {
        StringBuilder machineCode = new StringBuilder();
        int op = 0;
        int fn = 0;
        int rs = 0;
        int rt = 0;
        int rd = 0;
        String[] parts = instruction.split(" ");
        String str = parts[0];
        String[] arr = instruction.trim().substring(parts[0].length() , instruction.length()).split(",");
        op = opCode.valueOf(str).op;
        fn = function.valueOf(str).fn;
        rd = arr[0].trim().charAt(1) - 48;
        rs = arr[1].trim().charAt(1) - 48;
        rt = arr[2].trim().charAt(1) - 48;
        machineCode.append(BinaryOp.binaryConverter(op, 5));
        machineCode.append(BinaryOp.binaryConverter(fn, 2));
        machineCode.append(BinaryOp.binaryConverter(rd, 3));
        machineCode.append(BinaryOp.binaryConverter(rs, 3));
        machineCode.append(BinaryOp.binaryConverter(rt, 3));
        return String.valueOf(machineCode);
    }
}