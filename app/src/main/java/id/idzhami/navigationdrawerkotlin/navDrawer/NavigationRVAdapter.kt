package id.idzhami.navigationdrawerkotlin.navDrawer

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import id.idzhami.navigationdrawerkotlin.R

/**
 * Created by Idzhami on 13/04/2022
 */

class NavigationRVAdapter(private var items: ArrayList<NavigationItemModel>, private var currentPos: Int) :
    RecyclerView.Adapter<NavigationRVAdapter.NavigationItemViewHolder>() {

    private lateinit var context: Context

    class NavigationItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavigationItemViewHolder {
        context = parent.context
        val navItem = LayoutInflater.from(parent.context).inflate(R.layout.row_nav_drawer, parent, false)
        return NavigationItemViewHolder(navItem)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: NavigationItemViewHolder, position: Int) {
        // To highlight the selected item, show different background color
        if (position == currentPos) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark))
        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
        }
        val navigation_icon : ImageView
        val navigation_title: TextView
        navigation_icon = holder.itemView.findViewById(R.id.navigation_icon) as ImageView
        navigation_title = holder.itemView.findViewById(R.id.navigation_title) as TextView

        navigation_icon.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
        navigation_title.setTextColor(Color.WHITE)

        navigation_title.text = items[position].title

        navigation_icon.setImageResource(items[position].icon)
    }

}