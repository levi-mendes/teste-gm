package br.com.levimendesestudos.avenuecode.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 809778 on 09/08/2016.
 */
public class Address implements Parcelable  {

    public String formattedAddress;
    public double lati;
    public double longi;

    private Address(Parcel in) {
        formattedAddress = in.readString();
        lati             = in.readDouble();
        longi            = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(formattedAddress);
        out.writeDouble(lati);
        out.writeDouble(longi);
    }

    public static final Parcelable.Creator<Address> CREATOR = new Parcelable.Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel in) {
            return new Address(in);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };

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
