package com.despensa.inteligente.services;

import com.despensa.inteligente.models.OcrResponse;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class OcrService {

    private static final String DATE_PATTERN = "\\b(\\d{1,2})[/-](\\d{1,2})[/-](\\d{2,4})\\b|\\b(\\d{2,4})[/-](\\d{1,2})[/-](\\d{1,2})\\b";
    private static final Pattern DATE_REGEX = Pattern.compile(DATE_PATTERN);

    public OcrResponse extraerFechaDeImagen(byte[] imagenBytes) {
        try {
            // Configurar Tesseract OCR
            Tesseract tesseract = new Tesseract();
            
            // Configurar idioma (español + inglés)
            tesseract.setLanguage("spa+eng");
            
            // Configurar modo de reconocimiento para texto
            tesseract.setPageSegMode(1);
            tesseract.setOcrEngineMode(1);
            
            // Convertir bytes a imagen
            BufferedImage imagen = ImageIO.read(new ByteArrayInputStream(imagenBytes));
            
            // Procesar imagen con Tesseract
            String textoExtraido = tesseract.doOCR(imagen);
            
            // Buscar fecha en el texto
            Date fechaExtraida = extraerFechaDelTexto(textoExtraido);
            boolean fechaValida = fechaExtraida != null;
            
            String mensaje = fechaValida ? 
                    "Fecha extraída exitosamente con Tesseract OCR" : 
                    "No se pudo extraer una fecha válida del texto";
            
            return new OcrResponse(textoExtraido.trim(), fechaExtraida, fechaValida, mensaje, 0.7);
            
        } catch (TesseractException e) {
            return new OcrResponse("", null, false, 
                    "Error de Tesseract OCR: " + e.getMessage(), 0.0);
        } catch (IOException e) {
            return new OcrResponse("", null, false, 
                    "Error al procesar imagen: " + e.getMessage(), 0.0);
        } catch (Exception e) {
            return new OcrResponse("", null, false, 
                    "Error inesperado: " + e.getMessage(), 0.0);
        }
    }

    private Date extraerFechaDelTexto(String texto) {
        Matcher matcher = DATE_REGEX.matcher(texto);
        
        while (matcher.find()) {
            String fechaStr = matcher.group();
            Date fecha = parsearFecha(fechaStr);
            if (fecha != null && esFechaValida(fecha)) {
                return fecha;
            }
        }
        return null;
    }

    private Date parsearFecha(String fechaStr) {
        String[] formatos = {
            "dd/MM/yyyy", "dd-MM-yyyy", "dd/MM/yy", "dd-MM-yy",
            "yyyy/MM/dd", "yyyy-MM-dd", "yy/MM/dd", "yy-MM-dd",
            "dd/MM/yyyy", "dd-MM-yyyy"
        };
        
        for (String formato : formatos) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(formato);
                sdf.setLenient(false);
                return sdf.parse(fechaStr);
            } catch (ParseException e) {
                // Continuar con el siguiente formato
            }
        }
        return null;
    }

    private boolean esFechaValida(Date fecha) {
        Date hoy = new Date();
        Date fechaLimite = new Date(hoy.getTime() + (365L * 24 * 60 * 60 * 1000)); // 1 año en el futuro
        
        return fecha.after(hoy) && fecha.before(fechaLimite);
    }
}
