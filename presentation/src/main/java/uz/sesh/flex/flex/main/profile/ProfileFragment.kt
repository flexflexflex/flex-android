package uz.sesh.flex.flex.main.profile

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_profile.*
import uz.sesh.flex.data.datasource.repositoryProviders.UserRepositoryProvider

import uz.sesh.flex.flex.R

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ProfileFragment : Fragment() {

    private var viewModel: ProfileFragmentViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (viewModel == null)
            viewModel = ProfileFragmentViewModel.Factory(UserRepositoryProvider().provideUserRepository(context!!)).create(ProfileFragmentViewModel::class.java)
        viewModel?.getUser()?.observe(this, Observer {
            tvUsername.text = "@${it?.username}"
            tvPhoneNumber.text = "+${it?.phone}"
            tvName.text = it?.firstName
            tvBio.text = it?.bio
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onDetach() {
        super.onDetach()
    }

    companion object {
        @JvmStatic
        fun newInstance() = ProfileFragment()
    }
}
