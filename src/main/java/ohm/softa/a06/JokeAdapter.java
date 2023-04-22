package ohm.softa.a06;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import jdk.jshell.spi.ExecutionControl;
import ohm.softa.a06.model.Joke;
import org.apache.commons.lang3.NotImplementedException;

import java.io.IOException;
import java.util.ArrayList;

public class JokeAdapter extends TypeAdapter<Joke> {

	@Override
	public void write(JsonWriter out, Joke value) throws IOException {
		throw new NotImplementedException();
	}

	@Override
	public Joke read(JsonReader in) throws IOException {
		in.beginObject();
		String type=in.nextString();
		if(type.equals("success")){
			in.beginObject();
			int id = in.nextInt();
			String joke = in.nextString();
			in.beginArray();
			ArrayList<String> categories = new ArrayList();
			while(in.hasNext()){
				categories.add(in.nextString());
			}
			in.endArray();
			in.endObject();
		}
		in.endObject();
	}
}
