package tw.edu.pu.unik

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_container.*


class ContainerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)

        bottomNavigationSetup()

    }

    fun bottomNavigationSetup() {

    val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
    val navController = navHostFragment.navController
    bottomNavigationView.setupWithNavController(navController)

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