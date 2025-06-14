package interview.lucashos.sakeshop.data.providers

import android.content.Context
import interview.lucashos.sakeshop.data.R

class ShopListJsonProvider(private val context: Context) : JsonProvider {
    override fun provide(): String {
        return context.resources
            .openRawResource(R.raw.shops)
            .bufferedReader()
            .use { it.readText() }
    }

}