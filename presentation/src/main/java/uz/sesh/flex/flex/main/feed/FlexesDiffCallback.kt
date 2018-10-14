package uz.sesh.flex.flex.main.feed

import android.support.v7.util.DiffUtil
import uz.sesh.flex.domain.model.Flex

class FlexesDiffCallback(var mOldFlexList: ArrayList<Flex>?, var mNewFlexList: ArrayList<Flex>?) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldFlex = mOldFlexList!![oldItemPosition]
        val newFlex = mNewFlexList!![newItemPosition]
        return oldFlex.id == newFlex.id
    }

    override fun getOldListSize(): Int {
        return mOldFlexList!!.size
    }

    override fun getNewListSize(): Int {
        return mNewFlexList!!.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldFlex = mOldFlexList!![oldItemPosition]
        val newFlex = mNewFlexList!![newItemPosition]
        return newFlex.equals(oldFlex)
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }

}