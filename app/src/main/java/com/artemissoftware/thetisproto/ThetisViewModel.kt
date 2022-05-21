package com.artemissoftware.thetisproto

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artemissoftware.thetisproto.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ThetisViewModel: ViewModel()  {

    private lateinit var repository: UserRepository


    var stateFlag  = mutableStateOf("white" to 1f)


    fun getUserPreferences(context: Context){

        repository = UserRepository(context = context)

        viewModelScope.launch {

            repository.getUserColorPreference().collect { pair ->

                if(pair.first != "") {

                    stateFlag.value = pair
                }

            }
        }
    }

    fun saveLanguage(color: String, alpha: Float) {
        viewModelScope.launch(Dispatchers.Default){
            repository.saveUserColorPreference(color = color, alpha = alpha)
        }

    }

}