/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boot.models;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author lufeb
 */
public class Paciente {
    private String cedula;
    private int numerohc;
    private String nombre;
    private String apellidop;
    private String apellidom;
    private String estadocivil;
    private String telefono;
    private int edad;
    private String genero;
    private String direccion;
    private LocalDate fecha_nacimiento;
    private String mail;
    private Double estatura;
    private Double peso;
    private String ocupacion;
    private String imagen;
    private int enfermero;
    private String categoria;

    public Paciente() {
    }

    public Paciente(String cedula, int numerohc, String nombre, String apellidop, String apellidom, String estadocivil, String telefono, int edad, String genero, String direccion, LocalDate fecha_nacimiento, String mail, Double estatura, Double peso, String ocupacion, String imagen, int enfermero, String categoria) {
        this.cedula = cedula;
        this.numerohc = numerohc;
        this.nombre = nombre;
        this.apellidop = apellidop;
        this.apellidom = apellidom;
        this.estadocivil = estadocivil;
        this.telefono = telefono;
        this.edad = edad;
        this.genero = genero;
        this.direccion = direccion;
        this.fecha_nacimiento = fecha_nacimiento;
        this.mail = mail;
        this.estatura = estatura;
        this.peso = peso;
        this.ocupacion = ocupacion;
        this.imagen = imagen;
        this.enfermero = enfermero;
        this.categoria = categoria;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public int getNumerohc() {
        return numerohc;
    }

    public void setNumerohc(int numerohc) {
        this.numerohc = numerohc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidop() {
        return apellidop;
    }

    public void setApellidop(String apellidop) {
        this.apellidop = apellidop;
    }

    public String getApellidom() {
        return apellidom;
    }

    public void setApellidom(String apellidom) {
        this.apellidom = apellidom;
    }

    public String getEstadocivil() {
        return estadocivil;
    }

    public void setEstadocivil(String estadocivil) {
        this.estadocivil = estadocivil;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Double getEstatura() {
        return estatura;
    }

    public void setEstatura(Double estatura) {
        this.estatura = estatura;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getEnfermero() {
        return enfermero;
    }

    public void setEnfermero(int enfermero) {
        this.enfermero = enfermero;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }



    

  
     
}    