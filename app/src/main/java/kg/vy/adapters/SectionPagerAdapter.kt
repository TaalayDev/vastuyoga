package kg.vy.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class SectionsPagerAdapter(fm: FragmentManager?, val pages: List<Fragment>) : FragmentPagerAdapter(fm!!) {

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        // Show 3 total pages.
        return pages.size
    }

}