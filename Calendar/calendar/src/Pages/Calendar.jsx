import React, { useState } from "react";
import { useEffect } from "react";
import CalendarDraw from "../Components/CalendarDraw";

const Calendar = ({ events }) => {
  const [eventGroups, setEventGroups] = useState([]);
  const [windowHeight, setWindowHeight] = useState(window.innerHeight);
  const [windowHWidth, setWindowWidth] = useState(window.innerWidth);

  const constructGroupEvent = () => {
    // Sort the events by start time
    events.sort((a, b) => (a.start > b.start ? 1 : -1));

    // Group overlapping events together
    const eventGroups = [];
    let currentGroup = [];
    let lastEventEndTime = null;

    events.forEach((event) => {
      const eventStartTime = timeToMinutes(event.start);
      const eventEndTime = eventStartTime + event.duration;

      if (lastEventEndTime !== null && eventStartTime >= lastEventEndTime) {
        // The current event doesn't overlap with the previous group, so start a new group
        eventGroups.push(currentGroup);
        currentGroup = [];
      }

      currentGroup.push(event);
      lastEventEndTime = Math.max(lastEventEndTime, eventEndTime);
    });

    if (currentGroup.length > 0) {
      eventGroups.push(currentGroup);
    }

    setEventGroups(eventGroups);
  };

  const timeToMinutes = (time) => {
    const parts = time.split(":");
    return parseInt(parts[0], 10) * 60 + parseInt(parts[1], 10);
  };

  const resizeScreen = () => {
    const handleResize = () => {
      setWindowHeight(window.innerHeight);
      setWindowWidth(window.innerWidth);
    };

    window.addEventListener("resize", handleResize);
    return () => window.removeEventListener("resize", handleResize);
  };
  useEffect(() => {
    constructGroupEvent();
    resizeScreen();
  }, []);

  return <CalendarDraw eventGroups={eventGroups} />;
};

export default Calendar;
