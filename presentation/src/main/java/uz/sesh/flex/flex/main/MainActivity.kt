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
import uz.sesh.flex.flex.main.profile.ProfileFragment
import android.R.attr.tag



class MainActivity : AppCompatActivity(), FlexListFragment.OnListFragmentInteractionListener {
    override fun onListFragmentInteraction(item: Flex?) {

    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                changeFragment(FlexListFragment.newInstance(2), "home")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_feed -> {
                changeFragment(FlexListFragment.newInstance(2), "feed")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {
                changeFragment(ProfileFragment.newInstance(), "profile")
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        changeFragment(FlexListFragment.newInstance(2), "feed")
        InvalidAuthLiveData.getAuthErrorObservable()?.observe(this, Observer {
            it?.let {
                if (it == InvalidAuthLiveData.EVENT.logout) {
                    logout()
                    InvalidAuthLiveData.clearData()
                }
            }
        })
    }

    public fun logout() {
        PreferenceManager(this).clearToken()
        var intent = Intent(this, SplashActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finish()
    }

    private fun loadFragment(fragment: Fragment?, tag: String): Boolean {
        //switching fragment

        if (supportFragmentManager.findFragmentByTag(tag) != null) {
            var findedFragment = supportFragmentManager.findFragmentByTag(tag)

            if (findedFragment?.isAdded!!){
                return false
            }
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, findedFragment!!, tag)
                    .commit()
        } else {
            if (fragment != null)
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.main_container, fragment, tag)
                        .commit()
        }
        return true
    }

    private fun changeFragment(myFragment: Fragment?,tag: String){
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        val curFrag = supportFragmentManager.getPrimaryNavigationFragment()
        if (curFrag != null) {
            fragmentTransaction.detach(curFrag)
        }

        var fragment = supportFragmentManager.findFragmentByTag(tag)
        if (fragment == null) {
            fragment = myFragment
            fragmentTransaction.add(container.id, fragment!!, tag)
        } else {
            fragmentTransaction.attach(fragment)
        }

        fragmentTransaction.setPrimaryNavigationFragment(fragment)
        fragmentTransaction.setReorderingAllowed(true)
        fragmentTransaction.commitNowAllowingStateLoss()
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                super.onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
