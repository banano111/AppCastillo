package com.example.appcastillo.modelos;

public class Usuario {

    private String uid;
    private String nombreUser;
    private String apellidoPUser;
    private String apellidoMUser;
    private String deporteUser;

    public Usuario() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNombreUser() {
        return nombreUser;
    }

    public void setNombreUser(String nombreUser) {
        this.nombreUser = nombreUser;
    }

    public String getApellidoPUser() {
        return apellidoPUser;
    }

    public void setApellidoPUser(String apellidoPUser) {
        this.apellidoPUser = apellidoPUser;
    }

    public String getApellidoMUser() {
        return apellidoMUser;
    }

    public void setApellidoMUser(String apellidoMUser) {
        this.apellidoMUser = apellidoMUser;
    }

    public String getDeporteUser() {
        return deporteUser;
    }

    public void setDeporteUser(String deporteUser) {
        this.deporteUser = deporteUser;
    }

    @Override
    public String toString() {
        return nombreUser;
    }
}
