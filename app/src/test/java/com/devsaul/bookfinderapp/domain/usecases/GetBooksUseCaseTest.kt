package com.devsaul.bookfinderapp.domain.usecases

import com.devsaul.bookfinderapp.data.Repository
import com.devsaul.bookfinderapp.domain.models.Book
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetBooksUseCaseTest {

    @RelaxedMockK
    private lateinit var repository: Repository

    lateinit var getBooksUseCase: GetBooksUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getBooksUseCase = GetBooksUseCase(repository)
    }

    @Test
    fun `when the user whan to seach book by author and api retorn data`() = runBlocking {

        val AUTHOR = "Gabriel Garcia Marquez"
        val AUTHOR_TRANSFORM = "gabriel+garcia+marquez"

        coEvery { repository.getBookForAuthor(AUTHOR) } returns listOf(
            Book(
                null,
                listOf("Gabriel Garcia Marquez"),
                null,
                null,
                listOf("Esp"),
                224,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                listOf("123123123", "21312312123"),
                null
            )
        )

        getBooksUseCase.searchBook(author = AUTHOR)

        coVerify(exactly = 1) {
            repository.getBookForAuthor(AUTHOR_TRANSFORM)
        }
    }

    @Test
    fun `when the user whan to seach book by title and api retorn data`() = runBlocking {

        val TITLE = "Blanca nieves"
        val TITLE_TRANSFORM = "blanca+nieves"

        coEvery { repository.getBookForTitle(TITLE) } returns listOf(
            Book(
                null,
                null,
                null,
                null,
                listOf("Esp"),
                224,
                null,
                null,
                null,
                null,
                null,
                null,
                "Blanca nieves",
                null,
                listOf("123123123", "21312312123"),
                null
            )
        )

        getBooksUseCase.searchBook(title = TITLE)

        coVerify(exactly = 1) {
            repository.getBookForTitle(TITLE_TRANSFORM)
        }
    }


    @Test
    fun `when the user whan to seach book by title and api doesnt return enything`() = runBlocking {

        val TITLE = "Blanca nieves"

        coEvery { repository.getBookForTitle(TITLE) } returns listOf()

        val response = getBooksUseCase.searchBook(title = TITLE)


        assert(response.isNullOrEmpty())
    }

    @Test
    fun `when the user whan to seach book by author and api doesnt return enything`() =
        runBlocking {

            val AUTHOR = "Gabriel Garcia Marquez"

            coEvery { repository.getBookForAuthor(AUTHOR) } returns listOf()

            val response = getBooksUseCase.searchBook(author = AUTHOR)

            assert(response.isNullOrEmpty())
        }


}