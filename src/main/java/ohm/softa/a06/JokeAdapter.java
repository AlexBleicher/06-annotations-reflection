package ohm.softa.a06;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import ohm.softa.a06.model.Joke;
import org.apache.commons.lang3.NotImplementedException;
import retrofit2.Retrofit;

import java.io.IOException;

public class JokeAdapter extends TypeAdapter<Joke> {

	private Gson gson;

	public JokeAdapter() {
		this.gson = new Gson();
	}

	@Override
	public void write(JsonWriter out, Joke value) throws IOException {
		throw new NotImplementedException();
	}

	@Override
	public Joke read(JsonReader in) throws IOException {
		return gson.fromJson(in, Joke.class);
	}
}
