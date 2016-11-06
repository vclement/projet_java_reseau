import java.io.*;
import java.nio.*;
import java.util.*;

public class TCP extends Couche4{
    
    private byte[] payload;
    private byte[] portSource;
    private byte[] portDest;
    private int length;
    private byte[] flags;

    public TCP(byte[] donnee, byte[] portSource, byte[] portDest){
        super();
        this.portSource = portSource;
        this.portDest = portDest;
        this.payload = donnee;
        this.length = donnee.length -32;
        this.flags = data(donnee, 13, 1);
    }

    public void Informations(){
        System.out.println("\n-->TCP:");
        
        System.out.println("\tPort Source: " + HexaToInt(portSource));

        System.out.println("\tPort Destination: "+ HexaToInt(portDest));
        System.out.println("\tTaille: " + length);
        System.out.println("\tFlags: 0x0"+ String.format("%02x", flags[0]));

    }
}
