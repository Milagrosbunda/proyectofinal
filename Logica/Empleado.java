
package Logica;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Delfina
 */
@Entity
public class Empleado implements Serializable{
    
   @Id
   @GeneratedValue(strategy=GenerationType.AUTO)
    int id_empleado;
 @Basic
    String nombre;
    int contraseña;

@ManyToOne
    Juego juego;
    

    public Empleado() {
    }

    public Empleado(int id_empleado, String nombre, Juego juego, int contraseña) {
        this.id_empleado = id_empleado;
        this.nombre = nombre;
        this.contraseña = contraseña;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }




    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }

    public int getContraseña() {
        return contraseña;
    }

    public void setContraseña(int contraseña) {
        this.contraseña = contraseña;
    }
    
    
    
    
    
}
