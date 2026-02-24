import { createAsyncThunk, createSlice } from '@reduxjs/toolkit'
import api from '../services/api'

export const fetchPortfolioData = createAsyncThunk('portfolio/fetch', async () => {
  const [profile, skills, projects] = await Promise.all([
    api.get('/profile'),
    api.get('/skills'),
    api.get('/projects'),
  ])
  return { profile: profile.data, skills: skills.data, projects: projects.data }
})

export const sendContactMessage = createAsyncThunk('portfolio/contact', async (payload) => {
  const res = await api.post('/contact', payload)
  return res.data
})

const portfolioSlice = createSlice({
  name: 'portfolio',
  initialState: { profile: null, skills: [], projects: [], loading: false, error: null, contactStatus: 'idle' },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchPortfolioData.pending, (state) => { state.loading = true; state.error = null })
      .addCase(fetchPortfolioData.fulfilled, (state, action) => {
        state.loading = false
        state.profile = action.payload.profile
        state.skills = action.payload.skills
        state.projects = action.payload.projects
      })
      .addCase(fetchPortfolioData.rejected, (state, action) => { state.loading = false; state.error = action.error.message })
      .addCase(sendContactMessage.pending, (state) => { state.contactStatus = 'loading' })
      .addCase(sendContactMessage.fulfilled, (state) => { state.contactStatus = 'success' })
      .addCase(sendContactMessage.rejected, (state) => { state.contactStatus = 'error' })
  },
})

export default portfolioSlice.reducer
