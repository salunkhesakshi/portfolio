import { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import Hero from '../components/Hero'
import SkillsSection from '../components/SkillsSection'
import ProjectsSection from '../components/ProjectsSection'
import ReachOutForm from '../components/ReachOutForm'
import { fetchPortfolioData, sendContactMessage } from '../features/portfolioSlice'

export default function HomePage() {
  const dispatch = useDispatch()
  const { profile, skills, projects, loading, error, contactStatus } = useSelector((s) => s.portfolio)

  useEffect(() => { dispatch(fetchPortfolioData()) }, [dispatch])

  if (loading) return <div className="p-8">Loading...</div>
  if (error) return <div className="p-8 text-red-400">{error}</div>

  return (
    <main className="max-w-6xl mx-auto px-4 sm:px-6 lg:px-8">
      <Hero profile={profile} />
      <SkillsSection skills={skills} />
      <ProjectsSection projects={projects} />
      <ReachOutForm status={contactStatus} onSubmit={(payload) => dispatch(sendContactMessage(payload))} />
    </main>
  )
}
