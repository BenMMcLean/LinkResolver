package cl.benm.linkresolver

import android.content.Context
import androidx.navigation.NavController
import cl.benm.linkresolver.handlers.ActivityLinkHandler
import cl.benm.linkresolver.handlers.PackageDeeplinkHandler
import cl.benm.linkresolver.helpers.DefaultExceptionHandler
import cl.benm.linkresolver.helpers.ExceptionHandler
import cl.benm.linkresolver.helpers.GlobalContextStrategy
import java.util.*

interface LinkResolver {

    companion object {

        @JvmStatic
        fun create(globalContextStrategy: GlobalContextStrategy): LinkResolver {
            return DefaultLinkResolver(globalContextStrategy).apply {
                register(ActivityLinkHandler(), 1000)
            }
        }

        @JvmStatic
        fun createDeeplinked(globalContextStrategy: GlobalContextStrategy, navController: NavController): LinkResolver {
            return DefaultLinkResolver(globalContextStrategy).apply {
                register(PackageDeeplinkHandler(navController))
                register(ActivityLinkHandler(), 900)
            }
        }

    }

    fun resolve(link: String, localContext: Context? = null)

    fun register(linkHandler: LinkHandler, order: Int = 100)
    fun deregister(linkHandler: LinkHandler)

}

class DefaultLinkResolver(
    private val globalContextStrategy: GlobalContextStrategy,
    private val exceptionHandler: ExceptionHandler = DefaultExceptionHandler()
): LinkResolver {

    private val priorityQueue = PriorityQueue<LinkHandlerPriority>()

    override fun resolve(link: String, localContext: Context?) {
        try {
            priorityQueue.iterator().forEach {
                if (!it.linkHandler.handle(link, localContext ?: globalContextStrategy.fetchGlobalContext())) return
            }
        } catch (e: Exception) {
            exceptionHandler.catch(link, e, localContext ?: globalContextStrategy.fetchGlobalContext())
        }
    }

    override fun register(linkHandler: LinkHandler, order: Int) {
        priorityQueue.add(LinkHandlerPriority(order, linkHandler))
    }

    override fun deregister(linkHandler: LinkHandler) {
        priorityQueue.removeAll { it.linkHandler == linkHandler }
    }
}

data class LinkHandlerPriority(
    val priority: Int,
    val linkHandler: LinkHandler
): Comparable<LinkHandlerPriority> {

    override fun compareTo(other: LinkHandlerPriority): Int {
        return priority.compareTo(other.priority)
    }

}