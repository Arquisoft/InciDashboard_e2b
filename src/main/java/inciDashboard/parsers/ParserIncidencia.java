package inciDashboard.parsers;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import inciDashboard.entities.Incidencia;

public class ParserIncidencia extends JsonDeserializer<Incidencia> {
	@Override
	public Incidencia deserialize(JsonParser parser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		ObjectCodec objectCodec = parser.getCodec();
		JsonNode jsonNode = objectCodec.readTree(parser);

		String name = jsonNode.get("nombre").asText();
		return null;
	}
	
	public static void main(String[] args) {
		ParserIncidencia p = new ParserIncidencia();
		p.deserialize(Incidencia.class, new Deserializer());
	}

}
