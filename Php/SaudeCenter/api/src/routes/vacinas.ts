import Router, {Request, Response} from "express"
import knex from "../database/knex"
import AppError from '../utils/AppError';


const routes = Router()

routes.post("/", (req: Request, res: Response) => {
    const objSalvar = req.body;

    knex('vacinas').insert(objSalvar)
    .then(() => {
        res.json({message: "vacinas Salvo"})
    })

})

routes.get("/", (req, res) => {
    knex('vacinas').then((resposta) => {
        res.json({vacinas: resposta})
    })
})

routes.put("/:id", async (req: Request, res: Response) =>{
    const objSalvar = req.body;

    const {id} = req.params

    let vacina = await knex('vacinas').where({id}).first()


    if(!vacina){
        throw new AppError('Vacina não encontrado', 404)
    }

    let newVacina = {
        ...vacina,
        ...objSalvar
    }

    await knex('vacinas').update(newVacina)
    .where({id: vacina.id})

    return res.json({
        message: 'Vacina alterado com Sucesso'
    })

})

routes.delete('/:id', async (req, res) => {
    const { id } = req.params

    let vacina = await knex('vacinas').where({id}).first()

    if(!vacina){
        throw new AppError('Vacina não encontrada', 404)
    }

    await knex ('vacinas').where({id}).delete()

    return res.json({message: "Vacina deletado com sucesso"})
})
export default routes
