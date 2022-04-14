
package Q2;

import java.net.*;
import java.io.*;

/**
 * @author Carlos Verduzco, Steven Dao
 * @version 1.0
 *
 * Date: September 25, 2021
 * Purpose: An echo server listening on port 6007. This server reads from the client
 *          and echoes back the result.
 */
public class EchoServer extends Thread {

    // member variables
    private boolean isListening;
    private final ServerSocket socket;

    /**
     * Default constructor for the QuoteServer class.
     *
     * @param port the port of which the socket will be attached
     */
    public EchoServer(int port) throws IOException {

        isListening = true;
        socket = new ServerSocket(port);
    }

    /**
     * Synchronously stops the socket after waiting the specified amount of time.
     *
     * @param time the time to wait in milliseconds
     */
    public void startTimeout(int time) throws SocketException {

        this.socket.setSoTimeout(time);
    }

    /**
     * Executes the application.
     *
     * @param args the command-line arguments to the application
     */
    public static void main(String[] args) {

        // port and timeout variables
        int port = 6007;
        int timeout = 120_000;      // in milliseconds
        EchoServer server = null;

        // handle I/O exceptions
        try {
            server = new EchoServer(port);

            System.out.print("""
                    
                    Server connection has been established.
                    Waiting for a client connection ...
                    """);

            // now listen for connections
            while (server.isListening) {
                try {
                    // throw a SocketTimeoutException after idly waiting the specified time in milliseconds
                    server.startTimeout(timeout);

                    // accept any client sockets that attempt a connection with this server
                    Socket client = server.socket.accept();
                    System.out.println("\nClient connection established.");

                    // input stream to read from the client
                    // use an InputStreamReader to read the byte level in case the client sends binary data
                    InputStreamReader in = new InputStreamReader(client.getInputStream());
                    // output stream to write to the client
                    PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                    boolean clientDisconnected = false;

                    // loop for each time the user wants to enter more input
                    while (!clientDisconnected) {
                        // create a StringBuilder variable, so we can store the bytes as a string of characters
                        StringBuilder bytesAsString = new StringBuilder();
                        int currentByte;

                        // continue reading each byte until the client has ended the socket connection
                        while ((currentByte = in.read()) != -1) {
                            // also stop reading when the user enters their input
                            if (currentByte == '\n')
                                break;

                            // cast each byte into its corresponding character
                            bytesAsString.append((char) currentByte);
                        }

                        // check if the client closed their socket connection
                        if (currentByte == -1) {
                            // close all streams and client sockets before we loop again
                            out.close();
                            client.close();

                            // let the server know the connection was terminated, then listen for more connections
                            System.out.print("""
                                        Connection terminated successfully.
                                        Waiting for a client connection ...
                                        """);
                            clientDisconnected = true;
                        }

                        // convert to a standard String
                        String line = bytesAsString.toString();
                        // store the length to prevent excessive method calls within the loop
                        int lineLength = line.length();

                        // traverse through all substrings of length 6 throughout the line sent by the client
                        for (int i = 0; i + 6 < lineLength; ++i) {
                            String substring = line.substring(i, i + 6);

                            // echo the client's message by replacing all instances of "client" with "server"
                            // and vice versa from the line sent by the client
                            line = switch (substring) {
                                case "client" -> line.substring(0, i) + "server" + line.substring(i + 6, lineLength);
                                case "server" -> line.substring(0, i) + "client" + line.substring(i + 6, lineLength);
                                case "Client" -> line.substring(0, i) + "Server" + line.substring(i + 6, lineLength);
                                case "Server" -> line.substring(0, i) + "Client" + line.substring(i + 6, lineLength);
                                case "CLIENT" -> line.substring(0, i) + "SERVER" + line.substring(i + 6, lineLength);
                                case "SERVER" -> line.substring(0, i) + "CLIENT" + line.substring(i + 6, lineLength);
                                default -> line;
                            };
                        }

                        // write the echoed line back to the client
                        out.println(line);
                    }

                } catch (SocketException err) {
                    System.err.println("The connection was interrupted.\n");
                }
            }

        } catch (SocketTimeoutException err) {
            System.err.println("\nThe server timed out after no connection was found.");
            assert server != null;
            server.isListening = false;

        } catch (IOException err) {
            err.printStackTrace();
        }
    }
}
