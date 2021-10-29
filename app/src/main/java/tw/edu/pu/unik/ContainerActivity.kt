package tw.edu.pu.unik

import android.Manifest
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import tw.edu.pu.unik.ui.home.LiveStream

class ContainerActivity : AppCompatActivity() {
    lateinit var navHost: NavHostFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        bottomNavigationSetup()
    }

    private fun bottomNavigationSetup() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navHost = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHost.navController
        bottomNavigationView.setupWithNavController(navController)
    }


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return if(navHost.childFragmentManager.fragments[0] is LiveStream) {
            val f = navHost.childFragmentManager.fragments[0] as LiveStream
            if (ev != null) {
                f.fragmentTouch(ev)
            }else
                super.dispatchTouchEvent(ev)
        }else
            super.dispatchTouchEvent(ev)
    }

    fun touch(ev: MotionEvent?): Boolean {
        return super.dispatchTouchEvent(ev)
    }
        /*bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
               /* R.id.homeActivity -> {
                    val intent = Intent(this,HomeActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(0,0)
                    return@setOnItemSelectedListener true
                }*/
                R.id.searchFragment -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView,SearchFragment())
                        .commit()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }

    }*/

}