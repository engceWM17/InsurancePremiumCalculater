package com.example.insurancepremiumcalculater

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val selectedItem=spinnerAge.getItemAtPosition(position)
        Toast.makeText(this,"Selected Item="+selectedItem,Toast.LENGTH_LONG)
            .show()
    }

   /*override fun onClick(v:View?){
        when(v){
            buttonCalculate->calculatePremium()
            buttonReset->resetOutput()
            else->// 

        }
    }*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinnerAge.onItemSelectedListener=this

        buttonCalculate.setOnClickListener {
            calculatePremium()
        }

        /*val buttonThird: Button
        buttonThird.setOnClickListener(this)*/
    }

    private fun calculatePremium() {
        //get the age group.position of an array
        val age: Int = spinnerAge.selectedItemPosition

        var premium = when(age){
            0->60
            1->70
            2->90
            3->120
            else->150
        }

        //get the gender selection.id of radio button
        val gender =radioGroupGender.checkedRadioButtonId

        if(gender==R.id.radioButtonMale){
            //calculate premium for male
            premium += when(age){
                0->0
                1->50
                2->100
                3->150
                else->200
            }

        }else{
            //calculate premium for female
        }

        //Determine smoker or non-smoker
        val smoker : Boolean =checkBoxSmoker.isChecked
        if(smoker){
            //calculate premium for smoker
            premium += when(age){
                0->0
                1->100
                2->150
                3->200
                4->250
                else->300
            }
        }
        val symbol=Currency.getInstance(Locale.getDefault()).symbol
        textViewPremium.text = String.format("%s %s %d",getString(R.string.insurance_premium),symbol,premium)
    }

    fun reset(view: View) {
        textViewPremium.text=getString(R.string.insurance_premium)
        checkBoxSmoker.isChecked=false
        radioGroupGender.clearCheck()
        spinnerAge.setSelection(0)
    }

}
