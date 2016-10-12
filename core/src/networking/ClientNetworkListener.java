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
public class ClientNetworkListener extends Listener {
    @Override
    public void received(Connection c, Object o) {
        if(o instanceof Packet0Message) {
            System.out.println("Message: "+((Packet0Message) o).message);
        }
    }
}
