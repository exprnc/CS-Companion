package com.exprnc.cscompanion

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class MapsViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val collectionRef = db.collection("maps")

    var activeMaps = mutableStateListOf<Map>()
        private set
    var inactiveMaps = mutableStateListOf<Map>()
        private set

    init {
        fetchMaps()
    }

    private fun fetchMaps() {
        viewModelScope.launch {
            collectionRef.get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        val map = Map(
                            id = document.id,
                            image = document.getString("image")!!,
                            name = document.getString("name")!!,
                            status = document.getString("status")!!,
                            mapImage = document.getString("mapImage")!!
                        )
                        if (map.status == "active") {
                            activeMaps.add(map)
                        } else {
                            inactiveMaps.add(map)
                        }
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w("FIRESTORE", "Error getting documents: ", exception)
                }
        }
    }
}