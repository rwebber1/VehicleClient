package GUI;

import javax.swing.*;
import java.awt.event.*;

public class addDialog extends JDialog {
    private JPanel contentPane;
    private JButton addButton;
    private JButton buttonCancel;
    private JTextField idField;
    private JTextField yearField;
    private JTextField makeField;
    private JTextField modelField;
    private JLabel IDLabel;
    private JLabel YEARLabel;
    private JLabel MAKELabel;
    private JLabel MODELLabel;
    Object[] record = new Object[4];

    public addDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(addButton);


        addButton.addActionListener(new ActionListener() {
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

        idField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyPressed(e);
                validateInput();
            }
        });
        yearField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyPressed(e);
                validateInput();
            }
        });
        makeField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyPressed(e);
                validateInput();
            }
        });
        modelField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyPressed(e);
                validateInput();
            }
        });
    }

    public Object[] onOK() {

        //Retrieve User Input
        record[0] = Integer.parseInt(idField.getText());
        record[1] = Integer.parseInt(yearField.getText());
        record[2] = makeField.getText();
        record[3] = modelField.getText();

        dispose();

        return record;
    }

    private void onCancel() {
        dispose();
    }

    private void validateInput() {
        //Validation Checks
        try {
            if ((Integer.parseInt(yearField.getText()) >= 1950 && Integer.parseInt(yearField.getText()) <= 2050) &&
                    (!(makeField.getText().isEmpty()) && !(modelField.getText().isEmpty()))) {
                addButton.setEnabled(true);
            }
            else{addButton.setEnabled(false);}
        } catch (Exception error){
            addButton.setEnabled(false);
        }
    }
}
