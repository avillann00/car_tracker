'use client'

import { Menu, X } from 'lucide-react'
import { useState } from 'react'

export default function HamburgerButton() {
  const [open, setOpen] = useState(false)

  return (
    <button onClick={() => setOpen(!open)} aria-label='Toggle menu'>
      {open ? <X size={28} /> : <Menu size={28} />}
    </button>
  )
}
