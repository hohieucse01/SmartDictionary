// src/components/WordDetails.js
import React from 'react';
import ReactMarkdown from 'react-markdown';

const WordDetails = ({ details }) => {
  if (!details) {
    return <div>No word details available.</div>;
  }

  return (
    <div>
      <ReactMarkdown>{details.markdownContent}</ReactMarkdown>
      {details.imageUrl && <img src={details.imageUrl} alt="Generated visual representation" />}
    </div>
  );
};

export default WordDetails;
