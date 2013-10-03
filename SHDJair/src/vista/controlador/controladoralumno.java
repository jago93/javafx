package vista.controlador;

import java.beans.FeatureDescriptor;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import modelo.cdalumnos;
import modelo.cdcarrera;

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
import entidades.alumnos;
import entidades.carrera;

public class controladoralumno implements Initializable {
	
	@FXML private TextField txtNombre,txtApaterno,txtAmaterno,txtFecha;
	@FXML private ComboBox<String> cbosexo;
	@FXML private ComboBox<carrera> cbocarrera;
	@FXML private TableView<alumnos>tblalumnos;
	@FXML private Label lblmensaje;
	 ObservableList<alumnos> listaalumno = FXCollections.observableArrayList(); 
	 ObservableList<carrera> listacarrera = FXCollections.observableArrayList();

	alumnos alumnomoficado= null;
	cdalumnos alumno= new cdalumnos();
	 
	private int posiciontabla;
	
	@FXML protected void guardar(ActionEvent event) throws SQLException{

		if(txtNombre.getText().trim().isEmpty()|
				txtApaterno.getText().trim().isEmpty()|
				txtAmaterno.getText().trim().isEmpty()|
				txtFecha.getText().trim().isEmpty())
				
				lblmensaje.setText("No se insertaron los datos");
		
		else{	
			
			
			alumnos pers = new alumnos(txtNombre.getText(), txtApaterno.getText(), txtAmaterno.getText(), txtFecha.getText(),
					cbosexo.getValue(),(carrera)cbocarrera.getValue());
			lblmensaje.setText(pers.guardar());
							
		}
	
	llenartabla();
	limpiar();
	}
	
	public void limpiar(){
		
		txtNombre.setText("");
		txtApaterno.setText("");
		txtAmaterno.setText("");
		txtFecha.setText("");
	}

	
	
	@FXML protected void actualizar() throws SQLException{
		
	alumnomoficado.setNombre(txtNombre.getText());
		alumnomoficado.setApaterno(txtApaterno.getText());
		alumnomoficado.setAmaterno(txtAmaterno.getText());
		alumnomoficado.setFecha(txtFecha.getText());
		alumnomoficado.setSexo(cbosexo.getValue());
		alumnomoficado.setOcarrera(cbocarrera.getValue());
		
		
		alumno.modificar(alumnomoficado);
		lblmensaje.setText("Actualizados");
		
		
		llenartabla();
		limpiar();
	}
	

	 private final ListChangeListener<alumnos> seleccionaralumnos= 
			 new ListChangeListener<alumnos>() {

				@Override
				public void onChanged(
						javafx.collections.ListChangeListener.Change<? extends alumnos> c) {
					
					selecciondatos();
				}

				
			
	 
	 };
		
	 public alumnos gettablaseleccionada(){
			if(tblalumnos !=null){
				List<alumnos> tabla = tblalumnos.getSelectionModel().getSelectedItems();
				
			if(tabla.size()==1){
				final alumnos seleccion= tabla.get(0);
				return seleccion;
			}
			
			
			
			}
			 
			 
			 return null;
			 
		 }
	
	 
	
	 
		@FXML public void eliminar(ActionEvent event) throws SQLException{
		alumnos pe= new alumnos();
			pe.eliminar(txtNombre.getText());
			lblmensaje.setText("Eliminado");
		
			
			llenartabla();
			limpiar();
					
		}
	 

	 private void selecciondatos() {
		alumnomoficado = gettablaseleccionada();		
		 
		 posiciontabla= listacarrera.indexOf(alumnomoficado);
	
		 
		 if(alumnomoficado !=null){
			
			
			 txtNombre.setText(alumnomoficado.getNombre());
			 txtApaterno.setText(alumnomoficado.getApaterno());
			 txtAmaterno.setText(alumnomoficado.getAmaterno());
			 txtFecha.setText(alumnomoficado.getFecha());
			 cbosexo.setValue(alumnomoficado.getSexo());
			 cbocarrera.setValue(alumnomoficado.getOcarrera());
			 
			
		 }
	 }

	 
	 
	 
	 public void llenartabla() throws SQLException{
		 cdalumnos al= new cdalumnos();
			listaalumno = al.listaalumno();
			tblalumnos.setItems(listaalumno);

		}

	 
	 

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		TableColumn<alumnos, String> nombre = new TableColumn<>("Nombre");
		TableColumn<alumnos, String> amaterno = new TableColumn<>("Apaterno");
		TableColumn<alumnos, String> apaterno = new TableColumn<>("Amaterno");
		TableColumn<alumnos, String> fecha = new TableColumn<>("fecha");
		TableColumn<alumnos, String> sexo = new TableColumn<>("Sexo");
		nombre.setCellValueFactory(new PropertyValueFactory<alumnos, String>("nombre"));
		apaterno.setCellValueFactory(new PropertyValueFactory<alumnos, String>("apaterno"));
		amaterno.setCellValueFactory(new PropertyValueFactory<alumnos, String>("amaterno"));
		fecha.setCellValueFactory(new PropertyValueFactory<alumnos, String>("Fecha"));
		sexo.setCellValueFactory(new PropertyValueFactory<alumnos, String>("sexo"));
		tblalumnos.getColumns().addAll(nombre,apaterno,amaterno,fecha,sexo);

		
		try {
			llenartabla();
			 tblalumnos.setItems(listaalumno);
				listaalumno = tblalumnos.getSelectionModel().
						 getSelectedItems();
				listaalumno.addListener(seleccionaralumnos);
				
				
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			listacarrera =alumno.listacarrera() ;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cbocarrera.setItems(listacarrera);

		
		
	}	
	
}
