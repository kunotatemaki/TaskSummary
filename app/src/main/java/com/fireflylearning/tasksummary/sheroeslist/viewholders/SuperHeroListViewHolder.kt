package com.fireflylearning.tasksummary.sheroeslist.viewholders

import android.support.v7.widget.RecyclerView
import com.fireflylearning.tasksummary.databinding.SuperheroItemBinding
import com.fireflylearning.tasksummary.model.SuperHero
import com.fireflylearning.tasksummary.sheroeslist.presenters.SuperHeroListPresenter

/**
 * Created by Roll on 31/8/17.
 */
class SuperHeroListViewHolder (val binding: SuperheroItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(superhero: SuperHero, presenter: SuperHeroListPresenter) {
        binding.superhero = superhero
        binding.presenter = presenter
        binding.executePendingBindings()
    }
}