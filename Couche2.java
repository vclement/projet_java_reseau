import java.io.*;
import java.nio.*;


public class Couche2 {
   
    private byte[] addresseSource;
    private byte[] addresseDest;
    private byte[] protoSup;
    private byte[] coucheSup;

    public Couche2(byte[] paquet){ 
     
        this.addresseDest = new byte[6];
        this.addresseDest = this.data(paquet,0,6);
        //this.addresseSource = this.data(paquet,6,6);
        //this.protoSup = this.data(paquet,12,2);

    }
    
    public void Informations(){
        System.out.print("Adresse de destination = ");
        System.out.println(AdresseHexa(addresseDest));
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
                data += String.format("%02X", donnee[i]);
        }
        return data;
    }

    public byte[] data(byte[] donnee, int offset, int length){
        byte[] tab = null;
        tab = new byte[length];
        for(int i=0; i < length; i++){
            System.out.println(donnee.length);
            tab[i] = donnee[offset+i];
        }
        return tab;
    }
}



