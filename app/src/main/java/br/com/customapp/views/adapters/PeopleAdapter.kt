package br.com.customapp.views.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.customapp.R
import br.com.customapp.models.Event
import br.com.customapp.models.People
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.people_item.view.*

class PeopleAdapter(private var peopleItens: List<People>,
                    private val context: Context) : RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleAdapter.ViewHolder {
        return PeopleAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.people_item, parent, false))
    }

    override fun getItemCount(): Int {
        return peopleItens.size
    }

    override fun onBindViewHolder(holder: PeopleAdapter.ViewHolder, position: Int) {
        val people = peopleItens[position]

        holder.name.text = people.name
        Picasso.get().load(people.picture).resize(600,400).centerCrop().into(holder.picture)
    }

    fun updateList(statementItens: List<People>) {
        this.peopleItens = statementItens
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val picture: CircleImageView = view.picture
        val name: TextView = view.name
    }
}