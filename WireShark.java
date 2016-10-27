import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


class WireShark {
    public static void main( String[] args) throws IOException {
        FileInputStream ficreader = null;
        List list = new LinkedList();
        int octet=0, cpt=0;
        try {
            ficreader = new FileInputStream ( args[0]);
            while((octet = ficreader.read()) !=-1){
                list.add(octet);
            }
        } catch( FileNotFoundException exc) {
            System.out.println("Le fichier existe");
            System.exit(1);
        }

        for(int i =1; i<=list.size();i++){
            System.out.print(String.format("%x ", list.get(i-1)));
            if(i%4 ==0)
                System.out.println("");
        }
        ficreader.close();
    }
}
