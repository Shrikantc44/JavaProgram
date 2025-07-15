import java.sql.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;

class Query01 {
    public static void main(String args[]) {
        DatabaseCreator dbCreator = new DatabaseCreator();
        dbCreator.createDatabasesAndTables();

        SwingUtilities.invokeLater(() -> new Connect());
    }
}

class DatabaseCreator {
    static final String[] DB_NAMES = {"school", "hospital", "library", "company"};
    static final String USERNAME = "root";
    static final String PASSWORD = "shrik";

    void createDatabasesAndTables() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/?useSSL=false&serverTimezone=UTC",
                USERNAME, PASSWORD
            );

            Statement stmt = conn.createStatement();

            for (String dbName : DB_NAMES) {
                try {
                    stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + dbName);
                    System.out.println("Database created: " + dbName);
                } catch (SQLException e) {
                    System.out.println("Error creating database " + dbName + ": " + e.getMessage());
                }
            }

            createSchoolTables(conn);
            createHospitalTables(conn);
            //createLibraryTables(conn);
            //createCompanyTables(conn);

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database initialization failed: " + e.getMessage());
        }
    }

    void createSchoolTables(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("USE school");

            String createStudents = "CREATE TABLE IF NOT EXISTS students (" +
                "student_id INT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(50) NOT NULL, " +
                "age INT, " +
                "grade VARCHAR(10))";
            stmt.executeUpdate(createStudents);

            String createTeachers = "CREATE TABLE IF NOT EXISTS teachers (" +
                "teacher_id INT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(50) NOT NULL, " +
                "subject VARCHAR(50), " +
                "experience_years INT)";
            stmt.executeUpdate(createTeachers);

            String createDoctors = "CREATE TABLE IF NOT EXISTS doctors (" +
                "doctor_id INT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(50) NOT NULL, " +
                "specialization VARCHAR(50), " +
                "years_experience INT)";
            stmt.executeUpdate(createDoctors);

            // Add departments table to school database
            String createDepartments = "CREATE TABLE IF NOT EXISTS departments (" +
                "dept_id INT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(50) NOT NULL, " +
                "manager VARCHAR(50))";
            stmt.executeUpdate(createDepartments);

            stmt.executeUpdate("INSERT IGNORE INTO students (name, age, grade) VALUES " +
                "('Rahul Sharma', 15, '10A'), " +
                "('Priya Patel', 16, '11B'), " +
                "('Amit Singh', 14, '9C')");

            stmt.executeUpdate("INSERT IGNORE INTO teachers (name, subject, experience_years) VALUES " +
                "('Dr. Gupta', 'Mathematics', 10), " +
                "('Mrs. Desai', 'Science', 8), " +
                "('Mr. Joshi', 'History', 5)");

            stmt.executeUpdate("INSERT IGNORE INTO doctors (name, specialization, years_experience) VALUES " +
                "('Dr. Sinha', 'Pediatrics', 6), " +
                "('Dr. Rao', 'General Medicine', 12), " +
                "('Dr. Verma', 'Dentistry', 8)");
            
            // Add sample data to departments table
            stmt.executeUpdate("INSERT IGNORE INTO departments (name, manager) VALUES " +
                "('Science', 'Dr. Gupta'), " +
                "('Mathematics', 'Mrs. Desai'), " +
                "('Humanities', 'Mr. Joshi')");
        }
    }

    void createHospitalTables(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("USE hospital");

            String createPatients = "CREATE TABLE IF NOT EXISTS patients (" +
                "patient_id INT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(50) NOT NULL, " +
                "age INT, " +
                "diagnosis VARCHAR(100))";
            stmt.executeUpdate(createPatients);

            String createDoctors = "CREATE TABLE IF NOT EXISTS doctors (" +
                "doctor_id INT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(50) NOT NULL, " +
                "specialization VARCHAR(50), " +
                "years_experience INT)";
            stmt.executeUpdate(createDoctors);

            stmt.executeUpdate("INSERT IGNORE INTO patients (name, age, diagnosis) VALUES " +
                "('Sanjay Verma', 45, 'Hypertension'), " +
                "('Meena Iyer', 32, 'Diabetes'), " +
                "('Vikram Reddy', 28, 'Fracture')");

            stmt.executeUpdate("INSERT IGNORE INTO doctors (name, specialization, years_experience) VALUES " +
                "('Dr. Kapoor', 'Cardiology', 15), " +
                "('Dr. Banerjee', 'Neurology', 12), " +
                "('Dr. Nair', 'Orthopedics', 8)");
        }
    }

    void createLibraryTables(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("USE library");

            String createBooks = "CREATE TABLE IF NOT EXISTS books (" +
                "book_id INT PRIMARY KEY AUTO_INCREMENT, " +
                "title VARCHAR(100) NOT NULL, " +
                "author VARCHAR(50), " +
                "available BOOLEAN DEFAULT true)";
            stmt.executeUpdate(createBooks);

            String createMembers = "CREATE TABLE IF NOT EXISTS members (" +
                "member_id INT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(50) NOT NULL, " +
                "join_date DATE, " +
                "books_borrowed INT DEFAULT 0)";
            stmt.executeUpdate(createMembers);

            stmt.executeUpdate("INSERT IGNORE INTO books (title, author, available) VALUES " +
                "('The Great Indian Novel', 'Shashi Tharoor', true), " +
                "('Wings of Fire', 'APJ Abdul Kalam', false), " +
                "('The God of Small Things', 'Arundhati Roy', true)");

            stmt.executeUpdate("INSERT IGNORE INTO members (name, join_date, books_borrowed) VALUES " +
                "('Arun Kumar', '2022-01-15', 2), " +
                "('Deepa Menon', '2023-03-22', 1), " +
                "('Rajat Mehta', '2021-11-05', 0)");
        }
    }

    void createCompanyTables(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("USE company");

            String createEmployees = "CREATE TABLE IF NOT EXISTS employees (" +
                "emp_id INT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(50) NOT NULL, " +
                "department VARCHAR(50), " +
                "salary DECIMAL(10,2))";
            stmt.executeUpdate(createEmployees);

            stmt.executeUpdate("INSERT IGNORE INTO employees (name, department, salary) VALUES " +
                "('Neha Shah', 'IT', 75000.00), " +
                "('Rajiv Malhotra', 'Finance', 82000.00), " +
                "('Ananya Das', 'HR', 68000.00)");
        }
    }
}

class Connect {
    JFrame jf;
    JButton jb01, jb02;
    JComboBox<String> dbComboBox;

    Connect() {
        jf = new JFrame("MySQL Connection");
        jf.setSize(400, 200);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLayout(null);

        JLabel jl = new JLabel("Select Database:");
        jl.setBounds(20, 20, 100, 25);
        jf.add(jl);

        dbComboBox = new JComboBox<>(new String[]{"school", "hospital", "library", "company", "test"});
        dbComboBox.setBounds(150, 20, 200, 25);
        jf.add(dbComboBox);

        jb01 = new JButton("Connect");
        jb01.setBounds(50, 80, 120, 30);
        jf.add(jb01);

        jb02 = new JButton("Exit");
        jb02.setBounds(200, 80, 120, 30);
        jf.add(jb02);

        ConnectEvent conev = new ConnectEvent(this);
        jb01.addActionListener(conev);
        jb02.addActionListener(conev);

        jf.setVisible(true);
    }

    public Vector<String> getTableNames(String dbName) {
        Vector<String> tableNames = new Vector<>();
        String dbUrl = "jdbc:mysql://localhost:3306/" + dbName + "?useSSL=false&serverTimezone=UTC";
        
        try (Connection c = DriverManager.getConnection(dbUrl, DatabaseCreator.USERNAME, DatabaseCreator.PASSWORD);
             ResultSet rs = c.getMetaData().getTables(null, null, "%", new String[]{"TABLE"})) {
            
            tableNames.add("---None---");
            while (rs.next()) {
                tableNames.add(rs.getString(3));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(jf, "Error connecting to database:\n" + e.getMessage());
        }
        return tableNames;
    }
}

class ConnectEvent implements ActionListener {
    Connect con;

    ConnectEvent(Connect con) {
        this.con = con;
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == con.jb01) {
            String dbName = con.dbComboBox.getSelectedItem().toString();
            Vector<String> tableNames = con.getTableNames(dbName);
            
            if (!tableNames.isEmpty()) {
                con.jf.dispose();
                new QueryBuilderFrame(dbName, tableNames);
            }
        } else if (ae.getSource() == con.jb02) {
            System.exit(0);
        }
    }
}

class QueryBuilderFrame {
    private String dbName;
    private Vector<String> tableNames;
    private Vector<String> columnNames = new Vector<>();
    private Vector<String> selectedFields = new Vector<>();
    
    private JFrame frame;
    private JComboBox<String> tableComboBox;
    private JList<String> columnsList, selectedColumnsList;
    private JButton refreshBtn, addBtn, removeBtn, clearBtn, queryBtn, executeBtn, exitBtn;
    private JCheckBox orderByCheckBox;
    
    private JComboBox<String> fieldComboBox, operatorComboBox;
    private JTextField valueField;
    private JRadioButton andRadio, orRadio, noneRadio;
    
    private StringBuilder queryBuilder = new StringBuilder();
    private StringBuilder criteriaBuilder = new StringBuilder();
    private StringBuilder orderByBuilder = new StringBuilder();
    
    public QueryBuilderFrame(String dbName, Vector<String> tableNames) {
        this.dbName = dbName;
        this.tableNames = tableNames;
        
        initializeUI();
        setupEventHandlers();
    }
    
    private void initializeUI() {
        frame = new JFrame("Query Builder - " + dbName);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        
        // Table selection components
        JLabel tableLabel = new JLabel("Table to Query:");
        tableLabel.setBounds(20, 20, 100, 25);
        frame.add(tableLabel);
        
        tableComboBox = new JComboBox<>(tableNames);
        tableComboBox.setBounds(130, 20, 200, 25);
        frame.add(tableComboBox);
        
        refreshBtn = new JButton("Refresh");
        refreshBtn.setBounds(350, 20, 100, 25);
        frame.add(refreshBtn);
        
        // Columns lists
        columnsList = new JList<>();
        JScrollPane leftScroll = new JScrollPane(columnsList);
        leftScroll.setBounds(20, 60, 200, 200);
        frame.add(leftScroll);
        
        addBtn = new JButton(">>");
        addBtn.setBounds(240, 120, 60, 30);
        frame.add(addBtn);
        
        removeBtn = new JButton("<<");
        removeBtn.setBounds(240, 160, 60, 30);
        frame.add(removeBtn);
        
        clearBtn = new JButton("<<<");
        clearBtn.setBounds(240, 200, 60, 30);
        frame.add(clearBtn);
        
        selectedColumnsList = new JList<>();
        JScrollPane rightScroll = new JScrollPane(selectedColumnsList);
        rightScroll.setBounds(320, 60, 200, 200);
        frame.add(rightScroll);
        
        orderByCheckBox = new JCheckBox("Order By");
        orderByCheckBox.setBounds(240, 270, 100, 25);
        frame.add(orderByCheckBox);
        
        // Criteria section
        JLabel criteriaLabel = new JLabel("Criteria:");
        criteriaLabel.setBounds(20, 300, 100, 25);
        frame.add(criteriaLabel);
        
        JLabel fieldLabel = new JLabel("Field:");
        fieldLabel.setBounds(20, 330, 100, 25);
        frame.add(fieldLabel);
        
        fieldComboBox = new JComboBox<>();
        fieldComboBox.addItem("---None---");
        fieldComboBox.setBounds(130, 330, 150, 25);
        frame.add(fieldComboBox);
        
        JLabel operatorLabel = new JLabel("Operator:");
        operatorLabel.setBounds(20, 360, 100, 25);
        frame.add(operatorLabel);
        
        operatorComboBox = new JComboBox<>(new String[]{"---None---", "=", ">", "<", "<=", ">=", "!=", "like", "soundex", "between", "in"});
        operatorComboBox.setBounds(130, 360, 150, 25);
        frame.add(operatorComboBox);
        
        JLabel valueLabel = new JLabel("Value:");
        valueLabel.setBounds(20, 390, 100, 25);
        frame.add(valueLabel);
        
        valueField = new JTextField("Enter Value");
        valueField.setBounds(130, 390, 150, 25);
        frame.add(valueField);
        
        // Radio buttons for AND/OR
        andRadio = new JRadioButton("AND");
        andRadio.setBounds(300, 330, 80, 25);
        frame.add(andRadio);
        
        orRadio = new JRadioButton("OR");
        orRadio.setBounds(300, 360, 80, 25);
        frame.add(orRadio);
        
        noneRadio = new JRadioButton("None");
        noneRadio.setBounds(300, 390, 80, 25);
        frame.add(noneRadio);
        
        ButtonGroup criteriaGroup = new ButtonGroup();
        criteriaGroup.add(andRadio);
        criteriaGroup.add(orRadio);
        criteriaGroup.add(noneRadio);
        noneRadio.setSelected(true);
        
        // Action buttons
        executeBtn = new JButton("Execute");
        executeBtn.setBounds(550, 330, 120, 30);
        frame.add(executeBtn);
        
        queryBtn = new JButton("Build Query");
        queryBtn.setBounds(550, 370, 120, 30);
        frame.add(queryBtn);
        
        exitBtn = new JButton("Exit");
        exitBtn.setBounds(550, 410, 120, 30);
        frame.add(exitBtn);
        
        frame.setVisible(true);
    }
    
    private void setupEventHandlers() {
        refreshBtn.addActionListener(e -> refreshTableColumns());
        addBtn.addActionListener(e -> addSelectedColumns());
        removeBtn.addActionListener(e -> removeSelectedColumns());
        clearBtn.addActionListener(e -> clearAllColumns());
        queryBtn.addActionListener(e -> buildQuery());
        executeBtn.addActionListener(e -> executeQuery());
        exitBtn.addActionListener(e -> System.exit(0));
        orderByCheckBox.addActionListener(e -> showOrderByDialog());
        
        tableComboBox.addActionListener(e -> {
            if (tableComboBox.getSelectedIndex() > 0) {
                refreshTableColumns();
            }
        });
        
        // Initialize with first table if available
        if (tableNames.size() > 1) {
            tableComboBox.setSelectedIndex(1);
            refreshTableColumns();
        }
    }
    
    private void refreshTableColumns() {
        String tableName = (String) tableComboBox.getSelectedItem();
        if (tableName == null || tableName.equals("---None---")) {
            return;
        }
        
        columnNames.clear();
        selectedFields.clear();
        
        String dbUrl = "jdbc:mysql://localhost:3306/" + dbName + "?useSSL=false&serverTimezone=UTC";
        
        try (Connection conn = DriverManager.getConnection(dbUrl, DatabaseCreator.USERNAME, DatabaseCreator.PASSWORD)) {
            
            // Check if table exists first
            DatabaseMetaData dbMeta = conn.getMetaData();
            try (ResultSet tables = dbMeta.getTables(null, null, tableName, new String[]{"TABLE"})) {
                if (!tables.next()) {
                    JOptionPane.showMessageDialog(frame, 
                        "Table '" + dbName + "." + tableName + "' doesn't exist",
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            
            // Get columns if table exists
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " LIMIT 1")) {
                
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
                
                columnNames.clear();
                for (int i = 1; i <= columnCount; i++) {
                    columnNames.add(rsmd.getColumnName(i));
                }
                
                columnsList.setListData(columnNames);
                
                // Update field combo box
                fieldComboBox.removeAllItems();
                fieldComboBox.addItem("---None---");
                for (String colName : columnNames) {
                    fieldComboBox.addItem(colName);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, 
                "Error loading table columns:\n" + ex.getMessage(),
                "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        
        columnsList.setListData(columnNames);
        selectedColumnsList.setListData(selectedFields);
    }
    
    private void addSelectedColumns() {
        List<String> selected = columnsList.getSelectedValuesList();
        for (String col : selected) {
            if (!selectedFields.contains(col)) {
                selectedFields.add(col);
            }
        }
        selectedColumnsList.setListData(selectedFields);
    }
    
    private void removeSelectedColumns() {
        int[] selectedIndices = selectedColumnsList.getSelectedIndices();
        for (int i = selectedIndices.length - 1; i >= 0; i--) {
            selectedFields.remove(selectedIndices[i]);
        }
        selectedColumnsList.setListData(selectedFields);
    }
    
    private void clearAllColumns() {
        selectedFields.clear();
        selectedColumnsList.setListData(selectedFields);
    }
    
    private void buildQuery() {
        queryBuilder.setLength(0);
        queryBuilder.append("SELECT ");
        
        if (selectedFields.isEmpty()) {
            queryBuilder.append("*");
        } else {
            for (int i = 0; i < selectedFields.size(); i++) {
                if (i > 0) queryBuilder.append(", ");
                queryBuilder.append(selectedFields.get(i));
            }
        }
        
        queryBuilder.append(" FROM ").append(tableComboBox.getSelectedItem());
        
        // Add criteria if any
        if (fieldComboBox.getSelectedIndex() > 0 && 
            operatorComboBox.getSelectedIndex() > 0 && 
            !valueField.getText().equals("Enter Value")) {
            
            if (criteriaBuilder.length() == 0) {
                criteriaBuilder.append(" WHERE ");
            } else {
                if (andRadio.isSelected()) {
                    criteriaBuilder.append(" AND ");
                } else if (orRadio.isSelected()) {
                    criteriaBuilder.append(" OR ");
                }
            }
            
            criteriaBuilder.append(fieldComboBox.getSelectedItem())
                          .append(" ")
                          .append(operatorComboBox.getSelectedItem())
                          .append(" ")
                          .append("'").append(valueField.getText()).append("'");
        }
        
        queryBuilder.append(criteriaBuilder);
        
        // Add ORDER BY if checked
        if (orderByCheckBox.isSelected() && orderByBuilder.length() > 0) {
            queryBuilder.append(orderByBuilder);
        }
        
        JOptionPane.showMessageDialog(frame, "Generated Query:\n" + queryBuilder.toString());
    }
    
    private void executeQuery() {
        buildQuery(); // Make sure query is built
        
        if (queryBuilder.length() == 0 || queryBuilder.toString().equals("SELECT  FROM ")) {
            JOptionPane.showMessageDialog(frame, "Please build a valid query first");
            return;
        }
        
        String dbUrl = "jdbc:mysql://localhost:3306/" + dbName + "?useSSL=false&serverTimezone=UTC";
        
        try (Connection conn = DriverManager.getConnection(dbUrl, DatabaseCreator.USERNAME, DatabaseCreator.PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(queryBuilder.toString())) {
            
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            
            // Get column names
            Vector<String> columnNames = new Vector<>();
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(rsmd.getColumnName(i));
            }
            
            // Get row data
            Vector<Vector<String>> rowData = new Vector<>();
            while (rs.next()) {
                Vector<String> row = new Vector<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(rs.getString(i) != null ? rs.getString(i) : "NULL");
                }
                rowData.add(row);
            }
            
            // Display results in a new window
            JFrame resultFrame = new JFrame("Query Results");
            resultFrame.setSize(800, 400);
            resultFrame.setLocationRelativeTo(frame);
            
            JTable resultTable = new JTable(rowData, columnNames);
            JScrollPane scrollPane = new JScrollPane(resultTable);
            resultFrame.add(scrollPane);
            
            resultFrame.setVisible(true);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Error executing query:\n" + ex.getMessage());
        }
    }
    
    private void showOrderByDialog() {
        if (!orderByCheckBox.isSelected()) {
            orderByBuilder.setLength(0);
            return;
        }
        
        JDialog orderByDialog = new JDialog(frame, "Order By Options", true);
        orderByDialog.setSize(400, 250);
        orderByDialog.setLocationRelativeTo(frame);
        orderByDialog.setLayout(null);
        
        JLabel fieldLabel = new JLabel("Order by Field:");
        fieldLabel.setBounds(20, 20, 120, 25);
        orderByDialog.add(fieldLabel);
        
        JComboBox<String> fieldCombo = new JComboBox<>();
        fieldCombo.setBounds(150, 20, 200, 25);
        for (String col : columnNames) {
            fieldCombo.addItem(col);
        }
        orderByDialog.add(fieldCombo);
        
        JLabel directionLabel = new JLabel("Sort Direction:");
        directionLabel.setBounds(20, 60, 120, 25);
        orderByDialog.add(directionLabel);
        
        JRadioButton ascRadio = new JRadioButton("Ascending");
        ascRadio.setBounds(150, 60, 100, 25);
        orderByDialog.add(ascRadio);
        
        JRadioButton descRadio = new JRadioButton("Descending");
        descRadio.setBounds(260, 60, 100, 25);
        orderByDialog.add(descRadio);
        
        ButtonGroup directionGroup = new ButtonGroup();
        directionGroup.add(ascRadio);
        directionGroup.add(descRadio);
        ascRadio.setSelected(true);
        
        JButton addButton = new JButton("Add");
        addButton.setBounds(50, 120, 100, 30);
        orderByDialog.add(addButton);
        
        JButton okButton = new JButton("OK");
        okButton.setBounds(170, 120, 100, 30);
        orderByDialog.add(okButton);
        
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(290, 120, 100, 30);
        orderByDialog.add(cancelButton);
        
        addButton.addActionListener(e -> {
            if (orderByBuilder.length() == 0) {
                orderByBuilder.append(" ORDER BY ");
            } else {
                orderByBuilder.append(", ");
            }
            
            orderByBuilder.append(fieldCombo.getSelectedItem());
            orderByBuilder.append(ascRadio.isSelected() ? " ASC" : " DESC");
        });
        
        okButton.addActionListener(e -> orderByDialog.dispose());
        cancelButton.addActionListener(e -> {
            orderByBuilder.setLength(0);
            orderByDialog.dispose();
        });
        
        orderByDialog.setVisible(true);
    }
}