import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { getBurgers } from "../api/burgers";
import BurgerModal from "../components/BurgerModal";

export default function BurgerList() {
  const [burgers, setBurgers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // NEW: tracks which burger's popup is open. null means "none."
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
      <Link to="/add">+ Add a burger</Link>

      {burgers.length === 0 ? (
        <p>No burgers logged yet.</p>
      ) : (
        <ul>
          {burgers.map((burger) => (
            <li
              key={burger.id}
              onClick={() => setSelectedBurger(burger)}
              style={{ cursor: "pointer" }}
            >
              <strong>{burger.name}</strong> — {burger.rating}/10
            </li>
          ))}
        </ul>
      )}

      {/* Only render the modal AT ALL when something is selected.
          When selectedBurger is null, this whole expression is false,
          and React renders nothing for it. */}
      {selectedBurger && (
        <BurgerModal
          burger={selectedBurger}
          onClose={() => setSelectedBurger(null)}
        />
      )}
    </div>
  );
}