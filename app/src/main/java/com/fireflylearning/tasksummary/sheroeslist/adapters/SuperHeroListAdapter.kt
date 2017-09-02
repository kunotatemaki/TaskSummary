package com.fireflylearning.tasksummary.sheroeslist.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.fireflylearning.tasksummary.R
import com.fireflylearning.tasksummary.databinding.SuperheroItemBinding
import com.fireflylearning.tasksummary.dependencyinjection.scopes.CustomScopes
import com.fireflylearning.tasksummary.model.SuperHero
import com.fireflylearning.tasksummary.sheroeslist.presenters.SuperHeroListPresenter
import com.fireflylearning.tasksummary.sheroeslist.viewholders.SuperHeroListViewHolder
import com.fireflylearning.tasksummary.utils.ui.GlideBindingComponent
import javax.inject.Inject

/**
 * Created by Roll on 31/8/17.
 */
@CustomScopes.ActivityScope
class SuperHeroListAdapter @Inject constructor(val presenter: SuperHeroListPresenter):
        RecyclerView.Adapter<SuperHeroListViewHolder>() {

    val superHeroes: MutableList<SuperHero> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SuperHeroListViewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        val binding = DataBindingUtil.inflate<SuperheroItemBinding>(inflater, R.layout.superhero_item, parent,
                false, GlideBindingComponent())


        return SuperHeroListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SuperHeroListViewHolder?, position: Int) {
        val superHero: SuperHero = superHeroes[position]
        holder?.bind(superHero, presenter)
    }

    override fun getItemCount(): Int {
        return superHeroes.size
    }

}