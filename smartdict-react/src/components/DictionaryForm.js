// src/components/DictionaryForm.js
import React, { useState } from "react";
import { TextField, Button, Box } from "@mui/material";

const DictionaryForm = ({ onSearch }) => {
  const [word, setWord] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    if (word) {
      onSearch(word);
    }
  };

  return (
    <Box component="form" onSubmit={handleSubmit} sx={{ display: 'flex', flexDirection: 'column', gap: 2 }}>
      <TextField
        variant="outlined"
        fullWidth
        label="Enter a word"
        value={word}
        onChange={(e) => setWord(e.target.value)}
      />
      <Button type="submit" variant="contained" color="primary" fullWidth>
        Search
      </Button>
    </Box>
  );
};

export default DictionaryForm;
