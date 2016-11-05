import java.io.*;
import java.nio.file.*;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


class WireShark {
    public static void main( String[] args) throws IOException {
        byte[] donnee = Files.readAllBytes(Paths.get(args[0]));
        StringBuilder magicNumber = new StringBuilder();
        int offset=0, next_offset = 0, i=0, verif_i=0;

        System.out.print("Magic Number: ");
        System.out.println(MagicNumber(data(donnee,offset,offset+24)));
        offset=24;

        //System.out.println( data(donnee, 0, 4));
        while(offset < donnee.length){
            
            byte[] packetHeader = PcapHeader(donnee, offset);
            //System.out.println(affiche_hexa(packetHeader));
            i = packetHeader[8] + packetHeader[9] + packetHeader[10] + packetHeader[11];
            verif_i = packetHeader[12] + packetHeader[13] + packetHeader[14] + packetHeader[15];  
            if(i == verif_i)
                System.out.println("Taille du Paquet = "+ i);
            else{
                System.out.println("error!!");
                System.out.println(i);
                System.out.println(verif_i);
                System.exit(1);
            } 
                
            //Next offset
            next_offset = i;
            offset+=16;
            
            // System.out.println( String.format("%02X",donnee[next_offset]));

            System.out.print("addresse destination: ");
            affiche_adresse(affiche_hexa(data(donnee,offset,6)));

            System.out.print("Addresse source: ");
            affiche_adresse(affiche_hexa(data(donnee,offset+6,6)));

            System.out.print("Protocole de couche superieur: ");
            if(donnee[offset+12]==(byte)0x08 && donnee[offset+13]==(byte)0x00)
                System.out.println("IPV4\n");
            else if(donnee[offset+12]==(byte)0x08 && donnee[offset+13]==(byte)0x06)
                System.out.println("ARP\n");

            offset += next_offset;
        }
    }

    public static byte[] data(byte[] donnee, int offset, int length){
        byte[] tab = null;
        tab = new byte[length];
        for(int i = 0; i<length ; i++)    
            tab[i] = donnee[offset +i];

        return tab;
    }

    public static String MagicNumber(byte[] donnee){
        String magicNumber ="";
        for(int i =0; i < donnee.length; i++){
            magicNumber += String.format("%02X ", donnee[i]);
        }

        return magicNumber;
    }

    public static String affiche_hexa(byte[] donnee){
        String data="";
        for(int i=0; i< donnee.length ; i++){
            if(i == donnee.length-1)
                data += String.format("%02X", donnee[i]);
            else
                data += String.format("%02X:",donnee[i]);
        }

        return data;
    }

    public static void affiche_adresse(String adresse){
        String[] adr = adresse.split("\\%s");
        for(int i = 0 ; i < adr.length; i++){
            System.out.print(adr[i]);
        }
        System.out.println("");
    }


    public static byte[] PcapHeader(byte[] donnee, int offset){
        byte[] header = null;
        header = data(donnee, offset, 16);
        return header;
    }
}
