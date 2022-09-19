package com.rishabh.messaging;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@SpringBootApplication
public class MessagingApplication {

	public static void main(String[] args) throws IOException  {
		String serviceAccountKey="{\r\n"
				+ "  \"type\": \"service_account\",\r\n"
				+ "  \"project_id\": \"portfolio-b6b9e\",\r\n"
				+ "  \"private_key_id\": \"a388624ceb5729420e8e2f9c591f002de6b5a479\",\r\n"
				+ "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDVO5Ld/0ukcbIH\\nXBAG1BBNAM7G9hM+2jeDbCZxfh+XCZbzNsf0CNijPhExtJilQ7VwRoa4wVsErJJP\\nqne9h8J+E4Y4NroU7hFTnSXjnFNCkrrUjtkD+2gzCProx8m6sz48yp260qNL/wVs\\nWIvR/HC9OVj8V3t0CHFF94uY6UXu2Oq78yRWm35J1RlAULDaj/TY1BAgBvJ1LwTD\\n0aHhZE8sNTbPDIOu+JgjhRsBcJEcIfLJvI2KSWC9LeTSpKK3Y7Zzuu6+4ZJek1OY\\nBzqXO5r5d4k1GNLYwsd1UN8QBByxqkIulNk0tETy+/KF8BTlOr5VhBzZKR0lMWYa\\nZE9H7Ab9AgMBAAECgf8mMJIQfU81SvRxQ2VoOhLkhICEO3S283fkODp3MsX1oLVB\\n87Qo9AY/Nmtd7tQBkiusycGkCJbq/2POlBL3nv9c4gi0u+VLJyUM4XH0kf4kR9Z7\\nIbQJQoqNZjS9L3F+pRum4TyreSffmvOEGorwN1L5xCU4pj82FbNnFJVFjbU4uAAk\\nNoFgEM1a03WGtrySZ80+QU1b2n+/hvXHC0w7ByD6Ijz7MKgI8yVhk0x94E37Jox8\\nB2Pjpk3Nttu+etCVXs7YjQ7HtsYIqrPhFcMoeC6MYJNQXf8ESKIterAsuU+c7S7A\\n4CPKpM9s5rlXwezJpvxnNQEuUj8c93MSkCHtwekCgYEA/e+JLv74JMHVyLRt+h2K\\n5B4ugGsPVR8joegBHtzmIKW6SfIDgAbZKc/hxq2AAeDOd+YTQ9ualXbgPD2atX8Y\\nXxMduYyVxv0t6uXAZtiDga8F6T2huuscXVk/ZItAWT+UW+kZyhkn4dsGpRYki1He\\nz6QoVAX+yRABApBNKnwemkkCgYEA1vdUwlHv9/yIluKUeWaFWiiaJCX/8jHilM3V\\nHdHR4XJPSgXqX+w0wRFa21nxI25iqv14S4kNMSd+KuiTybD6tV4NIpjo8noEVS0u\\nTp5v2YpSyH7niwlme6JHRSzCKxdsvwU5xU+Q1/WOSq2YWZW4NLwBkZ2y+AsQxZbc\\nxM6mZxUCgYEAmcNmCjMSBw/oZ/P5dC5XX/J2rgQl8KE+bleR9iJEpxgzSXtmayu6\\na/sY97kvffOmkiubFsfvPvoQpemXuLcRBuEIasaaGzhLiaU9cAlC+FAHAEj1mb2s\\nG0pjfHplqKU7LLxlhV7eK4D48RYLYuFcTddVJCvNL+qk8ex3DXbDASECgYEAxNBw\\nTvW8Er2oxRSwQbJm15QjBIOyIaogvILz47huQ85n3+somihmaHoeSITBreHr/ofR\\ncr8JT/7LmjeUz+30rMdumUWMyiyC+QVDx4FyAKePgbgHAJpbtUhvnlsLb26kBwSe\\nzxM5FzDg1Wi4+xSEMewMN6k96bIf6fhlKt/VJL0CgYBrp0AIN3VWUbciOekWcxta\\n8cbTTWzATNHTZsip29qpV4yBnZAr75SXbepCjkyh0Vy7pq7gECIIfvp1Kd0CriT0\\nWr03mX7NlxUlc5RRsgEZbNCIWBeNjXwHvOVB5IevckhEzkidRBXYzF+BryIM6pFA\\n2E8S7Qhs2ATWsVDNEdx+1w==\\n-----END PRIVATE KEY-----\\n\",\r\n"
				+ "  \"client_email\": \"firebase-adminsdk-eeaen@portfolio-b6b9e.iam.gserviceaccount.com\",\r\n"
				+ "  \"client_id\": \"118173524263865592843\",\r\n"
				+ "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\r\n"
				+ "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\r\n"
				+ "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\r\n"
				+ "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-eeaen%40portfolio-b6b9e.iam.gserviceaccount.com\"\r\n"
				+ "}\r\n"
				+ "";
//		ClassLoader classLoader=MessagingApplication.class.getClassLoader();
//		File file = new File(Objects.requireNonNull(classLoader.getResource("serviceAccountKey.json")).getFile());
//		FileInputStream serviceAccount= new FileInputStream(file.getAbsolutePath());
		InputStream is = new ByteArrayInputStream(serviceAccountKey.getBytes());
		FirebaseOptions options = new FirebaseOptions.Builder()
				  .setCredentials(GoogleCredentials.fromStream(is))
				  .build();

		FirebaseApp.initializeApp(options);
		SpringApplication.run(MessagingApplication.class, args);
	}

}
