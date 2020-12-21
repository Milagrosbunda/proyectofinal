/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Juego implements Serializable {
    
   @Id
           @GeneratedValue(strategy=GenerationType.SEQUENCE)
    int id_juego;
 @Basic
    String nombre;
    double abre;
    double cierra;
    int edadMinima;


    public Juego() {
    }

    public Juego(int id_juego, String nombre, double abre, double cierra, int edadMinima) {
        this.id_juego = id_juego;
        this.nombre = nombre;
        this.abre = abre;
        this.cierra = cierra;
        this.edadMinima = edadMinima;
    }

    public int getEdadMinima() {
        return edadMinima;
    }

    public void setEdadMinima(int edadMinima) {
        this.edadMinima = edadMinima;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId_juego() {
        return id_juego;
    }

    public void setId_juego(int Id_juego) {
        this.id_juego = Id_juego;
    }

    public double getAbre() {
        return abre;
    }

    public void setAbre(double abre) {
        this.abre = abre;
    }

    public double getCierra() {
        return cierra;
    }

    public void setCierra(double cierra) {
        this.cierra = cierra;
    }

   

    
}
