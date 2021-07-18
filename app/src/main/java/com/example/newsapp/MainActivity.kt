package com.example.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.example.newsapp.adapter.NewsListAdapter
import com.example.newsapp.adapter.ViewPagerAdapter
import com.example.newsapp.fragments.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

   lateinit var madapter : NewsListAdapter
    lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle =ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close)
        toggle.isDrawerIndicatorEnabled = true                         //it is used for showing up icon in the toolbar
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()                                             //it is telling if state is open or close

        setUpTabs()
        nav_view.setNavigationItemSelectedListener {

            // Switch Fragments in a ViewPager on clicking items in Navigation Drawer
            when(it.itemId){
                R.id.heatlh ->{ viewPager.setCurrentItem(0)
                    drawerLayout.closeDrawer(GravityCompat.START,true);
                }
                R.id.Sports ->{ viewPager.setCurrentItem(1)
                    drawerLayout.closeDrawer(GravityCompat.START,true);

                }
                R.id.Business ->{viewPager.setCurrentItem(2)
                    drawerLayout.closeDrawer(GravityCompat.START,true);

                }
                R.id.Science ->{viewPager.setCurrentItem(3)
                    drawerLayout.closeDrawer(GravityCompat.START,true);

                }
                R.id.Technology ->{viewPager.setCurrentItem(4)
                    drawerLayout.closeDrawer(GravityCompat.START,true);

                }
                R.id.entertainment ->{viewPager.setCurrentItem(5)
                    drawerLayout.closeDrawer(GravityCompat.START,true);

                }
            }
            true
        }
    }



    fun setUpTabs(){

        viewPagerAdapter= ViewPagerAdapter(supportFragmentManager)
        viewPagerAdapter.addFragment(HealthFragment(),"Health")
        viewPagerAdapter.addFragment(SportsFragment(),"Sports")
        viewPagerAdapter.addFragment(BusinessFragment(),"Business")
        viewPagerAdapter.addFragment(ScienceFragment(),"Science")
        viewPagerAdapter.addFragment(TechnologyFragment(),"Technology")
        viewPagerAdapter.addFragment(EntertainmentFragment(),"Entertainment")

        viewPager.adapter=viewPagerAdapter

        tabLayout.setupWithViewPager(viewPager)
    }

}