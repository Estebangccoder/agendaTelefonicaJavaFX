package model;

import java.util.ArrayList;

public class Agenda {
    private ArrayList<Contacto> contactos;
    private int capacidadMaxima = 10;

    // Constructor con capacidad
    public Agenda(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
        this.contactos = new ArrayList<>();
    }

    // Constructor por defecto
    public Agenda() {
        this.capacidadMaxima = 10;
        this.contactos = new ArrayList<>();
    }

    public boolean agendaLlena() {
        return contactos.size() >= capacidadMaxima;
    }

    public boolean contactoExiste(String nombre, String apellido) {
        for (Contacto contacto : contactos) {
            if (contacto.getNombre().equalsIgnoreCase(nombre.trim()) &&
                    contacto.getApellido().equalsIgnoreCase(apellido.trim())) {
                return true;
            }
        }
        return false;
    }

    private boolean existeContacto(Contacto nuevoContacto) {
        return contactos.contains(nuevoContacto);
    }

    public boolean anadirContacto(Contacto nuevoContacto) {
        if (agendaLlena()) {
            return false;
        } else if (existeContacto(nuevoContacto)) {
            return false;
        } else {
            contactos.add(nuevoContacto);
            return true;
        }
    }

    public ArrayList<Contacto> listarContactos() {
        return new ArrayList<>(contactos);
    }

    public Contacto buscarContacto(String nombre, String apellido) {
        for (Contacto contacto : contactos) {
            if (contacto.getNombre().equalsIgnoreCase(nombre.trim()) &&
                    contacto.getApellido().equalsIgnoreCase(apellido.trim())) {
                return contacto;
            }
        }
        return null;
    }

    public boolean eliminarContacto(String nombre, String apellido) {
        Contacto encontrado = buscarContacto(nombre, apellido);
        if (encontrado != null) {
            contactos.remove(encontrado);
            return true;
        }
        return false;
    }

    public boolean cambiarNumeroContacto(String nombre, String apellido, String nuevoTelefono) {
        Contacto encontrado = buscarContacto(nombre, apellido);
        if (encontrado != null) {
            encontrado.setTelefono(nuevoTelefono.trim());
            return true;
        }
        return false;
    }

    public int espaciosLibres() {
        return capacidadMaxima - contactos.size();
    }
}

