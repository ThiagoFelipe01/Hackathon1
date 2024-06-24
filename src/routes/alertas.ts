import Router, {Request, Response} from "express"
import knex from "../database/knex"
import AppError from '../utils/AppError';


const routes = Router()

routes.post("/", (req: Request, res: Response) => {
    const objSalvar = req.body;

    knex('alertas').insert(objSalvar)
    .then(() => {
        res.json({message: "Alerta Salvo"})
    })

})

routes.get("/", (req, res) => {
    knex('alertas').then((resposta) => {
        res.json({alertas: resposta})
    })
})

routes.put("/:id", async (req: Request, res: Response) =>{
    const objSalvar = req.body;

    const {id} = req.params

    let alerta = await knex('alertas').where({id}).first()


    if(!alerta){
        throw new AppError('alerta não encontrado', 404)
    }

    let newAlerta = {
        ...alerta,
        ...objSalvar
    }

    await knex('alertas').update(newAlerta)
    .where({id: alerta.id})

    return res.json({
        message: 'Alerta alterado com Sucesso'
    })

})

routes.delete('/:id', async (req, res) => {
    const { id } = req.params

    let alerta = await knex('alertas').where({id}).first()

    if(!alerta){
        throw new AppError('Alerta não encontrado', 404)
    }

    await knex ('alertas').where({id}).delete()

    return res.json({message: "Alerta deletado com sucesso"})
})

export default routes
