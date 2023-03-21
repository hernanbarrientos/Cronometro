package com.nandev.cronometro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Toast
import com.nandev.cronometro.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var running = false // variavel de estado referente ao cronometro se ele está correndo ou não
    var pause: Long = 0 // variavel que compõe a lógia de continuar de onde parou ou zerar o cronometro

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.iniciar.setOnClickListener {
            iniciarCronometro()
            Toast.makeText( // Confirmando que o botão foi pressionado
                this,
                "Cronômetro iniciado",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.pausar.setOnClickListener {
            pausarCronometro()
            Toast.makeText(
                this,
                "Cronômetro Pausado",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.zerar.setOnClickListener {
            zerarCronometro()
            Toast.makeText(
                this,
                "Cronômetro zerado",
                Toast.LENGTH_SHORT
            ).show()


        }
    }

    private fun iniciarCronometro(){
        if(!running){
            binding.cronometro.base = SystemClock.elapsedRealtime() - pause
            binding.cronometro.start() //iniciando o cronometro
            running = true // setando o estado running como true
        }
    }
    private fun pausarCronometro(){
        if(running){
            binding.cronometro.stop()
            pause = SystemClock.elapsedRealtime() - binding.cronometro.base
            running = false
        }
    }

    private fun zerarCronometro(){
        binding.cronometro.base = SystemClock.elapsedRealtime()
        pause = 0
    }
}