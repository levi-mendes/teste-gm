package br.com.levimendesestudos.avenuecode.dagger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import br.com.levimendesestudos.avenuecode.api.GoogleAPI;
import br.com.levimendesestudos.avenuecode.deserializer.AddressDeserializer;
import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by 809778 on 23/05/2016.
 */
@Module
public class GoogleApiModule {

    @Provides
    GoogleAPI providesGoogleApiModule() {
        Gson gson = new GsonBuilder().registerTypeAdapter(List.class, new AddressDeserializer()).create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GoogleAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit.create(GoogleAPI.class);
    }
}