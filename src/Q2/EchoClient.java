
package Q2;

import java.net.*;
import java.io.*;

/**
 * @author Carlos Verduzco, Steven Dao
 * @version 1.0
 *
 * Date: September 25, 2021
 * Purpose: An echo client. The client enters data to the server, and the
 *          server echoes the data back to the client.
 */
public class EchoClient {

    /**
     * Executes the application.
     *
     * @param args the command-line arguments to the application
     */
    public static void main(String[] args) {

        // host IP address and port variables
        String host = "localhost";
        int port = 6007;

        // handle I/O exceptions
        try {
            // create a socket which connects to the local server on the echo port
            Socket socket = new Socket(host, port);
            System.out.println();

            // let the program end when the user inputs an empty String
            while (true) {
                System.out.print(">> ");

                // input stream to read from the user
                BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                // output stream to write to the server
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                // read the input received from the user
                String line = input.readLine();

                // terminate the program successfully if the input stream returns an end-of-input character
                if (line == null || line.equals("")) {
                    // close all output streams and sockets before we exit
                    out.close();
                    socket.close();
                    System.exit(0);
                }

                // write the user input to the server
                out.println(line);

                // input reader to read the echoed line from the server
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                // now, read the input received from the server and print it out
                line = ">> " + in.readLine();
                System.out.println(line);
            }

        } catch (ConnectException err) {
            System.err.printf("""
                                        
                    Could not connect to a server at host IP address "%s", port "%s".
                    A server must be actively online before the client can establish a connection.
                    """, host, port);

        } catch (UnknownHostException err) {
            System.err.println("\nThe provided host IP address could not be found.");

        } catch (IllegalArgumentException err) {
            System.err.println("\nThe provided port does not exist.");

        } catch (BindException err) {
            System.err.println("\nThe connection could not be established using the provided host address and port.");

        } catch (NullPointerException err) {
            System.err.println("\nThe connection was interrupted.");

        } catch (IOException err) {
            err.printStackTrace();
        }
    }
}
