import { Routes, Route } from "react-router-dom";
import BurgerList from "./pages/BurgerList";
import AddBurger from "./pages/AddBurger";
import "./App.css";

function App() {
  return (
    <Routes>
      <Route path="/" element={<BurgerList />} />
      <Route path="/add" element={<AddBurger />} />
    </Routes>
  );
}

export default App;