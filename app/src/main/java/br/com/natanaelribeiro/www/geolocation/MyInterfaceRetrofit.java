package br.com.natanaelribeiro.www.geolocation;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by 631610277 on 18/06/16.
 */
public interface MyInterfaceRetrofit {

    @GET("v2/576539791100005a0ea92a52")
    Call<Posicoes> searchPositions();


}


