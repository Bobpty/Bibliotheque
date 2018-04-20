package util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DBUtil {

	public static Connection setConnection(String serverIP) throws SQLException 
	{
		try 
		{
			return DriverManager.getConnection("jdbc:mysql://"+serverIP+":3306/", "root", "");
		} 
		catch (SQLException e) 
		{
			throw new SQLException("Erreur: " + e.getMessage());
		}
	}
	
	public static Connection setConnection(String serverIP, String dbName) throws SQLException
	{
		try
		{
			return DriverManager.getConnection("jdbc:mysql://"+serverIP+":3306/"+dbName, "root", "");
		}
		catch (SQLException e)
		{
			throw new SQLException("Erreur: " + e.getMessage());
		}
	}
	
	public static ArrayList<String> getSchemasList(Connection connect) throws SQLException {
		DatabaseMetaData metaDB = connect.getMetaData();
		
		ResultSet res = metaDB.getCatalogs();
		ArrayList<String> liste = new ArrayList<String>();
		while(res.next()) {
				liste.add(res.getString("TABLE_CAT"));
		}
		return liste;
	}

	public static ArrayList<String> getTablesList(Connection connect, String schema) throws SQLException {
		DatabaseMetaData metaDB = connect.getMetaData();

		ResultSet res =metaDB.getTables(null, schema, null, null);
		ArrayList<String> liste = new ArrayList<String>();
		while(res.next()) {
			liste.add(res.getString("TABLE_NAME"));
		}
		return liste;
	}
	
	public static ArrayList<String> getFieldsList(Connection connect, String schema, String table) throws SQLException {
		DatabaseMetaData metaDB = connect.getMetaData();
		
		ResultSet res =metaDB.getColumns(null, schema, table, null);
		ArrayList<String> liste = new ArrayList<String>();
		while(res.next()) {
			liste.add(res.getString("COLUMN_NAME"));
		}
		return liste;
	}
	
	public static String getColumnValue(ResultSet res, int i) throws SQLException {
		ResultSetMetaData meta = res.getMetaData();
		switch(meta.getColumnTypeName(i)) {
			case "INT" : 
			case "TINYINT":
				return String.valueOf(res.getInt(i));
			case "VARCHAR" : 
				return res.getString(i);
			case "DATE" : 
				Date laDate = res.getDate(i);
				if(laDate == null)
					return "null";
				return new SimpleDateFormat("dd/MM/yyyy").format(res.getDate(i));
			case "DATETIME":
				Date laDateTime = res.getDate(i);
				if(laDateTime == null)
					return "null";
				return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss,sss").format(res.getDate(i));
			case "UNKNOWN" : return res.getString(i);
			default : return "UNKNOW type " + meta.getColumnTypeName(i);
		}
	}
	
	/**
	 * 
	 * @param res
	 * @param fdName
	 * @return
	 * @throws SQLException
	 */
	public static String getColumnValue(ResultSet res, String fdName) throws SQLException {
		ResultSetMetaData meta = res.getMetaData();
		for(int i=1; i<=meta.getColumnCount();i++) {
			if(meta.getColumnName(i).equals(fdName))
				return getColumnValue(res, i);
		}
		return "Field "+fdName+" is unknown";
	}

	public static Object executeQuery(Connection connexion, String query) throws SQLException {
		Statement session = connexion.createStatement();
		if(query.toUpperCase().contains("SELECT"))
			return session.executeQuery(query);
		else
			return session.executeUpdate(query);
	}
}
