package com.grupo.salinas.exam.model;

import java.util.ArrayList;

public class ResponseOperation {
    private ArrayList resultList;
    private String nombre;
    private String descripcion;
    private int code;

    public ResponseOperation(String name, ArrayList result, String s, int i) {
        this.nombre=name;
        this.descripcion = s;
        this.code = i;
        this.resultList = result;
    }

    public ResponseOperation() {
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

    public ArrayList getResultList() {
        return resultList;
    }

    public void setResultList(ArrayList resultList) {
        this.resultList = resultList;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
