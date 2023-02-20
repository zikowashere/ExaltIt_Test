import React, { useState, useEffect } from "react";
import Square from "../Components/Event";
import events from "../Model/input.json";
import "../Style/square.css";

function Calendar() {
  const [eventGroups, setEventGroups] = useState([]);

  const timeToMinutes = (time) => {
    const parts = time.split(":");
    return parseInt(parts[0], 10) * 60 + parseInt(parts[1], 10);
  };

  const constructGroupEvent = () => {
    // Sort the events by start time
    events.sort((a, b) => (a.start > b.start ? 1 : -1));

    // Group overlapping events together
    const eventGroups = [];
    let currentGroup = [];
    let lastEventEndTime = null;

    for (let i = 0; i < events.length; i++) {
      const event = events[i];
      const eventStartTime = timeToMinutes(event.start);
      const eventEndTime = eventStartTime + event.duration;

      if (lastEventEndTime !== null && eventStartTime >= lastEventEndTime) {
        // The current event doesn't overlap with the previous group, so start a new group
        eventGroups.push(currentGroup);
        currentGroup = [];
      }

      currentGroup.push(event);
      lastEventEndTime = Math.max(lastEventEndTime, eventEndTime);
    }

    if (currentGroup.length > 0) {
      eventGroups.push(currentGroup);
    }

    setEventGroups(eventGroups);
  };

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
    const width = Math.floor(100 / nbOfEvent);
    const left = width * index;

    return { top, height, width, left };
  };

  useEffect(() => {
    constructGroupEvent();
  }, [events]);

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
              <Square
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
}

export default Calendar;
