package com.despensa.inteligente.services;

import com.despensa.inteligente.models.OcrResponse;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class OcrService {

    private static final String DATE_PATTERN = "\\b(\\d{1,4})[/-](\\d{1,2})[/-](\\d{1,4})\\b";
    private static final Pattern DATE_REGEX = Pattern.compile(DATE_PATTERN);

    private static final DateTimeFormatter[] FORMATTERS = new DateTimeFormatter[] {
            DateTimeFormatter.ofPattern("d/M/yyyy"),
            DateTimeFormatter.ofPattern("d-M-yyyy"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy"),
            DateTimeFormatter.ofPattern("yyyy/M/d"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("d/M/yy"),
            DateTimeFormatter.ofPattern("ddMMyyyy")
    };

    public OcrResponse extraerFechaDeImagen(byte[] imagenBytes) {
        if (imagenBytes == null || imagenBytes.length == 0) {
            return new OcrResponse("", null, false, "Imagen vacía", 0.0);
        }

        try {
            Tesseract tesseract = new Tesseract();
            tesseract.setLanguage("spa+eng");
            tesseract.setPageSegMode(1);
            tesseract.setOcrEngineMode(1);

            BufferedImage imagen = ImageIO.read(new ByteArrayInputStream(imagenBytes));
            if (imagen == null) {
                return new OcrResponse("", null, false, "No se pudo leer la imagen", 0.0);
            }

            String textoExtraido = tesseract.doOCR(imagen);
            LocalDate fechaExtraida = extraerFechaDelTexto(textoExtraido);
            boolean fechaValida = fechaExtraida != null;

            String mensaje = fechaValida ? "Fecha extraída exitosamente con Tesseract OCR" : "No se pudo extraer una fecha válida del texto";

            return new OcrResponse(textoExtraido == null ? "" : textoExtraido.trim(), fechaExtraida, fechaValida, mensaje, fechaValida ? 0.7 : 0.0);

        } catch (TesseractException e) {
            return new OcrResponse("", null, false, "Error de Tesseract OCR: " + e.getMessage(), 0.0);
        } catch (IOException e) {
            return new OcrResponse("", null, false, "Error al procesar imagen: " + e.getMessage(), 0.0);
        } catch (Exception e) {
            return new OcrResponse("", null, false, "Error inesperado: " + e.getMessage(), 0.0);
        }
    }

    private LocalDate extraerFechaDelTexto(String texto) {
        if (texto == null || texto.isBlank()) return null;
        Matcher matcher = DATE_REGEX.matcher(texto);
        while (matcher.find()) {
            String fechaStr = matcher.group();
            LocalDate fecha = parsearFecha(fechaStr.replaceAll("\\s+", ""));
            if (fecha != null && esFechaValida(fecha)) {
                return fecha;
            }
        }
        return null;
    }

    private LocalDate parsearFecha(String fechaStr) {
        for (DateTimeFormatter fmt : FORMATTERS) {
            try {
                return LocalDate.parse(fechaStr, fmt);
            } catch (DateTimeParseException ignored) {
            }
        }
        return null;
    }

    private boolean esFechaValida(LocalDate fecha) {
        LocalDate hoy = LocalDate.now();
        LocalDate limite = hoy.plusYears(2);
        return (fecha.isAfter(hoy) || fecha.isEqual(hoy)) && (fecha.isBefore(limite) || fecha.isEqual(limite));
    }
}