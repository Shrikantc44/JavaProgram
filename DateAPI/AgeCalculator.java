import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AgeCalculator extends Thread {
    AgeCalculator(Main main) {
        cal = new GregorianCalendar();
        m = main;
    }

    public void run() {
        jf = new JFrame();
        jf.setLayout(new BorderLayout());
        jf.setTitle("Age Calculator Calendar");
        jf.setSize(400, 300);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        jf.setLocation(450, 150);
        jf.setResizable(false);
        label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JButton jbutton = new JButton("<-M");
        jbutton.addActionListener(e -> {
            cal.add(Calendar.MONTH, -1);
            updateMonth();
        });

        JButton jbutton1 = new JButton("M->");
        jbutton1.addActionListener(e -> {
            cal.add(Calendar.MONTH, 1);
            updateMonth();
        });

        JButton jbutton2 = new JButton("<-Y");
        jbutton2.setBackground(Color.blue);
        jbutton2.setForeground(Color.white);
        jbutton2.addActionListener(e -> {
            cal.add(Calendar.YEAR, -1);
            updateMonth();
        });

        JButton jbutton3 = new JButton("Y->");
        jbutton3.setBackground(Color.blue);
        jbutton3.setForeground(Color.white);
        jbutton3.addActionListener(e -> {
            cal.add(Calendar.YEAR, 1);
            updateMonth();
        });

        JPanel jpanel = new JPanel(new BorderLayout());
        JPanel jpanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 30));
        jpanel1.add(jbutton);
        jpanel1.add(jbutton2);
        JPanel jpanel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 30));
        jpanel2.add(jbutton3);
        jpanel2.add(jbutton1);
        jpanel.add(jpanel1, BorderLayout.WEST);
        jpanel.add(label, BorderLayout.CENTER);
        jpanel.add(jpanel2, BorderLayout.EAST);

        String[] headers = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        model = new DefaultTableModel(null, headers);
        JTable jtable = new JTable(model);
        jtable.setRowHeight(35);
        jtable.setCellSelectionEnabled(true);

        jtable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    int col = target.getSelectedColumn();
                    try {
                        date = (Integer) target.getValueAt(row, col);
                    } catch (Exception ex) {
                        date = 0;
                    }
                    if (date != 0) {
                        String selectedDate = date + "/" + (month1 + 1) + "/" + year;
                        m.getResult(selectedDate);
                        jf.dispose();
                    }
                }
            }
        });

        jf.add(jpanel, BorderLayout.NORTH);
        jf.add(new JScrollPane(jtable), BorderLayout.CENTER);
        updateMonth();
    }

    void updateMonth() {
        cal.set(Calendar.DAY_OF_MONTH, 1);
        month1 = cal.get(Calendar.MONTH);
        month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        year = cal.get(Calendar.YEAR);
        label.setText(month + " " + year);
        int startDay = cal.get(Calendar.DAY_OF_WEEK);
        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int weeks = cal.getActualMaximum(Calendar.WEEK_OF_MONTH);
        model.setRowCount(0);
        model.setRowCount(weeks);
        int dayCounter = startDay - 1;
        for (int day = 1; day <= maxDay; day++) {
            model.setValueAt(day, dayCounter / 7, dayCounter % 7);
            dayCounter++;
        }
    }

    DefaultTableModel model;
    Calendar cal;
    JLabel label;
    String month;
    int year;
    int month1;
    int date;
    JFrame jf;
    Main m;
}

class Main {
    void getResult(String date) {
        System.out.println("Selected date: " + date);
    }

    public static void main(String[] args) {
        new AgeCalculator(new Main()).start();
    }
}
