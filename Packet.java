import java.io.*;
import java.nio.*;
import java.util.*;



public class Packet{

    private byte[] donnee;
    private Couche2 couche2;
    private int taillePacket;

    public Packet(byte[] donnee, int taille){
        this.donnee = donnee;
        this.couche2 = new Couche2( data( donnee, 0, taille ) );
        this.taillePacket = taille;
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
