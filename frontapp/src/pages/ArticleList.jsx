import { useEffect, useState } from 'react'

export default function ArticleList() {
    const [articleList, setArticleList] = useState([])

    useEffect(() => {
        fetch('http://localhost:8090/api/v1/articles')
            .then((res) => res.json)
            .then((res) => {
                setArticleList(res.data.articleList)
            })
    }, [])

    return (
        <>
            <ul>
                {articleList.map((article) => (
                    <li key={article.id}>
                        {article.id} / {article.subject} / {article.content} / {article.author}
                    </li>
                ))}
            </ul>
        </>
    )
}