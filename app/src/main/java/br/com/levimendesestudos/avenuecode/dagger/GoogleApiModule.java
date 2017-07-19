package br.com.levimendesestudos.avenuecode.dagger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import br.com.levimendesestudos.avenuecode.api.GoogleAPI;
import br.com.levimendesestudos.avenuecode.deserializer.AddressDeserializer;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 809778 on 23/05/2016.
 */
@Module
public class GoogleApiModule {

    @Provides
    public GoogleAPI providesGoogleApi(Retrofit retrofit) {
          return retrofit.create(GoogleAPI.class);
    }

    @Provides
    public String providesUrl() {
        return GoogleAPI.BASE_URL;
    }

    @Provides
    public Retrofit providesRetrofit(String url, GsonConverterFactory factory) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit;
    }

    @Provides
    public GsonBuilder providesGsonBuilder() {
        return new GsonBuilder();
    }

    @Provides
    public Gson providesGson(GsonBuilder gsonBuilder, AddressDeserializer deserializer) {
        return gsonBuilder.registerTypeAdapter(List.class, deserializer).create();
    }

    @Provides
    public AddressDeserializer providesDeserializer() {
        return new AddressDeserializer();
    }

    @Provides
    public GsonConverterFactory providesAdapterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }
}