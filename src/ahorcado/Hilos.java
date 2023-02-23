package ahorcado;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hilos extends Thread {

    // Declaration
    Socket socket = null;
    DataInputStream input;
    DataOutputStream output;
    Ahorcado slot = new Ahorcado();

    public Hilos() {
    }

    public Hilos(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
            String ip, user, equipo, so, letra;
            //String regex = "[a-zA-Z]";
            int intentos = 0;

            // IP
            ip = input.readUTF();

            System.out.println(
                    "IP: " + ip);

            // Usuario
            user = input.readUTF();

            System.out.println(
                    "Usuario: " + user);

            // Equipo
            equipo = input.readUTF();

            System.out.println(
                    "Equipo: " + equipo);

            // SO
            so = input.readUTF();

            System.out.println(
                    "Sistema: " + so);

            // Bienvenida
            output.writeUTF(
                    "Bienvenido " + user + ", vamos a jugar al ahorcado.\n\r"
                            + "\n\rDatos: " + user + "\n\r"
                            + "IP: " + ip + "\n\r"
                            + "Equipo: " + equipo + "\n\r"
                            + "Sistema operativo: " + so + "\n\r");

            // Palabra (DELETE ME AT RELEASE)
            output.writeUTF(Ahorcado.palabra);
            System.out.println(Ahorcado.palabra);

            do {
                letra = input.readUTF();
                System.out.println(letra);

                // Convierte String a un Array de char
                String palabra = Ahorcado.palabra;
                //char[] charPalabra = Ahorcado.palabra.toCharArray();
                int intentosLetra = 0;

                // Comprueba si la letra introducida está en la palabra
                if (palabra.contains("*")) output.writeUTF("Gracias por participar, adios.");
                else if (palabra.contains(letra)) {
                     if (palabra.contentEquals(letra)){
                         output.writeUTF("2 - Has ganado, la palabra era: " + palabra);
                     }
                    for (int i = 0; i < palabra.length(); i++) {
                       
                        if (palabra.charAt(i) == letra.charAt(0)) {
                            intentosLetra++;
                            output.writeUTF("1. Letra correcta "+ intentos +" intentos\n\r");
                            
                        }
                    }
                    
                }
                else if (!palabra.contains(letra)) output.writeUTF("0. Letra incorrecta " + intentos + " intentos\n\r");
                intentos++;
            } while (intentos <= 8);
                output.writeUTF("3 - Has perdido");
        } catch (Exception e) {
        }
    }
}