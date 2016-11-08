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
        System.out.print("\tFlags: 0x0"+ String.format("%02x", flags[0]));
        
        String flag = "0x0"+String.format("%02x", flags[0]);
        //Maintenant on indique l'etat de la connexion (SYN, SYN-ACK, ACK, FIN, FIN-ACK)
        if(flag.equals("0x002"))
            System.out.println(" (SYN)");
        else if(flag.equals("0x012"))
            System.out.println(" (SYN-ACK)");
        else if(flag.equals("0x010"))
            System.out.println(" (ACK)");
        else if(flag.equals("0x011"))
            System.out.println(" (FIN-ACK)");
        else if(flag.equals("0x001"))
            System.out.println(" (FIN)");
        else if(flag.equals("0x018")) 
            System.out.println(" (PSH,ACK)");
        else
            System.out.println("");

        if(length>0){
            System.out.println("Charge utile non nul, exitance d'un protocol supérieur");
        }
    }
}
