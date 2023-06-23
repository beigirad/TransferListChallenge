package ir.beigirad.challenge

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.wada811.viewbindingktx.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ir.beigirad.challenge.databinding.ActivityMainBinding
import ir.beigirad.challenge.transferlist.TransferListFragment
import java.util.Locale

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val binding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // lay out app in full-screen
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // TODO locale and layout direction setting must be handled in app startup or in a manager
        window.decorView.layoutDirection = View.LAYOUT_DIRECTION_RTL
        Locale.setDefault(Locale("fa", "IR"))

        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction()
                .add(binding.container.id, TransferListFragment())
                .commit()
    }
}