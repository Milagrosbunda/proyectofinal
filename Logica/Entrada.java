
package Logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Delfina
 */
@Entity
public class Entrada implements Serializable {
    
   @Id
           @GeneratedValue(strategy=GenerationType.SEQUENCE)
    int id_entrada;
 @Basic
    
    int hora;
    
    String cliente;
    int edad;
   @Temporal(javax.persistence.TemporalType.DATE)
    Date dia;
   @ManyToOne
   public Juego juego;
   
   
    public Entrada(){
    }

    public Entrada(int id_entrada, int hora, String cliente, int edad, Date dia, Juego juego) {
        this.id_entrada = id_entrada;
        this.hora = hora;
        this.cliente = cliente;
        this.edad = edad;
        this.dia = dia;
        this.juego = juego;
    }

 



    public int getId_entrada() {
        return id_entrada;
    }

    public void setId_entrada(int id_entrada) {
        this.id_entrada = id_entrada;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

 

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }

 



    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    
  
    
}
