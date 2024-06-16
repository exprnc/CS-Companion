package com.exprnc.cscompanion

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exprnc.cscompanion.models.Map
import com.exprnc.cscompanion.models.Nade
import com.exprnc.cscompanion.models.Position
import com.google.firebase.firestore.getField
import kotlinx.coroutines.launch

class SchemeViewModel : ViewModel() {
    private val collectionNades = FirestoreDatabase.getCollection("nades")

    var nades = mutableStateListOf<Nade>()
        private set

    init {
        fetchNades()
    }

    private fun fetchNades() {
        viewModelScope.launch {
            collectionNades.get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        val nade = Nade(
                            id = document.id,
                            mapId = document.getDocumentReference("mapId")?.toString() ?: "",
                            name = document.getField<String>("name") ?: "",
                            offsetX = document.getField<Float>("offsetX") ?: 0f,
                            offsetY = document.getField<Float>("offsetY") ?: 0f,
                            side = document.getField<String>("side") ?: "",
                            type = document.getField<String>("type") ?: "",
                            positions = document.getField<List<Position>>("position") ?: emptyList()
                        )
                        nades.add(nade)
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w("FIRESTORE", "Error getting documents: ", exception)
                }
        }
    }
}