/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boot.models;

import com.boot.controller.FrmCargaController;
import com.boot.dataaccess.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author develop
 */
public class OperacionHistoriaC extends Conexion {

    public int loginadmin(String us, String pass) {

        int resultado = 0;

        try {
            String sql = "SELECT cedula, clave\n"
                    + "  FROM public.administrador WHERE nombre='" + us + "' AND clave='" + pass + "'";
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            if (rs.next()) {
                resultado = 1;
            }

            return resultado;
        } catch (SQLException ex) {
            Logger.getLogger(FrmCargaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    public ResultSet Mostrarpacientes() {
        String sql = "SELECT cedula,numhclinic,nombre,apellido_p,apellido_m, edad, foto, categoria FROM paciente ORDER BY nombre ASC";
        try {
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(OperacionHistoriaC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public int InsertarPaciente(Paciente hc) {
        int band = 0;

        try {
            String sql = "INSERT INTO paciente(cedula,nombre,apellido_p,apellido_m,estadociv,telefono,edad,genero,direccion,"
                    + "fecha_nac,mail,estatura,peso,ocupacion,foto,categoria)"
                    + " values ('" + hc.getCedula()+ "','" + hc.getNombre()+ "','"+hc.getApellidop()+"','" +hc.getApellidom()+"','" +hc.getEstadocivil()+"','" + hc.getTelefono() + ""
                    + "','" + hc.getEdad() + "','" + hc.getGenero()+ "','" + hc.getDireccion() + "','" + hc.getFecha_nacimiento() + ""
                    + "','" + hc.getMail() + "','" + hc.getEstatura() + "','" + hc.getPeso() + "','" + hc.getOcupacion() + "','" + hc.getImagen() + ""
                    + "','" + hc.getCategoria() + "')";
            System.out.println(sql);
            stm = con.createStatement();
            band = stm.executeUpdate(sql);
        } catch (SQLException e) {
            Logger.getLogger(Paciente.class.getName()).log(Level.SEVERE, null, e);
        }
        return band;
    }

}
