import express, {Request, Response} from 'express'
import "express-async-errors"
import cors from "cors"


const app = express()
app.use(cors())

app.use(express.json())

const PORT = 8000

app.listen(PORT, () => {
    console.log('Express iniciou na porta: '+
        PORT
    )
})
