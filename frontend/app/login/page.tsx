'use client'

import { useRouter } from 'next/navigation'
import { useState } from 'react'
import axios from 'axios'

export default function Login(){
  const router = useRouter()

  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [error, setError] = useState('')

  const handleLogin = async (e: React.FormEvent) => {
    e.preventDefault()
    setError('')

    try{
      if(email.length < 1){
        setError('Email is too short')
        return
      }
      if(password.length < 1){
        setError('Password is too short')
        return
      }

      const response = await axios.post(`${process.env.NEXT_PUBLIC_API_URL}/auth/login`, {
        email,
        password
      }) 

      if(response.status === 200 || response.status === 201){
        router.push('/dashboard')
      }
    }
    catch(error){
      if(axios.isAxiosError(error)){
        setError(error.response?.data?.message ?? 'Login failed')
      } 
      else{
        setError('Unexpected error')
      }   
    }
  }

  return(
    <div className='bg-white w-screen h-screen flex flex-col items-center justify-center text-black'>
      <h1 className='text-xl'>Login</h1>
      <div className='flex flex-col rounded-md p-4 gap-2 w-1/2 h-2/3' onSubmit={handleLogin}>
        <form className='bg-blue-200 rounded-lg p-4 shadow-md flex flex-col gap-2'>
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
            Password
            <input
              type='password'
              className='border border-2 rounded-md shadow-lg w-2/3'
              onChange={e => setPassword(e.target.value)}
              value={password}
            />
          </label>
          <button className='hover:text-blue-500 mt-5' type='submit'>Login</button>
          {error.length > 0 && (
            <span className='text-red-500 text-center'>
              {error}
            </span>
          )}
        </form>
        <button className='hover:text-blue-500' onClick={() => router.push('/register')}>Don't have an account? Register.</button>
      </div>
    </div>
  )
}
