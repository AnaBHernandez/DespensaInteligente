package com.despensa.inteligente.models;

import java.time.LocalDate;
import java.util.Objects;

public class OcrResponse {
    private String textoExtraido;
    private LocalDate fechaExtraida;
    private boolean fechaValida;
    private String mensaje;
    private double confianza;

    // Constructores
    public OcrResponse() {}

    public OcrResponse(String textoExtraido, LocalDate fechaExtraida, boolean fechaValida, String mensaje, double confianza) {
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

    public LocalDate getFechaExtraida() {
        return fechaExtraida;
    }

    public void setFechaExtraida(LocalDate fechaExtraida) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OcrResponse that = (OcrResponse) o;
        return fechaValida == that.fechaValida &&
               Double.compare(that.confianza, confianza) == 0 &&
               Objects.equals(textoExtraido, that.textoExtraido) &&
               Objects.equals(fechaExtraida, that.fechaExtraida) &&
               Objects.equals(mensaje, that.mensaje);
    }

    @Override
    public int hashCode() {
        return Objects.hash(textoExtraido, fechaExtraida, fechaValida, mensaje, confianza);
    }
}
