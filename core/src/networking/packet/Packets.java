/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networking.packet;


/**
 *
 * @author kristian
 */
public class Packets {
    public static class Packet0Message {public String message;}
    public static class Packet1LoginRequest {}
    public static class Packet2LoginAnswer {public boolean mayConnect;}
    public static class Packet3MoveObject {public Object o;}
}