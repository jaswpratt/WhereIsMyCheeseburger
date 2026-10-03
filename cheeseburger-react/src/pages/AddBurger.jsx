import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { createBurger } from "../api/burgers";
import "./AddBurger.css";

export default function AddBurger() {
  const navigate = useNavigate();
  const [form, setForm] = useState({
    name: "",
    restaurantName: "",
    restaurantState: "",
    rating: "",
    notes: "",
    side: "",
    drink: "",
  });
  const [submitting, setSubmitting] = useState(false);
  const [error, setError] = useState(null);

  function handleChange(e) {
    const { name, value } = e.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  }

  async function handleSubmit(e) {
    e.preventDefault();
    setSubmitting(true);
    setError(null);
    try {
      await createBurger({
        ...form,
        rating: form.rating ? parseFloat(form.rating) : null,
      });
      navigate("/");
    } catch (err) {
      setError(err.message);
      setSubmitting(false);
    }
  }

  return (
    <div>
      <h1>Log a Cheeseburger</h1>
      <form onSubmit={handleSubmit} className="burger-form">
        <div className="field">
          <label>
            Burger name
            <input
              name="name"
              value={form.name}
              onChange={handleChange}
              required
            />
          </label>
        </div>
        <div className="field">
          <label>
            Restaurant
            <input
              name="restaurantName"
              value={form.restaurantName}
              onChange={handleChange}
              required
            />
          </label>
        </div>
        <div className="field">
          <label>
            Restaurant's state (optional)
            <input
              name="restaurantState"
              value={form.restaurantState}
              onChange={handleChange}
              placeholder="e.g. Oklahoma"
            />
          </label>
        </div>
        <div className="field">
          <label>
            Side (optional)
            <input name="side" value={form.side} onChange={handleChange} />
          </label>
        </div>
        <div className="field">
          <label>
            Drink (optional)
            <input name="drink" value={form.drink} onChange={handleChange} />
          </label>
        </div>
        <div className="field">
          <label>
            Rating (0-10)
            <input
              name="rating"
              type="number"
              step="0.1"
              min="0"
              max="10"
              value={form.rating}
              onChange={handleChange}
            />
          </label>
        </div>
        <div className="field">
          <label>
            Notes
            <textarea name="notes" value={form.notes} onChange={handleChange} />
          </label>
        </div>
        {error && <p className="error-message">{error}</p>}
        <button type="submit" disabled={submitting} className="submit-button">
          {submitting ? "Saving..." : "Save burger"}
        </button>
      </form>
    </div>
  );
}