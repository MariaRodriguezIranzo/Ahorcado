package ahorcado;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Ahorcado {

    // Declaration
    public static String[] palabras = {"mercho", "ladron", "florida", "yakuza", "dinero", "impuestos", "critican", "trayecto", "pinchada", "excusa"};
    public static int num;
    public static String palabra;

    public static void main(String[] args) throws IOException {
        Socket cliente = null;
        ServerSocket server = new ServerSocket(5556);
        
        num = randomNumbers(0, 9);
        palabra = palabras[num]; 

        while (true) {
            try {
                cliente = server.accept();
                Hilos hilos = new Hilos(cliente);
                hilos.start();
            } catch (Exception e) {
            }
        }
    }

    public static int randomNumbers(int min, int max) {
        double random = Math.random() * (max - min + 1) + min;
        return (int) (random);
    }
}
