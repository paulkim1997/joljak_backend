package com.joljak.firebase;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FireBaseInitializer {

    @PostConstruct
    public void initialization() {
        InputStream serviceAccount = null;
        try {
            serviceAccount = this.getClass().getClassLoader().getResourceAsStream("./serviceAccountKey.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://test2-8d4e7-default-rtdb.firebaseio.com")
                    .build();
            if(FirebaseApp.getApps().isEmpty())
                FirebaseApp.initializeApp(options);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Firestore getFirebase() {
        return FirestoreClient.getFirestore();
    }
}
