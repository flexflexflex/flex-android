package uz.sesh.flex.flex.main.feed

import android.arch.lifecycle.Observer
import android.content.Context
import android.databinding.Observable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_flex_feed_item_list.*
import uz.sesh.flex.data.datasource.repositoryProviders.FlexRepositoryProvider
import uz.sesh.flex.domain.model.Flex
import uz.sesh.flex.flex.R

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [FlexListFragment.OnListFragmentInteractionListener] interface.
 */
class FlexListFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {
    override fun onRefresh() {

        getFeed(0)
    }

    // TODO: Customize parameters
    private var columnCount = 1

    private var listener: OnListFragmentInteractionListener? = null

    private var flexListViewModel: FlexListViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (flexListViewModel == null) {
            flexListViewModel = FlexListViewModel.Factory(FlexRepositoryProvider().provideFlexRepository(context!!)).create(FlexListViewModel::class.java)
            //flexListViewModel?.loadingStatus?.addOnPropertyChangedCallback(object :Observable.OnPropertyChangedCallback)
        }
        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }

    }

    private fun getFeed(offser: Int) {
        flexListViewModel?.getList(0)
                ?.observe(this, Observer {
                    if (list != null && it != null)
                        (list.adapter as MyFlexFeedItemRecyclerViewAdapter).updateEmployeeListItems(it)
                })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_flex_feed_item_list, container, false)

        // Set the adapter
        view.findViewById<SwipeRefreshLayout>(R.id.swipeRefreshlayout).setOnRefreshListener(this)

        with(view.findViewById<RecyclerView>(R.id.list)) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> {
                    setHasFixedSize(false)
                    StaggeredGridLayoutManager(columnCount, LinearLayoutManager.VERTICAL)
                }
            }
            adapter = MyFlexFeedItemRecyclerViewAdapter(listener, context)
        }


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getFeed(0)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson
     * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: Flex?)
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
                FlexListFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_COLUMN_COUNT, columnCount)
                    }
                }
    }
}
