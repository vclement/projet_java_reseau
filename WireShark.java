import java.io.*;
import java.nio.file.*;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


class WireShark {
    public static void main( String[] args) throws IOException {
        FileInputStream ficreader = null;
        byte[] donnee = Files.readAllBytes(Paths.get(args[0]));
        List list = new LinkedList();
        StringBuilder magicNumber = new StringBuilder();
        int octet=0, cpt=0;
        try {
            ficreader = new FileInputStream ( args[0]);
            while((octet = ficreader.read()) !=-1){
                list.add( String.format("%x",octet));
            }
        } catch( FileNotFoundException exc) {
            System.out.println("Le fichier existe");
            System.exit(1);
        }
        
        System.out.print("Magic Number: ");
        for(int i = 1; i<=24 ;i++){
            System.out.print( list.get(i-1) + " ");
            magicNumber.append((String)list.get(i-1));
        }
        System.out.println(donnee);
        ficreader.close();
        //System.out.println( data(donnee, 0, 4));
        System.out.println(affiche_hexa(data(donnee,24,100)) );

        byte[] packetLength = data(donnee, 32, 4);
        System.out.println(affiche_hexa(packetLength));


        System.out.println("addresse destination: ");
        System.out.print( affiche_hexa(data(donnee,40,6)));
    }
    
    public static byte[] data(byte[] donnee, int offset, int length){
        byte[] tab = null;
        tab = new byte[length];
        for(int i = 0; i<length ; i++)    
            tab[i] = donnee[offset +i];

        return tab;
    }

    public static String affiche_hexa(byte[] donnee){
        String data="";
        for(int i=0; i< donnee.length ; i++){
            data += String.format("%02X ",donnee[i]);
        }

        return data;
    }
    public void check(List list){
        
    }
}
