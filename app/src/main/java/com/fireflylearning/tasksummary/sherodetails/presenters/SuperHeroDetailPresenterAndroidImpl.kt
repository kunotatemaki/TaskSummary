package com.fireflylearning.tasksummary.sherodetails.presenters

import com.fireflylearning.tasksummary.dependencyinjection.scopes.CustomScopes
import com.fireflylearning.tasksummary.sherodetails.views.SuperHeroDetailView
import java.lang.ref.WeakReference
import javax.inject.Inject

/**
 * Created by Roll on 1/9/17.
 */
@CustomScopes.ActivityScope
class SuperHeroDetailPresenterAndroidImpl @Inject constructor(val mView: WeakReference<SuperHeroDetailView>): SuperHeroDetailPresenter {

}