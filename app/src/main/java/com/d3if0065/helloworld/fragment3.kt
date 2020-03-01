package com.d3if0065.helloworld


import android.app.Application
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.d3if0065.helloworld.databinding.FragmentBlankBinding
import com.d3if0065.helloworld.databinding.FragmentFragment3Binding
import kotlinx.android.synthetic.main.fragment_fragment2.*
import kotlinx.android.synthetic.main.fragment_fragment2.et_nama
import kotlinx.android.synthetic.main.fragment_fragment3.*

/**
 * A simple [Fragment] subclass.
 */
class fragment3 : Fragment() {
    var nilaiAkhir : Double = 0.0
    var indeks = ""
    var gambar = 0
    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val binding: FragmentFragment3Binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_fragment3, container, false)
        binding.tvNilaiAkhir.visibility = View.GONE
        binding.tvIndeks.visibility = View.GONE
        binding.imageView3.visibility = View.GONE

        if (savedInstanceState != null){
            nilaiAkhir = savedInstanceState.getDouble("keyNilai",0.0)
            indeks = savedInstanceState.getString("keyIndeks","")
            gambar = savedInstanceState.getInt("gambar",0)

            binding.tvNilaiAkhir.visibility = View.VISIBLE
            binding.tvIndeks.visibility = View.VISIBLE
            binding.imageView3.visibility = View.VISIBLE
        }

        binding.nilaiAkhir = nilaiAkhir
        binding.indeks = indeks


        binding.btnHitung.setOnClickListener {
            binding.tvNilaiAkhir.visibility = View.VISIBLE
            binding.tvIndeks.visibility = View.VISIBLE
            binding.imageView3.visibility = View.VISIBLE

            if (!TextUtils.isEmpty(et_nama.text.toString())
                && !TextUtils.isEmpty(et_praktikum.text.toString())
                && !TextUtils.isEmpty(et_ass1.text.toString())
                && !TextUtils.isEmpty(et_ass2.text.toString())
                && isrange(et_praktikum.text.toString().toFloat())
                && isrange(et_ass1.text.toString().toFloat())
                && isrange(et_ass2.text.toString().toFloat())
            ) {
//                Log.d("data","halo halo ")
                nilaiAkhir = (0.3 * et_praktikum.text.toString().toFloat()) + (0.3 * et_ass1.text.toString().toFloat())
                if (rb_ya.isChecked){
                    nilaiAkhir += (0.4 * et_ass2.text.toString().toFloat())
                }
                binding.tvNilaiAkhir.text = nilaiAkhir.toString()

                if (nilaiAkhir <= 40){
                    indeks = "E"
                }else if(nilaiAkhir <= 50){
                    indeks = "D"
                }else if(nilaiAkhir <= 60){
                    indeks = "C"
                }else if (nilaiAkhir <= 65){
                    indeks = "BC"
                }else if (nilaiAkhir <= 70 ){
                    indeks = "B"
                }else if(nilaiAkhir <= 80){
                    indeks = "AB"
                }else if(nilaiAkhir >= 100){
                    indeks = "A"
                }
                binding.tvIndeks.text = indeks


                if (nilaiAkhir < 50){
                    gambar = R.drawable.ic_mood_black_24dp
                    binding.imageView3.setImageResource(R.drawable.ic_mood_bad__24dp)
                    binding.tvIndeks.setTextColor(Color.parseColor("#00ff00"))
                }else{
                    gambar = R.drawable.ic_mood_bad__24dp
                    binding.imageView3.setImageResource(R.drawable.ic_mood_black_24dp)
                    binding.tvIndeks.setTextColor(Color.parseColor("#0000ff"))

                }

            } else {

                Toast.makeText(context,"harap isi semua form",Toast.LENGTH_LONG).show()
            }

            binding.btnShare.setOnClickListener {
                val mIntent = Intent(Intent.ACTION_SENDTO)
                mIntent.data = Uri.parse("mailto:")

                mIntent.putExtra(Intent.EXTRA_EMAIL,"esaputra@gmail.com")

                mIntent.putExtra(Intent.EXTRA_SUBJECT,"receipt")
                mIntent.putExtra(
                    Intent.EXTRA_TEXT,"nilaiAkhir = $nilaiAkhir \n " +
                            "indeksnya $indeks \n ")
                startActivity(Intent.createChooser(mIntent,"https://gmail.com"))
            }
        }
        return binding.root
    }


    fun isrange(a: Float): Boolean {
        return a >= 0 && a <= 100
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putDouble("keyNilai",nilaiAkhir)
        outState.putString("keyIndeks",indeks)
        outState.putInt("gambar",gambar)
        super.onSaveInstanceState(outState)
    }
}
