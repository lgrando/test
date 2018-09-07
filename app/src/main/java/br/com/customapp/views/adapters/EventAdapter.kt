package br.com.customapp.views.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import br.com.customapp.R
import br.com.customapp.models.Event
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.event_item.view.*

/**
 * Created by lucas on 06/09/18.
 */
class EventAdapter(private var eventItens: List<Event>,
                   private val context: Context,
                   private val listener: (Int) -> Unit) : RecyclerView.Adapter<EventAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.event_item, parent, false))
    }

    override fun getItemCount(): Int {
        return eventItens.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = eventItens[position]

        Picasso.get().load(event.image).into(holder.image)
        holder.title.text = event.title
        holder.view.setOnClickListener {
            listener(position)
        }
    }

    fun updateList(statementItens: List<Event>) {
        this.eventItens = statementItens
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.image
        val title: TextView = view.title
        val view: LinearLayout = view.itemView
    }
}