import React, { useState, useEffect } from "react";
import Event from "./Event";
import "../Style/Event.css";

const CalendarDraw = ({ eventGroups }) => {
  useEffect(() => {}, []);

  const calculateTop = (start) => {
    const parts = start.split(":");
    const hours = parseInt(parts[0], 10);
    const minutes = parseInt(parts[1], 10);
    const minutesSince9am = (hours - 9) * 60 + minutes;
    return minutesSince9am;
  };
  // Calculate the position and size of an event based on its start time and duration
  const calculateEventStyle = (id, start, duration, index, nbOfEvent) => {
    const pixelsPerMinute = window.innerHeight / 720;
    const top = Math.floor(calculateTop(start) * pixelsPerMinute);
    const height = Math.floor(duration * pixelsPerMinute);
    const width = 100 / nbOfEvent;
    const left = width * index;

    return { top, height, width, left };
  };

  return (
    <div className="calendar">
      {eventGroups.map((eventGroup, i) => (
        <div key={i} className="event-group">
          {eventGroup.map((event, index) => {
            const style = calculateEventStyle(
              event.id,
              event.start,
              event.duration,
              index,
              eventGroup.length
            );

            return (
              <Event
                id={event.id}
                height={style.height}
                top={style.top}
                left={style.left}
                width={style.width}
              />
            );
          })}
        </div>
      ))}
    </div>
  );
};

export default CalendarDraw;
