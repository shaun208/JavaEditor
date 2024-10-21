import com.profesorfalken.jpowershell.*;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;


//

import java.util.Scanner;

class CodePanel extends JTextPane implements KeyListener {
    JMenuBar mb;
    JMenu x;
    System system;
    File file;
    JTextPane area;
    JTextArea compileArea = new JTextArea();
    JMenuItem m1, m2, m3, m4, m5;
    String classpath;
    JProgressBar progress;
    StyledDocument doc;
    JMenuBar p = new JMenuBar();
    ArrayList <File> fileTab = new ArrayList<File>();
    JLabel fileName = new JLabel("");
    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {

        // Check if the typed key is the opening brace '{'
        if (e.getKeyChar() == '{') {
            // Delay the insertion of the closing brace '}' until after '{' is inserted
            SwingUtilities.invokeLater(() -> {
                try {
                    // Get the current caret position (after the '{' is inserted)
                    int caretPosition = area.getCaretPosition();
                    // Insert the closing brace '}' after the current caret position
                    area.getDocument().insertString(caretPosition, "}", null);

                    // Optionally, move the caret back between the braces
                    area.setCaretPosition(caretPosition);
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
            });
        }
        if (e.getKeyChar() == '"') {
            // Delay the insertion of the closing brace '}' until after '{' is inserted
            SwingUtilities.invokeLater(() -> {
                try {
                    // Get the current caret position (after the '{' is inserted)
                    int caretPosition = area.getCaretPosition();
                    // Insert the closing brace '}' after the current caret position
                    area.getDocument().insertString(caretPosition, '"'+"", null);

                    // Optionally, move the caret back between the braces
                    area.setCaretPosition(caretPosition);
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
            });
        }
        if (e.getKeyChar() == '[') {
            // Delay the insertion of the closing brace '}' until after '{' is inserted
            SwingUtilities.invokeLater(() -> {
                try {
                    // Get the current caret position (after the '{' is inserted)
                    int caretPosition = area.getCaretPosition();
                    // Insert the closing brace '}' after the current caret position
                    area.getDocument().insertString(caretPosition, "]", null);

                    // Optionally, move the caret back between the braces
                    area.setCaretPosition(caretPosition);
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
            });
        }
        if (e.getKeyChar() == '(') {
            // Delay the insertion of the closing brace '}' until after '{' is inserted
            SwingUtilities.invokeLater(() -> {
                try {
                    // Get the current caret position (after the '{' is inserted)
                    int caretPosition = area.getCaretPosition();
                    // Insert the closing brace '}' after the current caret position
                    area.getDocument().insertString(caretPosition, ")", null);

                    // Optionally, move the caret back between the braces
                    area.setCaretPosition(caretPosition);
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
            });
        }

    }

    public CodePanel() {
        setLayout(new BorderLayout());
        JScrollPane scroller = new JScrollPane(compileArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        // Initialize components
        mb = new JMenuBar();
        x = new JMenu("Menu");
        area = new JTextPane();
        progress = new JProgressBar();
        progress.setIndeterminate(true);
        progress.setVisible(false);
        Dimension prefSize = progress.getPreferredSize();
        prefSize.width = 10;
        area.setFont(new Font("SansSerif",Font.PLAIN, 16));
        prefSize.height = 10;


        progress.setPreferredSize(prefSize);
        // Create menu items
        m1 = new JMenuItem("Open File");
        m2 = new JMenuItem("Run");
        m3 = new JMenuItem("Quit");
        m4 = new JMenuItem("Add New Classpath");
        m5 = new JMenuItem("Save");
        mb.add(x);
        mb.add(Box.createRigidArea(new Dimension(600, 10)));
        mb.add(progress);
        mb.add(Box.createRigidArea(new Dimension(5, 10)));
        area.addKeyListener(this);

        // Add menu items to menu
        x.add(m1);
        x.add(m2);
        x.add(m5);
        x.add(m4);
        x.add(m3);
        // Add menu to menu bar


        m1.addActionListener(new Listener_openFile() {});
        m2.addActionListener(new Listener_compileFile() {});
        m3.addActionListener(new Listener_quit() {});
        m4.addActionListener(new Listener_AddClassPath() {});
        m5.addActionListener(new Listener_saveFile() {});
        compileArea.setEditable(false);
        // Add the JTextArea to the center of the panel
        add(new JScrollPane(area), BorderLayout.CENTER);
        compileArea.setPreferredSize(new Dimension(400, 50));
        add(scroller, BorderLayout.SOUTH);
    }

    public JMenuBar getMenuBar() {
        return mb;  // Allow main class to add this to the JFrame
    }

    private class Listener_saveFile implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            if (file == null) {
                String[] splitCode = area.getText().split(" ");
                System.out.println(Arrays.asList(splitCode).indexOf("class"));
                int z = Arrays.asList(splitCode).indexOf("class")+1;
                JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int option = fc.showOpenDialog(null);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File file1 = fc.getSelectedFile();
                    file = new File(file1.getAbsolutePath() + "\\" + splitCode[z] + ".java");
                    x.setText(file.getName());
                    try {
                        FileWriter ft = new FileWriter(file);
                        ft.write(area.getText());
                        ft.close();
                    } catch (IOException o) {
                        compileArea.setText("Error: Could not extract data from File: Error INVALIDFILE");
                    }
                }
            } else {
                try {
                    FileWriter ft = new FileWriter(file);
                    ft.write(area.getText());
                    ft.close();
                } catch (IOException o) {
                    area.setText("Error: Could not extract data from File: Error INVALIDFILE");
                }
            }
        }
    }
    private class Listener_openFile implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Scanner myReader;
            JFileChooser fc = new JFileChooser();
            fc.showOpenDialog(null);


            File f = fc.getSelectedFile();
            System.out.println(f.getAbsolutePath());

            file = f;
            String data = "";
            try {
                myReader = new Scanner(file);
                while (myReader.hasNextLine()) {
                    data += myReader.nextLine() +"\n";
                }
                area.setText(data);

            }
            catch(FileNotFoundException t){
                area.setText("Shaun");
            }
            x.setText(file.getName());

//
//         }
//         catch(FileNotFoundException o) {
//            System.out.println("SKIBIDI");
//      }
        }
    }
    private class Listener_AddClassPath implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser fc = new JFileChooser();
            fc.showOpenDialog(null);
            classpath = fc.getSelectedFile().getAbsolutePath();


        }
    }
    private class Listener_compileFile implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            compileArea.setText("");
            if (file != null) {
                // Start the background task to compile and run the file
                new CompileAndRunWorker().execute();
            } else {
                JFileChooser fc = new JFileChooser();
                fc.showOpenDialog(null);
                File f = fc.getSelectedFile();
                file = f;
                x.setText(file.getName());
                if (f != null) {
                    new CompileAndRunWorker().execute();
                } else {
                    compileArea.setText("No file selected to compile.");
                }
            }
        }
    }
    private static class Listener_quit implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    // Background worker for compiling and running the file
    private class CompileAndRunWorker extends SwingWorker<Void, Void> {


        @Override
        protected Void doInBackground() throws IOException {
            SwingUtilities.invokeLater(() -> progress.setVisible(true));
            FileWriter ft = new FileWriter(file);
            ft.write(area.getText());
            ft.close();
            String oldPath = file.getAbsolutePath();
            String path = "";
            String oldName = file.getName();
            path = oldPath.replace(oldName, "");
//            System.out.println("path " + path.substring(0, path.length() - 1));
//            System.out.println("oldpath " + oldPath);
//            System.out.println("oldname " + oldName);
            try (PowerShell ps = PowerShell.openSession()) {
                // Change directory
                ps.executeCommand("cd " + '"' + path + '"');
//                System.out.println(ps.executeCommand("ls").getCommandOutput());
                // Compile the Java file
                if(classpath == null) {

                    ps.executeCommand("javac " + oldName);
                    PowerShellResponse run = ps.executeCommand("java " + oldName);
                    compileArea.setText("Execution finished\n");
                    compileArea.append("Classpath not initialized\n");
                    if(run.getCommandOutput() != null || !Objects.equals(run.getCommandOutput(), "")) {
                        compileArea.append("Compiler returned: "+ run.getCommandOutput());
                    }
                }
                if(classpath != null) {
                    ps.executeCommand("javac " + "-cp " + classpath + " " + oldName);
                    PowerShellResponse run = ps.executeCommand("java "+ "-cp " + classpath + " " + oldName);
                    compileArea.setText("Execution finished\n");
                    compileArea.append("Classpath initialized\n");
                    if(run.getCommandOutput() != null || !Objects.equals(run.getCommandOutput(), "")) {
                        compileArea.append("Compiler returned: "+ run.getCommandOutput());
                    }
                }

                // If compilation is successful, run the Java class


                return null;
            }
        }

        @Override
        protected void done() {
            progress.setVisible(false);
            // Task completed, we can update the UI here if necessary
            compileArea.append("\nCompilation and execution finished.");
        }


        private void publishRunResult(String output) {
            SwingUtilities.invokeLater(() -> {
                compileArea.append("\nProgram Output:\n" + output);
            });
        }
    }
}
