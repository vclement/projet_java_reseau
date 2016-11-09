import java.io.*;
import java.nio.*;
import java.util.*;

public class DNS extends UDP{

    private byte[] data;
    private byte[] flags;

    public DNS(byte[] donnee, byte[] portSource, byte[] portDest){
       super(donnee, portSource, portDest);
       this.data = donnee;
       this.flags = data(donnee, 10, 2);
    }

    public void Informations(){
        String flag = String.format("%02x%02x", this.flags[0], this.flags[1]);
        System.out.println("--> DNS:");
        System.out.print("\tTransaction ID:");
        System.out.println(" 0x"+String.format("%02x%02x", this.data[8], this.data[9]));
        System.out.print("\tFlags:");
        System.out.println(" Ox"+ flag);
       
        System.out.print("\tInfo: ");
        if(flag.equals("0100")){
            System.out.println("Standard query");
        }
        else if(flag.equals("8583")){
            System.out.println("Standard query response");
        }
    }
}
