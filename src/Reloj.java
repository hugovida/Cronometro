
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author usuario
 */public class Reloj implements Runnable {
    private int segundos;
    private int minutos;
    private boolean activo; // Para indicar si el cronómetro está activo o no

    // Constructor y métodos getters y setters omitidos para brevedad...

    public Reloj(int segundos, int minutos, boolean activo) {
        this.segundos = segundos;
        this.minutos = minutos;
        this.activo = activo;
    }

    public Reloj() {
    }
    
    

    public int getSegundos() {
        return segundos;
    }

    public int getMinutos() {
        return minutos;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void iniciar() {
        activo = true;
        while (activo) { // Bucle para que el cronómetro se ejecute continuamente
            segundos--;
            if (segundos < 0) {
                minutos--;
                segundos = 59;
            }
            if (minutos == 0 && segundos == 0) {
                detener(); // Detener el cronómetro cuando llegue a 0
            }
            System.out.println(this); // Imprimir el estado del reloj (para pruebas, puedes eliminar esta línea en producción)
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void detener() {
        activo = false;
    }

    // Resto de la clase omitido para brevedad...

    @Override
    public void run() {
        iniciar(); // Llamar al método iniciar para comenzar el cronómetro
    }
}