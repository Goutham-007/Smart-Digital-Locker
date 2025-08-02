import React, { useState } from 'react';
import axios from 'axios';
import './Form.css';

function Register() {
  const [form, setForm] = useState({ username: '', email: '', password: '' });
  const [message, setMessage] = useState('');

  const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post('http://localhost:8080/api/users/register', form);
      setMessage('✅ Registered successfully. Please login.');
      setForm({ username: '', email: '', password: '' });
    } catch (err) {
      setMessage(' Registration failed.');
    }
  };

  return (
    <div className="form-container">
      <h2>Register</h2>
      {message && <p>{message}</p>}
      <form onSubmit={handleSubmit}>
        <input name="username" placeholder="Username" value={form.username} onChange={handleChange} required />
        <input name="email" placeholder="Email" value={form.email} onChange={handleChange} required />
        <input name="password" type="password" placeholder="Password" value={form.password} onChange={handleChange} required />
        <button type="submit">Register</button>
      </form>
      <a href="/login">Already have an account? Login</a>
    </div>
  );
}

export default Register;
