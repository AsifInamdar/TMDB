package com.asif.tmdb

import com.asif.tmdb.utils.formatRevenue
import org.junit.Assert.assertEquals
import org.junit.Test

class MovieDetailsViewModelTest {

    @Test
    fun `formatRevenue with valid amt`() {
        val revenue = 123456789
        val result = formatRevenue(revenue)
        assertEquals("$123,456,789.00", result)
    }

    @Test
    fun `formatRevenue with null value`() {
        val revenue: Int? = null
        val result = formatRevenue(revenue)
        assertEquals(null, result)
    }

    @Test
    fun `formatRevenue with zero revenue`() {
        val revenue = 0
        val result = formatRevenue(revenue)
        assertEquals("$0.00", result)
    }

    @Test
    fun `formatRevenue with large revenue`() {
        val revenue = Int.MAX_VALUE
        val result = formatRevenue(revenue)

        assertEquals("$2,147,483,647.00", result)
    }
}