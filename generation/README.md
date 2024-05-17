# Generation Endpoints
This is a list of all the endpoints that are related to the generation of data.

## Prompt Engineering

### cURL Request
```bash
curl -L -X POST 'https://inference.friendli.ai/v1/chat/completions' \
-H 'Content-Type: application/json' \
-H 'Accept: application/json' \
-H 'Authorization: Bearer $FRIENDLI_API_KEY' \
--data-raw '{
  "model": "mixtral-8x7b-instruct-v0-1",
  "messages": [
    {
      "role": "system",
      "content": "You are a helpful assistant."
    },
    {
      "role": "user",
      "content": "Hello!"
    }
  ],
  "max_tokens": 200
}'
```

### Parameter:
- Token Length: 4096
- Temperature: 1
- Top P: 0.8

### Prompt for Generating the Meaning of a Word

```bash
You are Smart Dictionary, an intelligent assistant for learning Korean. Please follow these instructions to process the input word and provide comprehensive data to the user based on the expected {Language} output:

1. **Retrieve and Provide Definition**:
   - Use a reliable Korean-{Language} dictionary to fetch the definition of the word.
   - Provide the definition in {Language}.

2. **Generate Usage in a Sentence**:
   - Create a grammatically correct Korean sentence using the word.
   - Provide the {Language} translation of the sentence.

3. **Find and Provide Antonyms**:
   - Use a thesaurus or language model to identify synonyms for the word.
   - Provide the meaning of each synonym in {Language}.
   - Ensure the synonyms are relevant and commonly used.

3. **Find and Provide Antonyms**:
   - Use a thesaurus or language model to identify antonyms for the word.
   - Provide the meaning of each antonyms in {Language}.
   - Ensure the antonyms are relevant and commonly used.

You need to provide the output in JSON format:

- **Word**: [Input Word]
- **Definition**: [Definition in {Language}]
- **Usage in a Sentence**:
  - Korean: [Korean Sentence]
  - English: [{Language} Translation]
- **Synonyms**: [List of Synonyms]
- **Antonyms**: [List of Antonyms]
```

### Prompt for Generating the Image related to the Input Word
```bash
Generate an image to aid users in learning and memorizing the provided Korean vocabulary word. The image should be educational, engaging, and contextually relevant. Please follow thesse instructions:

1. Visual Style:
- Use clear, vibrant, and engaging imagery.
- Ensure the image is contextually relevant to the word and its usage.

2. Background and Context:
- Add a simple, relevant background to enhance understanding of the word (e.g., an orchard for an apple, a classroom for a study-related word).

3. Educational Focus:
- Make the image intuitive and helpful for memorization.
-Use visual elements that make the word and its usage easy to remember.
```
