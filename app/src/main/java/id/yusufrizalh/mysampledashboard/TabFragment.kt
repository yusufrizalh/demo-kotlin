package id.yusufrizalh.mysampledashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.tab_layout.*
import java.lang.IllegalStateException

class TabFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val x = inflater.inflate(R.layout.tab_layout, null)
        tabLayout = x.findViewById<View>(R.id.tabs) as TabLayout
        viewPager = x.findViewById<View>(R.id.viewpager) as ViewPager

        viewPager.adapter = MyAdapter(childFragmentManager)
        tabLayout.post { tabLayout.setupWithViewPager(viewPager) }

        return x
    }

    internal inner class MyAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            //var fragment: Fragment? = null
            when (position) {
                0 -> {
                    return FirstFragment()
                }
                1 -> {
                    return SecondFragment()
                }
                2 -> {
                    return ThirdFragment()
                }
                else -> {
                    throw IllegalStateException("$position is illegal")
                }
            }
        }

        override fun getCount(): Int {
            return int_items
        }

        override fun getPageTitle(position: Int): CharSequence? {
            when (position) {
                0 -> return "First"
                1 -> return "Second"
                2 -> return "Third"
            }
            return null
        }

    }

    companion object {
        lateinit var tabLayout: TabLayout
        lateinit var viewPager: ViewPager
        var int_items = 3
    }
}