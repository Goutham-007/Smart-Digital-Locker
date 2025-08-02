import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './Form.css';

function Login() {
  const [form, setForm] = useState({ username: '', password: '' });
  const [message, setMessage] = useState('');
  const navigate = useNavigate();

  const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await axios.get('http://localhost:8080/api/files', {
        auth: {
          username: form.username,
          password: form.password
        }
      });
      if (res.status === 200) {
        localStorage.setItem('username', form.username);
        localStorage.setItem('password', form.password);
        navigate('/dashboard');
      }
    } catch (err) {
      setMessage('Login failed. Check credentials.');
    }
  };

  return (
    <div className="form-container">
      <h2>Login</h2>
      {message && <p>{message}</p>}
      <form onSubmit={handleSubmit}>
        <input name="username" placeholder="Username" value={form.username} onChange={handleChange} required />
        <input name="password" type="password" placeholder="Password" value={form.password} onChange={handleChange} required />
        <button type="submit">Login</button>
      </form>
      <a href="/register">Don't have an account? Register</a>
    </div>
  );
}

export default Login;
