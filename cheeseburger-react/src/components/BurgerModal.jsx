export default function BurgerModal({ burger, onClose }) {
  return (
    // The dark overlay that covers the whole screen behind the popup.
    // Clicking it closes the modal, which feels natural to users.
    <div
      onClick={onClose}
      style={{
        position: "fixed",
        top: 0,
        left: 0,
        right: 0,
        bottom: 0,
        backgroundColor: "rgba(0, 0, 0, 0.5)",
        display: "flex",
        alignItems: "center",
        justifyContent: "center",
      }}
    >
      {/* The actual popup box. stopPropagation is important here — without
          it, clicking INSIDE the box would bubble up to the overlay's
          onClick above and close the modal immediately, which we don't want. */}
      <div
        onClick={(e) => e.stopPropagation()}
        style={{
          backgroundColor: "white",
          padding: "24px",
          borderRadius: "8px",
          maxWidth: "400px",
          width: "90%",
        }}
      >
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
        <button onClick={onClose}>Close</button>
      </div>
    </div>
  );
}