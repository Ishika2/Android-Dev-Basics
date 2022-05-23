package com.example.affirmations

import android.content.Context
import com.example.affirmations.adapter.ItemAdapter
import com.example.affirmations.model.Affirmation
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock

class AffirmationsAdapterTests {
    private val context = mock(Context::class.java)
    @Test
    fun adapter_size() {
        val data = listOf(
            Affirmation(R.string.affirmation1, R.drawable.image1),
            Affirmation(R.string.affirmation2, R.drawable.image2)
        )
        /*Create a function called adapter_size() and annotate it as a test.
        The goal of this test is to make sure that the size of the adapter is the size of the list that was passed to the adapter.
        To do this, create an instance of ItemAdapter and pass in the list returned by the loadAffirmations() method in the Datasource class.
        Alternatively, create a new list and test that. For unit tests, it's best practice to create our own data unique to the test, so we'll create a custom list for this test.*/
        val adapter = ItemAdapter(context, data)
        //override fun getItemCount() = dataset.size //return the size of dataset

        assertEquals("ItemAdapter is not the correct size", data.size, adapter.itemCount)
        //fisrt parameter - text, if the test fails
        //second parameter - expected value
        //third parameter - actual value
    }
}