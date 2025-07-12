import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class Rsmd1
{
	public static void main(String args[]) throws Exception
	{
		 // getOracleConnection method is called
		 Connection conn = getOracleConnection();

		Statement st = conn.createStatement();  //Statement object created using createStatement method
		st.executeUpdate("drop table survey");  //if survey table is already exist then it is deleted using drop statement
		st.executeUpdate("create table survey (id number(4), name varchar(30))"); // survey table is created with 2 columns id and name
		st.executeUpdate("insert into survey (id, name) values (9, 'meesa')"); // one row is inserted into the survey table

		ResultSet rs = st.executeQuery("SELECT * FROM survey");  //fetch all the data from survey table using SELECT statement

		ResultSetMetaData rsMetaData = rs.getMetaData(); //ResultSetMetaData object is created using getMetaData method

		int numberOfColumns = rsMetaData.getColumnCount(); //get the total no. of columns in survey table
		System.out.println("resultSet MetaData column Count= " + numberOfColumns);

		for (int i = 1; i <= numberOfColumns; i++)
		{
			System.out.println(rsMetaData.getColumnDisplaySize(i)); //return max. width of column
			System.out.println(rsMetaData.getColumnLabel(i)); //returns suggested column title for printout
			System.out.println(rsMetaData.getColumnName(i)); //returns column name
			System.out.println(rsMetaData.getColumnType(i)); //(Types.INTEGER == z)
			System.out.println(rsMetaData.getColumnTypeName(i)); //return database specific name of column type
			System.out.println(rsMetaData.getColumnClassName(i)); //return fully-qualified name of the Java class
			System.out.println(rsMetaData.getTableName(i)); //return name of the table
			System.out.println(rsMetaData.getPrecision(i)); //no. of decimal digits for numeric type
			System.out.println(rsMetaData.getScale(i)); //scale of the column
			System.out.println(rsMetaData.isAutoIncrement(i)); //check for auto increment
			System.out.println(rsMetaData.isCurrency(i)); //check for currency
			System.out.println(rsMetaData.isWritable(i)); //check for writable
			System.out.println(rsMetaData.isNullable(i)); //check for null values
			System.out.println(rsMetaData.isReadOnly(i)); //check for read only
			System.out.println(rsMetaData.isCaseSensitive(i)); //check for case sensitivity
			System.out.println(rsMetaData.isSearchable(i)); //check for searchable
			System.out.println(rsMetaData.isSigned(i)); //check for signed
			System.out.println("catalog name = " + rsMetaData.getCatalogName(i)); //return catalog name
			System.out.println("schema name = " + rsMetaData.getSchemaName(i)); //return schema name
		}

		st.close();
		conn.close();
	}

	public static Connection getOracleConnection() throws Exception
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");  // load OracleDriver class
		Connection c = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:xe",  // establish connection
				"system", "mca6"
		);
		return c;  // return Connection object
	}
}
