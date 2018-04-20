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

	private static String url = "jdbc:mysql://localhost:3306/bibliotheque";
	private static String user = "root";
    private static String pass = "";
    private static Connection connexion;

    /**
     * Cette m�thode donne l'instance n�cessaire � la gestion de la base de donn�e.
     * L'instance retourn�e n'est cr�� qu'au premier appel (lazy-loading)
     * @return l'instance qui sera utilis�e globalement pour g�rer la base de donn�es
     */
    public static Connection getInstance()
    {
        if(connexion == null)
        {
            try
            {
                connexion = DriverManager.getConnection(url, user, pass);
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return connexion;
    }
	
	/*
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
	}*/
	
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
