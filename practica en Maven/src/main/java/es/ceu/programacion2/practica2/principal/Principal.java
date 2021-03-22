package es.ceu.programacion2.practica2.principal;

import es.ceu.programacion2.practica2.dominio.Tablero;

import java.util.concurrent.TimeUnit;
import java.lang.InterruptedException;

public class Principal {
    public static void main(String[] args) {
        try {
            Tablero tablero = new Tablero();
            System.out.println("SIMULACIÓN CON TABLERO LEÍDO");
            tablero.leerEstadoActual();
            System.out.println(tablero);
            System.out.println("--------------------------------------");
            for (int i = 0; i <= 5; i++) {
                TimeUnit.SECONDS.sleep(1);
                tablero.transitarAlEstadoSiguiente();
                System.out.println(tablero);
                System.out.println("--------------------------------------");
            }
            System.out.println("SIMULACIÓN CON TABLERO GENERADO MEDIANTE MONTECARLO");
            tablero.generarEstadoActualPorMontecarlo();
            System.out.println(tablero);
            System.out.println("--------------------------------------");
            for (int i = 0; i <= 15; i++) {
                TimeUnit.SECONDS.sleep(1);
                tablero.transitarAlEstadoSiguiente();
                System.out.println(tablero);
                System.out.println("--------------------------------------");
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}
