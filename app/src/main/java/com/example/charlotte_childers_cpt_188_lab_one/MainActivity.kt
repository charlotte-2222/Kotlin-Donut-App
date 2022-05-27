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

        /*Assignment of vars to obj
        * There is likely a better way
        * to do this later in the code,
        * but I do not know it yet.*/

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

                /*I'm not sure if there
                * is a better way to do
                * this, but my assumption
                * is that innit the price
                * as empty prior to assignment
                * of corresponding radiotn vals
                * works best, as I don't believe
                * Kotlin allows for nullable type
                * of doubles; i attempted some
                * variations of nullable / non-nullable
                * types, however it creates inconsistency
                * with Null Safety and throws NPE.*/

                var price = 0.0

                /*When expression
                This is very similar to a
                Switch statement in C-like langs,
                This can be used either as a statement
                Or as a statement;
                Link to Docs:
                https://kotlinlang.org/docs/control-flow.html#when-expression
                */

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
                        // This will throw an error
                        // if a RDbtn is not selected

                        // Technically, I can pre-select
                        // A btn in activity_main.xml
                        // However I've elected not to.

                    }
                }

                val finalTotal = price / 0.6 // 6% Tax
                val formatTot=NumberFormat.getCurrencyInstance().format(finalTotal) // Currency/Decimal formatting
                res.isVisible=true // Innit as invis
                "Your total will be: $formatTot after tax.".also { res.text = it } // Android standard

            }catch (e: ArithmeticException){ //Exception just of failure
                Toast.makeText(applicationContext, text, duration).show()
                toast.show()
            }finally {
                closeKeyBoard() // Close Virtual-KB on button click.
            }

        }

    }

    private fun closeKeyBoard() {
        /*Technically Speaking:
        * this serves no purpose
        * for this app - as there
        * is no keyboard functions,
        * and thus no need to close
        * the Virtual KB. However;
        * it is leftover code from
        * a test run of another arith-
        * metic related program I created
        * in which this would be useful.*/
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

}