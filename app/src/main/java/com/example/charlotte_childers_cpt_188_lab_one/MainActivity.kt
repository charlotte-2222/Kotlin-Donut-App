package com.example.charlotte_childers_cpt_188_lab_one
//    Charlotte Childers CPT-188 Lab One
//    5/28/2022
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.core.view.isVisible
import java.text.NumberFormat



class MainActivity : AppCompatActivity() {
    //Declared vars
    lateinit var glazed: RadioButton
    lateinit var choco: RadioButton
    lateinit var cream: RadioButton
    lateinit var calculate: Button
    lateinit var res: TextView
    //...

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Assignment of vars to android objs
        glazed=findViewById(R.id.rdGlazed)
        choco=findViewById(R.id.rdChocolate)
        cream=findViewById(R.id.rdCream)
        calculate = findViewById(R.id.btnCalculate)
        res = findViewById(R.id.txtResult)
        //...

        // Error toast
        val text = "No values selected! Select a donut!"
        val duration = Toast.LENGTH_LONG
        val toast = Toast.makeText(applicationContext, text, duration)

        //...


        calculate.setOnClickListener{
            try{
                var price = 0.0
                when {
                    glazed.isChecked -> {
                        price=0.99
                    }
                    choco.isChecked -> {
                        price = 1.20
                    }
                    cream.isChecked -> {
                        price = 1.90
                    }
                    else -> {
                        toast.show()
                        res.isVisible=false
                    }
                }

                val finalTotal = price / 0.6
                val formatTot=NumberFormat.getCurrencyInstance().format(finalTotal)
                res.isVisible=true
                "Your total will be: $formatTot after tax.".also { res.text = it }

            }catch (e: ArithmeticException){
                Toast.makeText(applicationContext, text, duration).show()
                toast.show()
            }finally {
                closeKeyBoard()
            }

        }

    }

    private fun closeKeyBoard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

}