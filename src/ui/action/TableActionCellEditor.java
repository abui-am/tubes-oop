package ui.action;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Abui
 */
public class TableActionCellEditor extends DefaultCellEditor {
    
    private TableActionEvent event;

    public TableActionCellEditor(TableActionEvent ev) {
        super(new JCheckBox());
        this.event = ev;
    }

    
    @Override   
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
       PanelAction action = new PanelAction();
       action.initEvent(event, row);
       action.setBackground(table.getSelectionBackground());
       return action;
    }
    
}
