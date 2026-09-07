const API_BASE = "http://localhost:8080/api/burgers";

export async function getBurgers() {
  const response = await fetch(API_BASE);
  if (!response.ok) {
    throw new Error(`Failed to fetch burgers: ${response.status}`);
  }
  return response.json();
}

export async function createBurger(burger) {
  const response = await fetch(API_BASE, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(burger),
  });
  if (!response.ok) {
    throw new Error(`Failed to create burger: ${response.status}`);
  }
  return response.json();
}