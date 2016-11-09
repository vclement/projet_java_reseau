import java.io.*;
import java.nio.*;
import java.util.*;

public class DHCP extends UDP{

    private byte[] data;
    private byte flags;

    public DHCP(byte[] donnee, byte[] portSource, byte[] portDest){
       super(donnee, portSource, portDest);
       this.data = donnee;
       this.flags = data(donnee, 8, 1)[0];
    }

    public void Informations(){
        String flag = String.format("%02x", this.flags);
        System.out.println("--> DHCP:");
        System.out.print("\tTransaction ID:");
        System.out.println(" 0x"+String.format("%02x%02x%02x%02x", this.data[12], this.data[13], this.data[14], this.data[15]));
        System.out.print("\tMessage Type: ");
        
        if(flag.equals("01")){
            System.out.println("Request ("+flag+")");
        }
        else if(flag.equals("02")){
            System.out.println("Reply ("+flag+")");
        }
    }
}
