import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyInterfaceRetrofit {

    @GET("listaviagens")
    Call<Posicoes> searchPositions();

              /*@Query("withdraw_coordinates") String withdraw_coordinates,
               @Query("withdraw_radius") int withdraw_radius*/

       /*@POST("api/user/store")
       Call<User> createUser(
               @Body RequestBody user);

       @GET("api/user/{id}")
       Call<User> getUser(
               @Path("id") String userId);
   */
}
