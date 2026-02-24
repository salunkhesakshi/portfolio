export default function ProjectsSection({ projects }) {
  return (
    <section id="projects" className="py-12">
      <h2 className="text-3xl font-semibold mb-6">Projects</h2>
      <div className="grid md:grid-cols-2 gap-6">
        {projects.map((p) => (
          <article key={p.id} className="bg-slate-900 p-5 rounded-lg border border-slate-700">
            <h3 className="text-xl font-semibold">{p.title}</h3>
            <p className="mt-2 text-slate-300">{p.description}</p>
            <p className="mt-2 text-sm text-cyan-300">{p.techStack}</p>
            <div className="flex gap-4 mt-4">
              <a className="underline" href={p.githubLink} target="_blank" rel="noreferrer">GitHub</a>
              <a className="underline" href={p.liveLink} target="_blank" rel="noreferrer">Live</a>
            </div>
          </article>
        ))}
      </div>
    </section>
  )
}
