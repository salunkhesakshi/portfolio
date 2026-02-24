export default function SkillsSection({ skills }) {
  const grouped = skills.reduce((acc, skill) => {
    acc[skill.category] = acc[skill.category] || []
    acc[skill.category].push(skill.name)
    return acc
  }, {})

  return (
    <section id="skills" className="py-12">
      <h2 className="text-3xl font-semibold mb-6">Skills</h2>
      <div className="grid md:grid-cols-2 gap-4">
        {Object.entries(grouped).map(([category, items]) => (
          <div key={category} className="bg-slate-900 p-4 rounded-lg border border-slate-700">
            <h3 className="text-lg font-semibold text-cyan-300">{category}</h3>
            <div className="mt-3 flex flex-wrap gap-2">
              {items.map((item) => <span key={item} className="px-3 py-1 rounded-full bg-slate-800">{item}</span>)}
            </div>
          </div>
        ))}
      </div>
    </section>
  )
}
