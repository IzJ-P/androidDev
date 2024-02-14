package com.example.toinsureproperservice

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.toinsureproperservice.ui.theme.ToInsureProperServiceTheme

private const val TAG = "MainActivity"
private const val INIT_TIP_Amount = 15

class MainActivity : ComponentActivity() {

    private lateinit var etBillTotal: EditText
    private lateinit var seekTipAmount: SeekBar
    private lateinit var tvTipAmount: TextView
    private lateinit var tvTipDisplay: TextView
    private lateinit var tvTotal: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        etBillTotal = findViewById(R.id.etBillTotal)
        seekTipAmount = findViewById(R.id.seekTipAmount)
        tvTipAmount = findViewById(R.id.tvTipAmount)
        tvTipDisplay = findViewById(R.id.tvTipAmountDisplay)
        tvTotal = findViewById(R.id.tvTotalAmount)
        seekTipAmount.progress = INIT_TIP_Amount
        tvTipAmount.text = "$INIT_TIP_Amount%"
        seekTipAmount.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                Log.i(TAG, "onProgressChanged $progress")
                tvTipAmount.text = "$progress%"
                computeTipAndTotal()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        etBillTotal.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                Log.i(TAG, "afterTextChanged $s")
                computeTipAndTotal()
            }

        })

//        setContent {
//
//            ToInsureProperServiceTheme {
//
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting("Android")
//                }
//            }
//        }
    }

    private fun computeTipAndTotal() {

        if(etBillTotal.text.isEmpty()) {

            tvTipDisplay.text = " "
            tvTotal.text = " "
            return
        }
        val billAmount = etBillTotal.text.toString().toDouble()
        val tipPercent = seekTipAmount.progress

        val tipAmount = billAmount * tipPercent / 100
        val totalAmount = billAmount + tipAmount

        tvTipDisplay.text = "%.2f" .format(tipAmount)
        tvTotal.text = "%.2f" .format(totalAmount)

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ToInsureProperServiceTheme {
        Greeting("Android")
    }
}