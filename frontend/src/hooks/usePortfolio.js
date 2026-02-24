import { useSelector } from 'react-redux'

export const usePortfolio = () => useSelector((s) => s.portfolio)
