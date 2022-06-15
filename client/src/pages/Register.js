import React from "react";
import axios from 'axios'

class Register extends React.Component {
    constructor() {
        super();
        this.state = {
            email: ""
        }
    };

  submit = async (event) => {
    event.preventDefault()
    try {
      const res = await axios.post('/verifyEmail', {
        email: this.state.email
      });
    } catch (e) {
      console.log(e)
    }
  };
  handleChange = (e) => {
    this.setState({email: e.target.value});
  };

  render() {
      return (
          <>
            <form onSubmit={this.submit}>
              Email <input type="text" name="email" value={this.state.email} onChange={this.handleChange}/><br/>
              <input type="submit"/>
            </form>
          </>
      );
  }
}

export default Register;
