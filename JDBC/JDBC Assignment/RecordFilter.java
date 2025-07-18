import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

class RecordFilter extends JFrame implements KeyListener
{
    JLabel lid;
    JTextField tb;
    JTable ta;
    Connection cn;
    int columns;
    JScrollPane sp;
    DefaultTableModel model;

    public RecordFilter()
    {
        setSize(500,400);
        setResizable(true);
        setLayout(null);
        lid=new JLabel("Enter ID/Phone:");
        lid.setBounds(100,50,100,27);
        add(lid);
        tb=new JTextField();
        tb.setBounds(210,50,150,27);
        add(tb);
        tb.addKeyListener(this);

        try
        {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to MySQL database
            // Replace 'your_database_name' with actual DB name
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database_name", "system", "shrik");

            Statement ps = cn.createStatement();
            ResultSet rst = ps.executeQuery("select * from Employee");
            ResultSetMetaData metaData = rst.getMetaData();
            columns = metaData.getColumnCount();

            model = new DefaultTableModel();
            ta = new JTable(model);

            for (int i = 1; i <= columns; i++)
                model.addColumn(metaData.getColumnName(i));
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }

        sp = new JScrollPane(ta);
        sp.setBounds(50,100,400,150);
        add(sp);
        setVisible(true);
    }

    public void keyPressed(KeyEvent ke){}
    public void keyTyped(KeyEvent ke){}

    public void keyReleased(KeyEvent ke)
    {
        String str = tb.getText();
        if(str.equals(""))
        {
            clearTable();
            return;
        }
        try
        {
            clearTable();
            PreparedStatement ps = cn.prepareStatement("select * from Employee where eid like ? or phone like ?");
            ps.setString(1, str + "%");
            ps.setString(2, str + "%");
            ResultSet rst = ps.executeQuery();

            while(rst.next())
            {
                Vector row = new Vector();
                for (int i = 1; i <= columns; i++)
                {
                    row.add(rst.getString(i));
                }
                model.addRow(row);
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }

    void clearTable()
    {
        for(int i=0; i<model.getRowCount(); )
            model.removeRow(i);
    }

    public static void main(String [] args)
    {
        new RecordFilter();
    }
}
