package a.madalert.madalert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by adrianpanaderogonzalez on 22/3/18.
 */

public class Alertas {

    public String alerta;
    public String fecha;
    public String url;
    public String distrito;
    public String categoria;
    public String fuente;
    public Boolean verificado;

    public String getAlertas() {
        return alerta;
    }

    public String getFecha() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date fechaConvertida = new Date();
        try {
            fechaConvertida = dateFormat.parse(fecha);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat formatoFecha = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        String fechaReal = formatoFecha.format(fechaConvertida);
        return fechaReal;
    }

    public String getUrl() {
        return url;
    }

    public String getDistrito() {
        return distrito;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getFuente() {
        return fuente;
    }

    public Boolean getVerificado() {
        return verificado;
    }

    public void setAlertas(String alertas) {
        this.alerta = alertas;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setVerificado(Boolean verificado) {
        this.verificado = verificado;
    }
}
