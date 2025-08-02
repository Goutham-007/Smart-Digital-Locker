import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './Dashboard.css';

function Dashboard() {
  const [files, setFiles] = useState([]);
  const [file, setFile] = useState(null);
  const username = localStorage.getItem('username');
  const password = localStorage.getItem('password');

  const fetchFiles = async () => {
    const res = await axios.get('http://localhost:8080/api/files', {
      auth: { username, password }
    });
    setFiles(res.data);
  };

  useEffect(() => { fetchFiles(); }, []);

  const handleUpload = async () => {
    const formData = new FormData();
    formData.append('file', file);
    await axios.post('http://localhost:8080/api/files/upload', formData, {
      auth: { username, password },
      headers: { 'Content-Type': 'multipart/form-data' }
    });
    fetchFiles();
  };

  const handleDelete = async (id) => {
    await axios.delete(`http://localhost:8080/api/files/${id}`, {
      auth: { username, password }
    });
    fetchFiles();
  };

  const handleDownload = async (id) => {
    const res = await axios.get(`http://localhost:8080/api/files/download/${id}`, {
      responseType: 'blob',
      auth: { username, password }
    });
    const url = window.URL.createObjectURL(new Blob([res.data]));
    const link = document.createElement('a');
    link.href = url;
    link.setAttribute('download', 'file.pdf');
    document.body.appendChild(link);
    link.click();
  };

  const handleLogout = () => {
    localStorage.clear();
    window.location.href = '/login';
  };

  return (
    <div className="dashboard-container">
      <h2>Dashboard</h2>
      <button onClick={handleLogout} className="logout">Logout</button>

      <div className="upload-section">
        <input type="file" onChange={(e) => setFile(e.target.files[0])} />
        <button onClick={handleUpload}>Upload</button>
      </div>

      <div className="files-list">
        <h3>Your Files</h3>
        <ul>
          {files.map(f => (
            <li key={f.id}>
              {f.filename}
              <button onClick={() => handleDownload(f.id)}>Download</button>
              <button onClick={() => handleDelete(f.id)}>Delete</button>
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
}

export default Dashboard;
