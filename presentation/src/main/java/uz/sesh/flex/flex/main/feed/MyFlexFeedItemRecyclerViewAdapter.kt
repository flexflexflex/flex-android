package uz.sesh.flex.flex.main.feed

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import uz.sesh.flex.flex.R


import uz.sesh.flex.flex.main.feed.FlexListFragment.OnListFragmentInteractionListener

import kotlinx.android.synthetic.main.fragment_flex_feed_item.view.*
import uz.sesh.flex.domain.model.Flex
import android.support.v7.widget.StaggeredGridLayoutManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import uz.sesh.flex.flex.main.FlexDetailActivity
import java.util.*


/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyFlexFeedItemRecyclerViewAdapter(
        private val mListener: OnListFragmentInteractionListener?, var context: Context)
    : RecyclerView.Adapter<MyFlexFeedItemRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener
    private val mFlexes: ArrayList<Flex> = ArrayList()

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Flex
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    fun addAll(flexes: ArrayList<Flex>) {
        if (itemCount == 0) {
            mFlexes.addAll(flexes)
            notifyDataSetChanged()
        } else {
            val beforeCount = itemCount
            mFlexes.addAll(flexes)
            notifyItemRangeChanged(beforeCount, mFlexes.size)
        }
    }

    fun clearAll() {
        if (itemCount != 0) {
            mFlexes.clear()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_flex_feed_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var imageUrl: String = if (position % 2 == 0) {
            "https://pp.userapi.com/c846121/v846121556/7694e/EOTTrGlJTks.jpg"
        } else {
            "https://pp.userapi.com/c845218/v845218610/59fe7/nRrwz1HfEIw.jpg"
        }
        val item = mFlexes[position]
        holder.mIdView.text = item.title
        holder.mContentView.text =
                "${item.description}\n" +
                "friends count ${item.friendsCount}\n" +
                "members count ${item.membersCount}\n"
        holder.mContentView.visibility = View.GONE
        Glide.with(context).load(item.image)
                .into(holder.mIvFeedItem)
        with(holder.mView) {
            tag = item
            setOnClickListener({
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context as Activity, holder.mIvFeedItem, holder.mIvFeedItem.transitionName).toBundle()
                var intent = Intent(context, FlexDetailActivity::class.java)
                intent.putExtra("image", item.image)
                intent.putExtra("data",item)
                context.startActivity(intent, options)
            })
        }
    }

    override fun getItemCount(): Int = mFlexes.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.item_number
        val mContentView: TextView = mView.content
        val mIvFeedItem: ImageView = mView.ivFeedItem

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
