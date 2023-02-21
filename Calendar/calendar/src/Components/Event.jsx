import React from "react";
import "../Style/Event.css";

const Event = ({ id, height, top, left, width }) => {
  const style = {
    top: `${top}px`,
    left: `${left}%`,
    width: `${width}%`,
    height: `${height}px`,
    background: "blue",
    border: "0.5px solid black",
  };

  return (
    <div key={id} className="event" style={style}>
      Event {id}
    </div>
  );
};
export default Event;
