package modelo;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import entidades.carrera;

public class cdcarrera {
	
	private conexion con;

	public cdcarrera(){
		this.con= new conexion();
	}
	
public String modificar(carrera ca) throws SQLException{
		
		String mensaje="";
	
		try{
			
			if(con.conectar()==true){
				
				String query = "update carrera set nombre=?," +
						" siglas=?, jefe=?, matricula=?,acreditado=?"+
				" where idcarrera="+ca.getIdcarrera();
				
				PreparedStatement comando= con.getConexion().prepareStatement(query);
				comando.setString(1, ca.getNombre());
				comando.setString(2, ca.getSiglas());
				comando.setString(3, ca.getJefe());
				comando.setString(4, ca.getMatricula());
				comando.setString(5, ca.getAcreditado());
				System.out.println(comando.executeUpdate());
				comando.executeUpdate();
				
			mensaje="Datos modificados correctmente";
			}
			
			
			
		}catch(Exception e){
			mensaje="No se Actualizaron";
		}
		
		
		
		finally {
			con.desconectar();
			
		}
		
		return mensaje ;
	}










}
