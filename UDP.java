import java.io.*;
import java.nio.*;
import java.util.*;

public class UDP extends Couche4{
    
    private byte[] payload;
    private byte[] portSource;
    private byte[] portDest;
    private byte[] length;

    public UDP(byte[] donnee, byte[] portSource, byte[] portDest){
        super();
        this.portSource = portSource;
        this.portDest = portDest;
        this.payload = donnee;
        this.length = data(donnee, 4,2);
    }

    public void Informations(){
        System.out.println("\n-->UDP:");
        //En fonction du code, on affiche la requete ICMP
        System.out.println("\tPort Source:  "+ HexaToInt(portSource));

        System.out.println("\tPort Destination: "+ HexaToInt(portDest));

        System.out.println("\tTaille: " + HexaToInt(length));

    }
}
