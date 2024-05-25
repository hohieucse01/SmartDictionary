// src/components/WordDetails.js
import React from "react";
import ReactMarkdown from "react-markdown";
import { Paper, Box, Typography } from "@mui/material";

const WordDetails = ({ details }) => {
  if (!details) {
    return <div>No word details available.</div>;
  }

  return (
    <Paper elevation={3} sx={{ padding: 2 }}>
      <ReactMarkdown>
        {details.markdownContent}
      </ReactMarkdown>
    </Paper>
  );
};

export default WordDetails;
