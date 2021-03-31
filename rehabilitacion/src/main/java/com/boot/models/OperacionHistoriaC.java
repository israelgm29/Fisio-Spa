/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boot.models;

import com.boot.controller.FrmCargaController;
import com.boot.dataaccess.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        String sql = "SELECT codigo, paciente, cedula, edad, foto, categoria FROM \"historia clinica\" ORDER BY codigo ASC";
        try {
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(OperacionHistoriaC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
        public int InsertarPaciente(HistoriaClinica hc){
        int band=0;
        try{
            String sql="INSERT INTO \"historia clinica\" values ("+hc.getPaciente()+",'"+hc.getCedula()+"','"+hc.getTelefono()+""
                    + "',"+hc.getEdad()+","+hc.getSexo()+","+hc.getDireccion()+","+hc.getFecha_nacimiento()+""
                    + ","+hc.getMail()+","+hc.getEstatura()+","+hc.getPeso()+","+hc.getOcupacion()+","+hc.getImagen()+""
                    + ","+hc.getEnfermero()+","+hc.getCategoria()+");";
            stm=con.createStatement();
            band=stm.executeUpdate(sql);
        }catch(SQLException e){
            Logger.getLogger(HistoriaClinica.class.getName()).log(Level.SEVERE,null,e);
        }
        return band;
    }

}
