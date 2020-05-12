package com.example.sportsdb.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sportsdb.MoshiClass
import com.example.sportsdb.dataDetails
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.produce

class CatalogViewModel : ViewModel() {
    private val backgroundWorkScope = CoroutineScope(Dispatchers.Default + SupervisorJob())
    private val _LiveData: MutableLiveData<List<MoshiClass.Team>> = MutableLiveData()
    private val _MapLiveData: MutableLiveData<List<MoshiClass.Team>> = MutableLiveData()

    val LiveData: LiveData<List<MoshiClass.Team>>
        get() = _LiveData
    val MapLiveData: LiveData<List<MoshiClass.Team>>
        get() = _MapLiveData

    init {
        triggerTeamsFetch()
        println("triggerTeamsFetch")
    }

    fun triggerTeamsFetch() {
        println("inside triggerTeamsFetch")
        backgroundWorkScope.launch(Dispatchers.IO) {


            withContext(Dispatchers.Main) {
                _LiveData.value = dataDetails
            }

            withContext(Dispatchers.Main) {
                _MapLiveData.value = dataDetails

            }

        }
    }

    override fun onCleared() {
        // cancel all
        backgroundWorkScope.cancel()
    }
}


