import React, { useState } from "react";
import axios from "axios";
import {
  Container,
  Typography,
  Paper,
  Box,
  CssBaseline,
  CircularProgress,
  Alert,
} from "@mui/material";
import DictionaryForm from "./components/DictionaryForm";
import WordDetails from "./components/WordDetails";

const App = () => {
  const [wordDetails, setWordDetails] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const fetchWordDetails = async (word) => {
    setLoading(true);
    setError(null);
    try {
      // Fetch text details
      const textResponse = await axios.post(
        "https://inference.friendli.ai/v1/chat/completions",
        {
          model: "meta-llama-3-70b-instruct",
          messages: [
            {
              role: "system",
              content: `You are a high intelligent assistant that helps user learning Korean. Please follow these instructions to process the input word and provide sentences and explanation to user based on the expected English output. Each section should be clearly separated by new line and formatted. The output should be in markdown format.
                1. **Retrieve and Provide Definition**:
                  - Use a reliable Korean-English dictionary to explain the definition of the word.
                  - Provide the definition in English.
                
                2. **Generate Usage in a Sentence (5 sentences) MUST GRAMMATICALLY CORRECT KOREAN SENTENCES and the sentences must consists of KOREAN word only**:
                  - Provide the English translation of each sentence.

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
          max_tokens: 2048,
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

      // Extract definition
      const translation_request = await axios.post(
        "https://inference.friendli.ai/v1/chat/completions",
        {
          model: "meta-llama-3-70b-instruct",
          messages: [
            {
              role: "system",
              content: `You are a high intelligent assistant for learning Korean. Please provide the English translation of the Korean input word.`,
            },
            {
              role: "user",
              content: word,
            },
          ],
          max_tokens: 200,
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
      const translation = translation_request.data.choices[0].message.content;

      console.log("Definition:", translation);

      // Generate image
      const formData = new FormData();
      formData.append("prompt", `${translation}`);
      formData.append("model", "stable-diffusion-v1-5");
      formData.append(
        "negative_prompt",
        "worst quality, normal quality, low quality, low res, blurry, text, watermark, logo, banner, extra digits, cropped, jpeg artifacts, signature, username, error, sketch, duplicate, ugly, monochrome, horror, geometry, mutation, disgusting, human image, human face"
      );
      formData.append("num_outputs", "1");
      formData.append("num_inference_steps", "70");
      formData.append("guidance_scale", "7.5");

      console.log("Form Data:", formData);

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

      const imageUrls = imageResponse.data.data
        .map((img) => img.url)
        .join("\n");
      const contentWithImage = `${content}\n\n## Vocabulary by image \n${imageUrls
        .split("\n")
        .map((url) => `![Generated Image](${url})`)
        .join("\n")}`;

      setWordDetails({ markdownContent: contentWithImage });
    } catch (error) {
      console.error("Error fetching word details:", error);
      setError("Failed to fetch word details. Please try again.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <Container component="main" maxWidth="md">
      <CssBaseline />
      <Box
        sx={{
          marginTop: 8,
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
        }}
      >
        <Typography component="h1" variant="h4" gutterBottom>
          Smart Dictionary
        </Typography>
        <Paper elevation={3} sx={{ padding: 2, width: "100%", marginTop: 2 }}>
          <DictionaryForm onSearch={fetchWordDetails} />
        </Paper>
        {loading && (
          <Box sx={{ display: "flex", justifyContent: "center", marginTop: 4 }}>
            <CircularProgress />
          </Box>
        )}
        {error && (
          <Box sx={{ marginTop: 4, width: "100%" }}>
            <Alert severity="error">{error}</Alert>
          </Box>
        )}
        {wordDetails && (
          <Box sx={{ marginTop: 4, width: "100%" }}>
            <WordDetails details={wordDetails} />
          </Box>
        )}
      </Box>
    </Container>
  );
};

export default App;
