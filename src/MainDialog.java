import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.DataFormatException;

public class MainDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JRadioButton linerRadioButton;
    private JRadioButton exponentialRadioButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextArea showSeriesTextArea;
    private JTextArea firstElementTextArea;
    private JTextArea differenceTextArea;
    private JTextArea numberOfElementsTextArea;
    private JTextField textField3;
    private JButton saveButton;
    private Liner liner = new Liner();
    private Exponential exponential = new Exponential();

    public MainDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        exponentialRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                differenceTextArea.setText("Ratio");
            }
        });
        linerRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                differenceTextArea.setText("Difference");
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveIntoFile();
            }
        });
    }

    private void saveIntoFile(){
        JFileChooser fileChooser = new JFileChooser();
        int choose = fileChooser.showDialog(null, "Choose File");
        if(choose == JFileChooser.APPROVE_OPTION){
            if(linerRadioButton.isSelected()){
                try{liner.writeIntoFile(fileChooser.getSelectedFile().getAbsolutePath());}
                catch (FileNotFoundException fnfe) {JOptionPane.showMessageDialog(null, "File not found");}
                catch (IOException ioe){ JOptionPane.showMessageDialog(null, "Writing error");}
            } else {
                try{exponential.writeIntoFile(fileChooser.getSelectedFile().getAbsolutePath());}
                catch (FileNotFoundException fnfe) {JOptionPane.showMessageDialog(null, "File not found");}
                catch (IOException ioe){ JOptionPane.showMessageDialog(null, "Writing error");}
            }
        }
    }

    private void onOK() {
        try {
            if (linerRadioButton.isSelected()) {
                if(!textField1.getText().isEmpty() && !textField2.getText().isEmpty() && !textField3.getText().isEmpty()) {
                    liner.setFirstElement(Double.parseDouble(textField1.getText()));
                    liner.setDifference(Double.parseDouble(textField2.getText()));
                    liner.setNumberOfElements(Integer.parseInt(textField3.getText()));
                    showSeriesTextArea.setText("Sum: " + String.valueOf(liner.progressionSum()));
                    showSeriesTextArea.append('\n' + "Elements: " + liner.toString());
                }
            } else {
                if (!textField1.getText().isEmpty() && !textField2.getText().isEmpty() && !textField3.getText().isEmpty()) {
                    exponential.setFirstElement(Double.parseDouble(textField1.getText()));
                    exponential.setRatio(Double.parseDouble(textField2.getText()));
                    exponential.setNumberOfElements(Integer.parseInt(textField3.getText()));
                    showSeriesTextArea.setText("Sum: " + String.valueOf(exponential.progressionSum()));
                    showSeriesTextArea.append('\n' + "Elements: " + exponential.toString());
                }
            }
        } catch(NumberFormatException e){JOptionPane.showMessageDialog(null, "Wrong number format");}
        catch (DataFormatException e) {JOptionPane.showMessageDialog(null, "Wrong length of progression");}
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        MainDialog dialog = new MainDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
