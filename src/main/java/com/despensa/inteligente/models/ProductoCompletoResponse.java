package com.despensa.inteligente.models;

public class ProductoCompletoResponse {
    private Producto producto;
    private OcrResponse ocrResponse;
    private boolean productoEncontrado;
    private boolean fechaExtraida;
    private String mensaje;

    // Constructores
    public ProductoCompletoResponse() {}

    public ProductoCompletoResponse(Producto producto, OcrResponse ocrResponse, 
                                   boolean productoEncontrado, boolean fechaExtraida, String mensaje) {
        this.producto = producto;
        this.ocrResponse = ocrResponse;
        this.productoEncontrado = productoEncontrado;
        this.fechaExtraida = fechaExtraida;
        this.mensaje = mensaje;
    }

    // Getters y setters
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public OcrResponse getOcrResponse() {
        return ocrResponse;
    }

    public void setOcrResponse(OcrResponse ocrResponse) {
        this.ocrResponse = ocrResponse;
    }

    public boolean isProductoEncontrado() {
        return productoEncontrado;
    }

    public void setProductoEncontrado(boolean productoEncontrado) {
        this.productoEncontrado = productoEncontrado;
    }

    public boolean isFechaExtraida() {
        return fechaExtraida;
    }

    public void setFechaExtraida(boolean fechaExtraida) {
        this.fechaExtraida = fechaExtraida;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
