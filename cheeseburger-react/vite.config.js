import react from '@vitejs/plugin-react'
import { defineConfig } from 'vite'

// https://vite.dev/config/
export default defineConfig({
  base: '/cheeseburger-api/',
  plugins: [react()],
  server: {
    proxy: {
      '/cheeseburger-api/api': 'http://localhost:8080'
    }
  }
})
