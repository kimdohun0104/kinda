package dohun.kim.kinda.hilt_retrofit_test.github

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dohun.kim.kinda.hilt_retrofit_test.R
import dohun.kim.kinda.hilt_retrofit_test.github.adapter.UserListAdapter
import dohun.kim.kinda.kinda_android.KindaActivity
import kotlinx.android.synthetic.main.activity_github.*

@AndroidEntryPoint
class GithubActivity : KindaActivity<GithubState, GithubEvent, GithubSideEffect>() {

    override val viewModel: GithubViewModel by viewModels()

    private val userListAdapter = UserListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_github)

        viewModel.intent(GithubEvent.AttemptGetUsers)

        rv_user.adapter = userListAdapter
    }

    override fun render(state: GithubState) {
        pb_loading.visibility = if (state.isLoading) View.VISIBLE else View.INVISIBLE

        userListAdapter.submitList(state.users)

        state.toastEvent.getData()?.let { strRes ->
            Toast.makeText(this, strRes, Toast.LENGTH_SHORT).show()
        }
    }
}
