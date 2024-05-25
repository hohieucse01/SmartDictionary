// ServiceApi.kt

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
data class RequestMessage(val role: String, val content: String)
data class RequestBody(val model: String, val messages: List<RequestMessage>, val max_tokens: Int)

data class WordData(
    val Word: String,
    val Definition: String,
    val UsageInSentence: UsageInSentence,
    val Synonyms: List<SynonymAntonym>,
    val Antonyms: List<SynonymAntonym>
)

data class UsageInSentence(val Korean: String, val English: String)
data class SynonymAntonym(val Word: String, val Definition: String)

data class ResponseChoice(val message: ResponseMessage)
data class ResponseMessage(val content: String)
data class ApiResponse(val choices: List<ResponseChoice>)


interface ServiceApi {
    @Headers(
        "Content-Type: application/json",
        "Accept: application/json",
        "Authorization: Bearer flp_eBOsoO7oM0iaZZYW9skCpW6ZuiKcLtZVdyWIsaYyu94c7a"
    )
    @POST("v1/chat/completions")
    fun getWordData(@Body body: RequestBody): Call<ApiResponse>
}
