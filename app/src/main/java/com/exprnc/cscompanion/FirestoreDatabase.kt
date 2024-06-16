package com.exprnc.cscompanion

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

object FirestoreDatabase {
    private val db = FirebaseFirestore.getInstance()

    fun getCollection(collectionName: String): CollectionReference {
        return db.collection(collectionName)
    }
}