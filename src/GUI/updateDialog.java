package GUI;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.*;

public class updateDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel idLabel;
    private JTextField idField;
    private JTextField yearField;
    private JLabel yearLabel;
    private JTextField makeField;
    private JTextField modelField;
    private JLabel makeLabel;
    private JLabel modelLabel;

    public updateDialog(JTable tableData) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        //populate with current record data
        idField.setText(tableData.getValueAt(tableData.getSelectedRow(), 0).toString());
        yearField.setText(tableData.getValueAt(tableData.getSelectedRow(), 1).toString());
        makeField.setText(tableData.getValueAt(tableData.getSelectedRow(), 2).toString());
        modelField.setText(tableData.getValueAt(tableData.getSelectedRow(), 3).toString());

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
    }

    public Object[] onOK() {
        Object[] record = new Object[4];
        record[0] = idField.getText();
        record[1] = yearField.getText();
        record[2] = makeField.getText();
        record[3] = modelField.getText();
        dispose();

        return record;
    }

    private void onCancel() {
        dispose();
    }

}
