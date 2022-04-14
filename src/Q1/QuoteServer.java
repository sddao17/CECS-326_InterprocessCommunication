
package Q1;

import java.net.*;
import java.io.*;

/**
 * @author Carlos Verduzco, Steven Dao
 * @version 1.0
 *
 * Date: September 25, 2021
 * Purpose: Quote server listening to port 6017.
 */
public class QuoteServer extends Thread {

    // member variables
    private boolean isListening;
    private final ServerSocket socket;
    private final String[] quotes;

    /**
     * Default constructor for the QuoteServer class.
     *
     * @param port the port of which the socket will be attached
     */
    public QuoteServer(int port) throws IOException {

        isListening = true;
        socket = new ServerSocket(port);
        quotes = new String[] {
                "The greatest glory in living lies not in never falling, but in rising every time we fall.",
                "The way to get started is to quit talking and begin doing.",
                """
                    Your time is limited, so don't waste it living someone else's life.
                    Don't be trapped by dogma – which is living with the results of other people's thinking.
                    """,
                "If life were predictable it would cease to be life, and be without flavor.",
                """
                    If you look at what you have in life, you'll always have more.
                    If you look at what you don't have in life, you'll never have enough.
                    """,
                "If you set your goals ridiculously high and it's a failure, you will fail above everyone else's success.",
                "Life is what happens when you're busy making other plans.",
                "When you reach the end of your rope, tie a knot in it and hang on.",
                "Always remember that you are absolutely unique. Just like everyone else.",
                "I don't know, just bring me a bucket of fried chicken."
        };
    }

    /**
     * Returns a random quote from the array.
     *
     * @return the random quote from the list of quotes
     */
    public String getQuote() {

        // randomize our quote choice and store the random quote
        int randomChoice = (int) (Math.random() * 10);

        return this.quotes[randomChoice];
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
        int port = 6017;
        int timeout = 120_000;      // in milliseconds
        QuoteServer server = null;

        // handle I/O exceptions
        try {
            server = new QuoteServer(port);

            System.out.print("""
                    
                    Server connection has been established.
                    Waiting for a client connection ...
                    """);

            // now listen for connections
            while (server.isListening) {

                // throw a SocketTimeoutException after idly waiting the specified time in milliseconds
                server.startTimeout(timeout);

                // accept any client sockets that attempt a connection with this server
                Socket client = server.socket.accept();
                System.out.println("\nClient connection established.");

                // output stream to write to the client
                PrintWriter out = new PrintWriter(client.getOutputStream(), true);

                // grab a random quote from the server's quotes list
                String randomQuote = server.getQuote();

                // write the quote to the socket
                out.println("\"" + randomQuote + "\"");

                // close all output streams and sockets before we loop again
                out.close();
                client.close();

                // let the server know the connection was terminated, then listen for more connections
                System.out.print("""
                        Connection terminated successfully.
                        Waiting for a client connection ...
                        """);
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
