'use client'

import { useRouter } from 'next/navigation'

export default function Login(){
  const router = useRouter()

  return(
    <div className='bg-white w-screen h-screen flex flex-col items-center justify-center text-black'>
      <h1 className='text-xl'>Login</h1>
      <div className='flex flex-col'>
        <form>
          
        </form>
        <button className='hover:text-blue-500' onClick={() => router.push('/register')}>Don't have an account? Register.</button>
      </div>
    </div>
  )
}
