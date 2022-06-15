import React from "react";
import axios from 'axios'
import { createSearchParams, useSearchParams } from "react-router-dom";

export default function Register() {
      const submit = async (event) => {
        event.preventDefault();

         try {
          const res = await axios.post('/register', {
            email: event.target.email.value,
            token: event.target.token.value,
            username: event.target.username.value,
            password: event.target.password.value
          });
        } catch (e) {
          console.log(e)
        }
    }

  const [searchParams, setSearchParams] = useSearchParams();
  const token = searchParams.get("token");
  const email = searchParams.get("email");
  var password = "";
  var username = "";
  return (
      <>
        <form onSubmit={submit}>
          Email <input disabled type="text" name="email" value={email}/><br/>
          Token <input disabled type="text" name="token" value={token}/><br/>
          Username <input type="text" name="username"/><br/>
          Password <input type="text" name="password"/><br/>

          <input type="submit"/>
        </form>
      </>
  );

}
