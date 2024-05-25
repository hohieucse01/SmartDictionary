// src/components/WordDetails.js
import React from "react";
import ReactMarkdown from "react-markdown";

const WordDetails = ({ details }) => {
  if (!details) {
    return <div>No word details available.</div>;
  }

  return (
    <div>
      <ReactMarkdown>{details.markdownContent}</ReactMarkdown>
    </div>
  );
};

export default WordDetails;
