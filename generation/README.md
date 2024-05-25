# Generation Endpoints

This is a list of all the endpoints that are related to the generation of data.

## Text Generation

### cURL Request for Generating Text

````bash
curl -L -X POST 'https://inference.friendli.ai/v1/chat/completions' \
-H 'Content-Type: application/json' \
-H 'Accept: application/json' \
-H 'Authorization: Bearer flp_eBOsoO7oM0iaZZYW9skCpW6ZuiKcLtZVdyWIsaYyu94c7a' \
--data-raw '{
  "model": "meta-llama-3-70b-instruct",
  "messages": [
    {
      "role": "system",
      "content": "You are Smart Dictionary, an intelligent assistant for learning Korean. Please follow these instructions to process the input word and provide comprehensive data to the user based on the expected {Language} output:\n\n1. **Retrieve and Provide Definition**:\n  - Use a reliable Korean-{Language} dictionary to fetch the definition of the word.\n  - Provide the definition in {Language}.\n\n2. **Generate Usage in a Sentence**:\n  - Create a grammatically correct Korean sentence using the word.\n  - Provide the {Language} translation of the sentence.\n\n3. **Find and Provide Synonyms**:\n  - Use a thesaurus or language model to identify synonyms for the word.\n  - Provide the meaning of each synonym in {Language}.\n  - Ensure the synonyms are relevant and commonly used.\n\n4. **Find and Provide Antonyms**:\n  - Use a thesaurus or language model to identify antonyms for the word.\n  - Provide the meaning of each antonym in {Language}.\n  - Ensure the antonyms are relevant and commonly used.\n\nYou need to provide the output in JSON format:\n\n- **Word**: [Input Word]\n- **Definition**: [Definition in {Language}]\n- **Usage in a Sentence**:\n  - Korean: [Korean Sentence]\n  - English: [{Language} Translation]\n- **Synonyms**: [List of Synonyms]\n- **Antonyms**: [List of Antonyms]"
    },
    {
      "role": "user",
      "content": "대학"
    }
  ],
  "max_tokens": 1024
}'

choices.message.content

{
   "choices":[
      {
         "finish_reason":"stop",
         "index":0,
         "logprobs":null,
         "message":{
            "content":"Here is the comprehensive data for the Korean word \"대학\":\n\n**Output**\n\n```\n{\n  \"Word\": \"대학\",\n  \"Definition\": \"A school that provides higher education, especially one that is attended after completing high school.\",\n  \"Usage in a Sentence\": {\n    \"Korean\": \"저는 고려대학교에 다녔습니다.\",\n    \"English\": \"I attended Korea University.\"\n  },\n  \"Synonyms\": [\n    {\n      \"Word\": \"대학교\",\n      \"Definition\": \"A university or institution of higher education.\"\n    },\n    {\n      \"Word\": \"학원\",\n      \"Definition\": \"An institution of higher education, especially a private one.\"\n    }\n  ],\n  \"Antonyms\": [\n    {\n      \"Word\": \"초등학교\",\n      \"Definition\": \"An elementary school or primary school.\"\n    },\n    {\n      \"Word\": \"중학교\",\n      \"Definition\": \"A middle school or secondary school.\"\n    }\n  ]\n}\n```\n\nLet me know if you have any further requests!",
            "role":"assistant"
         }
      }
   ],
   "created":1716604779,
   "usage":{
      "completion_tokens":212,
      "prompt_tokens":299,
      "total_tokens":511
   }
}

````

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

## Image Generation

### Prompt for Generating the Image related to the Input Word

```bash
Generate an image to aid users in learning and memorizing the provided Korean vocabulary word. The image should be educational, engaging, and contextually relevant. Please follow thesse instructions:

1. Visual Style:
- Use clear, vibrant, and engaging imagery.
- Ensure the image is contextually relevant to the word and its usage.

2. Background and Context:
- Add a simple, relevant background to enhance understanding of the word.

3. Educational Focus:
- Make the image intuitive and helpful for memorization.
- Use visual elements that make the word and its usage easy to remember.
```

### cURL Request for Generating Image using Dall-e-3

```bash
curl https://api.openai.com/v1/images/generations \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $OPENAI_API_KEY" \
  -d '{
    "model": "dall-e-3",
    "prompt": "a white siamese cat",
    "n": 1,
    "size": "1024x1024"
  }'
```

### cURL Request for Generating Image using

```
curl -L -X POST 'https://inference.friendli.ai/v1/text-to-image' \
-H 'Content-Type: multipart/form-data' \
-H 'Accept: application/json' \
-H 'Authorization: Bearer flp_eBOsoO7oM0iaZZYW9skCpW6ZuiKcLtZVdyWIsaYyu94c7a' \
-F 'prompt="{keyword}"' \
-F 'negative_prompt="worst quality, normal quality, low quality, low res, blurry, text, watermark, logo, banner, extra digits, cropped, jpeg artifacts, signature, username, error, sketch, duplicate, ugly, monochrome, horror, geometry, mutation, disgusting, human face"' \
-F 'model="stable-diffusion-v1-5"' \
-F 'num_outputs="2"' \
-F 'num_inference_steps="60"' \
-F 'guidance_scale="7.5"'
```
