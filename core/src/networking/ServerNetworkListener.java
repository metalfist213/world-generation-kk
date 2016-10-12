/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networking;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import networking.packet.Packets.*;

/**
 *
 * @author kristian
 */
public class ServerNetworkListener extends Listener {

    public ServerNetworkListener() {

    }

    @Override
    public void connected(Connection c) {
        System.out.println("[SERVER]: Someone has connected!");
        MPServer.connections[MPServer.connections.length-1] = c; //Stores the connection object in the connections array
    }

    @Override
    public void disconnected(Connection c) {
        System.out.println("[SERVER]: Someone has disconnected!");
    }

    @Override
    public void received(Connection c, Object o) {
        if(o instanceof Packet0Message) {
            System.out.println("Message Received: "+((Packet0Message) o).message);
        }
    }
}
