package id.yusufrizalh.mysampledashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    // (1) inisialisasi
    lateinit var mDrawerLayout: DrawerLayout
    lateinit var mNavigationView: NavigationView
    lateinit var mFragmentManager: FragmentManager
    lateinit var mFragmentTransaction: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // (2) mengenali komponen
        mDrawerLayout = findViewById<View>(R.id.drawerLayout) as DrawerLayout
        mNavigationView = findViewById<View>(R.id.navView) as NavigationView

        mFragmentManager = supportFragmentManager
        mFragmentTransaction = mFragmentManager.beginTransaction()
        mFragmentTransaction.replace(R.id.containerView, TabFragment()).commit()

        mNavigationView.setNavigationItemSelectedListener { menuItem ->
            mDrawerLayout.closeDrawers()

            if (menuItem.itemId == R.id.nav_item_inbox) {
                val ft = mFragmentManager.beginTransaction()
                ft.replace(R.id.containerView, TabFragment()).commit()
            }

            if (menuItem.itemId == R.id.nav_item_sent) {
                val ft = mFragmentManager.beginTransaction()
                ft.replace(R.id.containerView, SentFragment()).commit()
            }

            if (menuItem.itemId == R.id.nav_item_draft) {
                val ft = mFragmentManager.beginTransaction()
                ft.replace(R.id.containerView, DraftFragment()).commit()
            }

            if (menuItem.itemId == R.id.nav_item_shared_pref) {
                val ft = mFragmentManager.beginTransaction()
                ft.replace(R.id.containerView, SharedPrefsFragment()).commit()
            }

            false
        }

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        val mDrawerToggle = ActionBarDrawerToggle(
            this,
            mDrawerLayout,
            toolbar,
            R.string.app_name,
            R.string.app_name
        )

        mDrawerLayout.setDrawerListener(mDrawerToggle)
        mDrawerToggle.syncState()
    }
}