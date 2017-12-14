package com.imploded.kotlingameapp.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.imploded.kotlingameapp.R
import com.imploded.kotlingameapp.model.FilterItem
import kotlinx.android.synthetic.main.row_filter.view.*

class FilterAdapter(private val filters: List<FilterItem>, private val checkedChangedFunc: (item: FilterItem, checked: Boolean) -> Unit) : RecyclerView.Adapter<FilterAdapter.FilterHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_filter, parent, false)
        return FilterHolder(view)
    }

    override fun onBindViewHolder(holder: FilterHolder, position: Int) {
        holder.bindGame(filters[position], checkedChangedFunc)
    }

    override fun getItemCount(): Int = filters.size

    class FilterHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindGame(filter: FilterItem, checkedChangedFunc: (item: FilterItem, checked: Boolean) -> Unit) {
            with(filter) {
                itemView.platformCheckBox.isChecked = checked
                itemView.platformCheckBox.text = name
                itemView.platformCheckBox.setOnCheckedChangeListener{ _, checked ->
                    this.checked = checked
                    checkedChangedFunc(this, checked)
                }
                itemView.countTextView.text = filter.count.toString()
            }
        }
    }
}
