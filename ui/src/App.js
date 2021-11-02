import React, {useState, useEffect} from "react";
import {
  BrowserRouter as Router,
  Switch,
  Route
} from "react-router-dom";
import userContext from "./context/userContext";
import './App.css';
import Header from "./components/Header";
import Home from "./components/Home";
import Login from "./components/Login";
import SignUp from "./components/SignUp";
import User from "./components/User";
import Leaderboard from "./components/Leaderboard";
import About from "./components/About";
import Admin from "./components/Admin";
import FourZeroFour from "./components/FourZeroFour";

export default function App() {
  const [user, setUser] = useState(null);

  const login = user => {
    setUser(user);
    localStorage.setItem("pet-parade-user-info", JSON.stringify(user));
  }

  const logout = () => {
    setUser(null);
    localStorage.removeItem("pet-parade-user-info");
  }

  useEffect(() => {
    if (window.performance) {
      if (performance.getEntriesByType("navigation")[0].type.localeCompare("reload") === 0) {
        let parsedData = JSON.parse(localStorage.getItem("pet-parade-user-info"));

        if(parsedData !== null) {
          setUser(parsedData);
        }
      }
    }
  }, []);

  return (
    <userContext.Provider value={{user: user, login: login, logout: logout}}>
      <Router>
        <div className="App">
          <Header user={user} logout={logout} />
          <Switch>
            <Route path="/about">
              <About />
            </Route>

            {
              user !== null && 
              <Route path="/user">
                <User user={user} login={login} />
              </Route>
            }

            {
              user !== null && user.roles.includes("ROLE_ADMIN") &&
                <Route path="/admin">
                  <Admin user={user} />
                </Route>
            }

            <Route path="/leaderboard">
              <Leaderboard user={user} />
            </Route>

            <Route path="/login">
              <Login login={login} />
            </Route>

            <Route path="/signup">
              <SignUp login={login} />
            </Route>

            <Route exact path="/">
              <Home user={user} />
            </Route>

            <Route path="*">
              <FourZeroFour />
            </Route>
          </Switch>
        </div>
      </Router>
    </userContext.Provider>
  );
}
