/*
 * Copyright (c) 2017 大前良介 (OHMAE Ryosuke)
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/MIT
 */

package net.mm2d.android.upnp.cds

/**
 * @author [大前良介 (OHMAE Ryosuke)](mailto:ryo@mm2d.net)
 */
internal class BrowseArgument {
    private val argument: MutableMap<String, String?> = mutableMapOf()

    init {
        setBrowseDirectChildren()
    }

    fun setObjectId(objectId: String): BrowseArgument = apply {
        argument[OBJECT_ID] = objectId
    }

    fun setBrowseDirectChildren(): BrowseArgument = apply {
        argument[BROWSE_FLAG] = BROWSE_DIRECT_CHILDREN
    }

    fun setBrowseMetadata(): BrowseArgument = apply {
        argument[BROWSE_FLAG] = BROWSE_METADATA
    }

    fun setFilter(filter: String?): BrowseArgument = apply {
        argument[FILTER] = filter
    }

    fun setSortCriteria(sortCriteria: String?): BrowseArgument = apply {
        argument[SORT_CRITERIA] = sortCriteria
    }

    fun setStartIndex(startIndex: Int): BrowseArgument = apply {
        argument[START_INDEX] = startIndex.toString()
    }

    fun setRequestCount(requestCount: Int): BrowseArgument = apply {
        argument[REQUESTED_COUNT] = requestCount.toString()
    }

    fun get(): Map<String, String?> {
        return argument
    }

    companion object {
        private const val OBJECT_ID = "ObjectID"
        private const val BROWSE_FLAG = "BrowseFlag"
        private const val BROWSE_DIRECT_CHILDREN = "BrowseDirectChildren"
        private const val BROWSE_METADATA = "BrowseMetadata"
        private const val FILTER = "Filter"
        private const val SORT_CRITERIA = "SortCriteria"
        private const val START_INDEX = "StartingIndex"
        private const val REQUESTED_COUNT = "RequestedCount"
    }
}
