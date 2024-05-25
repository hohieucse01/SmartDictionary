// src/App.js
import React, { useState } from "react";
import axios from "axios";
import DictionaryForm from "./components/DictionaryForm";
import WordDetails from "./components/WordDetails";

const App = () => {
  const [wordDetails, setWordDetails] = useState(null);

  const fetchWordDetails = async (word) => {
    try {
      const textResponse = await axios.post(
        "https://inference.friendli.ai/v1/chat/completions",
        {
          model: "meta-llama-3-70b-instruct",
          messages: [
            {
              role: "system",
              content: `You are an high intelligent assistant for learning Korean. Please follow these instructions to process the input word and provide comprehensive data to the user based on the expected English output:
                1. **Retrieve and Provide Definition**:
                  - Use a reliable Korean-English dictionary to explain the definition of the word.
                  - Provide the definition in English.
                2. **Generate Usage in a Sentence (3 sentences)**:
                  - Ensure a grammatically correct Korean sentence using the word. Sentences should be simple and easy to understand consisting Korean words only.
                  - Provide the English translation of the sentence.
                3. **Find and Provide Synonyms**:
                  - Use a thesaurus or language model to identify synonyms for the word.
                  - Provide the meaning of each synonym in English.
                  - Ensure the synonyms are relevant and commonly used.
                4. **Find and Provide Antonyms**:
                  - Use a thesaurus or language model to identify antonyms for the word.
                  - Provide the meaning of each antonym in English.
                  - Ensure the antonyms are relevant and commonly used.
                You need to provide the output in the following format:
                - **Word**: [Input Word]
                - **Definition**: [Definition in English]
                - **Usage in a Sentence**:
                  - Korean: [Korean Sentence]
                  - English: [English Translation]
                - **Synonyms**: [List of Synonyms]
                - **Antonyms**: [List of Antonyms]`,
            },
            {
              role: "user",
              content: word,
            },
          ],
          max_tokens: 1024,
        },
        {
          headers: {
            "Content-Type": "application/json",
            Accept: "application/json",
            Authorization:
              "Bearer flp_eBOsoO7oM0iaZZYW9skCpW6ZuiKcLtZVdyWIsaYyu94c7a",
          },
        }
      );

      const content = textResponse.data.choices[0].message.content;
      console.log("Content:", content);

      const formData = new FormData();
      formData.append("prompt", word);
      formData.append(
        "negative_prompt",
        "worst quality, normal quality, low quality, low res, blurry, text, watermark, logo, banner, extra digits, cropped, jpeg artifacts, signature, username, error, sketch, duplicate, ugly, monochrome, horror, geometry, mutation, disgusting, human face"
      );
      formData.append("model", "stable-diffusion-v1-5");
      formData.append("num_outputs", "2");
      formData.append("num_inference_steps", "60");
      formData.append("guidance_scale", "7.5");

      const imageResponse = await axios.post(
        "https://inference.friendli.ai/v1/text-to-image",
        formData,
        {
          headers: {
            "Content-Type": "multipart/form-data",
            Accept: "application/json",
            Authorization:
              "Bearer flp_eBOsoO7oM0iaZZYW9skCpW6ZuiKcLtZVdyWIsaYyu94c7a",
          },
        }
      );

      const imageUrl =
        imageResponse.data.length > 0 ? imageResponse.data[0].url : "";

      setWordDetails({ markdownContent: content, imageUrl });
    } catch (error) {
      console.error("Error fetching word details:", error);
    }
  };

  return (
    <div className="App">
      <h1>SmartDict</h1>
      <DictionaryForm onSearch={fetchWordDetails} />
      <WordDetails details={wordDetails} />
    </div>
  );
};

export default App;
