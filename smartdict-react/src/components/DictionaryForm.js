// src/components/DictionaryForm.js
import React, { useState } from "react";

const DictionaryForm = ({ onSearch }) => {
  const [word, setWord] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    if (word) {
      onSearch(word);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <input
        type="text"
        value={word}
        onChange={(e) => setWord(e.target.value)}
        placeholder="Enter a word"
      />
      <button type="submit">Search</button>
    </form>
  );
};

export default DictionaryForm;
