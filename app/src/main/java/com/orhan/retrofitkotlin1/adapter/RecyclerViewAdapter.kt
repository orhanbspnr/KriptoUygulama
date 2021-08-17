package com.orhan.retrofitkotlin1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.orhan.retrofitkotlin1.R
import com.orhan.retrofitkotlin1.model.CryptoModel
import kotlinx.android.synthetic.main.row_layout.view.*

class RecyclerViewAdapter(private val cryptoList : ArrayList<CryptoModel>) : RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>() {
    class RowHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(){


    }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false)
        return RowHolder(view)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return cryptoList.count()
    }

}