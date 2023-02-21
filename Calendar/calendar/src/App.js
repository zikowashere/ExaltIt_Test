import logo from "./logo.svg";
import "./App.css";
import events from "../src/Model/input.json";

import Calendar from "./Pages/Calendar";

function App() {
  return (
    <div className="App">
      <Calendar events={events} />
    </div>
  );
}

export default App;
