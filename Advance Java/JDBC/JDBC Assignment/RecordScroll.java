import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
import java.awt.*;

class RecordScroll extends JFrame implements AdjustmentListener
{
    Connection cn;
    Statement st;
    Scrollbar bar;
    JLabel [] la=new JLabel[4];
    JTextField [] tb=new JTextField[4];
    ResultSet rst;
    JLabel rv;
    int nor=0;

    public RecordScroll()
    {
        setSize(400,400);
        setResizable(true);
        setLayout(null);
        String [] str={"Sid","Name","City","Age"};
        int y=50;
        for(int i=0;i<la.length;i++)
        {
            la[i]=new JLabel(str[i]);
            la[i].setBounds(100,y,90,27);
            add(la[i]);
            tb[i]=new JTextField();
            tb[i].setBounds(200,y,150,27);
            add(tb[i]);
            y+=60;
        }

        try
        {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to MySQL database
            // Replace "your_db_name" with your actual database name
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_db_name", "system", "shrik");

            st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rst = st.executeQuery("select * from Student");

            // Calculate number of rows
            while (rst.next())
                nor++;

            rst.beforeFirst(); // Reset cursor to before first row
        }
        catch (Exception ex)
        {
            System.out.println("Error: " + ex);
        }

        bar = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, nor-1);
        bar.setBounds(100,300,200,30);
        add(bar);
        bar.addAdjustmentListener(this);

        rv = new JLabel();
        rv.setBounds(100,270,60,30);
        add(rv);

        showRecord(1);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void showRecord(int rn)
    {
        try
        {
            rst.absolute(rn);
            for(int i=0; i<4; i++)
                tb[i].setText(rst.getString(i+1));
        }
        catch(Exception ex)
        {
            System.out.println("Error in showRecord: " + ex);
        }
        rv.setText(rn + "/" + nor);
    }

    public void adjustmentValueChanged(AdjustmentEvent e)
    {
        showRecord(e.getValue() + 1);
    }

    public static void main(String [] args)
    {
        new RecordScroll();
    }
}
