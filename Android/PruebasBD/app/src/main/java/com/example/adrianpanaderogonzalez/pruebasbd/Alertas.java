package com.example.adrianpanaderogonzalez.pruebasbd;

import java.util.Date;

/**
 * Created by adrianpanaderogonzalez on 22/3/18.
 */

public class Alertas {

    public String alertas;
    public String fecha;
    public String url;
    public String distrito;
    public String categoria;
    public String fuente;
    public String veridico;

    public String getAlertas() {
        return alertas;
    }

    public String getFecha() {
        return fecha;
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
        this.alertas = alertas;
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
