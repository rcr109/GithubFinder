package rcr.projects.mygitreps.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.SearchView
import org.koin.androidx.viewmodel.ext.android.viewModel
import rcr.projects.mygitreps.R
import rcr.projects.mygitreps.core.createDialog
import rcr.projects.mygitreps.core.createProgressDialog
import rcr.projects.mygitreps.core.hideSoftKeyboard
import rcr.projects.mygitreps.data.model.Repo
import rcr.projects.mygitreps.databinding.ActivityMainBinding
import rcr.projects.mygitreps.presentation.MainViewModel

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private val dialog by lazy { createProgressDialog() }
    private val viewModel by viewModel<MainViewModel>()
    private val adapter by lazy { RepoListAdapter() }
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var atual : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.rvRepos.adapter = adapter
        binding.fabErase.setOnClickListener {
            clearList()
        }
        binding.fabErase.visibility = View.GONE
        setSupportActionBar(binding.toolbar)
        viewModel.repos.observe(this){
            when(it){
                is MainViewModel.State.Error -> {
                    createDialog {
                        setMessage(it.error.message)
                    }.show()
                    dialog.dismiss()
                }
                MainViewModel.State.Loading -> dialog.show()
                is MainViewModel.State.Success -> {
                    dialog.dismiss()
                    if (!it.list.isEmpty()) {
                        binding.tvTitle.setText(atual)
                    } else{
                        binding.tvTitle.setText("")
                    }
                    adapter.submitList(it.list)
                    binding.includeEmpty.emptyState.visibility = if (it.list.isEmpty()) View.VISIBLE
                    else View.GONE
                    binding.fabErase.visibility = if (it.list.isEmpty()) View.GONE
                    else View.VISIBLE
                }
            }
        }
    }


    fun clearList(){
        val list = listOf<Repo>()
        adapter.submitList(list)
        binding.tvTitle.setText("")
        binding.fabErase.visibility = View.GONE
        binding.includeEmpty.emptyState.visibility = if (list.isEmpty()) View.VISIBLE
        else View.GONE
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let { viewModel.getRepoList(it) }
        binding.root.hideSoftKeyboard()

        Log.e(TAG, "onQueryTextSubmit: $query")
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        Log.e(TAG, "onQUeryTextChange: $newText")
        atual = "Proprietário:  ${newText.toString()}"
        return true
    }

    companion object{
        private const val TAG ="TAG"
    }





}