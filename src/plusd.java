import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class plusd {

    public static void main(String[] args) {

        // Se comprueba la cantidad correcta de parámetros
        if (args.length != 2) {
            System.out.println("\nSintaxis correcta: java plusd puerto N");
            return;
        }

        // Se obtiene el puerto del servidor a partir del argumento indicado por línea
        // de comandos
        int serverPort = Integer.parseInt(args[0]);
        // Se obtiene el entero a partir del argumento indicado por línea de comandos
        int N = Integer.parseInt(args[1]);

        // Se crea el socket
        DatagramSocket datagramSocket = null;
        try {
            datagramSocket = new DatagramSocket(serverPort);
        } catch (SocketException e) {
            System.out.println("Error en la creación del socket");
        }

        // Se crea un array de bytes para almacenar el número entrante
        byte[] M = new byte[4];

        // Se crea el paquete de llegada
        DatagramPacket inDP = new DatagramPacket(M, M.length);

        // Se crea un array de bytes para almacenar el resultado
        byte[] result = new byte[4];

        // Bucle de espera
        while (true) {

            // Se espera a que llegue un paquete
            try {
                datagramSocket.receive(inDP);
            } catch (IOException e) {
                System.out.println("Error en la recepción del paquete");
            }

            // Se pasa el array de bytes a un entero
            int num = Integer.parseInt(new String(M, 0, inDP.getLength()));

            // Se hace la operación y se almacena el resultado
            result[3] = (byte) (num + N);
            result[2] = (byte) (num + N >> 8);
            result[1] = (byte) (num + N >> 16);
            result[0] = (byte) (num + N >> 24);

            // Se obtiene la dirección del cliente a partir del paquete entrante
            InetAddress clientAddress = inDP.getAddress();
            // Se obtiene el puerto del cliente a partir del paquete entrante
            int clientPort = inDP.getPort();

            // Se crea el paquete con el resultado y se envía al cliente
            DatagramPacket outDP = new DatagramPacket(result, result.length, clientAddress, clientPort);
            try {
                datagramSocket.send((outDP));
            } catch (IOException e) {
                System.out.println("Error al enviar el paquete");
            }
        }
    }

}
