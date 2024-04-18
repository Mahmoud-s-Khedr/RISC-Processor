import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import Managing_Instructions.Deal_With_Input;

public class Main {
    public static void main(String[] args) {
        // Text area for entering instructions
        JTextArea textArea = new JTextArea(10, 40);
        textArea.setToolTipText("Enter your MIPS assembly instructions here");

        JScrollPane inputScrollPane = new JScrollPane(textArea);

        // Text area for displaying hexadecimal code
        JTextArea hexTextArea = new JTextArea(10, 40);
        hexTextArea.setEditable(false);
        hexTextArea.setToolTipText("Hexadecimal machine code will be displayed here");

        JScrollPane hexScrollPane = new JScrollPane(hexTextArea);

        // Label for the first pane
        JLabel inputLabel = new JLabel("Input Instructions");
        JPanel inputLabelPanel = new JPanel();
        inputLabelPanel.add(inputLabel);

        // Label for the middle section
        JLabel hexLabel = new JLabel("Hexadecimal Result");
        JPanel hexPanel = new JPanel();
        hexPanel.add(hexLabel);

        // Button to trigger the translation
        JButton translateButton = new JButton("Convert to Hexadecimal");
        translateButton.addActionListener(e -> {
            try {
                String instructions = textArea.getText();
                // Pass the instructions to another class for processing
                Deal_With_Input processor = new Deal_With_Input(instructions);
                String processedInstructions = Deal_With_Input.convert();
                hexTextArea.setText(processedInstructions); // Display the processed instructions
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error converting instructions: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Button to clear the text areas
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> {
            textArea.setText("");
            hexTextArea.setText("");
            textArea.requestFocusInWindow();
        });

        // Button to open a file
        JButton openButton = new JButton("Open");
        openButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
            fileChooser.setFileFilter(filter);
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                // Read the file and set the text in the text area
                try {
                    String content = new String(java.nio.file.Files.readAllBytes(selectedFile.toPath()));
                    textArea.setText(content);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error opening file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Button to save the hexadecimal code to a file
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
            fileChooser.setFileFilter(filter);
            int returnValue = fileChooser.showSaveDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    // Prepend "v2.0 raw" to the hexTextArea content
                    writer.write("v2.0 raw\n" + hexTextArea.getText());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error saving file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        // Panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(translateButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);

        // Split pane to divide the screen in half
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, inputScrollPane, hexScrollPane);
        splitPane.setResizeWeight(0.5); // Equal split between the two components

        // Panel for the label and the split paness
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(inputLabelPanel);
        mainPanel.add(inputScrollPane);
        mainPanel.add(hexPanel);
        mainPanel.add(hexScrollPane);
        mainPanel.add(buttonPanel);

        // Create and set up the frame
        JFrame frame = new JFrame("MIPS Assembly Instruction Processor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);

        textArea.requestFocusInWindow(); // Set initial focus to the text area
    }
}