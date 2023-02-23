package ahorcado;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);
        try {
            Socket client = new Socket("127.0.0.1", 5556);
            DataInputStream input = new DataInputStream(client.getInputStream());
            DataOutputStream output = new DataOutputStream(client.getOutputStream());
            
            // IP
            output.writeUTF(client.getRemoteSocketAddress().toString());
            
            // Usuario
            output.writeUTF(System.getProperty("user.name"));
            
            // Equipo
            output.writeUTF(InetAddress.getLocalHost().getHostName());
            
            // SO
            output.writeUTF(System.getProperty("os.name"));
             System.out.println("----------------------------------------------------------");
            // Mensaje
            String bienvenido = input.readUTF();
            System.out.println(bienvenido);
            System.out.println("----------------------------------------------------------");
            String palabra = input.readUTF();
            //System.out.println(palabra);

            int intentos = 0;
            while (intentos <= 8) {
                //pedir letra
                System.out.println("INTRODUCE UNA LETRA DE A-Z" + "\nO * PARA SALIR");
                String letra = String.valueOf(s.next());
                output.writeUTF(letra);

                intentos++;
                //resultado
                String resultado = input.readUTF();
                System.out.println(resultado);
                if (resultado.contains("ganado")) System.exit(0);
            }
            // endGame
            String endGame = input.readUTF();
            System.out.println(endGame);
        } catch (Exception e) {
        }
    }
}
