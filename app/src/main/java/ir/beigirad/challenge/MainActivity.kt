package ir.beigirad.challenge

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.wada811.viewbindingktx.viewBinding
import ir.beigirad.challenge.databinding.ActivityMainBinding
import ir.beigirad.challenge.transferlist.TransferListFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val binding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.layoutDirection = View.LAYOUT_DIRECTION_RTL

        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction()
                .add(binding.container.id, TransferListFragment())
                .commit()
    }
}