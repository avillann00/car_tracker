'use client'

import { useRouter } from 'next/navigation'
import { useState } from 'react'
import axios from 'axios'

export default function Register(){
  const router = useRouter()

  const [email, setEmail] = useState('')
  const [firstName, setFirstName] = useState('')
  const [lastName, setLastName] = useState('')
  const [password, setPassword] = useState('')
  const [confirmPassword, setConfirmPassword] = useState('')
  const [error, setError] = useState('')

  const handleRegister = async (e: React.FormEvent) => {
    e.preventDefault()
    setError('')

    try{
      if(email.length < 1){
        setError('Email is too short')
        return
      }

      if(firstName.length < 1){
        setError('First name is too short')
        return
      }

      if(lastName.length < 1){
        setError('Last name is too short')
        return
      }

      if(password.length < 1){
        setError('Password is too short')
        return
      }

      if(confirmPassword.length < 1){
        setError('Confirm password is too short')
        return
      }

      if(password !== confirmPassword){
        setError('Passwords do not match')
        return
      }

      const response = await axios.post(`${process.env.NEXT_PUBLIC_API_URL}/auth/register`, {
        email,
        firstName,
        lastName,
        password
      })

      if(response.status === 200 || response.status === 201){
        router.push('/login')
      }
    }
    catch(error){
      if(axios.isAxiosError(error)){
        setError(error.response?.data?.message ?? 'Registration failed')
      } 
      else{
        setError('Unexpected error')
      }   
    }
  }

  return(
    <div className='bg-white w-screen h-screen flex flex-col items-center justify-center text-black'>
      <h1 className='text-xl'>Register</h1>
      <div className='flex flex-col rounded-md p-4 gap-2 w-1/2 h-2/3'>
        <form className='bg-blue-200 rounded-lg p-4 shadow-md flex flex-col gap-2' onSubmit={handleRegister}>
          <label className='flex flex-col text-center items-center'>
            Email
            <input
              type='email'
              className='border border-2 rounded-md shadow-lg w-2/3'
              onChange={e => setEmail(e.target.value)}
              value={email}
            />
          </label>

          <label className='flex flex-col text-center items-center'>
            First Name
            <input
              type='text'
              className='border border-2 rounded-md shadow-lg w-2/3'
              onChange={e => setFirstName(e.target.value)}
              value={firstName}
            />
          </label>

          <label className='flex flex-col text-center items-center'>
            LastName
            <input
              type='text'
              className='border border-2 rounded-md shadow-lg w-2/3'
              onChange={e => setLastName(e.target.value)}
              value={lastName}
            />
          </label>

          <label className='flex flex-col text-center items-center'>
            Password
            <input
              type='password'
              className='border border-2 rounded-md shadow-lg w-2/3'
              onChange={e => setPassword(e.target.value)}
              value={password}
            />
          </label>

          <label className='flex flex-col text-center items-center'>
            Confirm Password
            <input
              type='password'
              className='border border-2 rounded-md shadow-lg w-2/3'
              onChange={e => setConfirmPassword(e.target.value)}
              value={confirmPassword}
            />
          </label>

          <button className='hover:text-blue-500 mt-5' type='submit'>Register</button>
          {error.length > 0 && (
            <span className='text-red-500 text-center'>
              {error}
            </span>
          )}
        </form>
        <button className='hover:text-blue-500' onClick={() => router.push('/login')}>Already have an account? Login.</button>
      </div>
    </div>
  )
}
