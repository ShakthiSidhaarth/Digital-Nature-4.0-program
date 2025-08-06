
import { useState } from 'react';
import './App.css';
import Greeting from './components/Greeting';
import LoginButton from './components/LoginButton';
import LogoutButton from './components/LogoutButton';

function App() {
  const[isLoggedIn,setIsLoggedin]=useState(false);
  const handleLoginClick = () => {
    setIsLoggedin(true);
  };

  const handleLogoutClick = () => {
    setIsLoggedin(false);
  };
  return (
    <div className="App">
      <Greeting isLoggedIn={isLoggedIn}/>
      {isLoggedIn ?(
        <LogoutButton onClick={handleLogoutClick} />
      ):(
        <LoginButton onClick={handleLoginClick} />
      )}
    </div>
  );
}

export default App;
