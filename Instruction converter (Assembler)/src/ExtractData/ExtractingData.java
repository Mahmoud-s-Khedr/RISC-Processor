package ExtractData;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExtractingData {

    public static void extract (List<String> instructions, StringBuilder machineCodeTotal, StringBuilder machineCodeTotalHEXA){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String folderName = dateFormat.format(new Date());
        File folder = new File(folderName);
        if (!folder.exists()) {
            folder.mkdir();
        }

        File file = new File(folder, "output.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(instructions.toString());
            writer.newLine();
            writer.write(machineCodeTotal.toString());
            writer.newLine();
            writer.write(machineCodeTotalHEXA.toString());
            writer.newLine();
        }
        catch (IOException e) {
            e.printStackTrace();
        }


        file = new File(folder, "fullMachineCode.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(machineCodeTotal.toString());
        }
        catch (IOException e) {
            e.printStackTrace();
        }


        file = new File(folder, "fullMachineCode-HEXA.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(machineCodeTotalHEXA.toString());
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
