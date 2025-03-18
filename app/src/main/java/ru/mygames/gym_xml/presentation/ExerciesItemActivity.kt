package ru.mygames.gym_xml.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.mygames.gym_xml.R
import ru.mygames.gym_xml.domain.Exercies

class ExerciesItemActivity:AppCompatActivity() {
    private lateinit var viewModel: ExerciesViewModel
    private var screenMode = MODE_UNKNOWN
    private var exerciesId = Exercies.UNDEFINED_ID
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercies)
        parseIntent()
        changedMode()
    }


    private fun changedMode(){
        val fragment = when(screenMode){
            MODE_EDIT->ExerciesFragment.newInstanceEditItem(exerciesId)
            MODE_ADD->ExerciesFragment.newInstanceAddItem()
            else->throw RuntimeException("Unknow screen mode $screenMode")
        }
        supportFragmentManager.beginTransaction()
            .add(R.id.exercies_container, fragment)
            .commit()
    }

    private fun parseIntent(){
        if (!intent.hasExtra(EXTRA_SCREEN_MODE))
            throw RuntimeException("Param screen mode is absent")
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        if (mode != MODE_EDIT && mode != MODE_ADD)
            throw RuntimeException("Invalid screen mode: $mode")
        screenMode = mode
        if (screenMode == MODE_EDIT){
            if (!intent.hasExtra(EXTRA_EXERCIES_ITEM_ID))
                throw RuntimeException("Param exercies item id is absent")
            exerciesId = intent.getIntExtra(EXTRA_EXERCIES_ITEM_ID, Exercies.UNDEFINED_ID)
        }
    }



    companion object {
        const val MODE_UNKNOWN = ""
        private const val EXTRA_SCREEN_MODE = "extra_screen_mode"
        private const val EXTRA_EXERCIES_ITEM_ID = "extra_shop_item_id"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"


        fun newIntentAddItem(context: Context): Intent {
            val intent = Intent(context, ExerciesItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEditItem(context: Context,exerciesId: Int): Intent {
            val intent = Intent(context, ExerciesItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_EXERCIES_ITEM_ID, exerciesId)
            return intent
        }
    }
}