import java.io.*;
import java.nio.*;
import java.util.*;

public class PcapHeader{
  
    private byte[] ts_sec;
    private byte[] ts_usec;
    private byte[] incl_len;
    private byte[] orig_len;
    private byte[] dataheader;

    public PcapHeader(byte[] data){
        //Dans le constructeur on rempli les attributs avec les valeurs du header
        this.ts_sec = extractInfo(data, 0, 4);
        this.ts_usec = extractInfo(data, 4, 4);
        this.incl_len = extractInfo(data, 8, 4);
        this.orig_len = extractInfo(data, 12, 4);
        this.dataheader = data;
    }

    public byte[] getIncl_len(){
        //Cette fonction permet de connaitre la taille du paquet
        return this.incl_len;
    }

    public byte[] getOrig_len(){
        //Cette fonction permet de connaitre la taille originale du paquet
        return this.orig_len;
    }

    public void Informations(){
        //Cette fonction affiche les informations comprises dans le Pcap header
        System.out.println("Date du paquet: " + calcDate(this.ts_sec));
        System.out.println("Taille du Paquet: "+ valeurByteEnInt(this.incl_len) +"\nTaille original du paquet: " + valeurByteEnInt(this.orig_len));
        for(int i=0; i < dataheader.length; i++){
            System.out.print( String.format("%02X ", dataheader[i]) );
        }
        System.out.println("");
    }

    public Date calcDate(byte[] ts_sec){
        String date = ""+valeurByteEnInt(ts_sec);
        Date expiry = new Date(Long.parseLong(date)*1000);
        return expiry;
    }

    public byte[] extractInfo(byte[] donnee, int offset, int length){
        byte tab[] = null;
        tab = new byte[length];
        for(int i =0; i<length; i++){
            tab[i] = donnee[offset+i];
        }
        return tab;
    }

    public int valeurByteEnInt(byte[] data){
        String finale = "";
        StringBuilder temp = new StringBuilder();

        for(int i=data.length-1; i>=0; i-- ){
            temp.append( String.format( "%02X", data[i] ) );
        }
        finale = temp.toString();
        return Integer.parseInt(finale, 16);
    }
} 
