package com.imploded.kotlingameapp.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.imploded.kotlingameapp.R
import com.imploded.kotlingameapp.interfaces.OnItemCheckedListener
import com.imploded.kotlingameapp.model.FilterItem
import kotlinx.android.synthetic.main.row_filter.view.*

class FilterAdapter(private val filters: List<FilterItem>, private val onCheckedChangeListener: OnItemCheckedListener) : RecyclerView.Adapter<FilterAdapter.FilterHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_filter, parent, false)
        return FilterHolder(view)
    }

    override fun onBindViewHolder(holder: FilterHolder, position: Int) {
        holder.bindGame(filters[position], onCheckedChangeListener)
    }

    override fun getItemCount(): Int = filters.size

    class FilterHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindGame(filter: FilterItem, onCheckedChangeListener: OnItemCheckedListener) {
            with(filter) {
                itemView.platformCheckBox.isChecked = filter.checked
                itemView.platformCheckBox.text = filter.name
                itemView.platformCheckBox.setOnCheckedChangeListener{ _, checked ->
                    this.checked = checked
                    onCheckedChangeListener(this, checked)
                }
                itemView.countTextView.text = filter.count.toString()
            }
        }
    }
}
