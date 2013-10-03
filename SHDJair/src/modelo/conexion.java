package modelo;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class conexion {

	private String usuario;
	private String constrasenia;
	private String base;
	private String driver;
	public Connection con;
	
	public Statement un_st=null;
	public DatabaseMetaData dbmd;
	public String un_sql;
	public ResultSet resultado=null;
	public PreparedStatement ps=null;
	public PreparedStatement un_pst;
	
	
	
	
	
	public conexion() {
	
		usuario="postgres";
		constrasenia="eltigre";
		base="jdbc:postgresql://localhost:5432/jair";
		driver="org.postgresql.Driver";
		
	}
	
	
	public boolean conectar(){
		try {
			Class.forName(driver);

		con=DriverManager.getConnection(base,usuario,constrasenia);
	
		
	return true;
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return false;
	
	
	}
	
	
	
	public boolean desconectar(){
		try {
			con.close();
			
			;
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
		
	}
	
	
	public Connection getConexion(){
		
		return con;
		
	}
	
	
	
	
}
