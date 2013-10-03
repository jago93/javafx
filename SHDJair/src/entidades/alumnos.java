package entidades;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelo.conexion;

public class alumnos {

	
	private String nombre;
	private String apaterno;
	private String amaterno;
	private String fecha;
	private String sexo;
	private int idalumno;
	private carrera ocarrera;	
	
	public alumnos() {
	}
	
	public alumnos(String n, String ap,String am, String fe,String sex,carrera carr){
		
		setNombre(n);
		setApaterno(ap);
		setAmaterno(am);
		setFecha(fe);
		setSexo(sex);
		setOcarrera(carr);
	}

	public void setOcarrera(carrera ocarrera) {
		this.ocarrera = ocarrera;
	}
	
	public carrera getOcarrera() {
		return ocarrera;
	}
	
	
	public void setIdalumno(int idalumno) {
		this.idalumno = idalumno;
	}
	
	public int getIdalumno() {
		return idalumno;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setApaterno(String apaterno) {
		this.apaterno = apaterno;
	}
	
	public String getApaterno() {
		return apaterno;
	}
	
	public void setAmaterno(String amaterno) {
		this.amaterno = amaterno;
	}
	
	public String getAmaterno() {
		return amaterno;
	}
	
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public String getFecha() {
		return fecha;
	}
	
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public String getSexo() {
		return sexo;
	}
	
	
	public String guardar() throws SQLException{
		conexion con = new conexion();
		String mensaje="";
		
		if(con.conectar()==true){
			String query="insert into alumno values (default,?,?,?,?,?,?)";
						PreparedStatement ps= con.getConexion().prepareStatement(query);
					
					ps.setString(1, this.nombre);
					ps.setString(2, this.apaterno);
					ps.setString(3, this.amaterno);
					ps.setString(4, this.fecha);
					ps.setString(5,this.sexo);
					ps.setInt(6,this.ocarrera.getIdcarrera());
					ps.executeUpdate();
					mensaje="Datos Insertados";
		
	}
	
		return mensaje;	
	}
	
	
	public String eliminar(String n){
		String mensaje="";
		conexion con = new conexion();
		try{
			if(con.conectar()==true){
			
				String query="delete from alumno where nombre='"+n+"';";
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
	
	
	
	public String toString(){
		return this.nombre+" "+
				this.apaterno+" "+
				this.amaterno+" "+
				this.fecha+" "+
				this.sexo;

	}

public ObservableList<carrera> listacarrera() throws SQLException{
	ResultSet rs = null;
	conexion con = new conexion(); 
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



	

	
}
