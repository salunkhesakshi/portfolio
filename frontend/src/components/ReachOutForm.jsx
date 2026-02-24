import { useState } from 'react'

export default function ReachOutForm({ onSubmit, status }) {
  const [form, setForm] = useState({ name: '', email: '', message: '' })

  const handleSubmit = async (e) => {
    e.preventDefault()
    await onSubmit(form)
    if (status !== 'error') setForm({ name: '', email: '', message: '' })
  }

  return (
    <section id="contact" className="py-12">
      <h2 className="text-3xl font-semibold mb-6">Reach Out</h2>
      <form className="space-y-4 max-w-xl" onSubmit={handleSubmit}>
        <input className="w-full p-3 rounded bg-slate-900 border border-slate-700" placeholder="Name" value={form.name} onChange={(e)=>setForm({ ...form, name:e.target.value })} required />
        <input className="w-full p-3 rounded bg-slate-900 border border-slate-700" type="email" placeholder="Email" value={form.email} onChange={(e)=>setForm({ ...form, email:e.target.value })} required />
        <textarea className="w-full p-3 rounded bg-slate-900 border border-slate-700" rows="5" placeholder="Message" value={form.message} onChange={(e)=>setForm({ ...form, message:e.target.value })} required />
        <button className="px-4 py-2 bg-cyan-500 text-black rounded font-semibold" disabled={status==='loading'}>Send</button>
        {status === 'success' && <p className="text-green-400">Message sent successfully.</p>}
        {status === 'error' && <p className="text-red-400">Could not send message.</p>}
      </form>
    </section>
  )
}
