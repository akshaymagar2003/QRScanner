package com.example.qrscanner

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference

object FirebaseManager {

    fun saveToFirestore(decodedValue: String) {
        val db = FirebaseFirestore.getInstance()
        val valuesCollection: CollectionReference = db.collection("Decoded Value") // Change to your desired collection name

        // Create a new document with the decoded value
        valuesCollection.add(mapOf("value" to decodedValue))
            .addOnSuccessListener { documentReference: DocumentReference ->
                // Document saved successfully
            }
            .addOnFailureListener { e: Exception ->
                // Handle error while saving the document
            }
    }
}
