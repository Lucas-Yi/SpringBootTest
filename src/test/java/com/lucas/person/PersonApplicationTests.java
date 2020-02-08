package com.lucas.person;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.lucas.person.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.RequestBuilder;

import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
class PersonApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void testPersonController() throws Exception{

		RequestBuilder request = null;

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

		String requestJson = null;

		//1.Get user list, should be empty
		request = get("/api/v1/person");
		mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("[]")));

		// 2、post a User
		UUID randomUUID = UUID.randomUUID();
		Person newPerson = new Person(randomUUID, "lucas");
		requestJson = ow.writeValueAsString(newPerson);

		request = post("/api/v1/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestJson);
				//.param("id", "1").param("name", "lucas");
		mvc.perform(request)
				.andExpect(content().string(equalTo("success")));

		// 3、get user list again, should include the user we add previously
		request = get("/api/v1/person");
		mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("[{\"id\":\"" +
						randomUUID + "\",\"name\":\"lucas\"}]")));


		//4. get certainer user
		request = get("/api/v1/person/{id}", randomUUID);
		mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("{\"id\":\"" +
						randomUUID + "\",\"name\":\"lucas\"}")));

		// 5、update user whose id = randomUUID and set its name to emily
		Person updatePerson = new Person(randomUUID, "emily");
		requestJson = ow.writeValueAsString(updatePerson);
		request = put("/api/v1/person/{id}", randomUUID)
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestJson);
		mvc.perform(request)
				.andExpect(content().string(equalTo("success")));

		// 6、get user whose id = randomUUID and check
		request = get("/api/v1/person/{id}", randomUUID);
		mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("{\"id\":\"" +
						randomUUID + "\",\"name\":\"emily\"}")));

		// 6、delete user whose id = randomUUID
		request = delete("/api/v1/person/{id}", randomUUID);
		mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("success")));

		// 7、get user list again, should be empty
		request = get("/api/v1/person");
		mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("[]")));
	}



}
