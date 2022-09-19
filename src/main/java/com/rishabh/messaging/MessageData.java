package com.rishabh.messaging;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class MessageData {
	
	public String save(ComposeMessage cM) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ComposeMessage composeMessage = new ComposeMessage();
		composeMessage.setName(cM.getName());
		composeMessage.setOtp(cM.getOtp());
		LocalDateTime localDateTime = LocalDateTime.parse(cM.getLocalDateTime());
		Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
		Date date = Date.from(instant);
		composeMessage.setLocalDateTime(cM.getLocalDateTime());
		ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("MessageData").document(cM.getOtp())
				.set(composeMessage);
		return collectionsApiFuture.get().getUpdateTime().toString();
	}
	public ArrayList<ComposeMessage> getAllMessage() throws InterruptedException, ExecutionException {

		ArrayList<ComposeMessage> list = new ArrayList<>();
		Firestore dbFirestore = FirestoreClient.getFirestore();

		ApiFuture<QuerySnapshot> future = dbFirestore.collection("MessageData").get();

		List<QueryDocumentSnapshot> documents = future.get().getDocuments();
		
			
		
		for (QueryDocumentSnapshot document : documents) {
			list.add(document.toObject(ComposeMessage.class));
		}
		Collections.sort(list, new Comparator<ComposeMessage>(){
			  public int compare(ComposeMessage a, ComposeMessage b)
			    {
			    	return b.getLocalDateTime().compareTo(a.getLocalDateTime());
			    }
		});
		return list;
	}
	
	public ArrayList<Contacts> getAllContacts() throws FileNotFoundException, IOException, ParseException{ 
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse("{\r\n"
				+ "  \"users\": [\r\n"
				+ "    {\r\n"
				+ "      \"userId\": 1,\r\n"
				+ "      \"firstName\": \"Rishabh Parsediya\",\r\n"
				+ "      \"lastName\": \"Lee\",\r\n"
				+ "      \"phoneNumber\": \"+917987567233\",\r\n"
				+ "      \"emailAddress\": \"krish.lee@learningcontainer.com\"\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"userId\": 2,\r\n"
				+ "      \"firstName\": \"Racks\",\r\n"
				+ "      \"lastName\": \"Jacson\",\r\n"
				+ "      \"phoneNumber\": \"+918349419718\",\r\n"
				+ "      \"emailAddress\": \"racks.jacson@learningcontainer.com\"\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"userId\": 3,\r\n"
				+ "      \"firstName\": \"Denial\",\r\n"
				+ "      \"lastName\": \"Roast\",\r\n"
				+ "      \"phoneNumber\": \"+919131451204\",\r\n"
				+ "      \"emailAddress\": \"denial.roast@learningcontainer.com\"\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"userId\": 4,\r\n"
				+ "      \"firstName\": \"Devid\",\r\n"
				+ "      \"lastName\": \"Neo\",\r\n"
				+ "      \"phoneNumber\": \"+919752154909\",\r\n"
				+ "      \"emailAddress\": \"devid.neo@learningcontainer.com\"\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"userId\": 5,\r\n"
				+ "      \"firstName\": \"Jone\",\r\n"
				+ "      \"lastName\": \"Mac\",\r\n"
				+ "      \"phoneNumber\": \"+919810153260\",\r\n"
				+ "      \"emailAddress\": \"jone.mac@learningcontainer.com\"\r\n"
				+ "    }\r\n"
				+ "  ]\r\n"
				+ "}");
		JSONArray jsonArray = (JSONArray) jsonObject.get("users");
		Iterator<JSONObject> iterator = jsonArray.iterator();
		List<Contacts> list=new ArrayList<>();
		while (iterator.hasNext()) {
			JSONObject eachObject = iterator.next();
			String firstName = (String) eachObject.get("firstName");
			String lastName = (String) eachObject.get("lastName");
			String phoneNumber = (String) eachObject.get("phoneNumber");
			list.add(new Contacts(firstName, lastName, phoneNumber));
		}
		return (ArrayList<Contacts>) list;
	}

}
