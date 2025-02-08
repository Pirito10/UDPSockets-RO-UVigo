import java.io.IOException;
import java.net.*;

public class plus {

    public static void main(String[] args) {

        // Se comprueba la cantidad correcta de parámetros
        if (args.length != 4) {
            System.out.println("\nSintaxis correcta: java plus IP_servidor puerto_destino M timeout");
            return;
        }

        // Se obtiene la dirección del servidor a partir del argumento indicado por
        // línea de comandos
        InetAddress serverAddress = null;
        try {
            serverAddress = InetAddress.getByName(args[0]);
        } catch (UnknownHostException e) {
            System.out.println("Error en la dirección del servidor");
        }
        // Se obtiene el puerto del servidor a partir del argumento indicado por línea
        // de comandos
        int serverPort = Integer.parseInt(args[1]);
        // Se obtiene el entero (y se pasa a array de bytes) a partir del argumento
        // indicado por línea de comandos
        byte[] M = args[2].getBytes();
        // Se obtiene el timeout a partir del argumento indicado por línea de comandos
        int timeout = Integer.parseInt(args[3]);

        // Se crea el socket
        DatagramSocket datagramSocket = null;
        try {
            datagramSocket = new DatagramSocket();
        } catch (SocketException e) {
            System.out.println("Error en la creación del socket");
        }

        // Se crea el paquete de salida y se envía al servidor
        DatagramPacket outDP = new DatagramPacket(M, M.length, serverAddress, serverPort);
        try {
            datagramSocket.send(outDP);
        } catch (IOException e) {
            System.out.println("Error al enviar el paquete");
        }

        // Se crea un array de bytes para almacenar el resultado
        byte[] result = new byte[4];

        // Se crea el paquete de llegada
        DatagramPacket inDP = new DatagramPacket(result, result.length);

        // Se establece el timeout y se intenta recibir el paquete
        try {
            datagramSocket.setSoTimeout(timeout * 1000);
            datagramSocket.receive(inDP);
        } catch (IOException e) {
            // Si salta el timeout se avisa y se cierra el programa
            System.out.println(
                    "\nNo hay respuesta del servidor " + args[0] + ":" + args[1] + " tras " + timeout + " segundos");
            return;
        }

        // Se pasa el array de bytes a un entero
        int num;
        num = ((result[0] & 0xFF) << 24 | (result[1] & 0xFF) << 16 | (result[2] & 0xFF) << 8 | result[3] & 0xFF);

        // Se muestra el resultado
        System.out.println("\nResultado: " + num);

        // Se cierra el socket
        datagramSocket.close();
    }
}
