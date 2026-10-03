import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { getBurgers } from "../api/burgers";
import BurgerModal from "../components/BurgerModal";
import "./BurgerList.css";

export default function BurgerList() {
  const [burgers, setBurgers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [selectedBurger, setSelectedBurger] = useState(null);

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
      <Link to="/add" className="add-link">
        + Add a burger
      </Link>

      {burgers.length === 0 ? (
        <p className="empty-state">No burgers logged yet.</p>
      ) : (
        <ul className="burger-list">
          {burgers.map((burger) => (
            <li key={burger.id} onClick={() => setSelectedBurger(burger)}>
              <span className="burger-name">{burger.name}</span>
              <span className="rating">{burger.rating}/10</span>
            </li>
          ))}
        </ul>
      )}

      {selectedBurger && (
        <BurgerModal
          burger={selectedBurger}
          onClose={() => setSelectedBurger(null)}
        />
      )}
    </div>
  );
}