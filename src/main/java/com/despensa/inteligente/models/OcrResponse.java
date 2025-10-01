package com.despensa.inteligente.models;

import java.util.Date;

public class OcrResponse {
    private String textoExtraido;
    private Date fechaExtraida;
    private boolean fechaValida;
    private String mensaje;
    private double confianza;

    // Constructores
    public OcrResponse() {}

    public OcrResponse(String textoExtraido, Date fechaExtraida, boolean fechaValida, String mensaje, double confianza) {
        this.textoExtraido = textoExtraido;
        this.fechaExtraida = fechaExtraida;
        this.fechaValida = fechaValida;
        this.mensaje = mensaje;
        this.confianza = confianza;
    }

    // Getters y setters
    public String getTextoExtraido() {
        return textoExtraido;
    }

    public void setTextoExtraido(String textoExtraido) {
        this.textoExtraido = textoExtraido;
    }

    public Date getFechaExtraida() {
        return fechaExtraida;
    }

    public void setFechaExtraida(Date fechaExtraida) {
        this.fechaExtraida = fechaExtraida;
    }

    public boolean isFechaValida() {
        return fechaValida;
    }

    public void setFechaValida(boolean fechaValida) {
        this.fechaValida = fechaValida;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public double getConfianza() {
        return confianza;
    }

    public void setConfianza(double confianza) {
        this.confianza = confianza;
    }
}
