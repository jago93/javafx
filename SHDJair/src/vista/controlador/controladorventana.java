package vista.controlador;


import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import modelo.cdcarrera;
import modelo.conexion;


import entidades.carrera;


import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class controladorventana implements Initializable {

	
	@FXML private TextField txtnombre,txtsiglas,txtjefe,txtmatricula;
	@FXML private ComboBox<String> cboacreditado;
	@FXML private TableView<carrera>tblcarrera;
	@FXML private Label lblmensaje;
	
	
	
	@FXML private TableColumn<carrera, String> colnombre;
	@FXML private TableColumn<carrera, String> colsiglas;
	@FXML private TableColumn<carrera, String> coljefe;
	@FXML private TableColumn<carrera, String> colmatricula;
	@FXML private TableColumn<carrera, String> colacreditada;
	private int posiciontabla;
	carrera carremodificada= null;
	cdcarrera carrera= new cdcarrera();
	
	ObservableList<carrera>listacarrera = FXCollections.observableArrayList();
	
	
	
	

	
	@FXML protected void guardar() throws SQLException{
		if(txtnombre.getText().trim().isEmpty()|
				txtsiglas.getText().trim().isEmpty()|
				txtjefe.getText().trim().isEmpty()|
				txtmatricula.getText().trim().isEmpty())
				
				lblmensaje.setText("No se insertaron los datos");
		
		else{	
			
			
			carrera carre = new carrera(txtnombre.getText(),					
					txtsiglas.getText(),
					txtjefe.getText(),
					txtmatricula.getText(),
					cboacreditado.getValue());
			
			lblmensaje.setText(carre.guardar());
			
			
				
		}
		limpiar();
		llenartabla();			
			
	}
	
	@FXML protected void actualizar() throws SQLException{
		
		carremodificada.setNombre(txtnombre.getText());
		carremodificada.setSiglas(txtsiglas.getText());
		carremodificada.setJefe(txtjefe.getText());
		carremodificada.setMatricula(txtmatricula.getText());
		carremodificada.setAcreditado(cboacreditado.getValue());
		
		carrera.modificar(carremodificada);
		lblmensaje.setText("Actualizados");
		limpiar();
		llenartabla();
	}
	
	
	

	@FXML public void eliminar(ActionEvent event) throws SQLException{
		carrera pe= new carrera();
		pe.eliminar1(txtnombre.getText());
		lblmensaje.setText("Eliminado");
		limpiar();
		llenartabla();
		
		
		
		
		
	}
	

	public void limpiar(){
		txtnombre.setText("");
		txtsiglas.setText("");
		txtjefe.setText("");
		txtmatricula.setText("");
	}


	
	 private final ListChangeListener<carrera> seleccionarpersonas= 
			 new ListChangeListener<carrera>() {

				@Override
				public void onChanged(
						javafx.collections.ListChangeListener.Change<? extends carrera> c) {
					
					selecciondatos();
				}
			
	 
	 };
		
	
	
	
	
	 public carrera gettablaseleccionada(){
			if(tblcarrera !=null){
				List<carrera> tabla = tblcarrera.getSelectionModel().getSelectedItems();
				
			if(tabla.size()==1){
				final carrera seleccion= tabla.get(0);
				return seleccion;
			}
			
			
			
			}
			 
			 
			 return null;
			 
		 }
	

 
	 private void selecciondatos() {
		 carremodificada = gettablaseleccionada();		
		 
		 posiciontabla= listacarrera.indexOf(carremodificada);
	
		 
		 if(carremodificada !=null){
			 
			 txtnombre.setText(carremodificada.getNombre());
			 txtsiglas.setText(carremodificada.getSiglas());
			 txtmatricula.setText(carremodificada.getMatricula());
			 txtjefe.setText(carremodificada.getJefe());
			 cboacreditado.setValue(carremodificada.getAcreditado());
			 
			
		 }
	 }

 
public void llenartabla() throws SQLException{
	carrera carre= new carrera();
	listacarrera = carre.listacarrera();
	tblcarrera.setItems(listacarrera);

}




@Override
public void initialize(URL location, ResourceBundle resources) {
	// TODO Auto-generated method stub
	
	try {
		llenartabla();
		
		
		
		 tblcarrera.setItems(listacarrera);
	listacarrera = tblcarrera.getSelectionModel().
			 getSelectedItems();
	listacarrera.addListener(seleccionarpersonas);
		
		
		
			} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
} 




	
	
}
