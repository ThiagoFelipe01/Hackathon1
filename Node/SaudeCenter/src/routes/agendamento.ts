import Router, {Request, Response} from "express"
import knex from "../database/knex"
import AppError from '../utils/AppError';


const routes = Router()

routes.post("/", (req: Request, res: Response) => {
    const objSalvar = req.body;

    knex('agendamentos').insert(objSalvar)
    .then(() => {
        res.json({message: "Agendamentos Salvo"})
    })

})

routes.get("/", (req, res) => {

    knex('agendamentos').then((resposta) => {
        res.json({agendamentos: resposta})
    })
})

routes.put("/:id", async (req: Request, res: Response) =>{
    const objSalvar = req.body;

    const {id} = req.params

    let agendamento = await knex('agendamentos').where({id}).first()


    if(!agendamento){
        throw new AppError('Agendamento não encontrado', 404)
    }

    let newAgendamento = {
        ...agendamento,
        ...objSalvar
    }

    await knex('agendamentos').update(newAgendamento)
    .where({id: agendamento.id})

    return res.json({
        message: 'Agendamento alterado com Sucesso'
    })

})

routes.delete('/:id', async (req, res) => {
    const { id } = req.params

    let agendamento = await knex('agendamentos').where({id}).first()

    if(!agendamento){
        throw new AppError('agendamento não encontrado', 404)
    }

    await knex ('agendamentos').where({id}).delete()

    return res.json({message: "agendamento deletado com sucesso"})
})

export default routes
