package rest.armagan.papiexpansions

class Utils {
    companion object {
        fun marqueeAnimation(
            inputString: String,
            visibleLength: Int,
            spaceLength: Int,
            direction: String,
            position: Int
        ): String {
            val space = " ".repeat(spaceLength)
            val marqueeString = inputString + space
            val totalLength = marqueeString.length

            val normalizedPosition = position % totalLength

            val normalizedVisibleLength = if (visibleLength > totalLength) totalLength else visibleLength

            val adjustedPosition = when (direction.lowercase()) {
                "left" -> normalizedPosition
                "right" -> (totalLength - normalizedPosition) % totalLength
                else -> throw IllegalArgumentException("Direction must be 'left' or 'right'")
            }

            val scrolledString = marqueeString.substring(adjustedPosition) + marqueeString.substring(0, adjustedPosition)

            return scrolledString.take(normalizedVisibleLength).padEnd(normalizedVisibleLength, ' ')
        }
    }
}