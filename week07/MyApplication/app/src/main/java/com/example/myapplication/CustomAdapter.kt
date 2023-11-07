package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class CustomAdapter(private val profiles: List<Profile>, private val context: Context) : BaseAdapter() {

    override fun getCount(): Int {
        return profiles.size
    }

    override fun getItem(position: Int): Any {
        return profiles[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val profile = profiles[position]

        var view = convertView
        if (view == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.item_layout, null)
        }

        val nameTextView = view?.findViewById(R.id.text1) as TextView
        val ageTextView = view.findViewById(R.id.text2) as TextView

        nameTextView.text = profile.name
        ageTextView.text = profile.age

        return view
    }
}
