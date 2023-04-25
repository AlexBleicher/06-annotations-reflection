package ohm.softa.a06.tests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ohm.softa.a06.ICNDBApi;
import ohm.softa.a06.JokeAdapter;
import ohm.softa.a06.model.Joke;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Peter Kurfer
 * Created on 11/10/17.
 */
class ICNDBTests {

	private static final Logger logger = LogManager.getLogger(ICNDBTests.class);
	private static final int REQUEST_COUNT = 10;

	private Gson gson = new GsonBuilder()
		.registerTypeAdapter(Joke.class, new JokeAdapter())
		.create();

	private Retrofit retrofit = new Retrofit.Builder()
		.addConverterFactory(GsonConverterFactory.create(gson))
		.baseUrl("https://api.chucknorris.io")
		.build();

	@Test
	void testCollision() throws IOException {
		Set<String> jokeNumbers = new HashSet<>();
		int requests = 0;
		boolean collision = false;

		ICNDBApi api = retrofit.create(ICNDBApi.class);
		while (requests++ < REQUEST_COUNT) {
			Call<Joke> randomJoke = api.getRandomJoke();
			Response<Joke> jokeResponse = randomJoke.execute();

			Joke j = jokeResponse.body();;
			System.out.println(j.getContent());
			if(jokeNumbers.contains(j.getNumber())) {
				logger.info(String.format("Collision at joke %s", j.getNumber()));
				collision = true;
				break;
			}

			jokeNumbers.add(j.getNumber());
			logger.info(j.toString());
		}

		assertTrue(collision, String.format("Completed %d requests without collision; consider increasing REQUEST_COUNT", requests));
	}

	@Test
	void testSearchJoke() throws IOException {
		ICNDBApi api = retrofit.create(ICNDBApi.class);
		Call<Joke> randomJokeCall = api.getRandomJoke();
		Response<Joke> randomJoke = randomJokeCall.execute();
		String joke = randomJoke.body().getContent();
		String id = randomJoke.body().getNumber();

		System.out.println(id);
		Call<Joke> getjokeBySearch = api.getJokeyBySearch(id);
		Response<Joke> jokeResponse = getjokeBySearch.execute();
		assert(joke.equals(jokeResponse.body().getContent()));
	}
}
