package freelancer.istanbul.movielist.ui

import dagger.hilt.android.AndroidEntryPoint
import freelancer.istanbul.movielist.databinding.ActivityMainBinding
import freelancer.istanbul.movielist.ui.base.BaseActivity

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

}