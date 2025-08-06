import React, { Component } from 'react';
import './ComplaintRegister.css';

class ComplaintRegister extends Component {

  state = {
    ename: '',
    complaint: '',
    NumberHolder: Math.floor(Math.random() * 100) + 1,
  };

  handleChange = (event) => {
    this.setState({
      [event.target.name]: event.target.value,
    });
  };

  handleSubmit = (event) => {
    const msg =
      'Thanks ' +
      this.state.ename +
      '\nYour Complaint was Submitted.\nTransaction ID is: ' +
      this.state.NumberHolder;

    alert(msg);
    event.preventDefault();
  };

  render() {
    return (
       <div style={{marginTop:"250px"}}>
        <div className="complaint-container">
           <h1>Register your complaints here!!!</h1>
        </div> 
        <div className='cont'>
        <form onSubmit={this.handleSubmit}>
          <div className="form-group">
            <label htmlFor="ename">Name:</label>
            <input
              type="text"
              id="ename"
              name="ename"
              value={this.state.ename}
              onChange={this.handleChange}
              required
            />
          </div>
          <div className="form-group">
            <label htmlFor="complaint">Complaint:</label>
            <textarea
              id="complaint"
              name="complaint"
              value={this.state.complaint}
              onChange={this.handleChange}
              required
            ></textarea>
          </div>
        

          <button className="submit-button" type="submit">Submit</button>
        </form>
        </div>
      </div>

    );
  }
}

export default ComplaintRegister;
