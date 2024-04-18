package Managing_Instructions;
import BinaryOperations.BinaryOp;
import ExtractData.ExtractingData;
import Types.*;

import java.util.List;
import java.util.Scanner;

public class instructionsRedirect {
    static boolean isBranch = false;
    static boolean isJump = false;
    static int counter = 0;
    public static StringBuilder machineCodeTotal = new StringBuilder();
    public static StringBuilder machineCodeTotalHEXA = new StringBuilder();

    public static void divideInstructions (List<String> arr_Instructions) {
        String machineCode = null;
        String machineCodeHEXA = null;
        for (String line : arr_Instructions) {
            String[] arr = line.split(":");
            if (arr.length == 1) {
                machineCode = redirect(line); // Assuming execute() returns machine code
            } else {
                machineCode = redirect(arr[1]);
            }
            if (isJump) {
                machineCode = Jtype.execute(arr_Instructions, counter);
                isJump = false;
            }
            if (isBranch) {
                machineCode = Branches.execute(arr_Instructions, counter);
                isBranch = false;
            }
            machineCodeHEXA = BinaryOp.binaryToHexadecimal(machineCode);
            counter++;
            if (machineCode == "" || machineCode == null)
                continue;
            machineCodeTotal.append(machineCode + "\n");
            machineCodeTotalHEXA.append(machineCodeHEXA + "\n");
            System.out.println(line);
            //                System.out.println("Machine code for instruction:    "  + line  + " ----------->  " + machineCode + "with hexadecimal value :  0x" + BinaryOperations.binaryToHexadecimal(machineCode));
        }
        System.out.println("\n\n");
        System.out.println(machineCodeTotal);
        System.out.println(machineCodeTotalHEXA);



        ExtractingData.extract(arr_Instructions, machineCodeTotal, machineCodeTotalHEXA);
    }

    public static String redirect (String instruction) {
        instruction = instruction.trim();
        String machineCode = null;
        String[] parts = instruction.split(" ");
        String str = parts[0]; // Assuming opcode is the first word and converting to lowercase for case-insensitivity
        switch (str) {
            case "and":
            case "or":
            case "xor":
            case "nor":
            case "add":
            case "sub":
            case "slt":
            case "sltu":
                machineCode = Rtype.execute(instruction);
                break;
            case "jr":
                machineCode = JR.execute(instruction);
                break;

            case "andi":
            case "ori":
            case "xori":
            case "addi":
            case "sll":
            case "srl":
            case "sra":
            case "ror":
                machineCode = Itype.execute(instruction);
                break;
            case "lw":
            case "sw":
                machineCode = Load_And_Store.execute(instruction);
                break;

            case "lui":
                machineCode = LUI.execute(instruction);
                break;

            case "j":
            case "jal":
                isJump = true;
                break;

            case "beq":
            case "bne":
            case "blt":
            case "bge":
                isBranch = true;
                break;
            default:
                System.out.println("Unknown instruction: " + instruction);
                break;
        }
        return machineCode;
    }

    public static StringBuilder getMachineCodeTotalHEXA() {
        return machineCodeTotalHEXA;
    }
}