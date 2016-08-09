package br.com.levimendesestudos.avenuecode.models;

import java.io.Serializable;

/**
 * Created by 809778 on 09/08/2016.
 */
public class Address implements Serializable {
    public String formattedAddress;
    public double lati;
    public double longi;

    @Override
    public String toString() {
        return "Address: " + formattedAddress + "\nCoordinates>> lat: " + lati+ " long: " + longi;
    }
}
