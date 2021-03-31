/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boot.dataaccess;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Conexion {

    public Connection con = null;
    public Statement stm = null;
    public ResultSet rs = null;

    public void Conectar() {
        String cadena = "jdbc:postgresql://localhost:5432/hospital?user=postgres&password=03754";
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
           
        }
        try {
            con = DriverManager.getConnection(cadena);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Desconectar() {
        try {
            con.close();
            stm.close();
            rs.close();
        } catch (SQLException e) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
