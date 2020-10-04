package com.example.activities

import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG: String = "MainActivity"
    private var contador = 1
    private var numeroAleatorio = 0
    private val mensagens = mutableMapOf(
        0 to "O jogo vai começar!",
        1 to "Nossa você tem muita sorte!",
        2 to "Você tem sorte :)",
        3 to "Essa foi por pouco!",
        4 to "Não foi dessa vez, mas não desista!"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(TAG, "Chegando no onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "Chegando no onStart")

        this.numeroAleatorio = gerarNumero()
        Log.i(TAG, "Este é o número aleatório: $numeroAleatorio")
        exibirMensagemToast(mensagens.getValue(0))
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "Chegando no onResume")

        btn_verificar.setOnClickListener {
            val inputNum = input_num.text.toString().toInt()

            if (inputNum == this.numeroAleatorio) {
                exibirMensagemToast(mensagens.getValue(this.contador))
                mostrarResultadoEReiniciar()
                return@setOnClickListener
            } else {
                exibirMensagemToast("Tente novamente!")
            }

            this.contador++

            if (this.contador == 5) {
                mostrarResultadoEReiniciar()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "Chegando no onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "Chegando no onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(TAG, "Chegando no onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "Chegando no onDestroy")
    }

    private fun gerarNumero(): Int {
        return (1..10).shuffled().first()
    }

    private fun exibirMensagemToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun mostrarResultadoEReiniciar() {
        exibirMensagemToast("O numero era ${this.numeroAleatorio}. Reiniciando...")
        this.contador = 1
        onRestart()
        onStart()
    }
}