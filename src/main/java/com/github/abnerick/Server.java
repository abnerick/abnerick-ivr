package com.github.abnerick;

import org.asteriskjava.fastagi.AgiServer;
import org.asteriskjava.fastagi.DefaultAgiServer;

/**
 * Agi Server!
 *
 */
public class Server extends DefaultAgiServer
{
    public static void main( String[] args ) throws Exception
    {
    	final AgiServer server;

        server = new DefaultAgiServer();
        server.startup();
    }
}
