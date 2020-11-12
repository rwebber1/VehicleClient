package GUI;

import Vehicle.Vehicle;
import VehicleDao.VehicleDaoImplementation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;


public class VehicleClient extends JPanel {
    private DefaultTableModel model = new DefaultTableModel();
    private JTable table = new JTable() {
        public boolean editCellAt(int row, int column){
        return false;
    }};

    public VehicleClient() throws SQLException{
        setLayout(new BorderLayout(5, 5));
        add(initTable(), BorderLayout.CENTER);
        add(initButtons(), BorderLayout.SOUTH);
    }

    private JPanel initTable() throws SQLException{
        JPanel panel = new JPanel();

        //model setup
        Object[] columns = {"ID", "YEAR", "MAKE", "MODEL"};
        model.setColumnIdentifiers(columns);

        //Retrieve and populate model with vehicle data
        VehicleDaoImplementation vDao = new VehicleDaoImplementation();
        List<Vehicle> data = vDao.getVehicles();
        Object[] rowData = new Object[4];
        for (Vehicle datum : data) {
            rowData[0] = datum.getId();
            rowData[1] = datum.getYear();
            rowData[2] = datum.getMake();
            rowData[3] = datum.getModel();
            model.addRow(rowData);
        }

        //Table setup
        table = new JTable(model);
        table.setAutoCreateRowSorter(true);
        table.setCellSelectionEnabled(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        return panel;
    }

    private JPanel initButtons() {
        //Panel setup
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));

        //Buttons setup
        JButton addButton = new JButton("Add");
        panel.add(addButton);
        addButton.addActionListener(new ButtonHandler());

        JButton updateButton = new JButton("Update");
        panel.add(updateButton);
        updateButton.addActionListener(new ButtonHandler());

        JButton deleteButton = new JButton("Delete");
        panel.add(deleteButton);
        deleteButton.addActionListener(new ButtonHandler());

        return panel;
    }
    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            VehicleDaoImplementation vDao = new VehicleDaoImplementation();

            switch(e.getActionCommand()){
                case "Add" :
                    addDialog input = new addDialog();
                    input.pack();
                    input.setVisible(true);
                    Object[] addRecord = new Object[4];
                    try {
                        addRecord = input.onOK();

                        if (addRecord != null) {
                            //add to table
                            model.addRow(addRecord);

                            //add to database
                            try {
                                vDao.addVehicle(new Vehicle(Integer.parseInt(addRecord[0].toString()), Integer.parseInt(addRecord[1].toString()), addRecord[2].toString(), addRecord[3].toString()));
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                        }
                    }
                    catch (Exception error){
                    }
                    break;
                case "Update" :
                    if(table.getSelectedRow() == -1) {
                        JOptionPane.showMessageDialog(null, "Error, no record selected");
                    }
                    else {
                        updateDialog update = new updateDialog(table);
                        update.pack();
                        update.setVisible(true);
                        Object[] rowUpdate = new Object[4];
                        rowUpdate = update.onOK();

                        //update table
                        model.setValueAt(rowUpdate[0], table.getSelectedRow(), 0);
                        model.setValueAt(rowUpdate[1], table.getSelectedRow(), 1);
                        model.setValueAt(rowUpdate[2], table.getSelectedRow(), 2);
                        model.setValueAt(rowUpdate[3], table.getSelectedRow(), 3);

                        //update database
                        try {
                            vDao.updateVehicle(new Vehicle(Integer.parseInt(rowUpdate[0].toString()), Integer.parseInt(rowUpdate[1].toString()), rowUpdate[2].toString(), rowUpdate[3].toString()));
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                    break;
                case "Delete":
                    if(table.getSelectedRow() == -1) {
                        JOptionPane.showMessageDialog(null, "Error, no record selected");
                    }
                    else {
                        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete record with ID: " + table.getValueAt(table.getSelectedRow(), 0) + "", "Delete Confirmation", JOptionPane.YES_NO_OPTION);
                        if (confirm == 0) {
                        try {
                            vDao.deleteVehicle((Integer) table.getValueAt(table.getSelectedRow(), 0));
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                            model.removeRow(table.getSelectedRow());
                        }
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Error Message");
            }
        }
    }
}