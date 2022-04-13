package id.idzhami.navigationdrawerkotlin

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.idzhami.navigationdrawerkotlin.navDrawer.ClickListener
import id.idzhami.navigationdrawerkotlin.navDrawer.NavigationItemModel
import id.idzhami.navigationdrawerkotlin.navDrawer.NavigationRVAdapter
import id.idzhami.navigationdrawerkotlin.navDrawer.RecyclerTouchListener

class MainActivity : AppCompatActivity() {
    lateinit var drawerLayout: DrawerLayout
    private lateinit var adapter: NavigationRVAdapter
    private var items = arrayListOf(
        NavigationItemModel(R.drawable.ic_lock, "Change Password"),
        NavigationItemModel(R.drawable.ic_phone, "Customer service"),
        NavigationItemModel(R.drawable.ic_users, "Customer Guide"),
        NavigationItemModel(R.drawable.ic_log_out, "Logout"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navDrawer()
    }

    private fun navDrawer() {
        val rootContainer = findViewById<DrawerLayout>(R.id.rootContainer)
        val navigationRv = findViewById<RecyclerView>(R.id.navigation_rv)
        val activityMainToolbar = findViewById<Toolbar>(R.id.activity_main_toolbar)
        drawerLayout = rootContainer
        navigationRv.layoutManager = LinearLayoutManager(this)
        navigationRv.setHasFixedSize(true)

        // Add Item Touch Listener
        navigationRv.addOnItemTouchListener(
            RecyclerTouchListener(
                this,
                object : ClickListener {
                    override fun onClick(view: View, position: Int) {
                        when (position) {
                            0 -> {
                                Toast.makeText(
                                    applicationContext,
                                    "Change Password",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            1 -> {
                                Toast.makeText(
                                    applicationContext,
                                    "Customer Service",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            2 -> {
                                Toast.makeText(
                                    applicationContext,
                                    "Customer Guide",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            3 -> {
                                Toast.makeText(applicationContext, "Logout", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }

                        if (position != 6 && position != 4) {
                            updateAdapter(position)
                        }
                        Handler().postDelayed({
                            drawerLayout.closeDrawer(GravityCompat.START)
                        }, 200)
                    }
                })
        )

        updateAdapter(0)

        // Set 'Home' as the default fragment when the app starts
        val bundle = Bundle()
        bundle.putString("fragmentName", "Home Fragment")

        val toggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this,
            drawerLayout,
            activityMainToolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        ) {
            override fun onDrawerClosed(drawerView: View) {
                // Triggered once the drawer closes
                super.onDrawerClosed(drawerView)
                try {
                    val inputMethodManager =
                        this@MainActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    val v = (this@MainActivity as Activity).currentFocus ?: return
                    inputMethodManager.hideSoftInputFromWindow(v.windowToken, 0)

                } catch (e: Exception) {
                    e.stackTrace
                }
            }

            override fun onDrawerOpened(drawerView: View) {
                // Triggered once the drawer opens
                super.onDrawerOpened(drawerView)
                try {
                    val inputMethodManager =
                        this@MainActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    val v = (this@MainActivity as Activity).currentFocus ?: return
                    inputMethodManager.hideSoftInputFromWindow(v.windowToken, 0)
                } catch (e: Exception) {
                    e.stackTrace
                }
            }
        }
        drawerLayout.addDrawerListener(toggle)

        toggle.syncState()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateAdapter(highlightItemPos: Int) {
        val navigationRv = findViewById<RecyclerView>(R.id.navigation_rv)
        adapter = NavigationRVAdapter(items, highlightItemPos)
        navigationRv.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}