package com.example.caculator_t3h

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.caculator_t3h.databinding.ActivityMainBinding
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {


    lateinit var textViewHistory: TextView
    lateinit var textViewResult: TextView
    var number: String? = null
    var lastnumber = 0.0
    var firstnumber = 0.0
    var dot = true
    var del = true
    var operator = false
    var status: String? = null
    var isEqual = false
    var pattern = "###,###.#####"
    var decimalFormat = DecimalFormat(pattern)
    var history: String? = null
    var result: String? = null

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding by lazy {
        requireNotNull(_binding)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        textViewHistory = findViewById(R.id.tvInput)
        textViewResult = findViewById(R.id.tvOutput)
        binding.apply {
            btn0.setOnClickListener {
                numberclick(
                    "0"
                )
            }
            btn1.setOnClickListener { view: View? ->
                numberclick(
                    "1")
            }
            btn2.setOnClickListener{ view: View? ->
                numberclick(
                    "2"
                )
            }
            btn3.setOnClickListener { view: View? ->
                numberclick(
                    "3"
                )
            }
            btn4.setOnClickListener { view: View? ->
                numberclick(
                    "4"
                )
            }
            btn5.setOnClickListener{ view: View? ->
                numberclick(
                    "5"
                )
            }
            btn6.setOnClickListener { view: View? ->
                numberclick(
                    "6"
                )
            }
            btn7.setOnClickListener{ view: View? ->
                numberclick(
                    "7"
                )
            }
            btn8.setOnClickListener { view: View? ->
                numberclick(
                    "8"
                )
            }
            btn9.setOnClickListener{ view: View? ->
                numberclick(
                    "9"
                )
            }
            btnCong.setOnClickListener { view: View? ->
                if (isEqual) {
                    history = textViewHistory.getText().toString()
                    result = textViewResult.getText().toString()
                    textViewHistory.setText("$history+")
                } else {
                    history = textViewHistory.getText().toString()
                    result = textViewResult.getText().toString()
                    textViewHistory.setText("$history$result+")
                }
                if (operator) {
                    if (status === "multi") {
                        Multi()
                    } else {
                        if (status === "div") {
                            Div()
                        } else {
                            if (status === "minus") {
                                Minus()
                            } else {
                                Plus()
                            }
                        }
                    }
                }
                operator = false
                number = null
                status = "plus"
                isEqual = false
            }
            btnNhan.setOnClickListener { view: View? ->
                if (isEqual) {
                    history = textViewHistory.getText().toString()
                    result = textViewResult.getText().toString()
                    textViewHistory.setText("$history*")
                } else {
                    history = textViewHistory.getText().toString()
                    result = textViewResult.getText().toString()
                    textViewHistory.setText("$history$result*")
                }
                if (operator) {
                    if (status === "plus") {
                        Multi()
                    } else {
                        if (status === "div") {
                            Div()
                        } else {
                            if (status === "minus") {
                                Minus()
                            } else {
                                Multi()
                            }
                        }
                    }
                }
                operator = false
                number = null
                status = "multi"
                isEqual = false
            }
            btnChia.setOnClickListener { view: View? ->
                if (isEqual) {
                    history = textViewHistory.getText().toString()
                    result = textViewResult.getText().toString()
                    textViewHistory.setText("$history/")
                } else {
                    history = textViewHistory.getText().toString()
                    result = textViewResult.getText().toString()
                    textViewHistory.setText("$history$result/")
                }
                if (operator) {
                    if (status === "multi") {
                        Multi()
                    } else {
                        if (status === "plus") {
                            Plus()
                        } else {
                            if (status === "minus") {
                                Minus()
                            } else {
                                Div()
                            }
                        }
                    }
                }
                operator = false
                number = null
                status = "div"
                isEqual = false
            }
            btnTru.setOnClickListener { view: View? ->
                if (isEqual) {
                    history = textViewHistory.getText().toString()
                    result = textViewResult.getText().toString()
                    textViewHistory.setText("$history-")
                } else {
                    history = textViewHistory.getText().toString()
                    result = textViewResult.getText().toString()
                    textViewHistory.setText("$history$result-")
                }
                if (operator) {
                    if (status === "multi") {
                        Multi()
                    } else {
                        if (status === "div") {
                            Div()
                        } else {
                            if (status === "plus") {
                                Plus()
                            } else {
                                Minus()
                            }
                        }
                    }
                }
                operator = false
                number = null
                isEqual = false
                status = "minus"
            }
            btnKq.setOnClickListener{ view: View? ->
                history = textViewHistory.getText().toString()
                result = textViewResult.getText().toString()
                textViewHistory.setText(history + result)
                if (operator) {
                    if (status === "multi") {
                        Multi()
                    } else {
                        if (status === "plus") {
                            Plus()
                        } else {
                            if (status === "minus") {
                                Minus()
                            } else {
                                if (status === "div") {
                                    Div()
                                } else {
                                    firstnumber = textViewResult.getText().toString().toDouble()
                                }
                            }
                        }
                    }
                }
                operator = false
                isEqual = true
                dot = false
            }
            btnAC.setOnClickListener { view: View? ->
                number = null
                operator = false
                textViewResult.setText("0")
                textViewHistory.setText("")
                lastnumber = 0.0
                firstnumber = lastnumber
                dot = true
                del = true
            }

            btnDel.setOnClickListener { view: View? ->
                if (dot) {
                    number = if (number == null) {
                        "0."
                    } else {
                        "$number."
                    }
                }
                dot = false
                textViewResult.setText(number)
            }
        }
    }

    fun numberclick(view: String) {
        if (number == null) {
            number = view
        } else {
            number += view
        }
        textViewResult!!.text = number
        operator = true
        del = false

    }

    fun Minus() {
        if (firstnumber == 0.0) {
            firstnumber = textViewResult!!.getText().toString().toDouble()
        } else {
            lastnumber = textViewResult!!.getText().toString().toDouble()
            firstnumber -= lastnumber
        }
        textViewResult!!.text = decimalFormat.format(firstnumber)
        dot = true
    }

    fun Plus() {
        lastnumber = textViewResult!!.getText().toString().toDouble()
        firstnumber += lastnumber
        textViewResult!!.text = decimalFormat.format(firstnumber)
        dot = true
    }

    fun Multi() {
        if (firstnumber == 0.0) {
            firstnumber = 1.0
        }
        lastnumber = textViewResult!!.getText().toString().toDouble()
        firstnumber *= lastnumber
        textViewResult!!.text = decimalFormat.format(firstnumber)
        dot = true
    }

    fun Div() {
        if (firstnumber == 0.0) {
            lastnumber = textViewResult!!.getText().toString().toDouble()
            firstnumber = lastnumber
        } else {
            lastnumber = textViewResult!!.getText().toString().toDouble()
            firstnumber /= lastnumber
        }
        textViewResult!!.text = decimalFormat.format(firstnumber)
        dot = true
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}