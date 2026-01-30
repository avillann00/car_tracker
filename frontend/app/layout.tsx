import type { Metadata } from "next";
import { Rubik } from 'next/font/google'
import "./globals.css";
import Footer from './components/Footer.tsx'
import NavBar from './components/NavBar.tsx'

const rubik = Rubik({
  subsets: ['latin'],
  weight: ['300', '400', '500', '600', '700'],
  display: 'swap',
})

export const metadata: Metadata = {
  title: "Car Maintenance Tracker",
  description: "Track your cars maintenace"
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body
        className={rubik.className}
      >
        <NavBar />
        <main className='pt-10 pb-10 min-h-screen'>
          {children}
        </main>
        <Footer />
      </body>
    </html>
  );
}
