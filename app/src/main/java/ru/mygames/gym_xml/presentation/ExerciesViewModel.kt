package ru.mygames.gym_xml.presentation


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.mygames.gym_xml.data.Exercies_Repository_Impl
import ru.mygames.gym_xml.domain.home_exercies.Add_Exercies_Use_Case
import ru.mygames.gym_xml.domain.home_exercies.Edit_Exercies_Use_Case
import ru.mygames.gym_xml.domain.home_exercies.Exercies
import ru.mygames.gym_xml.domain.home_exercies.Get_Exercies_ID_Use_Case

class ExerciesViewModel:ViewModel() {
    private val repository = Exercies_Repository_Impl
    private val getExerciesItemUseCase = Get_Exercies_ID_Use_Case(repository)
    private val addExerciesItemUseCase = Add_Exercies_Use_Case(repository)
    private val editExerciesItemUseCase = Edit_Exercies_Use_Case(repository)

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName:LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean>
        get() = _errorInputCount

    private val _getExerciesItem = MutableLiveData<Exercies>()
    val getExerciesItem: LiveData<Exercies>
        get() = _getExerciesItem

    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit>
        get() = _shouldCloseScreen

    fun getExercies(itemId: Int){
        val item =  getExerciesItemUseCase.getExerciesById(itemId)
        _getExerciesItem.value = item
    }

    fun addExercies(inputName: String?, inputCount: String?){
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        if (validateInput(name, count)){
            addExerciesItemUseCase.addExercies(Exercies(name = name, durationOrCount = count.toString(), enabled = true))
            closeScreen()
        }

    }
    fun editExercies(inputName: String?, inputCount: String?){
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        if (validateInput(name, count)){
            _getExerciesItem.value?.let {
                val item = it.copy(name = name, durationOrCount = count.toString())
                editExerciesItemUseCase.editExercies(item)
                closeScreen()
            }
        }

    }

    private fun parseName(inputName: String?):String{
        return inputName?.trim()?: ""
    }

    private fun parseCount(inputCount: String?):Int{
        return try {
            inputCount?.trim()?.toInt()?:0
        }catch (e:Exception) {
            0
        }
    }
    private fun validateInput(name:String, count:Int):Boolean{
        var result = true
        if (name.isBlank()){
            _errorInputName.value = true
            result = false
        }
        if (count<=0){
            _errorInputCount.value = true
            result = false
        }
        return result
    }

    fun resetErrorInputName() {
        _errorInputName.value = false
    }
    fun resetErrorInputCount() {
        _errorInputCount.value = false
    }
    fun closeScreen() {
        _shouldCloseScreen.value = Unit
    }
}