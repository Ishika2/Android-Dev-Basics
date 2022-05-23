package com.example.affirmations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.affirmations.adapter.ItemAdapter
import com.example.affirmations.data.Datasource

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //initialize data
        val myDataset = Datasource().loadAffirmations()
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
//        val card =
//        card.setOnLongClickListener
//        {
//            card.setChecked(!card.ischecked)
//            true
//        }
//        val textView: TextView = findViewById(R.id.textview)
        recyclerView.adapter = ItemAdapter(this, myDataset)
        //use this setting to improve performance it you know that changes in content do not change in the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true)
//        val textView: TextView = findViewById(R.id.textview)
//        textView.text = Datasource().loadAffirmations().size.toString()
    }
}