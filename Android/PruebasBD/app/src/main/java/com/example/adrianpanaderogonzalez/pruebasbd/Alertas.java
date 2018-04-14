package com.example.adrianpanaderogonzalez.pruebasbd;

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
    public String veridico;

    public String getAlertas() {
        return alerta;
    }

    public String getFecha() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss dd-MM-yyy");
        Date fechaConvertida = new Date();
        try {
            fechaConvertida = dateFormat.parse(fecha);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateFormat.format(fechaConvertida);
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

    public String getVeridico() {
        return veridico;
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

    public void setVeridico(String veridico) {
        this.veridico = veridico;
    }
}
