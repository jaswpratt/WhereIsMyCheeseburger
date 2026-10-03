import { useEffect } from "react";
import "./BurgerModal.css";

export default function BurgerModal({ burger, onClose }) {
  useEffect(() => {
    function handleKeyDown(e) {
      if (e.key === "Escape") {
        onClose();
      }
    }

    document.addEventListener("keydown", handleKeyDown);

    return () => {
      document.removeEventListener("keydown", handleKeyDown);
    };
  }, [onClose]);

  return (
    <div className="modal-overlay">
      <div className="modal-box">
        <h2>{burger.name}</h2>
        <p>
          <strong>Restaurant:</strong> {burger.restaurantName}
          {burger.restaurantState && ` (${burger.restaurantState})`}
        </p>
        <p>
          <strong>Rating:</strong> {burger.rating}/10
        </p>
        {burger.side && (
          <p>
            <strong>Side:</strong> {burger.side}
          </p>
        )}
        {burger.drink && (
          <p>
            <strong>Drink:</strong> {burger.drink}
          </p>
        )}
        {burger.notes && (
          <p>
            <strong>Notes:</strong> {burger.notes}
          </p>
        )}
        <button className="modal-close-button" onClick={onClose}>
          Close
        </button>
      </div>
    </div>
  );
}