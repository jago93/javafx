package entidades;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sun.javafx.beans.annotations.Default;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import modelo.conexion;

public class carrera {

	private String nombre;
	private String siglas;
	private String jefe;
	private String matricula;
	private String acreditado;
	private conexion con;
	private int idcarrera;
	
	public carrera() {
		this.nombre="";
		this.siglas="";
		this.jefe="";
		this.matricula="";
		this.acreditado="";
		this.con=new conexion();
	}
	
	public carrera(String n,String si,String je,String mat,String a){
	
		this.nombre=n;
		this.siglas=si;
		this.jefe=je;
		this.matricula=mat;
		this.acreditado=a;
		this.con=new conexion();
		
	}
	
	
	public void setIdcarrera(int idcarrera) {
		this.idcarrera = idcarrera;
	}
	
	public int getIdcarrera() {
		return idcarrera;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}
	
	public String getSiglas() {
		return siglas;
	}
	
	public void setJefe(String jefe) {
		this.jefe = jefe;
	} 
	
	public String getJefe() {
		return jefe;
	} 
	
	
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	
	public String getMatricula() {
		return matricula;
	}
	
	
	public void setAcreditado(String acreditado) {
		this.acreditado = acreditado;
	}
	
	
	public String getAcreditado() {
		return acreditado;
	}
	
	
	public String toString(){
		return nombre;
	}

	public String guardar(){
		
		String mensaje="";
		
		try{
			
	
			
	if(con.resultado!=null){
				
		if(con.resultado.next()){
		
			
			con.un_sql="update carrera set nombre='"+this.nombre+
					"',siglas='"+this.siglas+
					"',jefe='"+this.jefe+
					"',matricula='"+this.matricula+
					"',acreditado='"+this.acreditado+
					"'where idcarrera=default";
		
					con.ps.executeUpdate(con.un_sql);
		mensaje="Datos modificados correctmente";
		}
				
				
		
		
	}else	if(con.conectar()==true){
			
	String query="insert into carrera values (default,?,?,?,?,?)";
				PreparedStatement ps= con.getConexion().
						prepareStatement(query);
			
			ps.setString(1, this.nombre);
			ps.setString(2, this.siglas);
			ps.setString(3, this.jefe);
			ps.setString(4, this.matricula);
			ps.setString(5,this.acreditado);
			ps.executeUpdate();
			mensaje="Datos Insertados";
			
			
			
	
	
	
		}else	if(con.conectar()==true){
			
			String query="insert into carrera values (default,?,?,?,?,?)";
						PreparedStatement ps= con.getConexion().
								prepareStatement(query);
					
					ps.setString(1, this.nombre);
					ps.setString(2, this.siglas);
					ps.setString(3, this.jefe);
					ps.setString(4, this.matricula);
					ps.setString(5,this.acreditado);
					ps.executeUpdate();
					mensaje="Datos Insertados";
					
					
					}	
	
	
			
			
			
		}catch(Exception e){
			mensaje="No se insertaron";
		}
		
		finally {
			con.desconectar();
			
		}
		return mensaje;
		
		
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


	
	
	
	
	
public String eliminar1(String n){
		
		String mensaje="";
		
		try{
			
	
			
			if(con.conectar()==true){
			
	String query="delete from carrera where nombre='"+n+"';";
				PreparedStatement ps= con.getConexion().
						prepareStatement(query);
			
			ps.executeUpdate();
			
			mensaje="Eliminado Correctamente";
			
			
			}
			
			
					
		}catch(Exception e){
			mensaje="No se insertaron";
		}
		
		finally {
			con.desconectar();
			
		}
		return mensaje;
		
		
	}






public carrera obtenercarrera(int clave) throws SQLException{
	
	conexion cn= new conexion();
	carrera cr= null;
	cn.un_sql="select idcarrera,nombre from carrera where idcarrera="+clave;
	con.resultado=con.un_st.executeQuery(cn.un_sql);
	
	while(con.resultado.next()){
		cr= new carrera();
		cr.setIdcarrera(con.resultado.getInt("idcarrera"));
		cr.setNombre(con.resultado.getString("nombre"));
		
	}
	
	return cr;
	
	
	
}

	
	
}
