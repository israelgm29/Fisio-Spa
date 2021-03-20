/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boot.models;

import java.sql.Date;

/**
 *
 * @author lufeb
 */
public class HistoriaClinica {
    private int codigo;
    private String paciente;
    private String cedula;
    private String telefono;
    private int edad;
    private String sexo;
    private String direccion;
    private Date fecha_nacimiento;
    private String mail;
    private Double estatura;
    private Double peso;
    private String ocupacion;
    private byte[] imagen;
    private Date fecha;
    private int enfermero;
    private String categoria;

    public HistoriaClinica() {
    }

    public HistoriaClinica(int codigo, String paciente, String cedula, String telefono, int edad, String sexo, String direccion, Date fecha_nacimiento, String mail, Double estatura, Double peso, String ocupacion, byte[] imagen, Date fecha, int enfermero, String categoria) {
        this.codigo = codigo;
        this.paciente = paciente;
        this.cedula = cedula;
        this.telefono = telefono;
        this.edad = edad;
        this.sexo = sexo;
        this.direccion = direccion;
        this.fecha_nacimiento = fecha_nacimiento;
        this.mail = mail;
        this.estatura = estatura;
        this.peso = peso;
        this.ocupacion = ocupacion;
        this.imagen = imagen;
        this.fecha = fecha;
        this.enfermero = enfermero;
        this.categoria = categoria;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setEstatura(Double estatura) {
        this.estatura = estatura;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setEnfermero(int enfermero) {
        this.enfermero = enfermero;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getPaciente() {
        return paciente;
    }

    public String getCedula() {
        return cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public int getEdad() {
        return edad;
    }

    public String getSexo() {
        return sexo;
    }

    public String getDireccion() {
        return direccion;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public String getMail() {
        return mail;
    }

    public Double getEstatura() {
        return estatura;
    }

    public Double getPeso() {
        return peso;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public Date getFecha() {
        return fecha;
    }

    public int getEnfermero() {
        return enfermero;
    }

    public String getCategoria() {
        return categoria;
    }

     
}    