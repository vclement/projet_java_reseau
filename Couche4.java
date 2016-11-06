import java.io.*;
import java.nio.*;
import java.util.*;

public class Couche4{
    
    protected byte[] portSource;
    protected byte[] portDest;
    private int protocole;
    private byte[] payload;

    public Couche4(){
    }

    public Couche4(byte[] donnee, int taille, int proto){
        this.portSource = data(donnee, 0, 2);
        this.portDest = data(donnee, 2, 2);
        this.protocole = proto;
        this.payload = donnee;
    }
    
    public void Informations(){
        if(protocole == 1){ 
            //Dans ce cas, on a un paquet TCP
            TCP tcp = new TCP(payload, portSource, portDest);
            tcp.Informations();
        }
        else if (protocole == 0){
            //Dans ce cas, on a un paquet UDP
            UDP udp = new UDP(payload, portSource, portDest);
            udp.Informations();
            
        }
    }

    public byte[]data(byte[] donnee, int offset, int length){
        byte tab[] = new byte[length];
        for(int i=0; i < length; i++){
            tab[i] = donnee[offset+i];
        }
        return tab;
    }

    public int HexaToInt(byte[] donnee){
        StringBuilder temp = new StringBuilder();
        for(int i=0; i<donnee.length; i++){
            temp.append(String.format("%02X", donnee[i]));
        }
        return Integer.parseInt(temp.toString(), 16);
    }
}
