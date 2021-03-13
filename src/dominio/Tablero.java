package dominio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Esta clase es responsable de leer el tablero de un
 * fichero en forma de ceros y unos, ir transitando de
 * estados e ir mostrando dichos estados.
 */

public class Tablero {
    private static int DIMENSION = 30;
    private int[][] estadoActual = new int[DIMENSION][DIMENSION]; //matriz que representa el estado actual.
    private int[][] estadoSiguiente = new int[DIMENSION][DIMENSION]; // Matriz que representa el estado siguiente.

    /********************************************************
     * Lee el estado inicial de un fichero llamado ‘matriz‘.
     ********************************************************/

    public void leerEstadoActual() {
        try {
            Scanner fichero = new Scanner(new File("matriz"));
            for (int i = 0; i < DIMENSION; i++) {
                String linea = fichero.nextLine(); //Cogemos la siguiente línea del fichero
                for (int j = 0; j < DIMENSION; j++) {
                    estadoActual[i][j] = Integer.parseInt(String.valueOf(linea.charAt(j)));
                }
            }

            for (int i = 0; i < DIMENSION; i++) {
                for (int j = 0; j < DIMENSION; j++) {
                    int vecinasVivas;
                    if (i == 0 && j == 0) { //vecinas vivas esquina superior izquierda
                        vecinasVivas = estadoActual[0][1] + estadoActual[1][0] + estadoActual[1][1];
                    } else if (i == 0 && j == DIMENSION - 1) { //vecinas vivas esquina superior derecha
                        vecinasVivas = estadoActual[0][DIMENSION - 2] + estadoActual[1][DIMENSION - 1] +
                                estadoActual[1][DIMENSION - 2];
                    } else if (i == DIMENSION - 1 && j == 0) { //vecinas vivas esquina inferior izquierda
                        vecinasVivas = estadoActual[DIMENSION - 1][1] + estadoActual[DIMENSION - 2][0] + estadoActual[DIMENSION - 2][1];
                    } else if (i == DIMENSION - 1 && j == DIMENSION - 1) { //vecinas vivas esquina inferior derecha
                        vecinasVivas = estadoActual[DIMENSION - 2][DIMENSION - 1] + estadoActual[DIMENSION - 1][DIMENSION - 2] +
                                estadoActual[DIMENSION - 2][DIMENSION - 2];
                    } else if (j == 0) { //vecinas vivas columna izquierda
                        vecinasVivas = estadoActual[i - 1][0] + estadoActual[i - 1][1] + estadoActual[i][1]
                                + estadoActual[i + 1][0] + estadoActual[i + 1][1];
                    } else if (j == DIMENSION - 1) { //vecinas vivas columna derecha
                        vecinasVivas = estadoActual[i - 1][DIMENSION - 1] + estadoActual[i - 1][DIMENSION - 2] + estadoActual[i][DIMENSION - 2]
                                + estadoActual[i + 1][DIMENSION - 1] + estadoActual[i + 1][DIMENSION - 2];
                    } else if (i == 0) { //vecinas vivas fila de arriba
                        vecinasVivas = estadoActual[0][j - 1] + estadoActual[1][j - 1] + estadoActual[1][j]
                                + estadoActual[0][j + 1] + estadoActual[1][j + 1];
                    } else if (i == DIMENSION - 1) { //vecinas vivas fila de abajo
                        vecinasVivas = estadoActual[DIMENSION - 1][j - 1] + estadoActual[DIMENSION - 2][j - 1] +
                                estadoActual[DIMENSION - 2][j] + estadoActual[DIMENSION - 1][j + 1] +
                                estadoActual[DIMENSION - 2][j + 1];
                    } else { //vecinas vivas interior
                        vecinasVivas = estadoActual[i - 1][j - 1] + estadoActual[i - 1][j] + estadoActual[i - 1][j + 1]
                                + estadoActual[i][j - 1] + estadoActual[i][j + 1] + estadoActual[i + 1][j - 1] +
                                estadoActual[i + 1][j] + estadoActual[i + 1][j + 1];
                    }

                    if (estadoActual[i][j] == 1 && (vecinasVivas == 2 || vecinasVivas == 3)) {
                        estadoSiguiente[i][j] = 1;
                    } else if (estadoActual[i][j] == 0 && vecinasVivas == 3) {
                        estadoSiguiente[i][j] = 1;
                    } else {
                        estadoSiguiente[i][j] = 0;
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
// La secuencia de ceros y unos del fichero es guardada en ‘estadoActual‘ y, utilizando las reglas del juego de la vida,
// se insertan los ceros y unos correspondientes en ‘estadoSiguiente‘.

    /********************************************************
     * Genera un estado inicial aleatorio. Para cada celda
     * genera un número aleatorio en el intervalo [0, 1). Si
     * el número es menor que 0,5, entonces la celda está
     * inicialmente viva. En caso contrario, está muerta.
     *******************************************************/
    public void generarEstadoActualPorMontecarlo() {
    }
// La secuencia de ceros y unos generada es guardada en ‘estadoActual‘ y, utilizando las reglas del juego de la vida, se
// insertan los ceros y unos  correspondientes en ‘estadoSiguiente‘.

    /********************************************************
     * Transita al estado siguiente según las reglas del
     * juego de la vida.
     ********************************************************/
    public void transitarAlEstadoSiguiente() {
        estadoActual = estadoSiguiente;
        estadoSiguiente = new int[DIMENSION][DIMENSION];
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                int vecinasVivas;
                if (i == 0 && j == 0) { //vecinas vivas esquina superior izquierda
                    vecinasVivas = estadoActual[0][1] + estadoActual[1][0] + estadoActual[1][1];
                } else if (i == 0 && j == DIMENSION - 1) { //vecinas vivas esquina superior derecha
                    vecinasVivas = estadoActual[0][DIMENSION - 2] + estadoActual[1][DIMENSION - 1] +
                            estadoActual[1][DIMENSION - 2];
                } else if (i == DIMENSION - 1 && j == 0) { //vecinas vivas esquina inferior izquierda
                    vecinasVivas = estadoActual[DIMENSION - 1][1] + estadoActual[DIMENSION - 2][0] + estadoActual[DIMENSION - 2][1];
                } else if (i == DIMENSION - 1 && j == DIMENSION - 1) { //vecinas vivas esquina inferior derecha
                    vecinasVivas = estadoActual[DIMENSION - 2][DIMENSION - 1] + estadoActual[DIMENSION - 1][DIMENSION - 2] +
                            estadoActual[DIMENSION - 2][DIMENSION - 2];
                } else if (j == 0) { //vecinas vivas columna izquierda
                    vecinasVivas = estadoActual[i - 1][0] + estadoActual[i - 1][1] + estadoActual[i][1]
                            + estadoActual[i + 1][0] + estadoActual[i + 1][1];
                } else if (j == DIMENSION - 1) { //vecinas vivas columna derecha
                    vecinasVivas = estadoActual[i - 1][DIMENSION - 1] + estadoActual[i - 1][DIMENSION - 2] + estadoActual[i][DIMENSION - 2]
                            + estadoActual[i + 1][DIMENSION - 1] + estadoActual[i + 1][DIMENSION - 2];
                } else if (i == 0) { //vecinas vivas fila de arriba
                    vecinasVivas = estadoActual[0][j - 1] + estadoActual[1][j - 1] + estadoActual[1][j]
                            + estadoActual[0][j + 1] + estadoActual[1][j + 1];
                } else if (i == DIMENSION - 1) { //vecinas vivas fila de abajo
                    vecinasVivas = estadoActual[DIMENSION - 1][j - 1] + estadoActual[DIMENSION - 2][j - 1] +
                            estadoActual[DIMENSION - 2][j] + estadoActual[DIMENSION - 1][j + 1] +
                            estadoActual[DIMENSION - 2][j + 1];
                } else { //vecinas vivas interior
                    vecinasVivas = estadoActual[i - 1][j - 1] + estadoActual[i - 1][j] + estadoActual[i - 1][j + 1]
                            + estadoActual[i][j - 1] + estadoActual[i][j + 1] + estadoActual[i + 1][j - 1] +
                            estadoActual[i + 1][j] + estadoActual[i + 1][j + 1];
                }

                if (estadoActual[i][j] == 1 && (vecinasVivas == 2 || vecinasVivas == 3)) {
                    estadoSiguiente[i][j] = 1;
                } else if (estadoActual[i][j] == 0 && vecinasVivas == 3) {
                    estadoSiguiente[i][j] = 1;
                } else {
                    estadoSiguiente[i][j] = 0;
                }
            }
        }
    }
// La variable ‘estadoActual‘ pasa a tener el contenido de ‘estadoSiguiente‘ y, éste útimo atributo pasar a reflejar el
// siguiente estado.

    /*******************************************************
     * Devuelve, en modo texto, el estado actual.
     * @return el estado actual.
     *******************************************************/
    @Override
    public String toString() {
        StringBuilder cadena = new StringBuilder();
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                if (estadoActual[i][j] == 0) {
                    cadena.append(" ");
                } else {
                    cadena.append("x");
                }
            }
            cadena.append("\n");
        }
        return cadena.toString();
    }
}

