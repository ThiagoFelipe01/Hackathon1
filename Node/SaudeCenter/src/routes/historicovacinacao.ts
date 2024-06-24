import Router, {Request, Response} from "express"
import knex from "../database/knex"
import AppError from '../utils/AppError';


const routes = Router()

routes.post("/", (req: Request, res: Response) => {
    const objSalvar = req.body;

    knex('historico_vacinacao').insert(objSalvar)
    .then(() => {
        res.json({message: "historico_vacinacao Salvo"})
    })

})

routes.get("/", (req, res) => {
    knex('historico_vacinacao').then((resposta) => {
        res.json({historicovacinacao: resposta})
    })
})

routes.put("/:id", async (req: Request, res: Response) =>{
    const objSalvar = req.body;

    const {id} = req.params

    let historico = await knex('historico_vacinacao').where({id}).first()


    if(!historico){
        throw new AppError('Historico não encontrado', 404)
    }

    let newHistorico = {
        ...historico,
        ...objSalvar
    }

    await knex('historico_vacinacao').update(newHistorico)
    .where({id: historico.id})

    return res.json({
        message: 'Historico alterado com Sucesso'
    })

})

routes.delete('/:id', async (req, res) => {
    const { id } = req.params

    let historicovacinacao = await knex('historico_vacinacao').where({id}).first()

    if(!historicovacinacao){
        throw new AppError('historico vacinação não encontrado', 404)
    }

    await knex ('historico_vacinacao').where({id}).delete()

    return res.json({message: "historico vacinação deletado com sucesso"})
})

export default routes
