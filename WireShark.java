import java.io.*;
import java.lang.Number;
import java.nio.file.*;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


class WireShark {
    public static void main( String[] args) throws IOException {
        byte[] donnee = Files.readAllBytes(Paths.get(args[0]));
        int offset=0, next_offset = 0, i=0, verif_i=0;

        //Ici on récupère quelques informations basique sur le fichier pcap.
        byte[] magicnumber = data(donnee,offset, offset+24);
        System.out.println("\nPremière information sur le paquet:");
        System.out.print("Magic Number: ");
        System.out.println(MagicNumber(magicnumber));
        if((HexaToString(donnee[0]) + HexaToString(donnee[1]) + HexaToString(donnee[2]) + HexaToString(donnee[3])).equals("d4c3b2a1"))
            System.out.println("Big-Endian: OK !");
        else{
            System.out.println("Fichier illisible, fin du programme");
            System.exit(1);
        }

        if(magicnumber[20] == (byte)0x01)
            System.out.println("Protocole : Ethernet");
        else{
            System.out.println("Protocole de niveau 2 inconnu.\n Fin du programme.");
            System.exit(1);
        }

        offset=24;
        System.out.println( "\nDébut de l'analyse de l'ensemble des paquets...\n" );
        
        while(offset < donnee.length){
            //On commence par recuperer le header generer par WireShark. Et on le stock dans un objet de type Header Pcap.
            PcapHeader pcapheader = new PcapHeader(data(donnee, offset, 16));
            pcapheader.Informations();

            i = taillePacketInt( pcapheader.getIncl_len() );
            verif_i = taillePacketInt( pcapheader.getOrig_len() );
            
            //Next offset
            next_offset = i;
            offset += 16;

            //La on cree le paquet reseau
            byte[] payloadPacket = data(donnee,offset, i);


            if(i == verif_i){
                Couche2 couche2 = new Couche2( payloadPacket, i );
                couche2.Informations();
            }
            else{
                System.out.println("Paquet incomplet, paquet non analysé !");
                System.out.println(i);
                System.out.println(verif_i);
                System.exit(1);
            } 

            offset += next_offset;
            System.out.println("---------------------------------------------------------------------------");
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

    public static String HexaToString(byte donnee){
        String data = "";
        return String.format("%02x",donnee);

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

    public static int taillePacketInt(byte[] donnee){
        //hexa = String.format("%02x",donnee[8]);
        String intFinale = "";
        StringBuilder dataTemp = new StringBuilder();
        for(int i=donnee.length-1 ; i >= 0; i--){
            dataTemp.append(String.format("%02X", donnee[i]));
        }
        intFinale = dataTemp.toString();

        return Integer.parseInt(intFinale, 16);

    }
}
