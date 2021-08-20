package by.godevelopment.rsshool2021androidtaskstorage

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import by.godevelopment.rsshool2021androidtaskstorage.database.SqlBox
import by.godevelopment.rsshool2021androidtaskstorage.databinding.ActivityMainBinding
import by.godevelopment.rsshool2021androidtaskstorage.entity.Cat
import by.godevelopment.rsshool2021androidtaskstorage.entity.OrderType

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val destinationListener = NavController.OnDestinationChangedListener {
            _, _, _ -> setupFabAndToolbar() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

         setupComponents()
    }

    private fun setupComponents() {
        setSupportActionBar(binding.toolbar)
        setupNavigation()
        setupFabAndToolbar()
    }

    private fun setupNavigation() {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
        findNavController(R.id.nav_host_fragment_content_main).addOnDestinationChangedListener(destinationListener)
    }

    private fun setupFabAndToolbar() {

        val stringDestFirst = resources.getString(R.string.first_fragment_label)
        val stringDestSecond = resources.getString(R.string.second_fragment_label)
        val stringDestThird = resources.getString(R.string.third_fragment_label)

        when (findNavController(R.id.nav_host_fragment_content_main).currentDestination?.label.toString()) {
            stringDestFirst ->
            {
                binding.fab.setImageResource(android.R.drawable.ic_input_add)
                binding.fab.show()
                binding.toolbar.menu.setGroupVisible(R.id.main_menu_group, true)
                binding.fab.setOnClickListener {
                    findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.action_FirstFragment_to_SecondFragment)
                }
            }
            stringDestSecond ->
            {
                binding.fab.setImageResource(R.drawable.ic_arrow_back_24)
                binding.fab.show()
                binding.toolbar.menu.setGroupVisible(R.id.main_menu_group, false)
                binding.fab.setOnClickListener {
                    findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.action_SecondFragment_to_FirstFragment)
                }
            }
            stringDestThird -> {
                binding.fab.hide()
                binding.toolbar.menu.setGroupVisible(R.id.main_menu_group, false)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.custom_order -> {
                findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.action_FirstFragment_to_ThirdFragment)
                true
            }
            R.id.reset_order -> {
                SqlBox.orderList = OrderType.ID
                findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.action_FirstFragment_to_WaitFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        findNavController(R.id.nav_host_fragment_content_main).removeOnDestinationChangedListener(destinationListener)
        SqlBox.destroy()
    }
}