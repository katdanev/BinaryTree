import React, { useState } from "react";
import "./App.css";

function App() {
  const [showForm, setShowForm] = useState(false);
  const [numbers, setNumbers] = useState('');
  const [tree, setTree] = useState(null);
  const [previousTrees, setPreviousTrees] = useState([]);

  const handleStartClick = () => {
    setShowForm(true);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      const response = await fetch('http://localhost:8080/api/process-numbers', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ numbers }),
      });
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      const data = await response.json();
      setTree(data);
    } catch (error) {
      console.error('Error:', error);
    }
  };

  const handleShowPrevious = async () => {
    try {
      const response = await fetch('http://localhost:8080/api/previous-trees');
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      const data = await response.json();
      // Обробка даних для збереження у стані
      setPreviousTrees(data);
    } catch (error) {
      console.error('Error:', error);
    }
  };

  const formatTree = (tree, indent = '') => {
    if (!tree) {
      return '';
    }

    let result = `${indent}Value: ${tree.value !== undefined ? tree.value : 'undefined'}\n`;
    indent += '  ';

    if (tree.left) {
      result += `${indent}Left:\n${formatTree(tree.left, indent + '  ')}`;
    } else {
      result += `${indent}Left: null\n`;
    }

    if (tree.right) {
      result += `${indent}Right:\n${formatTree(tree.right, indent + '  ')}`;
    } else {
      result += `${indent}Right: null\n`;
    }

    return result;
  };

  return (
    <div className="App">
      <header className="App-header">
        <h1>Data Structures and Algorithms</h1>
        {!showForm && (
          <button className="start-button" onClick={handleStartClick}>Start</button>
        )}
        {showForm && (
          <div className="form-container">
            <form onSubmit={handleSubmit}>
              <input
                type="text"
                value={numbers}
                onChange={(e) => setNumbers(e.target.value)}
                placeholder="Enter numbers separated by commas"
              />
              <button className="submit-button" type="submit">Submit</button>
            </form>
            <button className="show-previous-button" onClick={handleShowPrevious}>Show Previous</button>
            {previousTrees.length > 0 && (
              <div>
                <h2>Trees from DB</h2>
                {previousTrees.map((record, index) => (
                  <div key={record.record_id}>
                    <h3>Tree {index + 1}</h3>
                    <pre>{formatTree(JSON.parse(record.tree_json))}</pre>
                  </div>
                ))}
              </div>
            )}
          </div>
        )}
      </header>
    </div>
  );
}

export default App;


