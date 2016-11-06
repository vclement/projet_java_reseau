import java.io.*;
import java.nio.*;


public class Couche2 {
   
    protected byte[] macSource;
    protected byte[] macDest;
    private byte[] protoSup;
    private byte[] payload;

    public Couche2(){
        //Dans le cas de l'ARP
    }

    public Couche2(byte[] paquet, int taille){ 
     
        this.macDest = this.data(paquet,0,6);
        this.macSource = this.data(paquet,6,6);
        this.protoSup = this.data(paquet,12,2);
        this.payload = this.data(paquet,14, taille -14);
    }
    
    public void Informations(){
        System.out.println("--> Couche 2:");
        System.out.print("\tAdresse de destination = ");
        System.out.println(AdresseHexa(macDest));

        System.out.print("\tAdresse Source = ");
        System.out.println(AdresseHexa(macSource));

        System.out.print("\tProtocole suivant: ");

        if( protocoleSup().equals( "IPv4" ) ){
            System.out.println("IP");
            System.out.println("Analyse de la couche 3");
            //On cree une couche supplémentaire et on lui envoie la payload du niveau 2 :-)
            Couche3 couche3 = new Couche3(payload, payload.length);
            couche3.Informations();
        }
        else if( protocoleSup().equals( "ARP" ) ){
            System.out.println("ARP");
            System.out.println("\tPas de niveau 3! Analyse du protocole:");
            //On analyse l'ARP.
            ARP arp = new ARP(payload);
            arp.Informations();
        }
        
        
    }

    public String toString(){
        //Fonction qui gère l'affichage des information sur la couche
        String string="";
        return string;
    }

    public String AdresseHexa(byte[] donnee){
        String data ="";
        for(int i=0; i<donnee.length; i++){
            if( i == donnee.length-1)
                data += String.format("%02X", donnee[i]);
            else
                data += String.format("%02X:", donnee[i]);
        }
        return data;
    }

    public String protocoleSup(){
        String result="";
        if(this.protoSup[0] == 0x08 && this.protoSup[1] == 0x00)
            result = "IPv4";
        else if(this.protoSup[0] == 0x08 && this.protoSup[1] == 0x06 )
            result = "ARP";
        else
            result = "None";
        return result;
    }

    public byte[] data(byte[] donnee, int offset, int length){
        byte[] tab = null;
        tab = new byte[length];
        for(int i=0; i < length; i++){
            //System.out.println(donnee.length);
            tab[i] = donnee[offset+i];
        }
        return tab;
    }
}



