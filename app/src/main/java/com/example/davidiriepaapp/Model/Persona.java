package com.example.davidiriepaapp.Model;

public class Persona {
    private String Uid;
    private String Nombre;
    private String Apellidos;
    private String Correo;

    public Persona() {
    }

    public Persona (String uid, String nombre, String apellidos, String correo) {
        Uid = uid;
        Nombre = nombre;
        Apellidos = apellidos;
        Correo = correo;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }
    @Override
    public String toString() {
        return Nombre+" "+Apellidos;
    }
}
