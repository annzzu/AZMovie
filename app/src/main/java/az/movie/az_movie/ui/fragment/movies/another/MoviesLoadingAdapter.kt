package az.movie.az_movie.ui.fragment.movies.another

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import az.movie.az_movie.R
import az.movie.az_movie.databinding.LayoutLoadingBinding


class MoviesLoadingAdapter(
    private val adapter: MoviesPagingAdapter
) : LoadStateAdapter<MoviesLoadingAdapter.NetworkStateItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) =
        NetworkStateItemViewHolder(
            LayoutLoadingBinding.bind(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_loading, parent, false)
            )
        ) { adapter.retry() }

    override fun onBindViewHolder(holder: NetworkStateItemViewHolder , loadState: LoadState) =
        holder.bind(loadState)

    class NetworkStateItemViewHolder(
        private val binding: LayoutLoadingBinding,
        private val retryCallback: () -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.retryButton.setOnClickListener {
                retryCallback?.invoke()
            }
        }

        fun bind(loadState: LoadState) {
            binding.progressBar.isVisible = loadState is LoadState.Loading
            binding.retryButton.isVisible = loadState is LoadState.Error
            binding.errorMsg.isVisible =
                !(loadState as? LoadState.Error)?.error?.message.isNullOrBlank()
            binding.errorMsg.text = (loadState as? LoadState.Error)?.error?.message
        }
    }
}