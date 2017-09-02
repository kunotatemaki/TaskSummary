package com.fireflylearning.tasksummary.sherodetails.views

import android.arch.lifecycle.LifecycleObserver
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.MenuItem
import com.fireflylearning.tasksummary.utils.logger.LoggerHelper
import com.fireflylearning.tasksummary.FintonicApp
import com.fireflylearning.tasksummary.R
import com.fireflylearning.tasksummary.databinding.ActivitySuperHeroDetailBinding
import com.fireflylearning.tasksummary.dependencyinjection.modules.SuperHeroDetailModule
import com.fireflylearning.tasksummary.dependencyinjection.scopes.CustomScopes
import com.fireflylearning.tasksummary.model.SuperHero
import com.fireflylearning.tasksummary.sherodetails.lifecycleobservers.SuperHeroDetailLifecycleObserver
import com.fireflylearning.tasksummary.utils.FintonicConstants
import com.fireflylearning.tasksummary.utils.ui.BaseActivity
import com.fireflylearning.tasksummary.utils.ui.GlideBindingComponent
import kotlinx.android.synthetic.main.activity_super_hero_detail.*
import javax.inject.Inject

@CustomScopes.ActivityScope
class SuperHeroDetailActivity : BaseActivity(), SuperHeroDetailView {

    @Inject
    lateinit var log: LoggerHelper


    lateinit var mBinding: ActivitySuperHeroDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //dependency injection
        (application as FintonicApp).mComponent
                .getSuperHeroDetailSubcomponent(SuperHeroDetailModule(this)).inject(this)

        //databinding
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_super_hero_detail, GlideBindingComponent())
        val superHero = intent.getParcelableExtra<SuperHero>(FintonicConstants.SUPERHERO)
        mBinding.superhero = superHero
        mBinding.superheroDetailsContainer?.superhero = superHero
        setToolbar(toolbar, true)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home -> {
                supportFinishAfterTransition()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun addLifecycleObserver(observer: SuperHeroDetailLifecycleObserver) {
        if(observer is LifecycleObserver){
            lifecycle.addObserver(observer)
        }
    }
}
