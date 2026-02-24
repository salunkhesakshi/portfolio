import { FaGithub, FaLinkedin, FaEnvelope } from 'react-icons/fa'
import { SiHackerrank } from 'react-icons/si'

export default function Hero({ profile }) {
  if (!profile) return null
  const socials = [
    { icon: <FaGithub />, href: profile.githubUrl, label: 'GitHub' },
    { icon: <SiHackerrank />, href: profile.hackerrankUrl, label: 'HackerRank' },
    { icon: <FaLinkedin />, href: profile.linkedinUrl, label: 'LinkedIn' },
    { icon: <FaEnvelope />, href: 'mailto:hello@example.com', label: 'Email' },
  ]
  return (
    <section className="py-16">
      <h1 className="text-4xl font-bold">{profile.name}</h1>
      <p className="text-xl text-cyan-400 mt-2">{profile.title}</p>
      <p className="mt-4 max-w-2xl text-slate-300">{profile.summary}</p>
      <div className="flex gap-4 mt-6">
        {socials.map((s) => (
          <a key={s.label} href={s.href} target="_blank" rel="noreferrer" className="text-2xl hover:text-cyan-400">{s.icon}</a>
        ))}
      </div>
      <a href={profile.resumeUrl} target="_blank" rel="noreferrer" className="inline-block mt-6 px-5 py-2 bg-cyan-500 rounded-md text-black font-semibold">Download Resume</a>
    </section>
  )
}
