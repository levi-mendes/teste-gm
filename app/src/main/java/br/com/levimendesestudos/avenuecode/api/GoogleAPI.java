package br.com.levimendesestudos.avenuecode.api;

import java.util.List;
import java.util.Map;

import br.com.levimendesestudos.avenuecode.models.Address;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;
import retrofit.http.QueryMap;
import rx.Observable;

/**
 * Created by 809778 on 09/08/2016.
 */
public interface GoogleAPI {

    //http://maps.googleapis.com/maps/api/geocode/json?address=Springfield&sensor=false
    String BASE_URL = "http://maps.googleapis.com/";

    /**
     * Content type json
     */
    String CT_APP_JSON = "Content-Type: application/json";

    /**
     * @return retorna usuario
     */
    @Headers(CT_APP_JSON)
    @GET("/maps/api/geocode/json")
    //@FormUrlEncoded
    Observable<String> search(@QueryMap Map<String, String> params);


    //void getPositionByZip(@FieldMap Map<String, String> params, Callback<String> cb);


    //@GET(Constantes.POOL + "DmConsultarSolicitacaoPorVistoriante/{cpf}/{tipo_solicitacao}/{opm}")
    //Observable<List<Solicitacao>> dmConsultarSolicitacaoPorVistoriante(@Path("cpf") String cpf, @Path("tipo_solicitacao") int tipoSolicitacao, @Path("opm") String opm);
}