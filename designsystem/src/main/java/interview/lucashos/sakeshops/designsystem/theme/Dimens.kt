package interview.lucashos.sakeshops.designsystem.theme

import androidx.compose.ui.unit.dp

/**
 * Default spacing values based on [Grid]
 *
 * [Grid] defaults to 8dp
 *
 * [Spacing] - For paddings and margins
 *
 * [Icon] - For icon sizes
 *
 * [Image] - For image sizes
 * **/
object Dimens {

    /** [Grid] defaults to 8dp **/
    private val Grid = 8.dp

    /** [Grid] defaults to 8dp **/
    object Spacing {

        /**
         * [Grid] / 2 = 4dp 
         **/
        val XSmall = Grid / 2

        /**
         * [Grid] = 8dp 
         **/
        val Small = Grid

        /**
         * [Grid] * 2 = 16dp 
         **/
        val Medium = Grid * 2

        /**
         * [Grid] * 3 = 24dp 
         **/
        val XMedium = Grid * 3

        /**
         * [Grid] * 4 = 32dp 
         **/
        val Large = Grid * 4

        /**
         * [Grid] * 6 = 48dp 
         **/
        val XLarge = Grid * 6
    }

    /** [Grid] defaults to 8dp **/
    object Icon {
        /**
         * [Grid] * 2 = 16.dp
         */
        val XSmall = Grid * 2

        /**
         * [Grid] * 2.5 = 20.dp
         */
        val Small = Grid * 2.5f

        /**
         * [Grid] * 3 = 24.dp
         */
        val Medium = Grid * 3

        /**
         * [Grid] * 4 = 32.dp
         */
        val Large = Grid * 4

        /**
         * [Grid] * 5 = 40.dp
         */
        val XLarge = Grid * 5

        /**
         * [Grid] * 6 = 48.dp
         */
        val XXLarge = Grid * 6
    }

    /** [Grid] defaults to 8dp **/
    object Image {
        /**
         * [Grid] * 4 = 32.dp
         */
        val XSmall = Grid * 4

        /**
         * [Grid] * 8 = 64.dp
         */
        val Small = Grid * 8

        /**
         * [Grid] * 12 = 96.dp
         */
        val Medium = Grid * 12

        /**
         * [Grid] * 16 = 128.dp
         */
        val Large = Grid * 16

        /**
         * [Grid] * 24 = 192.dp
         */
        val XLarge = Grid * 24

        /**
         * [Grid] * 32 = 256.dp
         */
        val XXLarge = Grid * 32
    }
}