import React from "react";
import axios from 'axios'

export default function Login() {
      const submit = async (event) => {
        event.preventDefault();

         try {
          const res = await axios.post('/auth', {
            username: event.target.username.value,
            password: event.target.password.value
          });
        } catch (e) {
          console.log(e)
        }
    }

  var password = "";
  var username = "";
  return (
      <>
        <form onSubmit={submit}>
          Username <input type="text" name="username"/><br/>
          Password <input type="text" name="password"/><br/>

          <input type="submit"/>
        </form>
      </>
  );

}
