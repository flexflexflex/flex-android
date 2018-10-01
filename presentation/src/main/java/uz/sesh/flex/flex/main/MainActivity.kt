package uz.sesh.flex.flex.main

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import uz.sesh.flex.data.datasource.InvalidAuthLiveData
import uz.sesh.flex.data.datasource.PreferenceManager
import uz.sesh.flex.domain.model.Flex
import uz.sesh.flex.flex.R
import uz.sesh.flex.flex.splash.SplashActivity
import uz.sesh.flex.flex.main.feed.FlexListFragment

class MainActivity : AppCompatActivity(), FlexListFragment.OnListFragmentInteractionListener {
    override fun onListFragmentInteraction(item: Flex?) {

    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                loadFragment(FlexListFragment.newInstance(2))
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                loadFragment(FlexListFragment.newInstance(2))
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {
                loadFragment(FlexListFragment.newInstance(2))
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        loadFragment(FlexListFragment.newInstance(2))
        InvalidAuthLiveData.getAuthErrorObservable()?.observe(this, Observer {
            it?.let {
                if (it == InvalidAuthLiveData.EVENT.logout) {
                    logout()
                    InvalidAuthLiveData.clearData()
                }
            }
        })
    }
    public fun logout(){
        PreferenceManager(this).clearToken()
        var intent  = Intent(this, SplashActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finish()
    }
    private fun loadFragment(fragment: Fragment?): Boolean {
        //switching fragment
        if (fragment != null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, fragment)
                    .commit()
            return true
        }
        return false
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home -> {
                super.onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
