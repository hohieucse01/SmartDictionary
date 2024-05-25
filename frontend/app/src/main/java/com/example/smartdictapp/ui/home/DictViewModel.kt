package com.example.smartdictapp.ui.home

import ApiResponse
import RequestBody
import RequestMessage
import ServiceApi
import WordData
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DictViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(DictUiState())
    val uiState: StateFlow<DictUiState> = _uiState.asStateFlow()

    private val serviceApi = RetrofitClient.instance.create(ServiceApi::class.java)
    val wordData: MutableLiveData<WordData> = MutableLiveData()

    fun fetchWordData(inputKeyword: String) {
        val requestBody = RequestBody(
            model = "meta-llama-3-70b-instruct",
            messages = listOf(
                RequestMessage("system", "You are Smart Dictionary, an intelligent assistant for learning Korean. Please follow these instructions to process the input word and provide comprehensive data to the user based on the expected English output:\n\n1. **Retrieve and Provide Definition**:\n  - Use a reliable Korean-English dictionary to fetch the definition of the word.\n  - Provide the definition in English.\n\n2. **Generate Usage in a Sentence**:\n  - Create a grammatically correct Korean sentence using the word.\n  - Provide the English translation of the sentence.\n\n3. **Find and Provide Synonyms**:\n  - Use a thesaurus or language model to identify synonyms for the word.\n  - Provide the meaning of each synonym in English.\n  - Ensure the synonyms are relevant and commonly used.\n\n4. **Find and Provide Antonyms**:\n  - Use a thesaurus or language model to identify antonyms for the word.\n  - Provide the meaning of each antonym in English.\n  - Ensure the antonyms are relevant and commonly used.\n\nYou need to provide the output in JSON format:\n\n- **Word**: [Input Word]\n- **Definition**: [Definition in English]\n- **Usage in a Sentence**:\n  - Korean: [Korean Sentence]\n  - English: [English Translation]\n- **Synonyms**: [List of Synonyms]\n- **Antonyms**: [List of Antonyms]"),
                RequestMessage("user", inputKeyword)
            ),
            max_tokens = 1024
        )

        viewModelScope.launch {
            serviceApi.getWordData(requestBody).enqueue(object : Callback<ApiResponse> {
                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    if (response.isSuccessful) {
                        response.body()?.let { apiResponse ->
                            val responseContent = apiResponse.choices.firstOrNull()?.message?.content
                            responseContent?.let {
                                val wordData = Gson().fromJson(it, WordData::class.java)
                                this@DictViewModel.wordData.postValue(wordData)
                            }
                        }
                    } else {
                        Log.e("DictViewModel", "Response not successful: ${response.errorBody()?.string()}")
                    }
                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    Log.e("DictViewModel", "API call failed: ${t.message}")
                }
            })
        }
    }

    fun onEvent(dictEvent: DictEvent) {
        when (dictEvent) {
            is DictEvent.SetInputText -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        inputText = dictEvent.text
                    )
                }
            }

            is DictEvent.SetOutputLanguage -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        outputLanguage = dictEvent.language
                    )
                }
            }

            is DictEvent.ReturnSearch -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        inputText = ""
                    )
                }
            }
        }
    }
}
