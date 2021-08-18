package com.orhan.retrofitkotlin1.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.orhan.retrofitkotlin1.R
import com.orhan.retrofitkotlin1.adapter.RecyclerViewAdapter
import com.orhan.retrofitkotlin1.model.CryptoModel
import com.orhan.retrofitkotlin1.service.CryptoAPI
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity(),RecyclerViewAdapter.Listener{
    private val BASE_URL = "http://api.nomics.com/v1/"
    private var cryptoModels: ArrayList<CryptoModel>? = null
    private var recyclerViewAdapter : RecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         //http://api.nomics.com/v1/prices?key=f35b5008feba80ff17bb88acdaeaaaea712a97ea
        // f35b5008feba80ff17bb88acdaeaaaea712a97ea

        //RecyclerView
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
      loadData()
    }
    private fun loadData(){

      val retrofit = Retrofit.Builder()
          .baseUrl(BASE_URL)
          .addConverterFactory(GsonConverterFactory.create())
          .build()

        val service = retrofit.create(CryptoAPI::class.java)
        val call = service.getData()

        call.enqueue(object: Callback<List<CryptoModel>>{
            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<List<CryptoModel>>,
                response: Response<List<CryptoModel>>
            ) {
                if(response.isSuccessful){
                    response.body()?.let {
                        cryptoModels = ArrayList(it)
                        cryptoModels?.let {
                            recyclerViewAdapter = RecyclerViewAdapter(it,this@MainActivity)
                            recyclerView.adapter = recyclerViewAdapter
                        }


                        /*
                        for (cryptoModel : CryptoModel in cryptoModels!!){
                            println(cryptoModel.currency)
                            println(cryptoModel.price)

                        }

                         */
                    }
                }
            }

        })
    }

    override fun onItemClick(cryptoModel: CryptoModel) {
        Toast.makeText(this,"Clicked : ${cryptoModel.currency}",Toast.LENGTH_LONG).show()
    }
}