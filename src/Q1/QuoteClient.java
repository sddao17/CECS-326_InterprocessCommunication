
package Q1;

import java.net.*;
import java.io.*;

/**
 * @author Carlos Verduzco, Steven Dao
 * @version 1.0
 *
 * Date: September 25, 2021
 * Purpose: Modified DateClient so that it requests a quote
 *          from the quote server.
 */
public class QuoteClient {

    /**
     * Executes the application.
     *
     * @param args the command-line arguments to the application
     */
    public static void main(String[] args) {

        // host IP address and port variables
        String host = "localhost";
        int port = 6017;

        // handle I/O exceptions
        try {
            // create a socket which connects to the local server on the "quote of the day" port
            Socket socket = new Socket(host, port);
            System.out.println("\nQuote of the day: ");

            // input stream to read from the server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;

            // read each line of the input received from the server
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }

            // close all streams and sockets
            in.close();
            socket.close();

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