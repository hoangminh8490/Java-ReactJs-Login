import React, { useEffect, useState } from "react";

const HomePage = () => {
  const [list, setList] = useState("");
  const [enable01, setEnable01] = useState(false);

  const handleService01 = () => {
    const token = localStorage.getItem("token")
      ? localStorage.getItem("token")
      : "";
    fetch("http://localhost:8081/list", {
      headers: {
        'Authorization': 'Bearer ' + token,
      },
    })
      .then((response) => response.json())
      .then((res) => setList(res.value))
      .then(() => setEnable01(true))
      .catch((err) => console.log(err));
  };

  const handleService02 = () => {
    const token = localStorage.getItem("token")
      ? localStorage.getItem("token")
      : "";
    fetch("http://localhost:8082/list", {
      headers: {
        'Authorization': 'Bearer ' + token,
      },
    })
      .then((response) => response.json())
      .then((res) => setList(res.value))
      .then(() => setEnable01(true))
      .catch((err) => console.log(err));
  };

  const handleService03 = () => {
    const token = localStorage.getItem("token")
      ? localStorage.getItem("token")
      : "";
    fetch("http://localhost:8083/list", {
      headers: {
        'Authorization': 'Bearer ' + token,
      },
    })
      .then((response) => response.json())
      .then((res) => setList(res.value))
      .then(() => setEnable01(true))
      .catch((err) => console.log(err));
  };

  return (
    <div>
      <div>
        {enable01 === false ? (
          <div>
            <button className="link-btn" onClick={handleService01}>
              Service 1
            </button>
            <button className="link-btn" onClick={handleService02}>
              Service 2
            </button>
            <button className="link-btn" onClick={handleService03}>
              Service 3
            </button>
          </div>
        ) : (
          <div>
            <div>{list}</div>
            <button className="link-btn" onClick={() => setEnable01(false)}>
              Back to menu
            </button>
          </div>
        )}
      </div>
    </div>
  );
};

export default HomePage;
