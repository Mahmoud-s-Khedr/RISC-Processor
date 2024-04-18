package Managing_Instructions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Deal_With_Input {
    static String input = null;
    static List<String> arr_Instructions = null;
    static boolean insideComment = false;
    static boolean isLabel = false; // Flag to indicate if the current line is a label

    static boolean isLabelFirstLine = false; // Flag to indicate if the current line is a label

    public Deal_With_Input(String input) {
        this.input = input;
    }

    private static List<String> parseInstructions(String input) {
        List<String> instructions = new ArrayList<>();
        StringBuilder currentInstruction = new StringBuilder();
        String label;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == '#' && !insideComment) {
                insideComment = true;
                continue; // Skip to next iteration when encountering comment
            }
            else if (c == '\n' && !isLabelFirstLine) {
                if (String.valueOf(currentInstruction).trim().length() > 0) {
                    instructions.add(formatInstruction(currentInstruction.toString().trim()));
                }
                currentInstruction = new StringBuilder();
                isLabel = false;
                insideComment = false;
            }
            else if (!insideComment) {
                if (c == ':') {
                    currentInstruction.append(c);
                    isLabel = true;
                    isLabelFirstLine = true;
                    if (i == input.length()){
                        System.out.println("Invalid label");
                        break;
                    }
                  }
                else {
                    if (c != '\n')
                    currentInstruction.append(c);
                    isLabelFirstLine = false;
                }
            }
        }

        // Add the last instruction if it's not empty
        if (currentInstruction.length() > 0) {
            instructions.add(formatInstruction(currentInstruction.toString().trim()));
        }
        return instructions;
    }

    // Helper method to format the instruction with one whitespace after keyword
    private static String formatInstruction(String instruction) {
        if (isLabel){
            return addSpacesIsLabel(instruction.trim()); // Call separate function for whitespace handling
        }
        return addSpaces(instruction.trim()); // Call separate function for whitespace handling
    }

    // Function to add spaces between keyword and parameters
    private static String addSpaces(String instruction) {
        List<String> keywords = Arrays.asList("AND", "OR", "XOR", "Nor", "Add", "Sub", "SLT", "SLTU",
                "Andl", "ORI", "XORI", "Addl", "SLL", "SRL", "ROR",
                "BEQ", "BNE", "BLT", "BGE", "LUI", "JAL");

        StringBuilder keyword = new StringBuilder();
        StringBuilder parameters = new StringBuilder();
        boolean inKeyword = true; // Flag to indicate if currently processing keyword
        int index = 0;

        for (int i = 0; i < instruction.length(); i++) {
            char c = instruction.charAt(i);
            if (Character.isLetter(c)) {
                keyword.append(c);
            } else if (Character.isWhitespace(c)&& String.valueOf(keyword).length() > 0) {
                index = i;
                break;
            }
        }
        for (int i = index; i < instruction.length(); i++) {
            char c = instruction.charAt(i);
            if (!Character.isWhitespace(c))
                parameters.append(c);
            }
        return String.valueOf(keyword).toLowerCase() + " " + parameters.toString();
    }

    private static String addSpacesIsLabel(String instruction) {
        List<String> keywords = Arrays.asList("AND", "OR", "XOR", "Nor", "Add", "Sub", "SLT", "SLTU",
                "Andl", "ORI", "XORI", "Addl", "SLL", "SRL", "ROR",
                "BEQ", "BNE", "BLT", "BGE", "LUI", "JAL");

        StringBuilder label = new StringBuilder();
        int index = 0;
        for (int i = 0; i < instruction.length(); i++) {
            char c = instruction.charAt(i);
            if (c == ':') {
                index = i;
                break;
            }
            label.append(c);
        }

        return label + ":" +addSpaces(instruction.substring(index));
    }


    public static String convert() {
        arr_Instructions = parseInstructions(input);
        // Handle instructions in arr_Instructions (call instructionsRedirect functions)
        instructionsRedirect.divideInstructions(arr_Instructions);
        return String.valueOf(instructionsRedirect.machineCodeTotalHEXA);
    }
}
