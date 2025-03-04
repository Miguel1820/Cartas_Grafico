import java.util.Random;

import javax.swing.JPanel;

public class Jugador {

    private int DISTANCIA = 40;
    private int MARGEN = 10;
    private int TOTAL_CARTAS = 10;
    //vector de objetos
    private Carta[] cartas = new Carta[TOTAL_CARTAS];
    private Random r = new Random();

    public void repartir() {
        for (int i = 0; i < TOTAL_CARTAS; i++) {
            cartas[i] = new Carta(r);
        }
    }

    public void mostrar(JPanel pnl) {
        //para cuando reparta limpie o borre
        pnl.removeAll();
        int x = MARGEN + (TOTAL_CARTAS - 1) * DISTANCIA;
        //PARA CADA OBJETO CARTAS EN LA COLECION CARTA (CICLO PARA COLECCIONES)
        for (Carta carta : cartas) {
            carta.mostrar(pnl, x, MARGEN);
            //VA AGREGANDO LA DISTANCIA A MEDIDA Q INCREMENTA LAS CARTAS
            x -= DISTANCIA;
        }
        //para cuando agregue todo los objetos se repinte
        pnl.repaint();
    }

    //Logica en 1 metodo
    public String getGrupos() {
        String mensaje = "No se encontraron grupos";

        //declaramos vector contadores a NombreCarta
        //.values().length = es para organizar por enumerado NombreCarta
        int[] contadores = new int[NombreCarta.values().length];
        for (Carta carta : cartas) {
            //incrementa la variable contador si encuentra 1 carta
            //.ordinal() = pregunta por la propiedad ordinal (pregunta el numero exacto de esa carta)
            contadores[carta.getNombre().ordinal()]++;
        }

        //verificar si hubo grupos
        boolean hayGrupos = false;
        for (int contador : contadores) {
            if (contador >= 2) {
                hayGrupos = true;
                break;
            }
        }

        //Si hayGrupos = true haga esto:
        if (hayGrupos) {
            mensaje = "Se encontraron los siguientes Grupos:\n";
            int posicion = 0;
            for (int contador : contadores) {
                if (contador >= 2) {

                    mensaje += Grupo.values()[contador] + " de " + NombreCarta.values()[posicion] + "\n";
                }
                posicion++;
            }
        }

        //Para averiguar si NO hay cartas en grupo
        boolean totalGrupos = false;
        for (int contador : contadores) {
            if (contador == 1) {
                totalGrupos = true;
            }
        }

        //Si NO hay cartas en grupo haga esto:
        if (totalGrupos) {
            mensaje += "\n";
            mensaje += "Tu puntaje fue: ";
            int acumulador = 0;
            int posicion = 0;
            for (int contador : contadores) {
                if (contador == 1) {
                    if(NombreCarta.values()[posicion] == NombreCarta.AS){
                        acumulador += 1;
                    }
                    else if(NombreCarta.values()[posicion] == NombreCarta.DOS){
                        acumulador += 2;
                    }
                    else if(NombreCarta.values()[posicion] == NombreCarta.TRES){
                        acumulador += 3;
                    }
                    else if(NombreCarta.values()[posicion] == NombreCarta.CUATRO){
                        acumulador += 4;
                    }
                    else if(NombreCarta.values()[posicion] == NombreCarta.CINCO){
                        acumulador += 5;
                    }
                    else if(NombreCarta.values()[posicion] == NombreCarta.SEIS){
                        acumulador += 6;
                    }
                    else if(NombreCarta.values()[posicion] == NombreCarta.SIETE){
                        acumulador += 7;
                    }
                    else if(NombreCarta.values()[posicion] == NombreCarta.OCHO){
                        acumulador += 8;
                    }
                    else if(NombreCarta.values()[posicion] == NombreCarta.NUEVE){
                        acumulador += 9;
                    }
                    else if(NombreCarta.values()[posicion] == NombreCarta.DIEZ){
                        acumulador += 10;
                    }
                    else if(NombreCarta.values()[posicion] == NombreCarta.JACK){
                        acumulador += 10;
                    }
                    else if(NombreCarta.values()[posicion] == NombreCarta.QUEEN){
                        acumulador += 10;
                    }
                    else if(NombreCarta.values()[posicion] == NombreCarta.KING){
                        acumulador += 10;
                    }
                }
                posicion++;
            }
            mensaje += acumulador + "\n";
        }

        return mensaje;
    }
    
    public String obtenerEscalera() {

        // Ordenar las cartas por pinta y valor
        int n = cartas.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                boolean intercambiar = false;

                // Compara las cartas por su pinta primero
                if (cartas[j].getPinta().ordinal() > cartas[j + 1].getPinta().ordinal()) {
                    intercambiar = true;
                }
                // Si las pintas son iguales, compara por valor
                else if (cartas[j].getPinta().ordinal() == cartas[j + 1].getPinta().ordinal() &&
                         cartas[j].getNombre().ordinal() > cartas[j + 1].getNombre().ordinal()) {
                    intercambiar = true;
                }

                // Realiza el intercambio si es necesario
                if (intercambiar) {
                    Carta temp = cartas[j];
                    cartas[j] = cartas[j + 1];
                    cartas[j + 1] = temp;
                }
            }
        }

        // Buscar la secuencia de cartas consecutivas
        Carta[] escalera = new Carta[TOTAL_CARTAS];
        int contador = 0;
        String mensajeEscalera = ""; // Aquí guardaremos el mensaje de la escalera

        for (int i = 0; i < TOTAL_CARTAS - 1; i++) {
            if (contador == 0) {
                escalera[contador] = cartas[i];
                contador++;
            }

            if (cartas[i].getPinta() == cartas[i + 1].getPinta() &&
                cartas[i].getNombre().ordinal() + 1 == cartas[i + 1].getNombre().ordinal()) {

                escalera[contador] = cartas[i + 1];
                contador++;

                // Si encontramos una secuencia válida de 3 o más cartas
                if (contador >= 3) {
                    break; // Si encontramos una escalera válida de 3 o más cartas, salimos del ciclo
                }
            } else {
                // La secuencia se rompió, reiniciamos el contador y el arreglo
                contador = 0;
            }
        }

        // Si no se encontró una escalera válida
        if (contador < 3) {
            mensajeEscalera = "No se encontró una escalera válida.";
        } else {
            // Construir el mensaje de la escalera encontrada
            String pinta = escalera[0].getPinta().name(); // Obtener la pinta de la primera carta
            int inicio = escalera[0].getNombre().ordinal(); // Valor de la primera carta
            int fin = escalera[contador - 1].getNombre().ordinal(); // Valor de la última carta

            // Construir el mensaje
            mensajeEscalera = "Escalera de " + pinta + " de " +
                              NombreCarta.values()[inicio].name() + " a " +
                              NombreCarta.values()[fin].name();
        }

        return mensajeEscalera;
    }

    

    //Logica para ordenar cartas por su pinta
    public void ordenar(){
        
        for(int i = 0; i < cartas.length; i++){
            for(int j = 0; j < cartas.length; j++){
                if(cartas[i].getIndice() > cartas[j].getIndice()){
                    Carta ct = cartas[i];
                    cartas[i] = cartas[j];
                    cartas[j] = ct;
                }
            }
        }
        
    }

}
