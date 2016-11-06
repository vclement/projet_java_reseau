import java.io.*;
import java.nio.*;
import java.util.*;

public class Couche3{

    private int version;
    private byte[] length;
    private byte ttl;
    private byte protocole;
    private byte[] ipSource;
    private byte[] ipDest;
    private byte[] payload3;

    public Couche3(byte[] payload2, int taille){
        this.version = data(payload2, 0, 1)[0] == (byte)0x45 ? 1 : 0;
        this.length = data(payload2, 2, 2);
        this.ttl = data(payload2, 8,1)[0];
        this.protocole = data(payload2, 9, 1)[0];
        this.ipSource = data(payload2, 12, 4);
        this.ipDest = data(payload2, 14, 4);
        this.payload3 = data(payload2,20, taille-20);
    } 

    public void Informations(){
        System.out.println("\n--> Couche3:");
        if(version==1){
            System.out.println("\tVersion: IPv4") ;
            System.out.println("\tTotal length: "+ HexaToInt(length) );
            System.out.println("\tTime to live: "+ Integer.parseInt( (String.format("%02X",ttl) ), 16 ) );
            System.out.println("\tSource: "+AdresseIP(ipSource));
            System.out.println("\tDestination: "+AdresseIP(ipDest));
            if(protocole == (byte)0x06 ){
                //On cree une couche 4
                System.out.println("\tProtocole niveau 4 : TCP ");

            }
            else if(protocole == (byte)0x11){
                //On cree une couche 4
                System.out.println("\tProtocole niveau 4 : UDP");
            }
            else if(protocole == (byte)0x01){
                //On cree un pbjet spécifique a ICMP !
                System.out.println("\tProtocole suivant: ICMP");
                ICMP icmp = new ICMP(payload3);
                icmp.Informations();
            }
            else{
                //Pas besoin de 4eme couche
                System.out.println("\tPas de protocole de niveau 4 detecté!");
            }
        }
        else
            System.out.println("\tProtocole de niveau 3 non implémenté !");
    }

    public byte[] data(byte[] donnee, int offset, int length){
        byte tab[] = null;
        tab = new byte[length];

        for(int i=0; i < length; i++){
            tab[i] = donnee[offset+i];
        }
        return tab;
    }

    public int HexaToInt(byte[] donnee){
        String intFinale ="";
        StringBuilder temp = new StringBuilder();
        for(int i=0; i< donnee.length; i++){
            temp.append(String.format( "%02X", donnee[i]));
        }
        intFinale = temp.toString();
        return Integer.parseInt(intFinale, 16);
    }

    public String AdresseIP(byte[] donnee){
        String resultat = "";
        String temp = "";
        for(int i=0; i<donnee.length; i++){
            temp = String.format("%02X", donnee[i]);
            if(i == donnee.length-1)
                resultat += ""+ Integer.parseInt(temp.toString(), 16);
            else
                resultat += ""+ Integer.parseInt(temp.toString(), 16)+".";
        }
        return resultat;
        
    }
}
