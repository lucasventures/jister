package luke.example.jister

import luke.example.jister.repository.GistRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GistViewModelTest {
    private lateinit var gistRepository: GistRepository
    private var test1 =
        "https://gist.githubusercontent.com/lostintangent/dad2/fd30bf3544d061/styles"
    private var test2 =
        "https://gist.githubusercontent.com/lostintangent/dad2518733975d2d/style.scss"
    private var test3 =
        "https://gist.githubusercontent.com/lostintangent/dad2512be0bafd30bd061/styles/"
    private var test4 =
        "https://gist.githubusercontent.com/lostintangent/dad2510bf3544d061/styles//"
    private var test5 =
        "https://gist.githubusercontent.com/lostintangent/dad2d061/styles.sh////"

    @BeforeEach
    fun setup() {
        gistRepository = GistRepository()
    }

    @Test
    fun `test to see if gist repository correctly formats urls`() {
        val x = gistRepository.formatGistFileUrl(test1)
        val expected =
            "https://gist.githubusercontent.com/lostintangent/dad2/fd30bf3544d061/styles/"
        assertEquals(expected, x)
    }

    @Test
    fun `test to see if gist repository correctly formats urls 2`() {
        val x = gistRepository.formatGistFileUrl(test2)
        val expected =
            "https://gist.githubusercontent.com/lostintangent/dad2518733975d2d/style.scss/"
        assertEquals(expected, x)
    }

    @Test
    fun `test to see if gist repository correctly formats urls 3`() {
        val x = gistRepository.formatGistFileUrl(test3)
        val expected =
            "https://gist.githubusercontent.com/lostintangent/dad2512be0bafd30bd061/styles/"
        assertEquals(expected, x)
    }

    @Test
    fun `test to see if gist repository does not format when is not needed 1`() {
        val x = gistRepository.formatGistFileUrl(test4)
        val expected =
            "https://gist.githubusercontent.com/lostintangent/dad2510bf3544d061/styles//"
        assertEquals(expected, x)
    }

    @Test
    fun `test to see if gist repository does not format when is not needed 2`() {
        val x = gistRepository.formatGistFileUrl(test5)
        val expected =
            "https://gist.githubusercontent.com/lostintangent/dad2d061/styles.sh////"
        assertEquals(expected, x)
    }

}