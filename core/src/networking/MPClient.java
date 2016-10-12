/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networking;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import java.io.IOException;
import networking.packet.Packets;

/**
 *
 * @author kristian
 */
public class MPClient {
    public Client client;
    ClientNetworkListener cl;
    
    public MPClient() throws IOException {
        client = new Client();
        cl = new ClientNetworkListener();
        client.addListener(cl);
        registerPackets();
        
        client.start();
        client.connect(5000, "127.0.0.1", 23232);
    }
    private void registerPackets() {
        Kryo kryo = client.getKryo();
        kryo.register(Packets.Packet0Message.class);
        kryo.register(Packets.Packet1LoginRequest.class);
        kryo.register(Packets.Packet2LoginAnswer.class);
    }
}
