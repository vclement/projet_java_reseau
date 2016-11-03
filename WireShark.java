import java.io.*;
import java.nio.file.*;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


class WireShark {
    public static void main( String[] args) throws IOException {
        FileInputStream ficreader = null;
        List list = new LinkedList();
        byte[] donnee = Files.readAllBytes(Paths.get(args[0]));
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
    }

    public void check(List list){
        
    }
}
