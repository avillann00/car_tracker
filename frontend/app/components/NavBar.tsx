'use client'

import HamburgerButton from './HamburgerButton'
import { useRouter } from 'next/navigation'

export default function NavBar(){
  const router = useRouter()

  return(
    <div className='bg-gray-200 flex flex-row text-black  items-center justify-between p-4 fixed top-0 left-0 w-full z-50'>
      <HamburgerButton />

      <button className='hover:text-blue-500' onClick={() => router.push('/')}>
        Car Tracker
      </button>

      <button className='hover:text-blue-500' onClick={() => router.push('/login')}>
        Login
      </button>
    </div>
  )
}
