import { BrowserRouter, Route, Routes } from 'react-router-dom'
import Main from './pages/Main'
import Login from './pages/Login'
import ArticleList from './pages/ArticleList'

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route index element={<Main />}></Route>
                <Route path='/auth/login' element={<Login  />}></Route>
                <Route path='/article/list' element={<ArticleList/>}></Route>
            </Routes>
        </BrowserRouter>
    )
}
export default App
