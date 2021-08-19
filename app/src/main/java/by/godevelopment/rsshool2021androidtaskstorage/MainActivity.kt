package by.godevelopment.rsshool2021androidtaskstorage

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import by.godevelopment.rsshool2021androidtaskstorage.databinding.ActivityMainBinding
import by.godevelopment.rsshool2021androidtaskstorage.entity.Cat

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    // private lateinit var catProducer: CatProducerWithSql

    private val destinationListener =
        NavController.OnDestinationChangedListener { _, _, _ -> setupGoToButton() }

    private lateinit var dataList: List<Cat>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

         setupComponents()
    }

    private fun setupComponents() {
        setSupportActionBar(binding.toolbar)

        setupNavigation()
        setupGoToButton()
        setupDataBase()
    }

    private fun setupNavigation() {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        findNavController(R.id.nav_host_fragment_content_main).addOnDestinationChangedListener(destinationListener)
    }

    private fun setupDataBase() {
        // catProducer = CatProducerWithSql(applicationContext)
        // CatProvider.setCatsList(catProducer.getListOfCats())

    }

    private fun setupGoToButton() {

        val stringDestFirst = resources.getString(R.string.first_fragment_label)
        val stringDestSecond = resources.getString(R.string.second_fragment_label)
        val label = findNavController(R.id.nav_host_fragment_content_main).currentDestination?.label.toString()

        if ( label == stringDestFirst) {
            binding.fab.setImageResource(android.R.drawable.ic_input_add)
            binding.fab.setOnClickListener { _ ->
                findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.action_FirstFragment_to_SecondFragment)
            }
        }
        if (label == stringDestSecond) {

            binding.fab.setImageResource(R.drawable.ic_arrow_back_24)
            binding.fab.setOnClickListener { _ ->
                findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.action_SecondFragment_to_FirstFragment)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        // TODO "Настроить в других фрагментах"
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
                Toast.makeText(
                    this,
                    "Сброс фильтра. Перегрузка листа с данными", Toast.LENGTH_SHORT
                ).show()
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
        // catProducer.helperClose()
    }
}