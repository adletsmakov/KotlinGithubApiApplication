package com.kotlingithubapiapplication.activity.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlingithubapiapplication.R
import com.kotlingithubapiapplication.activity.model.Repository
import kotlinx.android.synthetic.main.item.view.*

class RepositoriesAdapter(
    private val context: Context,
    private val list: List<Repository>
) : RecyclerView.Adapter<RepositoriesAdapter.ReposViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposViewHolder {
        val rootView = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        return ReposViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {
        with(holder) {
            name.setText(context.getString(R.string.name_string) + " " + list.get(position).username.toString())
            forks.setText(context.getString(R.string.forks_string) + " " + list.get(position).forks.toString())
            watchers.setText(context.getString(R.string.watchers_string) + " " + list.get(position).watchers.toString())
            language.setText(context.getString(R.string.lang_string) + " " + list.get(position).language.toString())
        }

    }

    inner class ReposViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var name = itemView.tvName
        var forks = itemView.tv_forks
        var watchers = itemView.tv_watchers
        var language = itemView.tv_language
    }
}