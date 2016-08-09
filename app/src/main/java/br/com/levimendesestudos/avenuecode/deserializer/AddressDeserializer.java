package br.com.levimendesestudos.avenuecode.deserializer;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import br.com.levimendesestudos.avenuecode.models.Address;

/**
 *
 * this class is used to get only what we want
 *
 *
 */
public class AddressDeserializer implements JsonDeserializer<Object> {

    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        List<Address> list = new ArrayList<>();

        JsonObject root = json.getAsJsonObject();
        JsonArray array = root.getAsJsonArray("results");

        if (array != null && array.size() > 0) {

            for (int cont = 0; cont < array.size(); cont++) {

                JsonElement jsonElement = array.get(cont);
                JsonObject jsonObject = jsonElement.getAsJsonObject();

                String formattedAddress = jsonObject.get("formatted_address").toString();

                JsonObject geometry    = jsonObject.getAsJsonObject("geometry");
                JsonObject location    = geometry.getAsJsonObject("location");

                double latitude  = location.get("lat").getAsDouble();
                double longitude = location.get("lng").getAsDouble();

                LatLng latLng = new LatLng(latitude, longitude);

                Address address = new Address();

                address.formattedAddress = formattedAddress;
                address.latLng = latLng;

                list.add(address);
            }
        }

        return list;
    }
}
