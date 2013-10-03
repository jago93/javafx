package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import entidades.alumnos;
import entidades.carrera;

public class cdalumnos {

	private conexion con;

	public cdalumnos(){
		this.con= new conexion();
	}
	
public String modificar(alumnos al) throws SQLException{
		
		String mensaje="";
	
		try{
			
			if(con.conectar()==true){
				
				String query = "update alumnos set nombre=?," +
						" apaterno=?, amaterno=?, fechanac=?,sexo=?, carrera=?"+
				" where idalumno="+al.getIdalumno();
				
				PreparedStatement comando= con.getConexion().prepareStatement(query);
				comando.setString(1, al.getNombre());
				comando.setString(2, al.getApaterno());
				comando.setString(3, al.getAmaterno());
				comando.setString(4, al.getFecha());
				comando.setString(5, al.getSexo());
				comando.setInt(6, al.getOcarrera().getIdcarrera());
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

	




public ObservableList<carrera> listacarrera() throws SQLException{
	ResultSet rs = null;
	ObservableList<carrera> tabla= FXCollections.observableArrayList();
	carrera car=null;
	try{
		if(con.conectar()==true){
			String query="select idcarrera, nombre,siglas,jefe,matricula,acreditado from carrera";
			PreparedStatement comando = con.getConexion().prepareStatement(query);
			rs=comando.executeQuery();
			while(rs.next()){
				car=new carrera();
				car.setIdcarrera(rs.getInt("idcarrera"));
				car.setNombre(rs.getString("nombre"));
				car.setSiglas(rs.getString("siglas"));
				car.setJefe(rs.getString("jefe"));
				car.setMatricula(rs.getString("matricula"));
				car.setAcreditado(rs.getString("acreditado"));					
				tabla.add(car);					
			}
			
		}
		
	}
	catch(Exception ex){
		ex.printStackTrace();
	}
	finally{
		rs.close();
		con.desconectar();
	}
	return tabla;
	
}












public ObservableList<alumnos> listaalumno() throws SQLException{
	ResultSet rs = null;
	ObservableList<alumnos> tabla= FXCollections.observableArrayList();
	alumnos ali=null;
	try{
		if(con.conectar()==true){
			String query="select idalumno,nombre,apaterno,amaterno,fechanac,sexo,carrera from alumno";
			PreparedStatement comando = con.getConexion().prepareStatement(query);
			rs=comando.executeQuery();
			while(rs.next()){

				ali= new alumnos();
				
				ali.setIdalumno(rs.getInt("idalumno"));
				ali.setNombre(rs.getString("nombre"));
				ali.setApaterno(rs.getString("apaterno"));
				ali.setAmaterno(rs.getString("amaterno"));
				ali.setFecha(rs.getString("fechanac"));
				ali.setSexo(rs.getString("sexo"));
				ali.setOcarrera(obtenercarrera(rs.getInt("carrera")));
				
									
				tabla.add(ali);					
			}
			
		}
		
	}
	catch(Exception ex){
		ex.printStackTrace();
	}
	finally{
		rs.close();
		con.desconectar();
	}
	return tabla;
	
}



public carrera obtenercarrera(int idcarrera) throws SQLException, ClassNotFoundException{
conexion cdb= new conexion();
carrera carre= null;
cdb.conectar();

cdb.un_sql="select idcarrera,nombre from carrera where idcarrera=?";
PreparedStatement ps= cdb.con.prepareStatement(cdb.un_sql);
ps.setInt(1, idcarrera);
cdb.resultado=ps.executeQuery();

while(cdb.resultado.next()){
	carre= new carrera();
	carre.setIdcarrera(cdb.resultado.getInt("idcarrera"));
	carre.setNombre(cdb.resultado.getString("nombre"));
	
}
cdb.desconectar();	

return carre;
	
}
	
}