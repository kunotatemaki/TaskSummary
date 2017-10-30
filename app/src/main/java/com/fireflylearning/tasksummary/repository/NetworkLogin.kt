package com.fireflylearning.tasksummary.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.support.annotation.MainThread
import com.fireflylearning.tasksummary.R
import com.fireflylearning.tasksummary.network.FireflyServiceFactory
import com.fireflylearning.tasksummary.utils.FireflyConstants
import com.fireflylearning.tasksummary.utils.preferences.PreferencesManager
import com.fireflylearning.tasksummary.vo.Resource
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import com.rukiasoft.utilslibrary.resources.ResourcesManager
/**
 * Created by Roll on 4/10/17.
 * A generic class that can provide a login response from the network.
 * <p>
 * You can read more about it in the <a href="https://developer.android.com/arch">Architecture
 * Guide</a>.
 * @param <ResultType>
 * @param <RequestType>
 */

class NetworkLogin @MainThread @Inject
internal constructor(private val fireflyServiceFactory: FireflyServiceFactory,
                     private val preferencesManager: PreferencesManager,
                     private val resourcesManager: ResourcesManager
                     ) {

    private val result: MutableLiveData<Resource<Boolean>> = MutableLiveData()


    fun login(host: String, token: String, remainLogged: Boolean) {
        result.value = Resource.loading(null)

        val myCall = createCall(host, token)

        myCall.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                if (response?.isSuccessful as Boolean) {
                    val stringResponse: String? = response.body()?.string()
                    stringResponse?.let {
                        if(stringResponse == "OK"){
                            result.value = Resource.success(true)
                            if(remainLogged) {
                                preferencesManager.setHost(host)
                                preferencesManager.setSecretToken(token)
                            }
                            return
                        }
                    }
                    result.value = Resource.error(resourcesManager.getString(R.string.error_invalid_host), null)
                } else {
                    when(response.code()) {
                        401 -> {
                            result.value = Resource.error(resourcesManager.getString(R.string.error_invalid_token), null)
                        }
                        else -> {
                            result.value = Resource.error(resourcesManager.getString(R.string.error_invalid_host), null)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                result.value = Resource.error(resourcesManager.getString(R.string.error_internet_connection), null)
            }
        })

    }

    @MainThread
    private fun createCall(host: String, token: String): Call<ResponseBody>{
        return fireflyServiceFactory.getFireflyService(host, false).loginOld(FireflyConstants.DEVICE_ID, token)
    }

    fun asLiveData(): LiveData<Resource<Boolean>> {
        return result
    }

    fun resetLiveData(){
        result.value = null
    }


}