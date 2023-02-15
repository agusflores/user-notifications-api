package com.apirest.project.helper;

public class ResponseBase {
    private String cabecera;
    private String mensaje;

    public ResponseBase() {
    }

    public ResponseBase(String cabecera, String mensaje) {
        this.cabecera = cabecera;
        this.mensaje = mensaje;
    }

    public String getCabecera() {
        return cabecera;
    }

    public void setCabecera(String nombreCabecera) {
        this.cabecera = nombreCabecera;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
