import java.io.*;
import java.nio.*;


public class Couche2{
    
    private byte[] AddresseSource;
    private byte[] AddresseDest;
    private byte[] ProtoSup;

    public Couche2(){
        this.AddresseDest = null;
        this.AddresseSource = null;
        this.ProtoSup = null;
    }

    public void setAddresseDest(byte[] donnee){
        this.AddresseDest = donnee;
    }

    public void setAddresseSource(byte[] donnee){
        this.AddresseSource = donnee;
    }

    public void setProtoSup(byte[] donnee){
        this.ProtoSup = donnee;
    }
}



