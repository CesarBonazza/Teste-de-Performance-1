package com.example.calculadora_gorjetas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import java.text.NumberFormat

class MainActivity : AppCompatActivity(), View.OnFocusChangeListener, SeekBar.OnSeekBarChangeListener {

    private lateinit var ValorConta : EditText
    private lateinit var QntPessoas : EditText
    private lateinit var SkGorjeta : SeekBar

    private val formatador = NumberFormat.getCurrencyInstance()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ValorConta = this.findViewById<EditText>(R.id.InputValorConta)
        ValorConta.setOnFocusChangeListener(this)

        QntPessoas = this.findViewById<EditText>(R.id.InputQntPessoas)
        QntPessoas.setOnFocusChangeListener(this)

        SkGorjeta = this.findViewById<SeekBar>(R.id.SkBar)
        SkGorjeta.setOnSeekBarChangeListener(this)

    }
    // ------------------------ // ---------------------------- // ---------------------- //
    private fun Atualizar() {

        if(ValorConta.text.toString().isNotEmpty()
            && QntPessoas.text.toString().isNotEmpty()) {

            var VConta = ValorConta.text.toString().toDouble()
            var Qpessoas = QntPessoas.text.toString().toInt()
            // ------------------------- // ----------------------- //
            var ValorGorjetaBruto = this.findViewById<TextView>(R.id.lblResultVG)
            var ValorGorjeta = (VConta * SkGorjeta.progress) / 100
            ValorGorjetaBruto.setText(formatador.format(ValorGorjeta))
            // ---------------------------- // ----------------------- //
            var ValorAdicionado = this.findViewById<TextView>(R.id.lblResultV)
            var ValorTotalConta = (VConta + ValorGorjeta)
            ValorAdicionado.setText(formatador.format(ValorTotalConta))
            // --------------------------- // --------------------- //
            var QuantidadePessoas = this.findViewById<TextView>(R.id.lblResultVTD)
            QuantidadePessoas.setText(formatador.format((ValorTotalConta) / Qpessoas))

        }
    }


    override fun onFocusChange(p0: View?, p1: Boolean) {
        this.Atualizar()
    }

    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        var progressoBarrinha = this.findViewById<TextView>(R.id.SkBarPercent)
        progressoBarrinha.setText(SkGorjeta.progress.toString() + "%")

        this.Atualizar()
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {
    }

    override fun onStopTrackingTouch(p0: SeekBar?) {
    }
}