import React from "react";
import axios from 'axios'

export default function VerifyEmail() {
      var email = "";
      const submit = async (event) => {
        event.preventDefault()
        try {
          const res = await axios.post('/verifyEmail', {
            email: event.target.email.value
          });
        } catch (e) {
          console.log(e)
        }
      };


      return (
          <>
            <form onSubmit={submit}>
              Email <input type="text" name="email"/><br/>
              <input type="submit"/>
            </form>
          </>
      );
}
