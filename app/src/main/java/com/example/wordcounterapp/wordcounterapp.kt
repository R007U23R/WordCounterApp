package com.example.wordcounterapp

/**
 * TextCounter class handles all text analysis operations
 * Requirement #5: Separate class for metrics counting
 * Requirement #6: Two metrics use regex, two don't
 */
class TextCounter {

    /**
     * Counts sentences in the text using REGULAR EXPRESSION
     * Sentences end with . ! or ?
     */
    fun countSentences(text: String): Int {
        if (text.isBlank()) return 0

        // Regex pattern for sentence endings
        val sentenceRegex = Regex("[.!?]+")
        val sentences = text.trim().split(sentenceRegex)

        // Filter out empty strings and count
        return sentences.filter { it.trim().isNotEmpty() }.size
    }

    /**
     * Counts words in the text WITHOUT regular expression
     * Words can be separated by spaces, commas, or dots (Requirement #7)
     */
    fun countWords(text: String): Int {
        if (text.isBlank()) return 0

        // Replace commas and dots with spaces, then split by whitespace
        val cleanText = text.replace(',', ' ')
            .replace('.', ' ')
            .replace('!', ' ')
            .replace('?', ' ')

        // Split by whitespace and filter out empty strings
        val words = cleanText.split(" ")
            .filter { it.trim().isNotEmpty() }

        return words.size
    }

    /**
     * Counts characters in the text WITHOUT regular expression
     * Simple character count excluding spaces
     */
    fun countCharacters(text: String): Int {
        // Count all characters including spaces
        return text.length

        // If you want to exclude spaces, use:
        // return text.replace(" ", "").length
    }

    /**
     * Counts numbers in the text using REGULAR EXPRESSION
     * Finds all numeric sequences (integers and decimals)
     */
    fun countNumbers(text: String): Int {
        if (text.isBlank()) return 0

        // Regex pattern for numbers (including decimals)
        val numberRegex = Regex("\\b\\d+(\\.\\d+)?\\b")
        val matches = numberRegex.findAll(text)

        return matches.count()
    }
}