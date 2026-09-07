import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { getBurgers } from "../api/burgers";

export default function BurgerList() {
  const [burgers, setBurgers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    getBurgers()
      .then(setBurgers)
      .catch((err) => setError(err.message))
      .finally(() => setLoading(false));
  }, []);

  if (loading) return <p>Loading burgers...</p>;
  if (error) return <p>Error: {error}</p>;

  return (
    <div>
      <h1>Cheeseburger Log</h1>
      <Link to="/add">+ Add a burger</Link>

      {burgers.length === 0 ? (
        <p>No burgers logged yet.</p>
      ) : (
        <ul>
          {burgers.map((burger) => (
            <li key={burger.id}>
              <strong>{burger.name}</strong> at {burger.restaurantName} —{" "}
              {burger.rating}/10
              {burger.notes && <p>{burger.notes}</p>}
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}