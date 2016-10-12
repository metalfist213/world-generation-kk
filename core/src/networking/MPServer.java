/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networking;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Server;
import java.io.IOException;
import networking.packet.Packets;



/**
 *
 * @author kristian
 */
public class MPServer {
    int port = 23232;
    Server server;
    ServerNetworkListener sl;
    public static Connection[] connections;
    
    public MPServer() throws IOException {
        server = new Server();
        sl = new ServerNetworkListener();
        connections = new Connection[8];
        
        //Add listener
        server.addListener(sl);
        registerPackets();
        server.bind(port);
        
        server.start();
    }
    private void registerPackets() {
        Kryo kryo = server.getKryo();
        
        kryo.register(Packets.Packet0Message.class);
        kryo.register(Packets.Packet1LoginRequest.class);
        kryo.register(Packets.Packet2LoginAnswer.class);
    }
}
