import java.io.*;
import java.nio.*;
import java.util.*;


public class ARP extends Couche2{
    
    private byte[] hardware_type;
    private byte[] proto_type;
    private byte[] code; //Request ou Reply
    private byte[] ipsource;
    private byte[] ipdest;

    public ARP(byte[] donnee){
        this.code = data( donnee, 6, 2);
        this.macSource = data( donnee, 8, 6);
        this.ipsource = data( donnee, 14, 4);
        this.macDest = data( donnee, 18, 6);
        this.ipdest = data( donnee, 24, 4);
    }

    public void Informations(){
        System.out.print("\t\tType de requête: ");
        if(code[1] == (byte)0x01 )
            System.out.println("Request");
        else if(code[1] == (byte)0x02 )
            System.out.println("Reply");
        
        System.out.print("\t\tSender MAC address: ");
        System.out.println(AdresseHexa(macSource));

        System.out.print("\t\tSender IP adresse: ");
        System.out.println(AdresseIP(ipsource));

        System.out.print("\t\tTarget MAC address: ");
        System.out.println(AdresseHexa(macDest));

        System.out.print("\t\tTarget IP address: ");
        System.out.println(AdresseIP(ipdest));
            
        if(code[1] == (byte)0x01 ){
            System.out.println("\t\tInfo: Who has "+AdresseIP(ipdest)+"?  Tell "+ AdresseIP(ipsource));
        }
        else if(code[1] == (byte)0x02 ){
            System.out.println("\t\tInfo: "+AdresseIP(ipsource)+" is at "+AdresseHexa(macSource));
        }
    }

    public String AdresseIP(byte[] donnee){
        String resultat="";
        String temp = "";
        for(int i=0; i<donnee.length; i++){
            temp = String.format("%02X",donnee[i]);
            if(i == donnee.length-1){
                resultat +=""+ Integer.parseInt(temp.toString(), 16);
            }
            else
                resultat += Integer.parseInt(temp.toString(), 16) +".";
        }
        return resultat;
    }
    public int HexaToInt(byte donnee){
        String intfinale = "";
        StringBuilder temp = new StringBuilder();
        temp.append(String.format("%02X", donnee));
        intfinale = temp.toString();

        return Integer.parseInt(intfinale, 16);
    }

}
