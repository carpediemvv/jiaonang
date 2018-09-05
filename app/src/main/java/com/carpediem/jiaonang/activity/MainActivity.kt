package com.carpediem.jiaonang.activity

import android.net.Uri
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import com.carpediem.jiaonang.R
import com.carpediem.jiaonang.fragment.GameFragment
import com.carpediem.jiaonang.fragment.HomeFragment
import com.carpediem.jiaonang.fragment.MineFragment
import com.carpediem.jiaonang.fragment.dummy.DummyContent
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), HomeFragment.OnListFragmentInteractionListener, GameFragment.OnListFragmentInteractionListener, MineFragment.OnFragmentInteractionListener {

    var textFragment: Array<String> = arrayOf("HomeFragment", "GameFragment", "MineFragment")
    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onListFragmentInteraction(item: DummyContent.DummyItem?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                showFragment(R.id.mainContent, HomeFragment(), textFragment[0])
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                showFragment(R.id.mainContent, GameFragment(), textFragment[1])
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                showFragment(R.id.mainContent, MineFragment(), textFragment[2])
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        showFragment(R.id.mainContent, HomeFragment(), textFragment[0])
    }


    private fun AppCompatActivity.addFragment(frameId: Int, fragment: Fragment, fragmentTag: String) {
        supportFragmentManager.inTransaction { add(frameId, fragment) }
    }


    private fun AppCompatActivity.replaceFragment(frameId: Int, fragment: Fragment, fragmentTag: String) {
        supportFragmentManager.inTransaction { replace(frameId, fragment) }
    }

    private fun AppCompatActivity.showFragment(frameId: Int, fragment: Fragment, fragmentTag: String) {

        for (item in textFragment) {
            if (!fragmentTag.equals(item)) {
                if (supportFragmentManager.findFragmentByTag(item) != null)
                    supportFragmentManager.inTransaction { hide(supportFragmentManager.findFragmentByTag(item)) }
            }
        }
        if (supportFragmentManager.findFragmentByTag(fragmentTag) != null) {
            supportFragmentManager.inTransaction { show(supportFragmentManager.findFragmentByTag(fragmentTag)) }
        } else {
            supportFragmentManager.inTransaction { add(frameId, fragment, fragmentTag) }
        }
    }

    private inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
        beginTransaction().func().commit()
    }
}
