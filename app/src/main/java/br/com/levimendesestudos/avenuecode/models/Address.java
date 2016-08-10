package br.com.levimendesestudos.avenuecode.models;

import java.io.Serializable;

/**
 * Created by 809778 on 09/08/2016.
 */
public class Address implements Serializable {
    public String formattedAddress;
    public double lati;
    public double longi;

    public Address(String formattedAddress, double lati, double longi) {
        this.formattedAddress = formattedAddress;
        this.lati = lati;
        this.longi = longi;
    }

    @Override
    public String toString() {
        return "Address: " + formattedAddress + "  Coordinates>> lat: " + lati+ " long: " + longi;
    }
}
