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
    private val _MapLiveData: MutableLiveData<Map<String?, String?>> = MutableLiveData()

    val LiveData: LiveData<List<MoshiClass.Team>>
        get() = _LiveData
    val MapLiveData: LiveData<Map<String?, String?>>
        get() = _MapLiveData

    init {
        triggerCatalogFetch()
        println("triggerCatalogFetch")
    }

    fun triggerCatalogFetch() {
        println("inside triggerCatalogFetch")
        backgroundWorkScope.launch(Dispatchers.IO) {


            withContext(Dispatchers.Main) {
                _LiveData.value = dataDetails
            }

            withContext(Dispatchers.Main) {
                for (i in 0 until dataDetails!!.size)
                { _MapLiveData.value = mutableMapOf(dataDetails!![i].Team to dataDetails!![i].Stadium).apply {
                    _MapLiveData.value?.let { map ->
                        this.putAll(map)
                    }
                }}
                }
//            }
        }
    }

    override fun onCleared() {
        // cancel all
        backgroundWorkScope.cancel()
    }
}


