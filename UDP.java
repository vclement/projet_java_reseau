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
        this.length = data(donnee, 4,2);
        this.payload = donnee;
    }

    public void Informations(){
        System.out.println("\n-->UDP:");
        //En fonction du code, on affiche la requete ICMP
        System.out.println("\tPort Source:  "+ HexaToInt(portSource));

        System.out.println("\tPort Destination: "+ HexaToInt(portDest));

        System.out.println("\tTaille: " + HexaToInt(length));
        
        if(HexaToInt(portSource) == 53 || HexaToInt(portDest) == 53){
            //System.out.println("Next Protocol is DNS");
            DNS dns = new DNS(payload, portSource, portDest) ;
            dns.Informations();
        }
        else if(HexaToInt(portSource) == 68 || HexaToInt(portSource) == 67){
            DHCP dhcp = new DHCP(payload, portSource, portDest);
            dhcp.Informations();
        }
    }
}
