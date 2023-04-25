package ohm.softa.a06;

import ohm.softa.a06.model.Joke;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author Peter Kurfer
 * Created on 11/10/17.
 */
public interface ICNDBApi {

	@GET("/jokes/random")
	Call<Joke> getRandomJoke();

	@GET("/jokes/{i}")
	Call<Joke> getJokeyBySearch(@Path("i") String query);
}
