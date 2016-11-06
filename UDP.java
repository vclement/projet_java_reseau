import java.io.*;
import java.nio.*;
import java.util.*;

public class ICMP{
    
    private byte type;
    private byte code;
    private byte[] checksum;

    public ICMP(byte[] donnee){
        this.type = data(donnee, 0, 1)[0] ;
        this.code = data(donnee, 1, 1)[0] ;
        this.checksum = data(donnee, 2, 2);
    }

    public void Informations(){
        System.out.println("\n-->ICMP:");
        
        //En fonction du code, on affiche la requete ICMP
        System.out.print("\tType: "+ Integer.parseInt(String.format("%02X", type), 16) +"  ");

        if(code == (byte)0x08)
            System.out.println("(Echo (ping) Request)");
        else if( code == (byte)0x00 )
            System.out.println("(Echo (ping) Reply)");
        else if( code == (byte)0x11 )
            System.out.println("(Time-To-Live Exceeded)");
        else if( code == (byte)0x03 )
            System.out.println("(Destination unreacheable)");
        else
            System.out.println("(Code non implémenté)");
        
        System.out.println("\tCode: "+ Integer.parseInt(String.format("%02X", code), 16));
        System.out.println("\tChecksum: " + String.format("0x%02x%02x", checksum[0], checksum[1]));
        System.out.println("\tFin du paquet.");
    }

    public byte[] data(byte[] donnee, int offset, int length){
        byte tab[] = new byte[length];
        for(int i=0; i < length; i++)
            tab[i] = donnee[offset+i];
        return tab;
    }
}
