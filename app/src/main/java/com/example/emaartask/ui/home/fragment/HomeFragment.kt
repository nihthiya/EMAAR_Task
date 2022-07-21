package com.example.emaartask.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.emaartask.R
import com.example.emaartask.databinding.FragmentHomeBinding
import com.example.emaartask.ui.home.activity.HomeActivity
import com.example.emaartask.ui.home.adapter.UserListAdapter
import com.example.emaartask.ui.home.viewModel.UserListViewModel
import com.example.emaartask.ui.userDetails.activity.UserDetailsActivity
import com.example.emaartask.utils.PaginateListener
import com.example.emaartask.utils.Utils
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var userListBinding: FragmentHomeBinding
    private val userListViewModel: UserListViewModel by viewModels()
    private lateinit var paginateListener: PaginateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        userListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return userListBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        userListBinding.lifecycleOwner = this
        userListBinding.userListViewModel = userListViewModel

        val layoutManager = LinearLayoutManager(requireContext())
        userListBinding.rvUserList.layoutManager = layoutManager

        paginateListener = PaginateListener(layoutManager) { currentPage, _, _ ->
            userListBinding.loadMoreLayout.visibility = View.VISIBLE
            userListViewModel.getAllUsers(requireContext(), currentPage)
        }
        userListBinding.rvUserList.addOnScrollListener(paginateListener)
        userListViewModel.getAllUsers(requireContext(), 1)
        userListBinding.shimmerLayout.visibility = VISIBLE
        userListBinding.rvUserList.visibility = GONE
        userListViewModel.localUserData.observe(this.viewLifecycleOwner) { userList ->

             userListBinding.rvUserList.also { rv ->
                    val previousItemCount = 10

                    rv.adapter = UserListAdapter(
                        requireContext(),
                        userList
                    )
                 userListBinding.shimmerLayout.visibility = GONE
                 userListBinding.rvUserList.visibility = VISIBLE

//                    rv.adapter!!.notifyItemRangeInserted(
//                        previousItemCount,
//                        previousItemCount + userList.results!!.count()
//                    )
                    userListBinding.loadMoreLayout.visibility = View.GONE
                    rv.adapter!!.notifyDataSetChanged()
//                    previousItemCount = rv.adapter!!.itemCount
//                    rv.adapter!!.notifyItemRangeInserted(previousItemCount,previousItemCount + productList.data.count())
//                    userListBinding.loadMoreLayout.visibility = View.GONE

                }

//            }


        }

    }

}