package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Agenda;
import model.Contacto;

public class agendaController {

    //  Modelo principal
    private Agenda agenda = new Agenda(10);

    // Lista observable para mostrar en la tabla
    private ObservableList<Contacto> listaContactos = FXCollections.observableArrayList();

    @FXML
    private Button agregar;

    @FXML
    private TableColumn<Contacto, String> colApellido;

    @FXML
    private TableColumn<Contacto, String> colNombre;

    @FXML
    private TableColumn<Contacto, String> colTelefono;

    @FXML
    private TableView<Contacto> tableContactos;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTelefono;

    //  Inicializa la tabla al cargar la vista
    @FXML
    public void initialize() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));

        tableContactos.setItems(listaContactos);
    }

    @FXML
    void agregarContacto(ActionEvent event) {
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String telefono = txtTelefono.getText();

        try {


            if (nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty()) {
                mostrarAlerta("Error de Validación", "Todos los campos deben estar llenos", Alert.AlertType.ERROR);
                return;
            }

            //  Validación: nombre y apellido no pueden ser iguales
            if (nombre.equalsIgnoreCase(apellido)) {
                mostrarAlerta("Error de Validación", "El nombre y el apellido no pueden ser iguales", Alert.AlertType.ERROR);
                return;
            }

            Contacto nuevo = new Contacto(nombre, apellido, telefono);

            if (agenda.anadirContacto(nuevo)) {
                listaContactos.add(nuevo);
                limpiarCampos();
                mostrarAlerta("Éxito", "Contacto agregado correctamente", Alert.AlertType.INFORMATION);
            } else {
                mostrarAlerta("Error", "No se pudo agregar: agenda llena o contacto repetido", Alert.AlertType.WARNING);
            }
        } catch (IllegalArgumentException e) {
            mostrarAlerta("Error de Validación", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    void buscarContacto(ActionEvent event) {
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();

        if (nombre.isEmpty() || apellido.isEmpty()) {
            mostrarAlerta("Error de Validación", "Debes ingresar nombre y apellido para buscar", Alert.AlertType.ERROR);
            return;
        }
        Contacto encontrado = agenda.buscarContacto(nombre, apellido);
        if (encontrado != null) {
            tableContactos.getSelectionModel().select(encontrado);
            mostrarAlerta("Éxito", "Contacto encontrado:\n" + encontrado, Alert.AlertType.INFORMATION);
        } else {
            mostrarAlerta("No encontrado", "No existe un contacto con ese nombre y apellido", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void eliminarContacto(ActionEvent event) {
        Contacto seleccionado = tableContactos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            if (agenda.eliminarContacto(seleccionado.getNombre(), seleccionado.getApellido())) {
                listaContactos.remove(seleccionado);
                mostrarAlerta("Éxito", "Contacto eliminado correctamente", Alert.AlertType.INFORMATION);
            }
        } else {
            mostrarAlerta("Error", "Debes seleccionar un contacto de la tabla", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void listarContacto(ActionEvent event) {
        listaContactos.setAll(agenda.listarContactos());

        //  Ordenar alfabéticamente por nombre
        FXCollections.sort(listaContactos, (c1, c2) -> c1.getNombre().compareToIgnoreCase(c2.getNombre()));

        mostrarAlerta("Lista actualizada", "Se han cargado todos los contactos en orden alfabético", Alert.AlertType.INFORMATION);
    }

    @FXML
    void modificarContacto(ActionEvent event) {
        Contacto seleccionado = tableContactos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            String nuevoTel = txtTelefono.getText();
            if (agenda.cambiarNumeroContacto(seleccionado.getNombre(), seleccionado.getApellido(), nuevoTel)) {
                seleccionado.setTelefono(nuevoTel);
                tableContactos.refresh();
                mostrarAlerta("Éxito", "Teléfono actualizado correctamente", Alert.AlertType.INFORMATION);
            } else {
                mostrarAlerta("Error", "No se pudo actualizar el teléfono", Alert.AlertType.ERROR);
            }
        } else {
            mostrarAlerta("Error", "Debes seleccionar un contacto de la tabla", Alert.AlertType.ERROR);
        }
    }

    //  Método de utilidad para limpiar los campos
    private void limpiarCampos() {
        txtNombre.clear();
        txtApellido.clear();
        txtTelefono.clear();
    }

    //  Método para mostrar alertas
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
