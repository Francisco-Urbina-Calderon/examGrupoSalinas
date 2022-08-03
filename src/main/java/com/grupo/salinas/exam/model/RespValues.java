package com.grupo.salinas.exam.model;

import java.util.ArrayList;

public class RespValues {
    private String descripcion;
    private int code;

    public RespValues() {
        super();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    public RespValues(String descripcion, int code) {
        this.descripcion = descripcion;
        this.code = code;
    }
}
