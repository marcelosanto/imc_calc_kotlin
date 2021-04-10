package com.marcelo.imc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.marcelo.imc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val btn_calcular = binding.btnCalcular
        val mensagem = binding.mensagem

        btn_calcular.setOnClickListener {
            val editPeso = binding.editPeso.text.toString()
            val editAltura = binding.editAltura.text.toString()

            if(editPeso.isEmpty()){
                mensagem.setText("Digite o seu peso")
            } else if (editAltura.isEmpty()){
                mensagem.setText("Digite a sua  altura")
            } else {
                CalculoDeIMC()
            }
        }
    }

    private fun CalculoDeIMC(){
        val pesoID = binding.editPeso
        val alturaID = binding.editAltura
        val resultado = binding.mensagem

        val peso = Integer.parseInt(pesoID.text.toString())
        val altura = java.lang.Float.parseFloat(alturaID.text.toString())
        val imc = peso / (altura * altura)

        val Mensagem = when{
            imc <= 18.5 -> "Peso Baixo"
            imc <= 24.9 -> "Peso Normal"
            imc <= 29.9 -> "Sobrepeso"
            imc <= 34.9 -> "Obesidade (Grau I)"
            imc <= 39.9 -> "Obesidade Severa (Grau II)"
            else -> "Obesidade Mórbida (Grau III)"
        }

        imc.toString()
        resultado.setText("IMC: $imc \n $Mensagem")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflate = menuInflater
        inflate.inflate(R.menu.menu_principal, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.reset -> {
                val limparEditPeso = binding.editPeso
                val limparAltura = binding.editAltura
                val limparEditMensagem = binding.mensagem

                limparEditPeso.setText("")
                limparAltura.setText("")
                limparEditMensagem.setText("")
            }
        }

        return super.onOptionsItemSelected(item)
    }
}