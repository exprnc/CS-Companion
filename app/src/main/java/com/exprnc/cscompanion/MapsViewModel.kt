package com.exprnc.cscompanion

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exprnc.cscompanion.models.Map
import kotlinx.coroutines.launch

class MapsViewModel : ViewModel() {
    private val collectionMaps = FirestoreDatabase.getCollection("maps")

    var activeMaps = mutableStateListOf<Map>()
        private set
    var inactiveMaps = mutableStateListOf<Map>()
        private set

    init {
        fetchMaps()
    }

    private fun fetchMaps() {
        viewModelScope.launch {
            collectionMaps.get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        val map = Map(
                            id = document.id,
                            activePool = document.getBoolean("activePool")!!,
                            image = document.getString("image")!!,
                            name = document.getString("name")!!,
                            scheme = document.getString("scheme")!!
                        )
                        if (map.activePool) {
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