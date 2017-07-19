package br.com.levimendesestudos.avenuecode.api;

import java.util.List;
import java.util.Map;

import br.com.levimendesestudos.avenuecode.models.Address;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by 809778 on 09/08/2016.
 */
public interface GoogleAPI {

    String BASE_URL = "http://maps.googleapis.com/";

    /**
     * Content type json
     */
    String CT_APP_JSON = "Content-Type: application/json";

    /**
     * @return List of Address
     */
    @Headers(CT_APP_JSON)
    @GET("/maps/api/geocode/json")
    Observable<List<Address>> search(@QueryMap Map<String, String> params);
}