package br.com.customapp.views.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import br.com.customapp.models.EventItem
import br.com.customapp.R
import kotlinx.android.synthetic.main.event_item.view.*

/**
 * Created by lucas on 06/09/18.
 */
class EventAdapter(private var eventItens: List<EventItem>,
                   private val context: Context,
                   val listener: (Int) -> Unit) : RecyclerView.Adapter<EventAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.event_item, parent, false))
    }

    override fun getItemCount(): Int {
        return eventItens?.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = eventItens[position]

        holder.name.text = event.name
        holder.description.text = event.description
        holder.view.setOnClickListener({
            listener(position)
        })
    }

    fun updateList(statementItens: List<EventItem>) {
        this.eventItens = statementItens
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val view: LinearLayout = view.itemView
        val name: TextView = view.name
        val description: TextView = view.description
    }
}