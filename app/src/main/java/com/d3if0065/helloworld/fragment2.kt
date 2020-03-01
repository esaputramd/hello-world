package com.d3if0065.helloworld


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.d3if0065.helloworld.databinding.FragmentBlankBinding
import com.d3if0065.helloworld.databinding.FragmentFragment2Binding
import kotlinx.android.synthetic.main.fragment_fragment2.*

/**
 * A simple [Fragment] subclass.
 */
class fragment2 : Fragment() {
    var quantity = 0;
    var harga = 0
    var topping = 0
    var kue = ""
    var nama = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val binding: FragmentFragment2Binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_fragment2, container, false)

        binding.tvNama.visibility = View.GONE
        binding.tvHarga.visibility = View.GONE
        binding.tvKue.visibility = View.GONE
        binding.btnReset.visibility = View.GONE


        if (savedInstanceState != null){
            quantity = savedInstanceState.getInt("qty",0)
            harga = savedInstanceState.getInt("harga",0)
            nama = savedInstanceState.getString("nama","")
            kue = savedInstanceState.getString("kue","")

            binding.tvNama.visibility = View.VISIBLE
            binding.tvHarga.visibility = View.VISIBLE
            binding.tvKue.visibility = View.VISIBLE
            binding.btnReset.visibility = View.VISIBLE
        }

        binding.nama = nama
        binding.jumlah = quantity
        binding.harga = harga
        binding.kue = kue



        binding.btnPlus.setOnClickListener {
            quantity = binding.tvQuantity.text.toString().toInt() + 1
            binding.tvQuantity.setText(quantity.toString())
        }
        binding.btnMinus.setOnClickListener {
            quantity = binding.tvQuantity.text.toString().toInt() - 1
            binding.tvQuantity.setText(quantity.toString())
        }

        binding.btnBeli.setOnClickListener {

            binding.tvNama.visibility = View.VISIBLE
            binding.tvHarga.visibility = View.VISIBLE
            binding.tvKue.visibility = View.VISIBLE
            binding.btnReset.visibility = View.VISIBLE

            if (binding.cbIceCream.isChecked) {
                topping += 2000
            }
            if (binding.cbCeres.isChecked) {
                topping += 1000
        }

            if (binding.rbCoklat.isChecked) {
                kue = "Rasa Coklat"
                tv_kue.text = kue
            } else if (binding.rbStrawbery.isChecked) {
                kue = "Rasa Strawberry"
                tv_kue.text = kue
            }
            val qty = tv_quantity.text.toString().toInt()
            harga = qty * 5000 + topping
            nama = et_nama.text.toString()
//            harga = binding.tvQuantity.text.toString().toInt() * 5000 + topping
//            binding.tvHarga.setText("total Harga nya = "+harga.toString())
//            nama = et_nama.text.toString()
//            binding.tvNama.setText("nama pembelinya = "+nama)

            binding.nama = nama
            binding.jumlah = quantity
            binding.harga = harga
            binding.kue = kue
            binding.btnShare.visibility = View.VISIBLE

        }

        binding.btnReset.setOnClickListener{
            binding.rbCoklat.isChecked = false
            binding.rbStrawbery.isChecked = false
            binding.cbCeres.isChecked = false
            binding.cbIceCream.isChecked = false
            binding.tvNama.visibility = View.GONE
            binding.tvHarga.visibility = View.GONE
            binding.tvKue.visibility = View.GONE
            binding.btnShare.visibility = View.GONE
            binding.etNama.text.clear()
            binding.tvQuantity.setText("0")
        }

        binding.btnShare.setOnClickListener{
            val mIntent = Intent(Intent.ACTION_SENDTO)
            mIntent.data = Uri.parse("mailto:")

            mIntent.putExtra(Intent.EXTRA_EMAIL,"esaputra@gmail.com")
            mIntent.putExtra(Intent.EXTRA_SUBJECT,"receipt")
            mIntent.putExtra(Intent.EXTRA_TEXT,"nama = $nama \n " +
                    "kue nya adalah $kue \n " +
                    "harga nya $harga" )
            startActivity(Intent.createChooser(mIntent,"https://gmail.com"))


        }



        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("qty",quantity)
        outState.putString("nama",nama)
        outState.putString("kue",kue)
        outState.putInt("harga",harga)
    }
}
