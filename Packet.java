import java.io.*;
import java.nio.*;
import java.util.*;



public class Packet{
    private Couche2 couche2;
    private int taillePacket;

    public Packet(){
        this.couche2 = null;
        this.taillePacket = 0;
    }
    
    public byte[] data(byte[] donnee, int offset, int length){
        //On extrait dans le paquet le morceau d'octets qui nous intéresse.
        byte tab[] = null;
        tab = new byte[length];
        for(int i=0; i < donnee.length; i++){
            tab[i] = donnee[offset+i];
        }
        return tab;
    }
    
}
