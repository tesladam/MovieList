package freelancer.istanbul.movielist.ui.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseRecyclerViewAdapter<T> : RecyclerView.Adapter<BaseViewHolder<T>>() {

    private var mDataList = mutableListOf<T>()
    var mOnItemClickListener: ((position: Int, data: T) -> Unit)? = null
    private var mOnLoadMoreListener: (() -> Unit)? = null
    private var mThreshold = 0

    fun setOnItemClickListener(clickListener: ((position: Int, data: T) -> Unit)) {
        mOnItemClickListener = clickListener
    }

    fun setOnLoadMoreListener(onLoadMoreListener: () -> Unit, threshold: Int = 0) {
        mOnLoadMoreListener = onLoadMoreListener
        mThreshold = threshold
    }

    open fun setDataList(list: List<T>) {
        mDataList.clear()
        mDataList.addAll(list)
    }

    open fun addData(data: T) {
        mDataList.add(data)
    }

    open fun addData(data: T, index: Int) {
        mDataList.add(index, data)
    }

    open fun addAll(dataList: List<T>) {
        mDataList.addAll(dataList)
    }

    open fun removeData(position: Int) {
        mDataList.removeAt(position)
    }

    open fun clearData() {
        mDataList = mutableListOf()
    }

    fun getData(position: Int) = mDataList[position]

    fun getDataList(): List<T> = mDataList

    override fun getItemCount() = mDataList.size

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.itemView.setOnClickListener {
            mOnItemClickListener?.invoke(position, mDataList[position])
        }
        holder.bindView(mDataList[position])
        if (position == mDataList.size - 1 - mThreshold) {
            mOnLoadMoreListener?.invoke()
        }
    }
}

abstract class BaseViewHolder<T>(viewBinding: ViewBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {
    abstract fun bindView(data: T)
}
