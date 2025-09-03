package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Contacto {

    private final StringProperty nombre;
    private final StringProperty apellido;
    private final StringProperty telefono;

    public Contacto() {
        this.nombre = new SimpleStringProperty("");
        this.apellido = new SimpleStringProperty("");
        this.telefono = new SimpleStringProperty("");
    }

    public Contacto(String nombre, String apellido, String telefono) {
        if (nombre == null || nombre.trim().isEmpty() ||
                apellido == null || apellido.trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre y apellido no pueden estar vacíos");
        }
        if (!telefono.matches("\\d{10}")) {
            throw new IllegalArgumentException("El teléfono debe tener 10 dígitos númericos");
        }
        this.nombre = new SimpleStringProperty(nombre.trim());
        this.apellido = new SimpleStringProperty(apellido.trim());
        this.telefono = new SimpleStringProperty(telefono.trim());
    }

    public String getNombre() { return nombre.get(); }
    public void setNombre(String nombre) { this.nombre.set(nombre); }
    public StringProperty nombreProperty() { return nombre; }

    public String getApellido() { return apellido.get(); }
    public void setApellido(String apellido) { this.apellido.set(apellido); }
    public StringProperty apellidoProperty() { return apellido; }

    public String getTelefono() { return telefono.get(); }
    public void setTelefono(String telefono) { this.telefono.set(telefono); }
    public StringProperty telefonoProperty() { return telefono; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Contacto)) return false;
        Contacto c = (Contacto) obj;
        return this.getNombre().equalsIgnoreCase(c.getNombre()) &&
                this.getApellido().equalsIgnoreCase(c.getApellido());
    }

    @Override
    public int hashCode() {
        return (getNombre().toLowerCase() + getApellido().toLowerCase()).hashCode();
    }

    @Override
    public String toString() {
        return getNombre() + " " + getApellido() + " - " + getTelefono();
    }
}
